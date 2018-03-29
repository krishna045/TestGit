// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.SleepCommand;

/**
 * The Class Windows.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Windows {

	// To close all the other windows except the main window.
	/**
	 * Close all other windows.
	 *
	 * @param driver
	 *            the driver
	 * @return true, if successful
	 */
	public static void closeAllOtherWindows(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		Logs.LOGGER.info("Total Windows Found" + windows.size());
		try {
			for (String window : windows) {
				if (!window.equals(Constants.PARENTWINDOW)) {
					driver.switchTo().window(window);
					Logs.LOGGER.info("Closing Window : " + window);
					driver.close();
				}
			}
		} catch (Exception e) {
			Logs.LOGGER.warning("Problem in closing the browser." + e);
		} finally {
			Logs.LOGGER.info("Giving Control back to Parent Window: "
					+ Constants.PARENTWINDOW);
			driver.switchTo().window(Constants.PARENTWINDOW);
		}
	}

	/**
	 * Get active window handle.
	 *
	 * @param driver
	 *            the driver
	 * @return Active window handle.
	 */
	public static String getWindowHandle(WebDriver driver) {
		Logs.LOGGER.info("Get Window Handle");
		return driver.getWindowHandle();
	}

	/**
	 * Get windows handles.
	 *
	 * @param driver
	 *            the driver
	 * @return Window handles.
	 */
	public static Set<String> getWindowHandles(WebDriver driver) {
		Logs.LOGGER.info("Get Windows Handles");
		return driver.getWindowHandles();
	}

	/**
	 * Get window title.
	 *
	 * @param driver
	 *            the driver
	 * @return Window title.
	 */
	public static String getWindowTitle(WebDriver driver) {
		String title = driver.getTitle();
		Logs.LOGGER.info("Window Title: " + title);
		return title;
	}

	/**
	 * Click "go Back" button on the current page.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void goBack(WebDriver driver) {
		Logs.LOGGER.info("Browser Action - Go Back");
		driver.navigate().back();
	}

	/**
	 * Click "go Forward" button on the current page.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void goForward(WebDriver driver) {
		Logs.LOGGER.info("Browser Action - Go Forward");
		driver.navigate().forward();
	}

	/**
	 * Scroll page down by JS.
	 *
	 * @param driver
	 *            the driver
	 * @param iHeight
	 *            the i height
	 */
	public static void scrollPageDown(WebDriver driver, int iHeight) {
		Logs.LOGGER.info("Scroll Page To down");
		Utils.executeJS(driver, "window.scrollTo(0, " + iHeight + ");");
	}

	/**
	 * Scroll page to end by JS.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void scrollPageToEnd(WebDriver driver) {
		Logs.LOGGER.info("Scroll Page To End");
		Utils.executeJS(driver,
				"window.scrollTo(0, document.body.clientHeight )");
	}

	/**
	 * Scroll page to top by JS.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void scrollPageToTop(WebDriver driver) {
		Logs.LOGGER.info("Scroll Page To Top");
		Utils.executeJS(driver, "window.scrollTo(0, 0);");
	}

	/**
	 * Scroll page up by JS.
	 *
	 * @param driver
	 *            the driver
	 * @param iHeight
	 *            the i height
	 */
	public static void scrollPageUp(WebDriver driver, int iHeight) {
		Logs.LOGGER.info("Scroll Page To Top");
		Utils.executeJS(driver, "window.scrollTo(0, " + iHeight + ");");
	}

	/**
	 * Sets the window size.
	 *
	 * @param driver
	 *            the driver
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public static void setWindowSize(WebDriver driver, int width, int height) {
		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		Logs.LOGGER.info("Window Dimension Changed : "
				+ driver.manage().window().getSize());
	}

	/**
	 * Checks if is title contain.
	 *
	 * @param driver
	 *            the driver
	 * @param title
	 *            the title
	 * @return true, if is title contain
	 */
	public static boolean isTitleContain(WebDriver driver, String title) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver,
					Constants.DEFAULTTIME);
			return wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			Logs.LOGGER.severe("Problem in getting window title : " + e);
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
		}
	}

	/**
	 * Switch to the default window.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void switchToDefault(WebDriver driver) {
		Logs.LOGGER.info("Switch To Default");
		driver.switchTo().defaultContent();
	}

	/**
	 * Switch window.
	 *
	 * @param driver
	 *            the driver
	 * @return true, if successful
	 */
	public static boolean switchWindow(WebDriver driver) {
		Set<String> windows = driver.getWindowHandles();
		Logs.LOGGER.info("Total # of Windows: " + windows.size());
		if (windows.size() == 2) {
			for (String window : windows) {
				if (!window.equalsIgnoreCase(Constants.PARENTWINDOW)) {
					driver.switchTo().window(window);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Switch window id.
	 *
	 * @param driver
	 *            the driver
	 * @param ID
	 *            the id
	 * @return true, if successful
	 */
	public static boolean switchWindowByID(WebDriver driver, String ID) {
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty() && availableWindows.contains(ID)) {
			Logs.LOGGER.info(String
					.format("Switch To Window Using ID '%s'", ID));
			driver.switchTo().window(ID);
			return true;
		} else {
			Logs.LOGGER.info("Not able to find window with ID: " + ID);
			return false;
		}
	}

	/**
	 * Switch window title.
	 *
	 * @param driver
	 *            the driver
	 * @param title
	 *            the title
	 * @return true, if successful
	 */
	public static boolean switchWindowByTitle(WebDriver driver, String title) {
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty() && availableWindows.contains(title)) {
			Logs.LOGGER.info(String.format("Switch To Window Using Title '%s'",
					title));
			driver.switchTo().window(title);
			return true;
		} else {
			Logs.LOGGER.info("Not able to find window with title: " + title);
			return false;
		}
	}

	/**
	 * Wait for window.
	 *
	 * @param driver
	 *            the driver
	 * @param waitDuration
	 *            the wait duration
	 */
	public static void waitForWindow(WebDriver driver, int waitDuration) {
		int timer = 0;
		boolean found = false;
		try {
			while (timer < waitDuration) {
				if (driver.getWindowHandles().size() > 1) {
					found = true;
					break;
				} else {
					Logs.LOGGER.info("Waiting for new window to appear.");
					SleepCommand.sleepOneSecond();
				}
				timer++;
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		if (!found) {
			Assert.fail("No new Window found.");
		}
	}

	/**
	 * Wait windows count.
	 *
	 * @param driver
	 *            the driver
	 * @param numberOfWindows
	 *            the number of windows
	 * @param waitDuration
	 *            the wait duration
	 * @return true, if successful
	 */
	public boolean waitWindowsCount(WebDriver driver,
			final int numberOfWindows, int waitDuration) {
		Logs.LOGGER.info("Wait for Open Windows Count");
		new WebDriverWait(driver, waitDuration) {
		}.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver wDriver) {
				return (wDriver.getWindowHandles().size() == numberOfWindows);
			}
		});
		return false;
	}
}
