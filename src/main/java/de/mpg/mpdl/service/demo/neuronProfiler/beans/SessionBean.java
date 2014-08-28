package de.mpg.mpdl.service.demo.neuronProfiler.beans;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class SessionBean implements Serializable{

	private static final long serialVersionUID = 1620446193528678213L;

	private boolean readed = true;

	public boolean isReaded() {
		return readed;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}


	
	

}
