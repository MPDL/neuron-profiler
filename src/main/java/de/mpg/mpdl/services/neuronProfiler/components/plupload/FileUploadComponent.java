package de.mpg.mpdl.services.neuronProfiler.components.plupload;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.PostValidateEvent;

import org.apache.log4j.Logger;

@FacesComponent("plupload.FileUploadComponent")
@ListenerFor(systemEventClass=PostValidateEvent.class)
public class FileUploadComponent extends UINamingContainer {
	Logger logger = Logger.getLogger(FileUploadComponent.class);
	
	@Override
	 public void decode(FacesContext context)
	{
		System.err.println("decode");
		super.decode(context);
		//HTML 5 uploads & Flash
		if(context.getPartialViewContext().isAjaxRequest())
		{
			checkFileUpload();
		}		
	}
	
	
	@Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		System.err.println("processEvent");
        super.processEvent(event);
        //HTML 4 uploads
        if(!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest())
		{
        	logger.info("processEvent: " + event);	
			checkFileUpload();
		}
    }
     

     public void broadcast(FacesEvent event) throws AbortProcessingException {
    	 System.err.println("broadcast");
        super.broadcast(event);
        if(getAttributes().get("fileUploadEventListener")!=null)
        {
        	MethodExpression fileUploadExp = (MethodExpression)getAttributes().get("fileUploadEventListener");
        	fileUploadExp.invoke(this.getFacesContext().getELContext(), new Object[]{((FileUploadEvent)event)});
        }
     }
     
     private void checkFileUpload()
     {   	  
    	 System.err.println("checkFileUpload");
 		 Object request = getFacesContext().getExternalContext().getRequest();
 		//logger.info("checkFileUpload: ");	
 		 
 		 if(request instanceof MultipartRequest)
 			{
 			 //logger.info("request is multipart, get file for client id " + getClientId());	
 				if(((MultipartRequest)request).getFile(getClientId())!=null)
 				{
 					//logger.info("append to event queue");
 					FileUploadEvent evt = new FileUploadEvent(this); 
 					evt.setFileItem(((MultipartRequest)request).getFile(getClientId()));
 		            queueEvent(evt);
 				}
 	            
 	        }
     }
     
}
