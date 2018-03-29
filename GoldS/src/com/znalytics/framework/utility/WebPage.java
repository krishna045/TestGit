// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;

/**
 * The Class WebPage.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 9, 2015
 */
public class WebPage {

	/**
	 * Gets the page source.
	 *
	 * @param driver
	 *            the driver
	 * @return the page source
	 */
	public static String getPageSource(WebDriver driver) {
		Logs.LOGGER.info("Get WebPage Source ...");
		return driver.getPageSource();
	}

	/**
	 * Does page source contains.
	 *
	 * @param driver
	 *            the driver
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean doesPageSourceContains(WebDriver driver, String text) {
		String source = driver.getPageSource();
		if (Utils.containsIgnoreCase(source, text))
			return true;
		else
			return false;
	}

	/**
	 * Click "Refresh" button on the current page.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void refresh(WebDriver driver) {
		Logs.LOGGER.info("Refresh");
		driver.navigate().refresh();
	}

	/**
	 * Wait for page loaded.
	 *
	 * @param browser
	 *            the browser
	 */
	public static void waitForPageToLoad(WebDriver browser) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(browser, Constants.DEFAULTTIME);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			Logs.LOGGER
					.log(Level.SEVERE,
							"Timeout waiting for Page Load Request to complete.",
							error);
		}
	}

	/**
	 * Wait for j query processing.
	 *
	 * @param driver
	 *            the driver
	 * @param timeOutInSeconds
	 *            the time out in seconds
	 * @return true, if successful
	 */
	public static boolean waitForJQueryProcessing(WebDriver driver,
			int timeOutInSeconds) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			new WebDriverWait(driver, timeOutInSeconds) {
			}.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driverObject) {
					return (Boolean) ((JavascriptExecutor) driverObject)
							.executeScript("return jQuery.active == 0");
				}
			});
			return (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return jQuery.active == 0");
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Timeout waiting for JQueryProcessing to complete.", e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for angular requests to finish.
	 *
	 * @param js
	 *            the js
	 */
	public static void waitForAngularRequestsToFinish(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeAsyncScript("var callback = arguments[arguments.length - 1];"
				+ "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
	}

}
