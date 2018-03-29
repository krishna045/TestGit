// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.common.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.znalytics.common.pages.LoginPage;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;

/**
 * The Class Login.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * @purpose: Purpose of this class is to Login to application .
 */
@Test(groups = { "login" })
public class Login {
	WebDriver browser = Setup.getInstance().getDriver();
	LoginPage login = PageFactory.initElements(browser, LoginPage.class);
	boolean loginFailedFlag = false;

	@Parameters({ "appUrl", "username", "password" })
	@Test
	public void testLogin(String appUrl, String username, String password) {
		Logs.LOGGER.info("Open Url: " + appUrl);
		browser.get(appUrl);
		login.setUserName(username);
		login.setPassword(password);
		login.getSignIn().click();
		if (login.loginFailureErrorExist()) {
			loginFailedFlag = true;
			Assert.fail("Login failed.");
		}
		ScreenShot.capture(true);
	}

}
