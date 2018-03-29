// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.Listener;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.znalytics.framework.core.Logs;
import com.znalytics.framework.utility.Element;

/**
 * The listener interface for receiving driverEvent events. The class that is
 * interested in processing a driverEvent event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addDriverEventListener<code> method. When
 * the driverEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 10, 2015
 */
public class DriverEventListener implements WebDriverEventListener {

	/** The last find by. */
	private By lastFindBy;

	/** The original value. */
	private String originalValue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterChangeValueOf
	 * (org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver arg1) {
		Logs.LOGGER.info("WebDriver changing value in element found "
				+ lastFindBy + " from '" + originalValue + "' to '"
				+ element.getText() + "'");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterClickOn
	 * (org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterFindBy
	 * (org.openqa.selenium.By, org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateBack
	 * (org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * afterNavigateForward(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterNavigateTo
	 * (java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#afterScript
	 * (java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeChangeValueOf
	 * (org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver arg1) {
		originalValue = element.getText();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeClickOn
	 * (org.openqa.selenium.WebElement, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		Element.highlightElement(arg1, arg0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeFindBy
	 * (org.openqa.selenium.By, org.openqa.selenium.WebElement,
	 * org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeFindBy(By by, WebElement arg1, WebDriver arg2) {
		lastFindBy = by;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeNavigateBack
	 * (org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.events.WebDriverEventListener#
	 * beforeNavigateForward(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeNavigateTo
	 * (java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#beforeScript
	 * (java.lang.String, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.support.events.WebDriverEventListener#onException
	 * (java.lang.Throwable, org.openqa.selenium.WebDriver)
	 */
	@Override
	public void onException(Throwable error, WebDriver arg1) {
		if (error.getClass().equals(NoSuchElementException.class)) {
			Logs.LOGGER.severe("ERROR: Unable to find Element: " + lastFindBy);
		} else {
			Logs.LOGGER.severe("WebDriver error:" + error);
		}

	}

}
