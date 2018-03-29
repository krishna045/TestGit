/**
 * Copyright - Znalytics (http://www.Znalytics.com/)
 */
package com.znalytics.productmanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.znalytics.common.pages.LoginPage;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;

/**
 * @author: abhinay.srikant
 * @mail: asrikant@znalytics.com
 * @date: 4/10/2015
 * @purpose: Contains all common functions related to product management module.
 *
 */
public class ProductUtility {

	public static void loginToApplication(WebDriver browser) {
		LoginPage login = PageFactory.initElements(browser, LoginPage.class);
		String url = ProductManagement.app.get("test.url");
		Logs.LOGGER.info("Open Url: " + ProductManagement.app.get("test.url"));
		browser.get(url);
		login.setUserName(ProductManagement.app.get("username"));
		login.setPassword(ProductManagement.app.get("password"));
		login.getSignIn().click();
		ScreenShot.capture(true);
	}

}