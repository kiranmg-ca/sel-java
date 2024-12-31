package com.kmg.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.kmg.base.AutomationBase;
import com.kmg.exception.AutomationException;


public class Validations extends AutomationBase{
	Utilities utilityHelper = new Utilities();
	
	/**
	 * Verify the check-box is checked
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyCheckboxSelected(WebDriver driver, String elementName, String messageOnFailure) {
		boolean isCheckboxSelected = false;
		try {
			if (driver != null) {
				WebElement element = utilityHelper.getWebElement(driver, elementName);
				if (element != null) {
					if (element.getAttribute("checked") != null) {
						if (element.getAttribute("checked").equals("true")) {
							isCheckboxSelected = true;
						}
					} else {
						isCheckboxSelected = false;
					}
					Assert.assertTrue(isCheckboxSelected, messageOnFailure);
				}
			}
		} catch (Exception e) {
			Assert.assertTrue(isCheckboxSelected, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element text is as per the expectation
	 * 
	 * @param driver
	 * @param elementName
	 * @param expectedText
	 * @param messageOnFailure
	 */
	public void verifyElementText(WebDriver driver, String elementName, String expectedText, String messageOnFailure) {
		boolean textVerified = false;
		try {
			WebElement element = utilityHelper.getWebElement(driver, elementName);
			String actualText = element.getText();
			if (actualText.contentEquals(expectedText)) {
				textVerified = true;
			} else {
				textVerified = false;
			}
			Assert.assertTrue(textVerified, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textVerified, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element text contains the expected text
	 * 
	 * @param driver
	 * @param elementName
	 * @param expectedText
	 * @param messageOnFailure
	 */
	public void verifyElementTextContains(WebDriver driver, String elementName, String expectedText, String messageOnFailure) {
		boolean textContains = false;
		try {
			WebElement element = utilityHelper.getWebElement(driver, elementName);
			String actualText = element.getText();
			if (actualText.contains(expectedText)) {
				textContains = true;
			} else {
				textContains = false;
			}
			Assert.assertTrue(textContains, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textContains, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element attribute has the expected text
	 * 
	 * @param driver
	 * @param elementName
	 * @param attributeName
	 * @param expectedText
	 * @param messageOnFailure
	 */
	public void verifyElementAttributeHasText(WebDriver driver, String elementName, String attributeName,
			String expectedText, String messageOnFailure) {
		boolean textVerified = false;
		try {
			WebElement element = utilityHelper.getWebElement(driver, elementName);
			String actualAttributeValue = element.getAttribute(attributeName);
			if (actualAttributeValue.contentEquals(expectedText)) {
				textVerified = true;
			} else {
				textVerified = false;
			}
			Assert.assertTrue(textVerified, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textVerified, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is displayed on the web page
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementDisplayed(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementVisible = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isDisplayed()) {
				elementVisible = true;
			} else {
				elementVisible = false;
			}
			Assert.assertTrue(elementVisible, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementVisible, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is not displayed on the web page
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementNotDisplayed(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementNotVisible = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isDisplayed()) {
				elementNotVisible = true;
			} else {
				elementNotVisible = false;
			}
			Assert.assertFalse(elementNotVisible, messageOnFailure);
		} catch (Exception e) {
			Assert.assertFalse(elementNotVisible, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is enabled
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementEnabled(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementEnabled = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isEnabled()) {
				elementEnabled = true;
			} else {
				elementEnabled = false;
			}
			Assert.assertTrue(elementEnabled, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementEnabled, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is not enabled
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementNotEnabled(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementNotEnabled = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isEnabled()) {
				elementNotEnabled = true;
			} else {
				elementNotEnabled = false;
			}
			Assert.assertFalse(elementNotEnabled, messageOnFailure);
		} catch (Exception e) {
			Assert.assertFalse(elementNotEnabled, messageOnFailure);
		}
	}

	/**
	 * Verify the element is selected
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementSelected(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementSelected = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isSelected()) {
				elementSelected = true;
			} else {
				elementSelected = false;
			}
			Assert.assertTrue(elementSelected, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementSelected, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is not selected
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementNotSelected(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementNotSelected = false;
		try {
			if (utilityHelper.getWebElement(driver, elementName).isSelected()) {
				elementNotSelected = true;
			} else {
				elementNotSelected = false;
			}
			Assert.assertFalse(elementNotSelected, messageOnFailure);
		} catch (Exception e) {
			Assert.assertFalse(elementNotSelected, messageOnFailure);
		}
	}

	/**
	 * Verify the element is visible
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementVisible(WebDriver driver, String elementName, String messageOnFailure) {
		boolean isElementVisible = false;
		try {
			int count = utilityHelper.waitForElements(driver, elementName).size();
			if (count != 0) {
				isElementVisible = true;
			} else {
				isElementVisible = false;
			}
			Assert.assertTrue(isElementVisible, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(isElementVisible, messageOnFailure);
		}
	}
	
	/**
	 * Verify the element is not visible
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 */
	public void verifyElementNotVisible(WebDriver driver, String elementName, String messageOnFailure) {
		boolean elementNotPresent = false;
		try {
			int count = utilityHelper.waitForElements(driver, elementName).size();
			if (count != 0) {
				elementNotPresent = true;
			} else {
				elementNotPresent = false;
			}
			Assert.assertFalse(elementNotPresent, messageOnFailure);
		} catch (Exception e) {
			Assert.assertFalse(elementNotPresent, messageOnFailure);
		}
	}
	
	/**
	 * Verify the actual and expected boolean values are equal
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyEquals(boolean actual, boolean expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertEquals((boolean) actual, (boolean) expected, (String) messageOnFailure);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Verify the actual and expected String values are equal
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyEquals(String actual, String expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertEquals(actual, expected, messageOnFailure);
		} catch (Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}
	
	/**
	 * Verify the URl contains the expected text
	 * 
	 * @param driver
	 * @param expectedText
	 * @param messageOnFailure
	 */
	public void verifyURLContainsText(WebDriver driver, String expectedText, String messageOnFailure) {
		boolean urlConatinsText = false;
		try {
			String actualURL = driver.getCurrentUrl();
			if (actualURL.contains(expectedText)) {
				urlConatinsText = true;
			} else {
				urlConatinsText = false;
			}
			Assert.assertTrue(urlConatinsText, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(urlConatinsText, messageOnFailure);
		}
	}
	
}
