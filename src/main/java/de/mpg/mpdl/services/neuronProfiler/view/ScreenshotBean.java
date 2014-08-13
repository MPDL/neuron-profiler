package de.mpg.mpdl.services.neuronProfiler.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

@ManagedBean
@ViewScoped
public class ScreenshotBean {
	private File screenShot = new File("C://Users//yu//Desktop//uploaded.png");
	private String output;
	

	public File getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(File screenShot) {
		this.screenShot = screenShot;
	}

	public String getOutput() throws IOException {
		String screenShotPath = "C://Users//yu//Desktop//uploaded.png";



		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	
	
	

}
