package de.mpg.mpdl.service.demo.neuronProfiler.vo;

import java.io.Serializable;

public class SWCMetadata implements Serializable {
	
	private static final long serialVersionUID = -3923073369251828087L;

	private String key;
	private String value;
	
	public SWCMetadata(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
