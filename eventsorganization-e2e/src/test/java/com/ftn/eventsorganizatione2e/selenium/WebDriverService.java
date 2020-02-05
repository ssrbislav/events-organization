package com.ftn.eventsorganizatione2e.selenium;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverService {
	private static WebDriver driver;

	// Current users directory.
	private static final String USER_DIR = System.getProperty("user.dir");

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver startDriver(String browser) {
		Preconditions.checkNotNull(browser, "Target browser parameter is null");
		if (WebDriverService.driver != null) {
			throw new AssertionError("Something is wrong... WebDriver instance is tried to be re-initialized");
		}
		if (browser.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
//		driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(120L, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static void stopDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
