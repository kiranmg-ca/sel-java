package com.kmg.base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.kmg.exception.AutomationException;
import com.kmg.utils.AutomationConstants;

public class AutomationBase {
	
	public WebDriver driver;
	
	
	public WebDriver startDriver(String browserName) throws AutomationException {
		switch (browserName.toLowerCase()) {
		case "chrome":
		case "headless-chrome":
			startChrome(browserName);
			break;
		case "firefox":
			startFirefox(browserName);
			break;
		default:
			System.out.println(AutomationConstants.CHECKBROWSER_MESSAGE);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	/**
	 * Launch the Chrome browser
	 * @param browserName
	 * @throws AutomationException
	 */
	private void startChrome(String browserName) throws AutomationException {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--remote-allow-origins=*");
			if (browserName.equalsIgnoreCase("headless-chrome")) {
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--headless=new");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Launch the Firefox browser
	 * @param browserName
	 * @throws AutomationException
	 */
	private void startFirefox(String browserName) throws AutomationException {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
			if (browserName.equalsIgnoreCase("headless-firefox")) {
				firefoxOptions.addArguments("--no-sandbox");
				firefoxOptions.addArguments("--disable-dev-shm-usage");
				firefoxOptions.addArguments("--headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}
	
	/**
	 * Get the Exception message, to pass the message whenever an exception is encountered.
	 * @return
	 */
	public String getExceptionMessage() {
		StringBuffer message = new StringBuffer();
		try {
			message.append("Exception in ");
			message.append(Thread.currentThread().getStackTrace()[2].getClassName());
			message.append(".");
			message.append(Thread.currentThread().getStackTrace()[2].getMethodName());
			message.append("()");
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return message.toString();
	}

}
