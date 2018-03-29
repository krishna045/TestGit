// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;

import com.znalytics.framework.core.Logs;

// TODO: Auto-generated Javadoc
/**
 * The Class Process.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Process {

	/**
	 * Kill orphan drivers.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void killOrphanDrivers(WebDriver driver) {
		try {
			if (Utils.getCurrentBrowserName(driver).equals("IE")) {
				WindowsUtils.killByName("IEDriverServer.exe");
			} else if (Utils.getCurrentBrowserName(driver).equals("Chrome")) {
				WindowsUtils.killByName("chromedriver.exe");
			} else {
				Logs.LOGGER.info("No process cleanup required.");
			}
		} catch (Exception e) {
		}
	}
}
