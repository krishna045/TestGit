// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.znalytics.framework.core.Constants;

/**
 * The Class WaitUtils.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class WaitUtils {

	/**
	 * Wait for the element to be present in the DOM, and displayed on the page.
	 * And returns the first WebElement using the given method.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return WebElement the first WebElement using the given method, or null
	 *         (if the timeout is reached)
	 */
	public static WebElement waitForElement(WebDriver driver, final By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			Assert.fail("Unable to find Element on page: " + by);
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for the element to be present in the DOM, and displayed on the page.
	 * And returns the first WebElement using the given method.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return WebElement the first WebElement using the given method, or null
	 *         (if the timeout is reached)
	 */
	public static WebElement waitForElement(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("Unable to find Element on page: " + element);
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for the List<WebElement> to be present in the DOM, regardless of
	 * being displayed or not. Returns all elements within the current page DOM.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return List<WebElement> all elements within the current page DOM, or
	 *         null (if the timeout is reached)
	 */
	public static List<WebElement> waitForElementList(WebDriver driver,
			final By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			Assert.fail("Unable to find Element(s) on page: " + by);
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for text to be present in element.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 */
	public static void waitForTextToBePresentInElement(WebDriver driver,
			final WebElement element, String text) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions.textToBePresentInElement(element,
					text));
		} catch (Exception e) {
			Assert.fail("Unable to find text in element: " + text);
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for text to be present in element.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 */
	public static void waitForTextToBePresentInElement(WebDriver driver,
			final By by, String text) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
					text));
		} catch (Exception e) {
			Assert.fail("Unable to find text in element: " + text);
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait element to be clickable.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return the web element
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver,
			WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Assert.fail("Element is not Clickable: " + element);
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait element to be clickable.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return the web element
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver,
			final By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			Assert.fail("Element is not Clickable: " + by);
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for frame.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public static void waitForFrame(WebDriver driver, final By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
		} catch (Exception e) {
			Assert.fail("Unable to find Frame: " + by);
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for frame.
	 *
	 * @param driver
	 *            the driver
	 * @param frameName
	 *            the frame name
	 */
	public static void waitForFrame(WebDriver driver, final String frameName) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (Exception e) {
			Assert.fail("Unable to find Frame: " + frameName);
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for in visibility.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public static void waitForInVisibility(WebDriver driver, final By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			Assert.fail("Failed waitForInVisibility : " + by);
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Wait for alert.
	 *
	 * @param driver
	 *            the driver
	 * @return the alert
	 */
	public Alert waitForAlert(WebDriver driver) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			Assert.fail("Unable to find Alert on page : " + driver.getTitle());
			return null;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}
}
