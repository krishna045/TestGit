/**
 * Copyright - Znalytics (http://www.Znalytics.com/)
 */
package com.znalytics.quotemanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.znalytics.common.pages.LoginPage;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;


/**
 * @author: Shilpi Malpani
 * @mail: smalpani@znalytics.com
 * @date: 5/06/2015
 * @purpose: Contains all common functions related to quote request module.
 *
 */
public class QuoteUtility {
	public static void loginToApplication(WebDriver browser) {
		LoginPage login = PageFactory.initElements(browser, LoginPage.class);
		String url = QuoteManagement.app.get("test.url");
		Logs.LOGGER.info("Open Url: " + QuoteManagement.app.get("test.url"));
		browser.get(url);
		login.setUserName(QuoteManagement.app.get("username"));
		login.setPassword(QuoteManagement.app.get("password"));
		login.getSignIn().click();
		ScreenShot.capture(true);
	}
}
