package de.mpg.mpdl.services.neuronProfiler.beans;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class SessionBean implements Serializable{
	private File swcRespFile;
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
	

}
