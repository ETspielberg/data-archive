package unidue.ub.dataArchive.userauth;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;
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
@WebServlet(value = "/fachref/userDefine", name = "UserDefine")
public class UserDefineServlet extends MCRServlet {

	private static final Logger LOGGER = Logger.getLogger(UserDefineServlet.class);

	private final static String tmpDir;

	static {
		MCRConfiguration config = MCRConfiguration.instance();
		tmpDir = config.getString("ub.dataArchive.tmpDir");
	}

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
		MCRJDOMContent xml = new MCRJDOMContent(xmlJDOM);
		File outputFile = new File(tmpDir + "/", "metdata.xml");
		if (!outputFile.exists())
			outputFile.createNewFile();
		xml.sendTo(outputFile);
		LOGGER.info("written matadata to xml file");
		job.getResponse().sendRedirect(MCRFrontendUtil.getBaseURL() + "dataDir/questionaire");
	}
}
