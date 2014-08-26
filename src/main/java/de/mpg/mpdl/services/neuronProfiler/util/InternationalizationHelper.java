package de.mpg.mpdl.services.neuronProfiler.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class InternationalizationHelper extends Observable implements Serializable {
    private static Logger logger = Logger.getLogger(InternationalizationHelper.class);
	
    private String userLocaleString = "en";
    private Locale userLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    
    public static final String LABEL_BUNDLE = "Label";
    public static final String MESSAGES_BUNDLE = "Messages";
    
    
    
    public InternationalizationHelper(){
    	Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
    	
        boolean found = false;
        while (supportedLocales.hasNext())
        {
            Locale supportedLocale = supportedLocales.next();
            if (supportedLocale.getLanguage().equals(userLocale.getLanguage()))
            {
                found = true;
                break;
            }
        }
        if (!found)
        {
            userLocale = new Locale("en");
        }
       
        userLocaleString = userLocale.getLanguage();  

    }
    
    public void changeLanguage(ValueChangeEvent event)
    {
    	FacesContext fc = FacesContext.getCurrentInstance();
    	if (event.getOldValue() != null && !event.getOldValue().equals(event.getNewValue()))
    	{
    		Locale locale = null;
    		String language = event.getNewValue().toString();
            String country = language.toUpperCase();
            this.userLocaleString = language;
            try
            {
                locale = new Locale(language, country);
                fc.getViewRoot().setLocale(locale);
                Locale.setDefault(locale);
                userLocale = locale;
                logger.debug("New locale: " + language + "_" + country + " : " + locale);
            }
            catch (Exception e)
            {
                logger.error("unable to switch to locale using language = " + language + " and country = " + country, e);
            }

            try {
				super.setChanged();
				super.notifyObservers();
			} catch (Exception e) {
				logger.warn("Could not notify bean about language change", e);
			}
    	}
    }

	public String getUserLocaleString() {
		return userLocaleString;
	}

	public void setUserLocaleString(String userLocaleString) {
		this.userLocaleString = userLocaleString;
	}

	public Locale getUserLocale() {
		return userLocale ;
	}

	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}

	public String getSelectedLabelBundle() {
		return LABEL_BUNDLE + "_" + userLocale.getLanguage();
	}

	public String getSelectMessagesBundle() {
		return MESSAGES_BUNDLE + "_" + userLocale.getLanguage();
	}
	
	public static String getMessage (String name)
    {
    	return getResource("Messages", name);
    }

	public static String getLabel (String name)
    {
    	return getResource("Label", name);
    }
	
	 public static String getResource (String bundle, String name)
	    {
	    	try{
	    	Application application = FacesContext.getCurrentInstance().getApplication();
		    InternationalizationHelper i18nHelper = (InternationalizationHelper) application.getVariableResolver().resolveVariable(FacesContext.getCurrentInstance(), "internationalizationHelper");
		    ResourceBundle rBundle = ResourceBundle.getBundle(bundle, i18nHelper.getUserLocale());
		    return rBundle.getString(name);
	    	}
	    	catch(Exception e)
	    	{
	    		//logger.warn("Value: " + name + " not found in resource bundle: " + bundle);
	    		return name;
	    	}
	    }
    
    
    
}
