package de.mpg.mpdl.service.demo.neuronProfiler.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import de.mpg.mpdl.service.demo.neuronProfiler.beans.SessionBean;

@WebServlet("/screenshotImage/*")
public class ImageServlet extends HttpServlet{
	
	public static Logger logger = Logger.getLogger(ImageServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getParameter("path");
        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(path);

        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = getServletContext().getMimeType(image.getName());

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
    }
	
	
}
