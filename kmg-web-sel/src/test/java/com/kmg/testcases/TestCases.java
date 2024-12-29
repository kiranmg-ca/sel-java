package com.kmg.testcases;

import com.kmg.exception.AutomationException;
import com.kmg.keywords.DataHandlers;
import com.kmg.utils.AutomationConstants;

public class TestCases {
	
	public static void main(String[] args) throws AutomationException {
		DataHandlers obj = new DataHandlers();
		
		System.out.println(obj.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
		System.out.println(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG);
		System.out.println(AutomationConstants.SHORT_LOADING);
	}

}
