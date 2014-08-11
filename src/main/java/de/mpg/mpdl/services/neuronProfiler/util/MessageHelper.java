/*******************************************************************************
 * CDDL HEADER START
 * The contents of this file are subject to the terms of the Common Development and Distribution License, Version 1.0 only (the "License"). You may not use this file except in compliance with the License.
 * 
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE or http://www.escidoc.de/license. 
 * See the License for the specific language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and include the License file at license/ESCIDOC.LICENSE. If applicable, add the following below this CDDL HEADER, with the fields enclosed by brackets "[]" replaced with your own identifying information: Portions Copyright [yyyy] [name of copyright owner]
 * CDDL HEADER END
 * 
 * Copyright 2006-2013 Fachinformationszentrum Karlsruhe Gesellschaft für wissenschaftlich-technische Information mbH and Max-Planck-Gesellschaft zur Förderung der Wissenschaft e.V.
 * All rights reserved. Use is subject to license terms.
 ******************************************************************************/
package de.mpg.mpdl.services.neuronProfiler.util;


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
