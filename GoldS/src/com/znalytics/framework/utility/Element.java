// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;

/**
 * The Class Element.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Element {

	/**
	 * Click.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public static void click(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(by)).click();
		} catch (Exception e) {
			Logs.LOGGER
					.warning("Element was stale immediately after waiting to be clickable."
							+ " Waiting for element to be clickable again.");
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.elementToBeClickable(by)).click();
			} catch (Exception ex) {
				Logs.LOGGER.severe("Unable to click.");
			}
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Click.
	 *
	 * @param driver
	 *            the driver
	 * @param el
	 *            the el
	 */
	public static void click(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(element))
					.click();
		} catch (Exception e) {
			Logs.LOGGER
					.warning("Element was stale immediately after waiting to be clickable."
							+ " Waiting for element to be clickable again.");
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.elementToBeClickable(element))
						.click();
			} catch (Exception ex) {
				Logs.LOGGER.severe("Unable to click.");
			}
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Click js.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public static void clickJS(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		Logs.LOGGER.info("click element using JS.");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	/**
	 * Click js.
	 *
	 * @param driver
	 *            the driver
	 * @param el
	 *            the el
	 */
	public static void clickJS(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		Logs.LOGGER.info("click element using JS.");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	/**
	 * Click using mouse.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public static void clickUsingMouse(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		Logs.LOGGER.info("click element using Mouse movement.");
		WebElement el = driver.findElement(by);
		Actions builder = new Actions(driver);
		builder.moveToElement(el).click(el);
		builder.perform();
	}

	/**
	 * Click using mouse.
	 *
	 * @param driver
	 *            the driver
	 * @param el
	 *            the el
	 */
	public static void clickUsingMouse(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		Logs.LOGGER.info("click element using Mouse movement.");
		Element.highlightElement(driver, element);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click(element);
		builder.perform();
	}

	/**
	 * Find element containing text.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 * @return the web element
	 */
	public static WebElement findElementContainingText(WebDriver driver, By by,
			String text) {
		WebPage.waitForAngularRequestsToFinish(driver);
		List<WebElement> elements = driver.findElements(by);
		for (WebElement element : elements) {
			try {
				if (element.getText().contains(text)) {
					Element.highlightElement(driver, element);
					Logs.LOGGER
							.info("SUCCESS: Found web element containing text "
									+ text);
					return element;
				}
			} catch (Exception e) {
				Logs.LOGGER.log(Level.SEVERE,
						"Exception while searching for web elements containing text: "
								+ text, e);
			}
		}
		return null;
	}

	/**
	 * Highlight element.
	 *
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 */
	public static void highlightElement(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "border: 2px solid red;");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "");
	}

	/**
	 * Checks if is clickable.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is clickable
	 */
	public static boolean isClickable(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		WebElement element;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			element = wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Check failed for element to clickable : " + by, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is clickable.
	 *
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 * @return true, if is clickable
	 */
	public static boolean isClickable(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Check failed for element to clickable : " + element, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	// Wait until an element is no longer attached to the DOM.
	// false is the element is still attached to the DOM, true otherwise.
	/**
	 * Checks if is element removed.
	 *
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 * @return the boolean
	 */
	public static Boolean isElementRemoved(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Timeout waiting for web element "
					+ "to become stale (removed from the DOM).", e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is in visible.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is in visible
	 */
	public static boolean isInVisible(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions
					.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Unable to check the Invisibility of element : " + by, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is selected.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is selected
	 */
	public static boolean isSelected(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.elementToBeSelected(by));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Check failed to find text : " + by,
					e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is selected.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is selected
	 */
	public static boolean isSelected(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Check failed to find text : "
					+ element, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is text present.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 * @return true, if is text present
	 */
	public static boolean isTextPresent(WebDriver driver, WebElement element,
			String text) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.textToBePresentInElement(
					element, text));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Check failed to find text : "
					+ element, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is text present.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 * @return true, if is text present
	 */
	public static boolean isTextPresent(WebDriver driver, By by, String text) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions
					.textToBePresentInElementLocated(by, text));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Check failed to find text : " + by,
					e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is visible.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is visible
	 */
	public static boolean isVisible(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		WebElement ele;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			ele = wait.until(ExpectedConditions.visibilityOf(element));
			Element.highlightElement(driver, ele);
			return ele.isDisplayed();
		} catch (Exception e) {
			Logs.LOGGER
					.log(Level.SEVERE,
							"Unable to check the visibility of element : "
									+ element, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Checks if is visible.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is visible
	 */
	public static boolean isVisible(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		WebElement element;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(by));
			Element.highlightElement(driver, element);
			return element.isDisplayed();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Unable to check the visibility of element : " + by, e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Scroll element into view.
	 *
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 */

	public static void scrollElementIntoView(WebDriver driver,
			WebElement element) {
		Element.highlightElement(driver, element);
		WebPage.waitForAngularRequestsToFinish(driver);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);

	}

	/**
	 * Checks if is element present instant.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if is element present instant
	 */
	public static boolean isElementPresentInstant(WebDriver driver, By by) {
		WebPage.waitForAngularRequestsToFinish(driver);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception otherException) {
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

}
