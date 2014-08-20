package de.mpg.mpdl.services.neuronProfiler.vo;

public class SWCMetadata {
	
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
