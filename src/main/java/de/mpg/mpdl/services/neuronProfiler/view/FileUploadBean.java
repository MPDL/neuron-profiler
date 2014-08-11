package de.mpg.mpdl.services.neuronProfiler.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.mpg.mpdl.services.neuronProfiler.util.PropertyReader;

@ManagedBean
@RequestScoped
public class FileUploadBean {
	private List<File> uploadedFiles = new ArrayList<File>();
	private static Logger logger = Logger.getLogger(FileUploadBean.class);
	private String outputHTML = "";

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile f = event.getFile();
		String fName = f.getFileName();
		InputStream in = null;
		File uf = null;
		OutputStream out = null;
		String tmpDir = "";
		try {
			tmpDir = PropertyReader.getProperty("upload.tmpDir");
			File theDir = new File(tmpDir);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}
			in = f.getInputstream();
			uf = new File(tmpDir, fName);
			out = new FileOutputStream(uf);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} catch (URISyntaxException e) {
			logger.error("Cannot find tmpDir", e);
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		uploadedFiles.add(uf);
		try {
			outputHTML = postFile("http://vm15.mpdl.mpg.de:8080/swc/api/view", uf);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<File> getUploadedFiles() {
		return uploadedFiles;
	}

	public String getOutputHTML() {
		File f = new File("C:\\Users\\yu\\Desktop\\html.html");
		try {
			IOUtils.copy(new ByteArrayInputStream(outputHTML.getBytes()),new FileOutputStream(f));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(outputHTML);
		return outputHTML;
	}

	public void setOutputHTML(String outputHTML) {
		this.outputHTML = outputHTML;
	}

	public void setUploadedFiles(List<File> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public String outputString() {
		String targetURL = "http://vm15.mpdl.mpg.de:8080/swc/api/view";
		if (uploadedFiles.size() > 0)
			try {
				return postFile(targetURL, uploadedFiles.get(0));
			} catch (HttpException e) {

				e.printStackTrace();
				return "";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		else
			return "";
	}

	public String postFile(String targetURL, File f) throws HttpException,IOException {
		String resp = null;	
		PostMethod filePost = new PostMethod(targetURL);
		Part[] parts = { new FilePart(f.getName(), f) };
		filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(filePost);
		resp = filePost.getResponseBodyAsString();
		filePost.releaseConnection();
		return resp;

	}


}