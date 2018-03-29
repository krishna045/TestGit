// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.znalytics.framework.core.Logs;

/**
 * The Class DragAndDrop.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 25, 2015
 */
public class DragAndDrop {

	/** The source element. */
	private WebElement sourceElement;

	/** The destination element. */
	private WebElement destinationElement;

	/** The driver. */
	private WebDriver driver;

	/**
	 * Instantiates a new drag and drop.
	 *
	 * @param sourceElement
	 *            the source element
	 * @param destinationElement
	 *            the destination element
	 * @param driver
	 *            the driver
	 */
	public DragAndDrop(WebElement sourceElement, WebElement destinationElement,
			WebDriver driver) {
		this.sourceElement = sourceElement;
		this.destinationElement = destinationElement;
		this.driver = driver;
	}

	/**
	 * Gets the source element.
	 *
	 * @return the source element
	 */
	public WebElement getSourceElement() {
		return sourceElement;
	}

	/**
	 * Sets the source element.
	 *
	 * @param sourceElement
	 *            the new source element
	 */
	public void setSourceElement(WebElement sourceElement) {
		this.sourceElement = sourceElement;
	}

	/**
	 * Gets the destination element.
	 *
	 * @return the destination element
	 */
	public WebElement getDestinationElement() {
		return destinationElement;
	}

	/**
	 * Sets the destination element.
	 *
	 * @param destinationElement
	 *            the new destination element
	 */
	public void setDestinationElement(WebElement destinationElement) {
		this.destinationElement = destinationElement;
	}

	/**
	 * Drag and drop element.
	 *
	 * @return true, if successful
	 */

	public boolean dragAndDropElement() {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			if (Element.isVisible(driver, sourceElement)
					&& Element.isVisible(driver, destinationElement)) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build()
						.perform();
				return true;
			} else {
				Logs.LOGGER
						.severe("Either Source or Destination element is not visible on page.");
				return false;
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Problem in drag and drop operation.", e);
			Logs.LOGGER.info("Trying drag and drop one more time...");
			try {
				WebPage.waitForAngularRequestsToFinish(driver);
				if (Element.isVisible(driver, sourceElement)
						&& Element.isVisible(driver, destinationElement)) {
					Actions action = new Actions(driver);
					action.dragAndDrop(sourceElement, destinationElement)
							.build().perform();
					return true;
				} else {
					Logs.LOGGER
							.severe("Either Source or Destination element is not visible on page.");
					return false;
				}
			} catch (Exception ex) {
				Logs.LOGGER.log(Level.SEVERE,
						"Problem in drag and drop operation second time.", e);
			}
			return false;
		}
	}

	/**
	 * Drag and drop element using js.
	 *
	 * @return true, if successful
	 */
	public boolean dragAndDropElementUsingJS() {
		WebPage.waitForAngularRequestsToFinish(driver);
		if (Element.isVisible(driver, sourceElement)
				&& Element.isVisible(driver, destinationElement)) {
			String xto = Integer.toString(destinationElement.getLocation().x);
			String yto = Integer.toString(destinationElement.getLocation().y);
			((JavascriptExecutor) driver)
					.executeScript(
							"function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
									+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
							sourceElement, xto, yto);
			return true;
		} else {
			return false;
		}
	}

}
