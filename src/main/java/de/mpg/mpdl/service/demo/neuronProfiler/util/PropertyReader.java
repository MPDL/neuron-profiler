
package de.mpg.mpdl.service.demo.neuronProfiler.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Helper class for reading properties from the global escidoc property file.
 */
public class PropertyReader
{
    private static Properties properties;

    private static final String DEFAULT_PROPERTY_FILE = "neuron-profiler.properties";
    
    //private static URL solution;
    
    //private static String fileLocation = null;

    /**
     * Gets the value of a property for the given key from the system properties or the application property file.
     * It is always tried to get the requested property value from the system properties.
     * This option gives the opportunity to set a specific property temporary using the system properties.
     * If the requested property could not be obtained from the system properties the application property file is accessed.
     *
     * @param key The key of the property.
     * @return The value of the property.
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static String getProperty(String key) throws IOException, URISyntaxException
    {
        if (properties == null)
        {
            loadProperties();
        }
        String value = properties.getProperty(key);
        return value;
    }

    
    public static Properties getProperties() throws IOException, URISyntaxException 
    {
    	if(properties==null)
    	{
    		loadProperties();
    	}
    	
    	return properties;
    }
    
    public static void loadProperties()
    {
        String propertiesFile = DEFAULT_PROPERTY_FILE;
        InputStream instream = getInputStream(propertiesFile);
        properties = new Properties();
        try
        {
            properties.load(instream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        properties.putAll(properties);
    }

    private static InputStream getInputStream(String filePath)
    {   
        InputStream instream = null;
        try  
        {
        	String jbossHome = System.getProperty("catalina.base");
        	String path = jbossHome + "/conf/"  + filePath;
        	instream = new FileInputStream(path);
        }
        catch (Exception e)
        {
            URL url = PropertyReader.class.getClassLoader().getResource(filePath);
            if (url != null)
            {
                try
                {
                    instream = url.openStream();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        if (instream == null)
        {
            try
            {
                throw new FileNotFoundException(filePath);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return instream;
    }
}
