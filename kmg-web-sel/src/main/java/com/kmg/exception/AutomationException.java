package com.kmg.exception;

@SuppressWarnings("serial")
public class AutomationException extends Exception {
	
	/**
	 * User-defined exception constructor with message
	 * @param message
	 */
	public AutomationException (String message) {
		super (message);
	}
	
	/**
	 * User defined exception constructor with message and cause of exception
	 * @param message
	 * @param cause
	 * @throws AutomationException
	 */
	public AutomationException (String message, Throwable cause) throws AutomationException{
		super (message, cause);
	}
	
	/**
	 * ser defined exception constructor with cause of exception
	 * @param cause
	 * @throws AutomationException
	 */
	public AutomationException(Throwable cause) throws AutomationException {
		super(cause);
	}

}
