package unidue.ub.dataArchive;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.mycore.common.MCRSessionMgr;
import org.mycore.common.config.MCRConfiguration;
import org.mycore.common.content.MCRJDOMContent;
import org.mycore.frontend.MCRFrontendUtil;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;

/**
 * Saves the user properties file to disk.
 * 
 * @author Eike Spielberg
 * @version 1
 */
@WebServlet(value = "/dataArchive/xmlDefine", name = "XMLDefine")
public class XMLDefineServlet extends MCRServlet {

	private final static String tmpDir;
	
	private final static String NSDatacite = "http://datacite.org/schema/kernel-4";

	static {
		MCRConfiguration config = MCRConfiguration.instance();
		tmpDir = config.getString("ub.dataArchive.tmpDir");
	}
	
	private static final Logger LOGGER = Logger.getLogger(XMLDefineServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * Takes a XEditor submission and writes the corresponding user properties
	 * file as xml to disk
	 * 
	 * 
	 * @param job
	 *            <code>MCRServletJob</code>
	 */
	protected void doGetPost(MCRServletJob job) throws ServletException, IOException {
		org.jdom2.Document xmlJDOM = (org.jdom2.Document) job.getRequest().getAttribute("MCRXEditorSubmission");

		String handle = (String) MCRSessionMgr.getCurrentSession().get("handleValue");
		String tmpDirectory = (String) MCRSessionMgr.getCurrentSession().get("tmpDirectory");
		
		LOGGER.info("handle form session : " + handle);
		xmlJDOM.getRootElement().addContent(new Element("identifier",NSDatacite).setText(handle));

		MCRJDOMContent xml = new MCRJDOMContent(xmlJDOM);
		File outputFile = new File(tmpDir + "/" + tmpDirectory, "metadata.xml");
		if (!outputFile.exists())
			outputFile.createNewFile();
		xml.sendTo(outputFile);
		job.getResponse().sendRedirect(MCRFrontendUtil.getBaseURL() + "dataArchive/technical.xed");

	}

}
