package com.kmg.keywords;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kmg.base.AutomationBase;
import com.kmg.exception.AutomationException;
import com.kmg.utils.AutomationConstants;

public class DataHandlers {
	
	/**
	 * Method to get the property value from the property file
	 * 
	 * @param fileName
	 * @param propertyName
	 * @return
	 * @throws AutomationException
	 */
	public String getProperty(String fileName, String propertyName) throws AutomationException {
		try {
			String propValue = "";
			Properties props = new Properties();
			String filePath = fileName + ".properties";
			InputStream input = getClass().getClassLoader().getResourceAsStream(filePath);
			props.load(input);
			propValue = props.getProperty(propertyName);
			return propValue;
		} catch (IOException e) {
			throw new AutomationException(
					new AutomationBase().getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

}
