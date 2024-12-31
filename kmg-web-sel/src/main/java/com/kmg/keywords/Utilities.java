package com.kmg.keywords;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kmg.base.AutomationBase;
import com.kmg.exception.AutomationException;
import com.kmg.utils.AutomationConstants;

public class Utilities extends AutomationBase{
	
	public WebDriverWait wait;
	DataHandlers objHandlers = new DataHandlers();
	
	/**
	 * Get the web element based on the visibility and return WebElement
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public WebElement getWebElement(WebDriver driver, String elementName) throws AutomationException {
		WebElement element = null;
		DataHandlers objPropertyData = new DataHandlers();
		try {
			long timeout = Long.parseLong(objPropertyData.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG,
					AutomationConstants.SHORT_LOADING));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			By actualElement = getElementByLocator(elementName);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(actualElement));
			((JavascriptExecutor) driver)
					.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
		} catch (Exception e) {
			try {
				long timeout = Long.parseLong(objPropertyData.getProperty(
						AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				By actualElement = getElementByLocator(elementName);
				element = wait.until(ExpectedConditions.presenceOfElementLocated(actualElement));
				((JavascriptExecutor) driver).executeScript(
						"window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");

			} catch (Exception exe) {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
			}
		}
		return element;
	}
	
	/**
	 * Get the By locator value
	 * 
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public By getElementByLocator(String elementName) throws AutomationException {
		By byElement = null;
		try {
			if (elementName.startsWith("#") || elementName.startsWith("td[") || elementName.startsWith("tr[")
					|| elementName.startsWith("td ") || elementName.startsWith("tr ")
					|| elementName.startsWith("input[") || elementName.startsWith("span[")
					|| elementName.startsWith("div") || elementName.startsWith(".")) {
				byElement = By.cssSelector(elementName);
			} else if (elementName.startsWith("//") || elementName.startsWith(".//") || elementName.startsWith("(.//")
					|| elementName.startsWith("(//") || elementName.startsWith("((//")) {
				byElement = By.xpath(elementName);
			} else if (elementName.startsWith("name")) {
				byElement = By.name(elementName.split(">>")[1]);
			} else if (elementName.startsWith("id")) {
				byElement = By.id(elementName.split(">>")[1]);
			} else if (elementName.startsWith("className")) {
				byElement = By.className(elementName.split(">>")[1]);
			} else if (elementName.startsWith("linkText")) {
				byElement = By.linkText(elementName.split(">>")[1]);
			} else if (elementName.startsWith("partialLinkText")) {
				byElement = By.partialLinkText(elementName.split(">>")[1]);
			} else {
				byElement = By.tagName(elementName);
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return byElement;
	}
	
	/**
	 * To wait for the element based on the visibility of element located
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void waitForElement (WebDriver driver, String elementName) throws AutomationException {
		try {
			if (elementName != null) {
				long timeout = Long.parseLong(objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				By webElement = getElementByLocator(elementName);
				wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));	
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + " '"+ elementName+"'");
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Wait for the text to be present in the web element
	 * 
	 * @param driver
	 * @param elementName
	 * @param expectedText
	 * @throws AutomationException
	 */
	public void waitForTextPresent (WebDriver driver, String elementName, String expectedText) throws AutomationException {
		try {
			if (elementName != null) {
				long timeout = Long.parseLong(objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				By webElement = getElementByLocator(elementName);
				wait.until(ExpectedConditions.textToBePresentInElementLocated(webElement, expectedText));
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + " '"+ elementName+"'");
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Wait for the elements based on the presence of element located and return the list of WebElements
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public List<WebElement> waitForElements(WebDriver driver, String elementName) throws AutomationException {
		List<WebElement> elements;
		try {
			long timeout = Long.parseLong(
					objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, "SHORT_LOADING"));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			By actualElement = getElementByLocator(elementName);
			wait.until(ExpectedConditions.presenceOfElementLocated(actualElement));
			elements = driver.findElements(actualElement);
		} catch (Exception e) {
			try {
				long timeout = Long.parseLong(
						objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, "SHORT_LOADING"));
				wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				By actualElement = getElementByLocator(elementName);
				wait.until(ExpectedConditions.visibilityOfElementLocated(actualElement));
				elements = driver.findElements(actualElement);
			} catch (Exception exe) {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
			}
		}
		return elements;
	}
	
	/**
	 * Set delay between the steps
	 * 
	 * @param delayInSeconds
	 * @throws AutomationException
	 */
	public void delay(int delayInSeconds) throws AutomationException {
		try {
			Thread.sleep(delayInSeconds * 1000);
		} catch (Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}
	
	/**
	 * Scroll to the element
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void scrollToElement(WebDriver driver, String elementName) throws AutomationException {
		try {
			WebElement element = getWebElement(driver, elementName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Scroll to the bottom of the web page
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void scrollToBottom(WebDriver driver) throws AutomationException {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Scroll to the top of the web page
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void scrollToTop(WebDriver driver) throws AutomationException {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Scroll the web page up in the application based on the number of times provided
	 * 
	 * @param numberOfTimes
	 * @throws AutomationException
	 */
	public void scrollWebPageUp(int numberOfTimes) throws AutomationException {
		int x = 1;

		try {
			Robot robot = new Robot();
			new Utilities().delay(2);
			while (x <= numberOfTimes) {
				// Simulate a mouse click
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				// Simulate a key press
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
				new Utilities().delay(2);
				x++;
			}
		} catch (AWTException e) {
			throw new AutomationException(e);
		}

	}

	/**
	 *  Scroll the web page down in the application based on the number of times provided
	 * 
	 * @param numberOfTimes
	 * @throws AutomationException
	 */
	public void scrollWebPageDown(int numberOfTimes) throws AutomationException {
		int x = 1;

		try {
			Robot robot = new Robot();
			new Utilities().delay(2);
			while (x <= numberOfTimes) {
				// Simulate a mouse click
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				// Simulate a key press
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				new Utilities().delay(2);
				x++;
			}
		} catch (AWTException e) {
			throw new AutomationException(e);
		}
	}
	
	/**
	 * Convert a double value to an Integer
	 * 
	 * @param doubleValue
	 * @return
	 * @throws AutomationException
	 */
	public int convertDoubleToInt(double doubleValue) throws AutomationException {
		try {
			int intValue = (int) doubleValue;
			return intValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert a float value to an Integer
	 * 
	 * @param floatValue
	 * @return
	 * @throws AutomationException
	 */
	public int convertFloatToInt(float floatValue) throws AutomationException {
		try {
			int intValue = (int) floatValue;
			return intValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert a String value to an Integer
	 * 
	 * @param stringValue
	 * @return
	 * @throws AutomationException
	 */
	public int convertStringToInt(String stringValue) throws AutomationException {
		try {
			int intValue = Integer.parseInt(stringValue);
			return intValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert a String value to a Double value
	 * 
	 * @param stringValue
	 * @return
	 * @throws AutomationException
	 */
	public double convertStringToDouble(String stringValue) throws AutomationException {
		try {
			double doubleValue = Double.parseDouble(stringValue);
			return doubleValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert an Integer value to a String value
	 * 
	 * @param intValue
	 * @return
	 * @throws AutomationException
	 */
	public String convertIntToString(int intValue) throws AutomationException {
		try {
			String stringValue = String.valueOf(intValue);
			return stringValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert a Double value to a String value
	 * 
	 * @param doubleValue
	 * @return
	 * @throws AutomationException
	 */
	public String convertDoubleToString(double doubleValue) throws AutomationException {
		try {
			String stringValue = String.valueOf(doubleValue);
			return stringValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Convert a String value to a Long value
	 * 
	 * @param stringValue
	 * @return
	 * @throws AutomationException
	 */
	public long convertStringToLong(String stringValue) throws AutomationException {
		try {
			long longValue = Long.parseLong(stringValue);
			return longValue;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Clear an input field
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void clearInputField(WebDriver driver, String elementName) throws AutomationException {
		try {
			WebElement element = getWebElement(driver, elementName);
			element.clear();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Copy and paste the value from one input field to another input field
	 * 
	 * @param driver
	 * @param sourceElementName
	 * @param destinationElementName
	 * @throws AutomationException
	 */
	public void copyAndPasteFromOneInputFieldToAnother(WebDriver driver, String sourceElementName,
			String destinationElementName) throws AutomationException {
		try {
			WebElement sourceElement = getWebElement(driver, sourceElementName);
			sourceElement.sendKeys(Keys.CONTROL + "a");
			sourceElement.sendKeys(Keys.CONTROL + "c");

			WebElement destinationElement = getWebElement(driver, destinationElementName);
			destinationElement.clear();
			destinationElement.sendKeys(Keys.CONTROL + "v");
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * To delete a file
	 * 
	 * @param filePath
	 * @throws AutomationException 
	 */
	public void deleteFile(String filePath) throws AutomationException {
		try {
			FileUtils.forceDelete(new File(filePath));
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Select all and delete the value from the input field
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void selectAndDeleteInputFieldValue(WebDriver driver, String elementName) throws AutomationException {
		try {
			WebElement sourceElement = getWebElement(driver, elementName);
			sourceElement.sendKeys(Keys.CONTROL + "a");
			sourceElement.sendKeys(Keys.DELETE);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Select the value from the drop down using the visible text
	 * 
	 * @param driver
	 * @param elementName
	 * @param visibleText
	 * @throws AutomationException
	 */
	public void selectDropDownValueByVisibleText(WebDriver driver, String elementName, String visibleText)
			throws AutomationException {
		try {
			Select select = new Select(getWebElement(driver, elementName));
			select.selectByVisibleText(visibleText);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Select the value from the drop down using the index
	 * 
	 * @param driver
	 * @param elementName
	 * @param index
	 * @throws AutomationException
	 */
	public void selectDropDownValueByIndex(WebDriver driver, String elementName, int index) throws AutomationException {
		try {
			Select select = new Select(getWebElement(driver, elementName));
			select.selectByIndex(index);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Select the value from the drop down using value
	 * 
	 * @param driver
	 * @param elementName
	 * @param valueToSelect
	 * @throws AutomationException
	 */
	public void selectDropDownValueByValue(WebDriver driver, String elementName, String valueToSelect)
			throws AutomationException {
		try {
			Select select = new Select(getWebElement(driver, elementName));
			select.selectByValue(valueToSelect);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * De-select the selected value from the drop down based on the index
	 * 
	 * @param driver
	 * @param elementName
	 * @param index
	 * @throws AutomationException
	 */
	public void deselectElementByIndex(final WebDriver driver, final String elementName, final int index)
			throws AutomationException {
		try {
			if (driver != null) {
				WebElement element = getWebElement(driver, elementName);
				if (element != null) {
					final Select listbox = new Select(element);
					listbox.deselectByIndex(index);
				} else {
					throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
				}
			}
		} catch (final Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * De-select the selected value from the drop down based on the value
	 * 
	 * @param driver
	 * @param elementName
	 * @param value
	 * @throws AutomationException
	 */
	public void deselectElementByValue(final WebDriver driver, final String elementName, final String value)
			throws AutomationException {
		try {
			if (driver != null) {
				WebElement element = getWebElement(driver, elementName);
				if (element != null) {
					final Select listbox = new Select(element);
					listbox.deselectByValue(value);
				} else {
					throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
				}
			}
		} catch (final Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * De-select the selected value from the drop down based on visible text
	 * 
	 * @param driver
	 * @param elementName
	 * @param visibleText
	 * @throws AutomationException
	 */
	public void deselectElementByVisibleText(final WebDriver driver, final String elementName, final String visibleText)
			throws AutomationException {
		try {
			if (driver != null) {
				WebElement element = getWebElement(driver, elementName);
				if (element != null) {
					final Select listbox = new Select(element);
					listbox.deselectByVisibleText(visibleText);
				} else {
					throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + elementName + "'");
				}
			}
		} catch (final Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}
	
	/**
	 * Drag and drop an element from the source to the destination
	 * 
	 * @param driver
	 * @param sourceElementName
	 * @param destinationElementName
	 * @throws AutomationException
	 */
	public void dragAndDrop(WebDriver driver, String sourceElementName, String destinationElementName) throws AutomationException
	{
		try {
			WebElement sourceElement = getWebElement(driver, sourceElementName);
			WebElement destinationElement = getWebElement(driver, destinationElementName);
			Actions action = new Actions(driver);
			action.clickAndHold(sourceElement).build().perform();
			Thread.sleep(1000);
			action.moveToElement(destinationElement).build().perform();
			Thread.sleep(1000);
			action.moveByOffset(-1, -1).build().perform();
			Thread.sleep(1000);
			action.release().build().perform();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Get the count of the elements from the Web element list
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public int getCountOfElements(WebDriver driver, String elementName) throws AutomationException {
		try {
			List<WebElement> elements = waitForElements(driver, elementName);
			return elements.size();
		} catch (Exception e) {
			return 1;
		}
	}
	
	/**
	 * Get the current date
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public String getCurrentDate() throws AutomationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			return filePathdate;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Get the current date in the format DD MMM YYYY
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public String getCurrentDateInFormatddMMMyyyy() throws AutomationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			return filePathdate;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	/**
	 * Get the current date in the format DD-MM-YYYY
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public String getCurrentDateInFormatddMMyyyy() throws AutomationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			return filePathdate;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Get the day from the current date
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public String getDayFromCurrentDate() throws AutomationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			String day = filePathdate.substring(0, 2);
			return day;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Get the attribute value of the web element
	 * 
	 * @param driver
	 * @param elementName
	 * @param attributeName
	 * @return
	 * @throws AutomationException
	 */
	public String getElementAttributeValue(WebDriver driver, String elementName, String attributeName) throws AutomationException {
		String elementAttribute = "";
		try {
			WebElement element = getWebElement(driver, elementName);
			elementAttribute = element.getAttribute(attributeName);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return elementAttribute;
	}
	
	/**
	 * Get the text from the web element
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public String getElementText(WebDriver driver, String elementName) throws AutomationException {
		String elementText = "";
		try {
			WebElement element = getWebElement(driver, elementName);
			elementText = element.getText();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return elementText;
	}
	
	/**
	 * Get the X co-ordinate of the web element
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public int getElementXcoordinate(WebDriver driver, String elementName) throws AutomationException {
		try {
			WebElement element = getWebElement(driver, elementName);
			Point point = element.getLocation();
			return point.getX();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Get the Y co-ordinate of the web element
	 * 
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws AutomationException
	 */
	public int getElementycoordinate(WebDriver driver, String elementName) throws AutomationException {
		try {
			WebElement element = getWebElement(driver, elementName);
			Point point = element.getLocation();
			return point.getY();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Get the long waiting time from the framework config file
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public long getLongWaitingTime() throws AutomationException {
		long timeout;
		try {
			timeout = Long.parseLong(objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG,
					AutomationConstants.LONG_LOADING));
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return timeout;
	}

	/**
	 * Get the short waiting time from the framework config file
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public long getShortWaitingTime() throws AutomationException {
		long timeout;
		try {
			timeout = Long.parseLong(objHandlers.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG,
					AutomationConstants.SHORT_LOADING));
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return timeout;
	}
	
	/**
	 * Get random alphabets with the length mentioned
	 * 
	 * @param stringLength
	 * @return
	 * @throws AutomationException
	 */
	public String getRandomAlphabets(int stringLength) throws AutomationException {
		try {
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(stringLength);
			for (int i = 0; i < stringLength; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Get a random alpha numeric value the length mentioned
	 * 
	 * @param stringLength
	 * @return
	 * @throws AutomationException
	 */
	public String getRandomAlphanumeric(int stringLength) throws AutomationException {
		try {
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(stringLength);
			for (int i = 0; i < stringLength; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}
	
	/**
	 * Get a random number with the number length mentioned
	 * 
	 * @param numberLength
	 * @return
	 * @throws AutomationException
	 */
	public int getRandomNumber(int numberLength) throws AutomationException {
		try {
			Random random = new Random();
			int randomNum = 0;
			boolean loop = true;
			while (loop) {
				randomNum = random.nextInt();
				if (Integer.toString(randomNum).length() == numberLength
						&& !Integer.toString(randomNum).startsWith("-")) {
					loop = false;
				}
			}
			return randomNum;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

}
