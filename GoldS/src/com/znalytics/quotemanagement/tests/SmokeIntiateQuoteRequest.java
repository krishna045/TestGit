// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.Utils;
import com.znalytics.quotemanagement.pages.IntiateQuoteRequestPage;

/**
 * @author: Shilpi Malpani
 * @mail: smalpani@znalytics.com
 * @date: April 22, 2015
 * 
 *         Purpose of this class is to execute all test cases of
 *          Quote Request page Priority=50-60
 */

public class SmokeIntiateQuoteRequest {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	IntiateQuoteRequestPage intiateQuote;
	String getCurrentApplicationURL;
	String url, legalName, legalAddress1, legalAddress2, legalCity, legalState,
			legalZipCode, legalPhoneNo;

	SmokeIntiateQuoteRequest() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		intiateQuote = PageFactory.initElements(browser,
				IntiateQuoteRequestPage.class);
	}

	@Test(priority = 50, groups = { "napower" })
	public void testNavigateQouteRequestPage() {
		navigate.navigateQuoteRequestPage();
		if (Element.isTextPresent(browser, intiateQuote.getQuoteLabel(),
				"Quote Request for 'FName MName")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to qoute request page.");
		}
		ScreenShot.capture(true);
	}

	/**
	 * Method to enter values for legal address.
	 */
	@Test(priority = 51, groups = { "napower" })
	public void testLegalAddress() {
		legalName = "TestLegalName" + Utils.generateNumber(2);
		intiateQuote.setLegalAttention(legalName);
		legalAddress1 = "TestLegalAddress1" + Utils.generateNumber(2);
		intiateQuote.setLegalAddress1(legalAddress1);
		legalAddress2 = "TestLegalAddress2" + Utils.generateNumber(2);
		intiateQuote.setLegalAddress2(legalAddress2);
		legalCity = "TestLegalCity";
		intiateQuote.setLegalCity(legalCity);
		legalZipCode = Utils.generateNumber(9);
		intiateQuote.setLegalZipCode(legalZipCode);
		legalPhoneNo = Utils.generateNumber(10);
		intiateQuote.setLegalPhoneNo(legalPhoneNo);
		ScreenShot.capture(true);
	}

	/**
	 * Method to check billing address is same as legal address.
	 */
	@Test(priority = 52, groups = { "napower" })
	public void testBillingAddress() {
		intiateQuote.clicksameAsLegal().click();
	}

}
