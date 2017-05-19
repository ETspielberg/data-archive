package unidue.ub.dataArchive;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.mycore.common.config.MCRConfiguration;
import org.mycore.common.content.MCRJDOMContent;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;
import org.xml.sax.SAXException;

/**
 * Allows a personalized start page.
 * 
 * @author Eike Spielberg
 * @version 1
 */
@WebServlet("/dataArchive/start")
public class StartServlet extends MCRServlet {

	    private static final long serialVersionUID = 1L;

    private final static String userDir;

    private static final Logger LOGGER = Logger.getLogger(StartServlet.class);

    static {
        MCRConfiguration config = MCRConfiguration.instance();
        userDir = config.getString("ub.dataArchive.userDir");
    }

    /**
     * reads the user credentials and prepares a simple xml file that allows a personlized welcome page.
     * @param job
     *            <code>MCRServletJob</code>
     * @exception IOException exception while reading systematik.xml file from disk
     * @exception JDOMException exception upon parsing the systematik.xml file
     * @exception TransformerException exception while rendering output
     * @exception SAXException exception while rendering output
     */
    public void doGetPost(MCRServletJob job) throws ServletException, IOException, JDOMException, TransformerException, SAXException {
        Element output = new Element("start");

        String who = job.getRequest().getUserPrincipal().getName();
        File userFile = new File(userDir + "/" + who, "user_data.xml");
        if (userFile.exists()) {
            SAXBuilder builder = new SAXBuilder();
            org.jdom2.Document document;
            document = (org.jdom2.Document) builder.build(userFile);

            Element rootNode = document.getRootElement();
            String username = rootNode.getChild("details").getChild("fullname").getValue();
            org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
    		if (currentUser.hasRole("userAdmin"))
    			output.setAttribute("userAdmin","");
            output.setAttribute("loggedInAs", username);
            output.setAttribute("email", who);
            LOGGER.info("prepared start page for " + username);
         }
        getLayoutService().doLayout(job.getRequest(), job.getResponse(), new MCRJDOMContent(output));
     }
}
