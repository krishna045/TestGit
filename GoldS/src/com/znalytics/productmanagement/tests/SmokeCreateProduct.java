// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.AngularDropDown;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.Utils;
import com.znalytics.framework.utility.WebPage;
import com.znalytics.productmanagement.pages.CreateProductPage;

/**
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 19, 2015
 * @purpose: contains all smoke test cases for creating product Priority = 201
 *           to 300
 * 
 */

@Test(groups = { "napower" })
public class SmokeCreateProduct {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	CreateProductPage createProduct;
	String getCurrentApplicationURL;
	// Product related values
	String pdCode;
	String pdName;
	String pdDescription;
	String pdTagLine;
	String pdEffictiveDate;
	String pdExpDate;
	boolean pdNoExpDate;
	String pdContractSource;
	String pdType;
	String pdServiceType;
	String pdPricingAssignMethod;
	String pdRolloverProduct;
	String pdTermsInMonths;
	boolean pdPromoAllowed;
	boolean pdEnrollmentOnly;
	boolean pdAutoRenewOnly;
	String pdGuaranteedSavings;
	String pdRenewables;
	String pdSalesChannels;
	String pdCustomerTypes;
	String pdCommissionType;
	String pdCommissionAmount;

	SmokeCreateProduct() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		createProduct = PageFactory.initElements(browser,
				CreateProductPage.class);
		// Product related values
		pdCode = Utils.generateString(10);
		pdName = Utils.generateString(15);
		pdDescription = Utils.generateString(30);
		pdTagLine = Utils.generateString(10);
		pdEffictiveDate = Utils.getToday("MM/dd/yyyy");
		pdExpDate = Utils.getNextDate(10);
		pdNoExpDate = true;
		pdContractSource = "Commercial and Industrial";
		pdType = "Fixed";
		pdServiceType = "Electric";
		pdPricingAssignMethod = "Custom";
		pdRolloverProduct = "";
		pdTermsInMonths = "";
		pdPromoAllowed = false;
		pdEnrollmentOnly = false;
		pdAutoRenewOnly = false;
		pdGuaranteedSavings = "";
		pdRenewables = "";
		pdSalesChannels = "";
		pdCustomerTypes = "";
		pdCommissionType = "";
		pdCommissionAmount = "";
	}

	@Test(priority = 201, groups = { "napower" })
	public void testNavigateToAddProductPage() {
		navigate.navigateAddProductPage();
		if (Element.isTextPresent(browser, createProduct.getAddProductLabel(),
				"Add Product - Step 1: Define Product Attributes")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to add product page.");
		}
		ScreenShot.capture(true);
	}

	@Test(priority = 202, groups = { "napower" }, dependsOnMethods = { "testNavigateToAddProductPage" })
	public void testAddProduct() {
		WebPage.waitForPageToLoad(browser);
		AngularDropDown DropDown = new AngularDropDown();

		createProduct.setProductCode(pdCode);
		createProduct.setProductName(pdName);
		createProduct.setProductDescription(pdDescription);
		createProduct.setProductTagLine(pdTagLine);
		createProduct.setEffectiveDate(pdEffictiveDate);
		if (pdNoExpDate) {
			createProduct.setNoExpDate();
		} else {
			createProduct.setExpirationDate(pdExpDate);
		}
		JavascriptExecutor executor = (JavascriptExecutor) browser;
		executor.executeScript("window.scrollTo(0,"
				+ (createProduct.getContractSourceSpanElement().getLocation().y - 70)
				+ ")");
		createProduct.getContractSourceSpanElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getContractSourceOptions(), 1);

		createProduct.getProductTypeElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getProductTypeOptions(), 1);

		createProduct.getServiceTypeElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getServiceTypeOptions(), 1);

		createProduct.getPricingAssignmentElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getPricingAssignmentOptions(), 1);

		createProduct.getRolloverProductElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getRolloverProductOptions(), 1);

		createProduct.getProductTermsInMonthsElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getProductTermsInMonthsOptions(), 1);

		createProduct.getPromoAllowedElement().click();

		createProduct.getSalesChannelsElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getSalesChannelsOptions(), 1);

		createProduct.getCustomerTypeElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getCustomerTypeOptions(), 1);

		createProduct.getNextButton().click();
		WebPage.waitForPageToLoad(browser);
		createProduct.getCancelButton().click();
		WebPage.waitForPageToLoad(browser);
		WebPage.waitForAngularRequestsToFinish(browser);
		createProduct.getCancelAddProductButton().click();
		if (createProduct.informationMessageBox("Product Add Cancelled")) {
			ScreenShot.capture(true);
			createProduct.infoMessageClose();
		} else {
			Assert.fail("No Error message displayed.");
		}
	}
}