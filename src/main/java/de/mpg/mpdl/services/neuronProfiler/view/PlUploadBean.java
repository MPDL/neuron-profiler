package de.mpg.mpdl.services.neuronProfiler.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import de.mpg.mpdl.services.neuronProfiler.components.plupload.FileUploadEvent;
import de.mpg.mpdl.services.neuronProfiler.util.InternationalizationHelper;
import de.mpg.mpdl.services.neuronProfiler.util.MessageHelper;

@ManagedBean
@SessionScoped
@URLMapping(id="plupload", viewId = "/plupload.xhtml", pattern = "/plupload")
public class PlUploadBean {
	private static Logger logger = Logger.getLogger(PlUploadBean.class);
	
	private List<DiskFileItem> uploadedFiles = new ArrayList<DiskFileItem>();
	
	private String uploadedFilesByPlupload;
    
	public void fileUploaded(FileUploadEvent evt)
	{		
		logger.info("File uploaded" + evt.getFileItem().getName() +" (" + evt.getFileItem().getSize()+")");
		FileUploadEvent fue = (FileUploadEvent) evt;		
		if(fue.getFileItem()!=null)
		{
			uploadedFiles .add((DiskFileItem)fue.getFileItem());
		}
		System.err.println("File uploaded" + evt.getFileItem().getName() +" (" + evt.getFileItem().getSize()+")");
	}
	
	
	public String uploadComplete()
	{
		MessageHelper.infoMessage(InternationalizationHelper.getMessage("upload_complete"));
		
		List<String> uploadedFileNamesByPluploader = new LinkedList<String>();
		for(String plFileName : uploadedFilesByPlupload.split("\\|\\|"))
		{
			if (!plFileName.isEmpty())
			{
				uploadedFileNamesByPluploader.add(plFileName);
			}
		}

		//TODO validatation
		for(DiskFileItem img : uploadedFiles)
		{

		}
		
		uploadedFiles.clear();
		
		if(uploadedFileNamesByPluploader.size()>0)
		{
			MessageHelper.errorMessage(InternationalizationHelper.getMessage("error_uploadFollowing Files") + ": " + uploadedFileNamesByPluploader);
		}
	
		this.uploadedFilesByPlupload = "";		
		return "";
	}


	public String getUploadedFilesByPlupload() {
		return uploadedFilesByPlupload;
	}


	public void setUploadedFilesByPlupload(String uploadedFilesByPlupload) {
		this.uploadedFilesByPlupload = uploadedFilesByPlupload;
	}
	
	
	
}
