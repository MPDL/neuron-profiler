package de.mpg.mpdl.services.neuronProfiler.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.mpg.mpdl.services.neuronProfiler.beans.SessionBean;
import de.mpg.mpdl.services.neuronProfiler.util.PropertyReader;

@ManagedBean
@ViewScoped
public class FileUploadBean {

	private List<File> uploadedFiles = new ArrayList<File>();

	private static Logger logger = Logger.getLogger(FileUploadBean.class);
	private String outputHTML;
//	private File swcRespFile;
	private boolean show3DView = false;
	
	@ManagedProperty("#{sessionBean}")
	private SessionBean sb;
	private int percent = 100;

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile f = event.getFile();
		String fName = f.getFileName();
		InputStream in = null;
		File uf = null;
		OutputStream out = null;
//		String tmpDir = "";
		try {
			uf = File.createTempFile("swc_", ".swc");
//			tmpDir = PropertyReader.getProperty("upload.tmpDir");
//			File theDir = new File(tmpDir);
//			if (!theDir.exists()) {
//				theDir.mkdirs();
//			}
			in = f.getInputstream();
//			uf = new File(tmpDir, fName);
			out = new FileOutputStream(uf);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}  catch (IOException e1) {
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
			sb.setSwcRespFile(generate3DView(uf));
			sb.setReaded(false);
		
//			setSwcRespFile(postFileGetRespFile(PropertyReader.getProperty("swcService.targetURL"), uf));
//			setOutputHTML(FileUtils.readFileToString(getSwcRespFile()));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	public String getOutputHTML() {
		try {
			if(sb != null && !sb.isReaded())
			{
				outputHTML = FileUtils.readFileToString(sb.getSwcRespFile());
				this.show3DView = true;
				sb.setReaded(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputHTML;
	}

	public void setOutputHTML(String outputHTML) {
		this.outputHTML = outputHTML;
	}


	public File generate3DView(File f) throws HttpException,IOException, URISyntaxException {
		String targetURL = PropertyReader.getProperty("swc.3Dview.targetURL");
	    File respFile = File.createTempFile("swc_3d", ".html");
    	PostMethod filePost = new PostMethod(targetURL);
		Part[] parts = { new FilePart(f.getName(), f) };
		filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(filePost);
		logger.debug("status : " + status);
		String resp = filePost.getResponseBodyAsString();	
	    Files.write(respFile.toPath(), resp.getBytes());
		filePost.releaseConnection();
		System.err.println(respFile);
		return respFile;
	}
	
	
	public String generate()
	{

		return "";
	}
	
	public String generateScreenshot() throws HttpException,IOException, URISyntaxException {
		File f = uploadedFiles.get(0);
		int p = getPercent();
		
		String targetURL = PropertyReader.getProperty("swc.screenshot.targetURL");
	    File respFile = File.createTempFile("swc_ss", ".jpg");
    	PostMethod filePost = new PostMethod(targetURL);
		Part[] parts = { new FilePart(f.getName(), f) };
		filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(filePost);
		logger.debug("status : " + status);
		String resp = filePost.getResponseBodyAsString();	
	    Files.write(respFile.toPath(), resp.getBytes());
		filePost.releaseConnection();
		System.err.println("screenshot" + respFile);
		return "";
	}

//	public File getSwcRespFile() {
//		return swcRespFile;
//	}
//	public void setSwcRespFile(File swcRespFile) {
//		this.swcRespFile = swcRespFile;
//	}
	
	public List<File> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<File> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	public SessionBean getSb() {
		return sb;
	}
	public void setSb(SessionBean sb) {
		this.sb = sb;
	}
	public boolean isShow3DView() {
		return show3DView;
	}
	public void setShow3DView(boolean show3dView) {
		show3DView = show3dView;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	


}