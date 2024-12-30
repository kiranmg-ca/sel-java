package com.kmg.keywords;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kmg.base.AutomationBase;
import com.kmg.exception.AutomationException;
import com.kmg.utils.AutomationConstants;

public class UIActions extends AutomationBase{
	Utilities utilityHelper = new Utilities();
	DataHandlers dataHandlerHelper = new DataHandlers();

	/**
	 * To click on an object
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void click (WebDriver driver, String elementName) throws AutomationException {
		try {
			if(elementName!=null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				element.click();
				System.out.println("==========================================================");
				System.out.println("Successfully clicked on " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND+ "'" + elementName + "'");
			}
		} catch (Exception e) {
			try {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				Actions builder = new Actions(driver);
				Action action = builder.moveToElement(element).click().build();
				action.perform();
				System.out.println("==========================================================");
				System.out.println("Successfully clicked on " + elementName);
			} catch (Exception e1) {
				System.out.println("==========================================================");
				System.out.println("Failed to click on the " + elementName);
				throw new AutomationException(getExceptionMessage(), e1);
			}
		}
	}
	
	/**
	 * To right-click on a web element
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void rightClick (WebDriver driver, String elementName) throws AutomationException {
		try {
			if (elementName!= null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				Actions action = new Actions(driver);
				action.contextClick(element).perform();
				System.out.println("==========================================================");
				System.out.println("Successfully right clicked on " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Double click on an object
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void doubleClick (WebDriver driver, String elementName) throws AutomationException {
		try {
			if(elementName != null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				Actions action = new Actions(driver);
				action.doubleClick(element).perform();
				System.out.println("==========================================================");
				System.out.println("Successfully double clicked on " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND+ "'"+ elementName + "'");
			}
		} catch (Exception e) {
			try {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()", element);
				System.out.println("==========================================================");
				System.out.println("Successfully clicked on " + elementName);
			} catch (Exception e1) {
				throw new AutomationException(getExceptionMessage(), e1);
			}
		}
	}
	
	/**
	 * To multi click on an object
	 * 
	 * @param driver
	 * @param elementName
	 * @param clickCount
	 * @throws AutomationException
	 */
	public void multiClick (WebDriver driver, String elementName, int clickCount) throws AutomationException {
		try {
			if(elementName != null) {
				long timeout = Long.parseLong(dataHandlerHelper.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				WebElement element = wait.until(ExpectedConditions.visibilityOf(utilityHelper.getWebElement(driver, elementName)));
				Actions action = new Actions(driver);
				for (int i =0; i < clickCount; i++) {
					action.click().perform();
					Thread.sleep(1000);
					wait.until(ExpectedConditions.elementToBeClickable(element));
				}
				System.out.println("==========================================================");
				System.out.println("Successfully multi-clicked on " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'"+ elementName+ "'");
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Click using the co-ordinates
	 * 
	 * @param driver
	 * @param xOffset
	 * @param yOffset
	 * @throws AutomationException
	 */
	public void clickUsingCoordinates(WebDriver driver, int xOffset, int yOffset) throws AutomationException {
		try {
			Actions action = new Actions(driver);
			action.moveByOffset(xOffset, yOffset).click().build().perform();
			System.out.println("==========================================================");
			System.out.println("Successfully clicked on " + xOffset + " and " + yOffset + " locations");
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * To type the values to an input field
	 * 
	 * @param driver
	 * @param elementName
	 * @param typeValue
	 * @throws AutomationException
	 */
	public void type(WebDriver driver, String elementName, String typeValue) throws AutomationException {
		try {
			if (driver != null && elementName != null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				element.sendKeys(typeValue);
				System.out.println("==========================================================");
				System.out.println("Data " + typeValue + " successfully entered into " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
			}
		} catch (Exception e) {
			try {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0]. value='" + typeValue + "';", element);
				System.out.println("==========================================================");
				System.out.println("Data " + typeValue + " successfully entered into " + elementName);
			} catch (Exception ex) {
				System.out.println("==========================================================");
				System.out.println("Data " + typeValue + " failed to enter into " + elementName);
				throw new AutomationException(getExceptionMessage(), ex);
			}
		}
	}
	
	/**
	 * Clear an input field and enter values to that input field
	 * 
	 * @param driver
	 * @param elementName
	 * @param typeValue
	 * @throws AutomationException
	 */
	public void clearAndType(WebDriver driver, String elementName, String typeValue) throws AutomationException {
		try {
			if (elementName != null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				element.clear();
				element.sendKeys(typeValue);
				System.out.println("==========================================================");
				System.out.println("Data " + typeValue + " successfully entered into " + elementName);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
			}
		} catch (Exception e) {
			try {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				element.clear();
				executor.executeScript("arguments[0]. value='" + typeValue + "';", element);
				System.out.println("==========================================================");
				System.out.println("Data " + typeValue + " successfully entered into " + elementName);
			} catch (Exception ex) {
				throw new AutomationException(getExceptionMessage(), ex);
			}
		}
	}
}
