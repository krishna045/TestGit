// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.znalytics.framework.core.Logs;

/**
 * The Class Cookies.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Cookies {

	/** The driver. */
	private WebDriver driver;

	/**
	 * Instantiates a new cookies.
	 *
	 * @param driver
	 *            the driver
	 */
	public Cookies(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the all cookies.
	 *
	 * @return the all cookies
	 */
	public Set<Cookie> getAllCookies() {
		return driver.manage().getCookies();
	}

	/**
	 * Adds the cookies.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param domainName
	 *            the domain name
	 */
	public void addCookies(String name, String value, String domainName) {
		Cookie cookie = new Cookie(name + "=" + value, "domain=." + domainName);
		driver.manage().addCookie(cookie);
	}

	/**
	 * Remove all cookies from browser.
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
		Logs.LOGGER.info("All cookies Removed.");
	}

	/**
	 * Delete the named cookie from the current domain. This is equivalent to
	 * setting the named cookie's expiry date to some time in the past.
	 *
	 * @param cookieName
	 *            The name of the cookie to delete
	 * @return true, if successful
	 */
	public boolean deleteCookieByName(String cookieName) {
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(cookieName)) {
				driver.manage().deleteCookieNamed(cookieName);
				Logs.LOGGER.info(String
						.format("Removed cookie: %s", cookieName));
				return true;
			}
		}
		return false;
	}

	/**
	 * Log cookie.
	 *
	 * @param cookieName
	 *            The name of the cookie to log.
	 * @return true, if is cookie present
	 */
	public boolean isCookiePresent(String cookieName) {
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(cookieName)) {
				Logs.LOGGER
						.info(String.format("Cookie Found : %s", cookieName));
				return true;
			}
		}
		return false;
	}
}
