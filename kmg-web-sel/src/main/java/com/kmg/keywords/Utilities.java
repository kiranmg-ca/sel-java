package com.kmg.keywords;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kmg.base.AutomationBase;
import com.kmg.exception.AutomationException;
import com.kmg.utils.AutomationConstants;

public class Utilities extends AutomationBase{
	
	public WebDriverWait wait;
	
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


}
