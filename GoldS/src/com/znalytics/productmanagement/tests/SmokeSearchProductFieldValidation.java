// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.LoginPage;
import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.Utils;
import com.znalytics.framework.utility.WebPage;
import com.znalytics.framework.utility.WebTable;
import com.znalytics.productmanagement.pages.SearchProductPage;

/**
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 24, 2015
 * @purpose: This class is to execute all test cases for search validation
 *           product Priority=200
 * 
 */
@Test(groups = { "napower" })
public class SmokeSearchProductFieldValidation {
	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	LoginPage Login;
	SearchProductPage searchProduct;
	WebTable WebTab;
	String getCurrentApplicationURL;

	SmokeSearchProductFieldValidation() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		Login = PageFactory.initElements(browser, LoginPage.class);
		searchProduct = PageFactory.initElements(browser,
				SearchProductPage.class);
		WebTab = new WebTable(searchProduct.getWebTable(), browser);
	}

	@Test(priority = 101, groups = { "napower" })
	public void testNavigateToSearchProductPage() {
		navigate.navigateSearchProductPage();
		if (Element.isTextPresent(browser,
				searchProduct.getProductSearchLabel(), "Product Search")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to search page.");
		}
		ScreenShot.capture(true);
	}

	@Test(priority = 102, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyProductNameAllValidation() {
		searchProduct.clickReset();
		Element.isVisible(browser, searchProduct.getProductNameElement());
		searchProduct
				.setProductName(Utils.generateSpecialCharactersString(112));
		if (searchProduct.getProductNameElement().getText().length() > 100) {
			Assert.fail("Product name is excepting more then 100 character string.");
		} else {
			Logs.LOGGER.info("Verified Product code/name max length");
		}
		WebPage.waitForAngularRequestsToFinish(browser);
		searchProduct.getSelectSearchElement().click();
		WebPage.waitForAngularRequestsToFinish(browser);
		if (searchProduct
				.getNoFieldSelectedInformationMessageElement("No results were found")) {
			ScreenShot.capture(true);
			searchProduct.getInfoClose();
		} else {
			Assert.fail("No Error message displayed.");
		}
		WebPage.waitForAngularRequestsToFinish(browser);
		searchProduct.clickReset();
	}

	@Test(priority = 103, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyMarketFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectMarketsElement().click();
		searchProduct.getSelectMarketsElementTextBox().sendKeys(
				Utils.generateString(112));
		searchProduct.getSelectSearchElement().click();
		WebPage.waitForAngularRequestsToFinish(browser);
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 104, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyUtilityFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectUtilitiesElementTextBox().sendKeys(
				((Utils.generateString(20))));
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 105, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyZoneFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectZoneElementTextBox().sendKeys(
				((Utils.generateString(20))));
		Element.scrollElementIntoView(browser,
				searchProduct.getSelectSearchElement());
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 106, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifySegmentsFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSegmentElementTextBox().sendKeys(
				((Utils.generateString(20))));
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 107, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifySalesChannelFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSalesChannelElementTextBox().sendKeys(
				((Utils.generateString(20))));
		Element.scrollElementIntoView(browser,
				searchProduct.getSelectSearchElement());
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 108, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyContractSocurceFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectContractSourceElementTextBox().sendKeys(
				((Utils.generateString(20))));
		Element.scrollElementIntoView(browser,
				searchProduct.getSelectSearchElement());
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}

	@Test(priority = 109, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testVerifyPromoAllowedFeildWroungValue() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		Element.scrollElementIntoView(browser,
				searchProduct.getSelectSearchElement());
		searchProduct.getselectPromoAllowedElement().sendKeys(
				((Utils.generateString(20))));
		searchProduct.getSelectSearchElement().click();
		if (searchProduct
				.getNoFieldSelectedErrorMessageElement("Please provide at least one search criteria.")) {
			ScreenShot.capture(true);
		} else {
			Assert.fail("No Error message displayed.");
		}
		searchProduct.getErrorClose();
		searchProduct.clickReset();
	}
}