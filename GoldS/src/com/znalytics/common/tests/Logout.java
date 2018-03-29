// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.common.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.LogoutPage;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;

/**
 * The Class Logout.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * @purpose: Purpose of this class is to Logout from application .
 */
@Test(groups = { "logout" })
public class Logout {
	WebDriver browser = Setup.getInstance().getDriver();
	LogoutPage logout = PageFactory.initElements(browser, LogoutPage.class);

	@Test
	public void testLogout() {
		if (!logout.logoutFromApplication()) {
			Assert.fail("Unable to logout from application.");
		} else {
			Logs.LOGGER.info("Successfully logged out from application.");
		}
		ScreenShot.capture(true);
	}
}
