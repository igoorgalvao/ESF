package br.com.esf.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLConnection;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Este Servlet carrega uma imagem armazenada no hd
 */
public class LoadImageHDServlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get file name from reques
		// Capturando o caminho do arquivo atraves do request
		String imageFileName = request.getParameter("file");

		if (imageFileName != null) {
			imageFileName = imageFileName.replaceAll("\\.+(\\\\|/)", "");

			// Decode the file name and prepare file object.
			imageFileName = URLDecoder.decode(imageFileName, "UTF-16");

			// Get content type by filename.
			String contentType = URLConnection.guessContentTypeFromName(imageFileName);

			File file = new File(imageFileName);

			// Preparando os streams
			BufferedInputStream input = null;
			BufferedOutputStream output = null;

			try {
				// Abrindo a imagem
				input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);

				// Init servlet response.
				response.reset();
				response.setBufferSize(DEFAULT_BUFFER_SIZE);
				response.setContentType(contentType);
				response.setHeader("Content-disposition", "inline; filename=\"" + imageFileName + "\"");
				output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

				// Write file contents to response.
				byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
				int length;
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}

				// Finalizando
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// Gently close streams.
				close(output);
				close(input);
			}
		}
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Short description";
	}
}
