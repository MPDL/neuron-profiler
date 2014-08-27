package de.mpg.mpdl.service.demo.neuronProfiler.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class ServiceHelper {
	
	public static InputStream connectService(String targetURL, Object object) throws FileNotFoundException, HttpException, IOException{		
		PostMethod post = new PostMethod(targetURL);
		Part[] parts = { new FilePart(((File) object).getName(), (File)object) };
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		HttpClient client = new HttpClient();
		int status = client.executeMethod(post);
		InputStream is = post.getResponseBodyAsStream();
		post.releaseConnection();
		return is;
	}

}
