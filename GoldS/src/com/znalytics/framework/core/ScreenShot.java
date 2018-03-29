// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.File;
import java.net.InetAddress;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.znalytics.framework.utility.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreenShot.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class ScreenShot {

	/**
	 * Capture.
	 *
	 * @param status
	 *            the status
	 */
	public static void capture(Boolean status) {
		String screenShotLocation = null;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		String methodName = stacktrace[2].getMethodName();
		try {
			if (status) {
				if (DataSource.globalConfig.get("take.screenshot")
						.equalsIgnoreCase("yes")) {
					screenShotLocation = Constants.TESTOUTPUT
							+ "/screenshots/pass/";
					saveScreenShot(screenShotLocation, methodName);
				}
			} else {
				screenShotLocation = Constants.TESTOUTPUT
						+ "/screenshots/fail/";
				saveScreenShot(screenShotLocation, methodName);
			}

		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in taking the screenshot.",
					e);
		}
	}

	/**
	 * Capture.
	 *
	 * @param status
	 *            the status
	 * @param name
	 *            the name
	 */
	public static void capture(Boolean status, String name) {
		String screenShotLocation = null;
		try {
			if (status) {
				if (DataSource.globalConfig.get("take.screenshot")
						.equalsIgnoreCase("yes")) {
					screenShotLocation = Constants.TESTOUTPUT
							+ "/screenshots/pass/";
					saveScreenShot(screenShotLocation, name);
				}
			} else {
				screenShotLocation = Constants.TESTOUTPUT
						+ "/screenshots/fail/";
				saveScreenShot(screenShotLocation, name);
			}

		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in taking the screenshot.",
					e);
		}
	}

	// Method which takes the screenshot and save the files at provided path.
	/**
	 * Save screen shot.
	 *
	 * @param dirLocation
	 *            the dir location
	 * @param fileName
	 *            the file name
	 */
	private static void saveScreenShot(String dirLocation, String fileName) {
		try {
			File dir = new File(dirLocation);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					Logs.LOGGER
							.info("Screenshot folder path created successfully.");
				}
			}
			WebDriver browser = Setup.getInstance().getDriver();
			browser.manage().window().maximize();
			SleepCommand.sleepOneSecond();
			File pngFile = new File(dirLocation + "/" + fileName + "_"
					+ Utils.getUniqueName() + ".png");
			File screenshot = ((TakesScreenshot) browser)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, pngFile);
			if (Constants.RUN_MODE == 2) {
				String prefix = "\\\\"
						+ InetAddress.getLocalHost().getHostName() + "\\";
				String screenFileName = pngFile.getCanonicalPath().toString()
						.replace(":", "");
				Reporter.setEscapeHtml(false);
				Reporter.log("Saved <a href=" + prefix + screenFileName
						+ ">Screenshot</a>");
			} else {
				Reporter.setEscapeHtml(false);
				Reporter.log("Saved <a href=" + pngFile.getCanonicalPath()
						+ ">Screenshot</a>");
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in saving the screenshot.",
					e);
		}
	}

}
