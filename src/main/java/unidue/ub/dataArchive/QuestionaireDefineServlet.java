package unidue.ub.dataArchive;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.mycore.common.MCRSessionMgr;
import org.mycore.common.config.MCRConfiguration;
import org.mycore.common.content.MCRJDOMContent;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;
import org.xml.sax.SAXException;

import gov.loc.repository.bagit.creator.BagCreator;
import gov.loc.repository.bagit.domain.Bag;
import gov.loc.repository.bagit.domain.SupportedAlgorithms;
import gov.loc.repository.bagit.writer.BagWriter;

/**
 * Saves the user properties file to disk.
 * 
 * @author Eike Spielberg
 * @version 1
 */
@WebServlet(value = "/dataArchive/questionaireSave", name = "QuestionaireDefine")
public class QuestionaireDefineServlet extends MCRServlet {

	private final static String tmpDir;
	
	private final static String NSDatacite = "http://datacite.org/schema/kernel-4";

	static {
		MCRConfiguration config = MCRConfiguration.instance();
		tmpDir = config.getString("ub.dataArchive.tmpDir");
	}
	
	private static final Logger LOGGER = Logger.getLogger(QuestionaireDefineServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * Takes a XEditor submission and writes the corresponding user properties
	 * file as xml to disk
	 * 
	 * 
	 * @param job
	 *            <code>MCRServletJob</code>
	 * @throws SAXException 
	 * @throws TransformerException 
	 * @throws NoSuchAlgorithmException 
	 */
	protected void doGetPost(MCRServletJob job) throws ServletException, IOException, TransformerException, SAXException, NoSuchAlgorithmException {
		Element output = new Element("confirmation");
		
		Element xmlJDOM = ((org.jdom2.Document) job.getRequest().getAttribute("MCRXEditorSubmission")).detachRootElement().clone();

		String handle = (String) MCRSessionMgr.getCurrentSession().get("handleValue");
		String tmpDirectory = (String) MCRSessionMgr.getCurrentSession().get("tmpDirectory");
		LOGGER.info("handle form session : " + handle);
		xmlJDOM.addContent(new Element("identifier",NSDatacite).setText(handle));
		output.addContent(new Element("message").setText("fileUpoadInProgress"));

		MCRJDOMContent xml = new MCRJDOMContent(xmlJDOM);
				
		File outputFile = new File(tmpDir + "/" + tmpDirectory, "technical.xml");
		if (!outputFile.exists())
			outputFile.createNewFile();
		xml.sendTo(outputFile);
		getLayoutService().doLayout(job.getRequest(), job.getResponse(), new MCRJDOMContent(output));
		File pathToDirectory = new File(tmpDir + "/" + tmpDirectory);
		SupportedAlgorithms algorithm = SupportedAlgorithms.MD5;
		boolean includeHiddenFiles = false;
		Bag bag = BagCreator.bagInPlace(pathToDirectory, algorithm, includeHiddenFiles);
		BagWriter.write(bag, new File(tmpDir));
		ZipUtils appZip = new ZipUtils(pathToDirectory,new File(tmpDir + "/" + tmpDirectory + ".zip"));
		appZip.zip();
		
		
	}
}
