// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.znalytics.framework.core.Logs;

/**
 * The Class AlertUtils.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class AlertUtils {

	/** The alert. */
	private Alert alert;

	/** The driver. */
	private WebDriver driver;

	/**
	 * Instantiates a new alert utils.
	 *
	 * @param alert
	 *            the alert
	 * @param driver
	 *            the driver
	 */
	public AlertUtils(Alert alert, WebDriver driver) {
		this.alert = alert;
		this.driver = driver;
	}

	/**
	 * Sets the alert.
	 *
	 * @param alert
	 *            the new alert
	 */
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	/**
	 * Gets the alert.
	 *
	 * @return the alert
	 */
	public Alert getAlert() {
		return this.alert;
	}

	/**
	 * Accept alert.
	 */
	public void acceptAlert() {
		this.driver.switchTo().alert();
		alert.accept();
		Logs.LOGGER.info("Alert Accepted.");
	}

	/**
	 * Dismiss alert.
	 */
	public void dismissAlert() {
		this.driver.switchTo().alert();
		alert.dismiss();
		Logs.LOGGER.info("Alert dismissed.");
	}

	/**
	 * Gets the alert text.
	 *
	 * @return the alert text
	 */
	public String getAlertText() {
		this.driver.switchTo().alert();
		String alertText = alert.getText();
		Logs.LOGGER.info("Alert Text: " + alertText);
		return alertText;
	}

	/**
	 * Send keys alert.
	 *
	 * @param str
	 *            the str
	 */
	public void sendKeysAlert(String str) {
		this.driver.switchTo().alert();
		Logs.LOGGER.info("Sending string to alert : " + str);
		alert.sendKeys(str);
	}

}
