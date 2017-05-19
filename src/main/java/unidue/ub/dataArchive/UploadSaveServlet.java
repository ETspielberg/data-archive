package unidue.ub.dataArchive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.mycore.common.MCRSessionMgr;
import org.mycore.common.config.MCRConfiguration;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;


@WebServlet(value = "/dataArchive/uploadSave", name = "UploadSave")
@MultipartConfig
public class UploadSaveServlet extends MCRServlet {
        
        private final static String tmpDir;
        
        //private final static byte[] privateKey;

        static {
            MCRConfiguration config = MCRConfiguration.instance();
            tmpDir = config.getString("ub.dataArchive.tmpDir");
            //privateKey = config.getString("ub.dataArchive.tmpDir").getBytes();
        }
        
        private static final Logger LOGGER = Logger.getLogger(UploadSaveServlet.class);

        private static final long serialVersionUID = 1;

        public void doPost(MCRServletJob job) throws Exception {
        	HttpServletRequest req = job.getRequest();
        	          
            Collection<Part> parts = req.getParts();
            String tmpDirectory = String.valueOf(System.currentTimeMillis());
            File uploadDir = new File(tmpDir + "/" + tmpDirectory); 
            if (!uploadDir.exists()) uploadDir.mkdirs();
            byte[] buffer = new byte[8 * 1024];
            for (Part part : parts) {
            File uploadedFile = new File(uploadDir,getFileName(part));
            

            InputStream input = part.getInputStream();
            try {
              OutputStream output = new FileOutputStream(uploadedFile);
              try {
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                  output.write(buffer, 0, bytesRead);
                }
              } finally {
                output.close();
              }
            } finally {
              input.close();
            }
            LOGGER.info("saved file " + uploadedFile);
            // String adminHandle = "handle";
            // int index = 13;
            // HSAdapterFactory handleFactory = new HSAdapterFactory(adminHandle);
            // HASadapter connection = handleFactory.instance()
            // HandleValue handle = handleFactory.instance().createHandleValue();
            // String handleValue = handle.toString();
            String handleValue = String.valueOf(uploadedFile.hashCode());
            LOGGER.info("handle: " + handleValue);
            //MCRSessionMgr.getCurrentSession().put("handle", handle);
            MCRSessionMgr.getCurrentSession().put("handleValue", handleValue);
            MCRSessionMgr.getCurrentSession().put("tmpDirectory", tmpDirectory);
            }
        }
        
        private String getFileName(Part part) {
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                            content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }
    }
