package de.mpg.mpdl.service.demo.neuronProfiler.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.FieldPosition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import de.mpg.mpdl.service.demo.neuronProfiler.util.PropertyReader;
import de.mpg.mpdl.service.demo.neuronProfiler.vo.SWCItem;
import de.mpg.mpdl.service.demo.neuronProfiler.vo.SWCMetadata;

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {

	private static final long serialVersionUID = 7677383714766328388L;

	private static Logger logger = Logger.getLogger(FileUploadBean.class);

	private SWCItem item = new SWCItem();

	private String outputHTML;

	private boolean show3DView = false;

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile f = event.getFile();
		InputStream in = null;

		File uf = null;
		OutputStream out = null;
		try {
			uf = File.createTempFile("swc_", ".swc");
			in = f.getInputstream();
			out = new FileOutputStream(uf);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
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
		this.item.setSwcFile(uf);
		try {
			this.item.setSwcRespHTMLFile(generate3DView(uf));
			setShow3DView(true);
			outputHTML = FileUtils.readFileToString(this.item.getSwcRespHTMLFile());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	public String getOutputHTML() {
//		try {
//			//return FileUtils.readFileToString(this.item.getSwcRespHTMLFile());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return outputHTML;
	}

	public void setOutputHTML(String outputHTML) {
		this.outputHTML = outputHTML;
	}

	public void generateMD() throws HttpException, IOException,
			URISyntaxException, ParseException {

		File f = item.getSwcFile();

		String targetURL = PropertyReader.getProperty("swc.analyze.targetURL");

		PostMethod post = new PostMethod(targetURL);

		Part[] parts = { new FilePart(f.getName(), f) };
		post.setRequestEntity(new MultipartRequestEntity(parts, post
				.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(post);
		logger.debug("---generate Metadata --Status-- :" + status);
		InputStream is = post.getResponseBodyAsStream();

		JSONParser jsonParser = new JSONParser();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		ContainerFactory containerFactory = new ContainerFactory() {
			public List creatArrayContainer() {
				return new LinkedList();
			}

			public Map createObjectContainer() {
				return new LinkedHashMap();
			}

		};

		try {
			Map json = (Map) jsonParser.parse(br, containerFactory);
			Iterator iter = json.entrySet().iterator();
			this.item.getMds().clear();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				this.item.getMds().add(
						new SWCMetadata((String) entry.getKey(), (String) entry
								.getValue()));

			}

		} catch (ParseException pe) {
			System.out.println(pe);
		}

		post.releaseConnection();

	}

	public File generate3DView(File f) throws HttpException, IOException,
			URISyntaxException {
		String targetURL = PropertyReader.getProperty("swc.3Dview.targetURL");
		File respFile = File.createTempFile("swc_3d", ".html");
		PostMethod post = new PostMethod(targetURL);
		post.setParameter("swc", IOUtils.toString(new FileInputStream(f)));
		post.setParameter("portable", "true");
		HttpClient client = new HttpClient();
		int status = client.executeMethod(post);
		logger.debug("status : " + status);
		IOUtils.copy(post.getResponseBodyAsStream(), new FileOutputStream(
				respFile));
		post.releaseConnection();
		System.err.println(respFile);
		return respFile;
	}

	public void generate() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 800);
		try {
			generateScreenshot();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().openDialog("screenshot", options,
				null);
	}

	public void generateScreenshot() throws HttpException, IOException,
			URISyntaxException {
		File f = item.getSwcFile();
		File screenshot = File.createTempFile("swc_ss", ".png");

		String targetURL = PropertyReader
				.getProperty("swc.screenshot.targetURL");

		PostMethod post = new PostMethod(targetURL);

		Part[] parts = { new FilePart(f.getName(), f) };
		post.setRequestEntity(new MultipartRequestEntity(parts, post
				.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(post);
		logger.debug("status : " + status);
		IOUtils.copy(post.getResponseBodyAsStream(), new FileOutputStream(
				screenshot));
		post.releaseConnection();
		System.err.println(screenshot);
		this.item.setScreenshotFilePath(screenshot.getAbsolutePath());

	}

	public SWCItem getItem() {
		return item;
	}

	public void setItem(SWCItem item) {
		this.item = item;
	}

	public boolean isShow3DView() {
		return show3DView;
	}

	public void setShow3DView(boolean show3dView) {
		show3DView = show3dView;
	}

}