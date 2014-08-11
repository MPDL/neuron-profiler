package de.mpg.mpdl.services.neuronProfiler.components.plupload;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

import org.apache.commons.fileupload.FileItem;


public class FileUploadEvent extends ActionEvent {

	public FileUploadEvent(UIComponent component) {
		super(component);
	}

	private FileItem fileItem;

	public void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	public FileItem getFileItem() {
		return fileItem;
	}
	

}
