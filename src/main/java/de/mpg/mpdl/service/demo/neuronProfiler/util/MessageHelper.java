package de.mpg.mpdl.service.demo.neuronProfiler.util;


import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

public class MessageHelper {


		private static Logger logger = Logger.getLogger(MessageHelper.class);
	    
	    
	    /**
	     * @param summary summary text
	     */
	    public static void infoMessage(String summary)
	    {
	        infoMessage(summary, null, null);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void infoMessage(String summary, String detail)
	    {
	        infoMessage(summary, detail, null);
	    }

	    /**
	     * @param component associated <code>UIComponent</code>
	     * @param summary summary text
	     */
	    public static void infoMessage(UIComponent component, String summary)
	    {
	        infoMessage(summary, null, component);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void infoMessage(String summary, String detail, UIComponent component)
	    {
	        message(summary, detail, component, FacesMessage.SEVERITY_INFO);
	    }

	    /** 
	     * @param summary summary text
	     */
	    public static void warnMessage(String summary)
	    {
	        warnMessage(summary, null, null);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void warnMessage(String summary, String detail)
	    {
	        warnMessage(summary, detail, null);
	    }

	    /** 
	     * @param component associated <code>UIComponent</code>
	     * @param summary summary text
	     */
	    public static void warnMessage(UIComponent component, String summary)
	    {
	        warnMessage(summary, null, component);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void warnMessage(String summary, String detail, UIComponent component)
	    {
	        message(summary, detail, component, FacesMessage.SEVERITY_WARN);
	    }

	    /** 
	     * @param summary summary text
	     */
	    public static void errorMessage(String summary)
	    {
	        errorMessage(summary, null, null);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void errorMessage(String summary, String detail)
	    {
	        errorMessage(summary, detail, null);
	    }

	    /** 
	     * @param component associated <code>UIComponent</code>
	     * @param summary summary text
	     */
	    public static void errorMessage(UIComponent component, String summary)
	    {
	        errorMessage(summary, null, component);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void errorMessage(String summary, String detail, UIComponent component)
	    {
	        message(summary, detail, component, FacesMessage.SEVERITY_ERROR);
	    }

	    /** 
	     * @param summary summary text
	     */
	    public static void fatalMessage(String summary)
	    {
	        fatalMessage(summary, null, null);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void fatalMessage(String summary, String detail)
	    {
	        fatalMessage(summary, detail, null);
	    }

	    /**
	     * @param component associated <code>UIComponent</code>
	     * @param summary summary text
	     */
	    public static void fatalMessage(UIComponent component, String summary)
	    {
	        fatalMessage(summary, null, component);
	    }

	    /**
	     * @param summary summary text
	     */
	    public static void fatalMessage(String summary, String detail, UIComponent component)
	    {
	        message(summary, detail, component, FacesMessage.SEVERITY_FATAL);
	    }

	    /** 
	     * @param summary summary text
	     */
	    public static void message(String summary, String detail, UIComponent component, Severity severity)
	    {
	        FacesMessage fm = new FacesMessage(severity, summary, detail);
	        if (component == null)
	        {
	            FacesContext.getCurrentInstance().addMessage(null, fm);
	        }
	        else
	        {
	            FacesContext.getCurrentInstance().addMessage(component.getId(), fm);
	        }
	    }
	    
	
	/*
	public static void infoMessage(String msg)
	{
		addMessage(msg, null, FacesMessage.SEVERITY_INFO, null);
	}
	
	public static void errorMessage(String msg)
	{
		addMessage(msg, null, FacesMessage.SEVERITY_ERROR, null);
	}
	
	
	public static void warnMessage(String msg)
	{
		addMessage(msg, null, FacesMessage.SEVERITY_WARN, null);
	}
	
	private static void addMessage(String summary, String detail, Severity severity, String clientId)
	{
		FacesMessage facesMsg = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, facesMsg);
	}
	*/

}
