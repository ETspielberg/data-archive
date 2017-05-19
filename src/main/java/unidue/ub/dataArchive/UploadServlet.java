package unidue.ub.dataArchive;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import org.jdom2.Element;
import org.mycore.common.content.MCRJDOMContent;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;


@WebServlet("/dataArchive/upload")
@MultipartConfig
public class UploadServlet extends MCRServlet {
        
        private static final long serialVersionUID = 1;

        public void doGetPost(MCRServletJob job) throws Exception {
        	Element output = new Element("upload");
        	getLayoutService().doLayout(job.getRequest(), job.getResponse(), new MCRJDOMContent(output));
        }
    }
