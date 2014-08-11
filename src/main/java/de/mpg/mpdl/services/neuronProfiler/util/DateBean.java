package de.mpg.mpdl.services.neuronProfiler.util;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DateBean {
	
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) { 
		this.date = date;
	}
	
	public String submit()
	{ 
		return "";
	}
	
	
}
