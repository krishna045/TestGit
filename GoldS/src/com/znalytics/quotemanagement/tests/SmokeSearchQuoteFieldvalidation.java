// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.AngularDropDown;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.WebTable;
import com.znalytics.quotemanagement.pages.SearchQuotePage;

/**
 * @author: Shilpi Malpani
 * @mail: smalpani@znalytics.com
 * @date: April 22, 2015
 * 
 *        Purpose of this class is to execute all test cases for validating
 *        fields on Sales Dashboard Priority=80-100
 */

@Test(groups = { "napower" })
public class SmokeSearchQuoteFieldvalidation {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	SearchQuotePage searchQuote;
	String getCurrentApplicationURL;
	WebTable webTab;
	WebTable webTabHeader;
	AngularDropDown DropDown;
	String url;

	SmokeSearchQuoteFieldvalidation() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		searchQuote = PageFactory.initElements(browser,
				com.znalytics.quotemanagement.pages.SearchQuotePage.class);
		webTab = new WebTable(searchQuote.getWebTable(), browser);
		webTabHeader = new WebTable(searchQuote.getWebTableHeader(),
				browser);
		DropDown = new AngularDropDown();

	}

	@Test(priority = 81, groups = { "napower" })
	public void testNavigateQouteRequestPage() {
		navigate.navigateSalesDashboard();
		if (Element.isTextPresent(browser, searchQuote.getSalesLabel(),
				"Sales Dashboard")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to qoute request page.");
		}
		ScreenShot.capture(true);
	}

	/**
	 * Method to verify label are present.
	 */
	@Test(priority = 82, groups = { "napower" })
	public void testVerifyLabels() {
		Element.isVisible(browser, searchQuote.getComanyName());
		Element.isVisible(browser, searchQuote.getContractNumber());
		Element.isVisible(browser, searchQuote.getMarket());
		Element.isVisible(browser, searchQuote.getReferenceNumber());
		Element.isVisible(browser, searchQuote.getLastUpdated());
		Element.isVisible(browser, searchQuote.getServiceRequestStatus());
		Element.isVisible(browser, searchQuote.getOfferExpirationDate());
		ScreenShot.capture(true);
	}
	
	/**
	 * Method to sort by ascending.
	 */
	@Test(priority = 83, groups = { "napower" })
	public void testSortByAscending() {
		Element.highlightElement(browser, searchQuote.getComanyName());
		Element.click(browser, searchQuote.getComanyName());
		
		Element.highlightElement(browser, searchQuote.getContractNumber());
		Element.click(browser, searchQuote.getContractNumber());

		Element.highlightElement(browser, searchQuote.getMarket());
		Element.click(browser, searchQuote.getMarket());

		Element.highlightElement(browser, searchQuote.getReferenceNumber());
		Element.click(browser, searchQuote.getReferenceNumber());

		Element.highlightElement(browser, searchQuote.getLastUpdated());
		Element.click(browser, searchQuote.getLastUpdated());

		Element.highlightElement(browser, searchQuote.getServiceRequestStatus());
		Element.click(browser, searchQuote.getServiceRequestStatus());

		Element.highlightElement(browser, searchQuote.getOfferExpirationDate());
		Element.click(browser, searchQuote.getOfferExpirationDate());
		ScreenShot.capture(true);

	}

	/**
	 * Method to sort by descending.
	 */
	@Test(priority = 84, groups = { "napower" })
	public void testSortByDescending() {
		Element.highlightElement(browser, searchQuote.getComanyName());
		Element.click(browser, searchQuote.getComanyName());
		Element.click(browser, searchQuote.getComanyName());
		
		Element.highlightElement(browser, searchQuote.getContractNumber());
		Element.click(browser, searchQuote.getContractNumber());
		Element.click(browser, searchQuote.getContractNumber());
		
		Element.highlightElement(browser, searchQuote.getMarket());
		Element.click(browser, searchQuote.getMarket());
		Element.click(browser, searchQuote.getMarket());
		
		Element.highlightElement(browser, searchQuote.getReferenceNumber());
		Element.click(browser, searchQuote.getReferenceNumber());
		Element.click(browser, searchQuote.getReferenceNumber());
		
		Element.highlightElement(browser, searchQuote.getLastUpdated());
		Element.click(browser, searchQuote.getLastUpdated());
		Element.click(browser, searchQuote.getLastUpdated());

		Element.highlightElement(browser, searchQuote.getServiceRequestStatus());
		Element.click(browser, searchQuote.getServiceRequestStatus());
		Element.click(browser, searchQuote.getServiceRequestStatus());

		Element.highlightElement(browser, searchQuote.getOfferExpirationDate());
		Element.click(browser, searchQuote.getOfferExpirationDate());
		Element.click(browser, searchQuote.getOfferExpirationDate());
		ScreenShot.capture(true);
	}

}
