package de.mpg.mpdl.services.neuronProfiler.beans;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class SessionBean implements Serializable{
	private File swcFile; 
	private File swcRespFile;
	private String screenshotFile;
	private boolean readed = true;
	public File getSwcRespFile() {
		return swcRespFile;
	}
	public void setSwcRespFile(File swcRespFile) {
		this.swcRespFile = swcRespFile;
	}
	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public File getSwcFile() {
		return swcFile;
	}
	public void setSwcFile(File swcFile) {
		this.swcFile = swcFile;
	}
	public String getScreenshotFile() {
		return screenshotFile;
	}
	public void setScreenshotFile(String screenshotFile) {
		this.screenshotFile = screenshotFile;
	}

	
	

}
