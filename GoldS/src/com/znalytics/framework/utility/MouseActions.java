// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.logging.Level;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.znalytics.framework.core.Logs;

/**
 * The Class MouseActions.
 */
public class MouseActions {

	/**
	 * Right click.
	 *
	 * @param driver
	 * @param element
	 */
	public static void rightClick(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();
			Logs.LOGGER.info("Sucessfully Right clicked on the element.");
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in right click on element.",
					e);
		}
	}

	/**
	 * Mouse over.
	 *
	 * @param driver
	 * @param element
	 */
	public static void mouseOver(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Logs.LOGGER.info("Sucessfully moved focus on Element.");
	}

	/**
	 * Mouse over using js.
	 *
	 * @param driver
	 * @param element
	 */
	public static void mouseOverUsingJS(WebDriver driver, WebElement element) {
		WebPage.waitForAngularRequestsToFinish(driver);
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, element);
		Logs.LOGGER.info("Sucessfully moved focus on Element using JS.");
	}

}
