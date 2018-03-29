// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
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
 * @author: Abhinay Srikant
 * @mail: asrikant@znalytics.com
 * @date: Mar 30, 2015
 * @purpose This class is to execute all test cases for Adding product.
 *          Priority= 301 to 400
 */

@Test(groups = { "napower" })
public class SmokeCreateProductFieldValidation {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	CreateProductPage createProduct;
	String getCurrentApplicationURL;

	SmokeCreateProductFieldValidation() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		createProduct = PageFactory.initElements(browser,
				CreateProductPage.class);
	}

	@Test(priority = 301, groups = { "napower" })
	public void testVerifyAllElementExist() {
		navigate.navigateAddProductPage();
		if (Element.isTextPresent(browser, createProduct.getAddProductLabel(),
				"Add Product - Step 1: Define Product Attributes")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to add product page.");
		}
		getCurrentApplicationURL = browser.getCurrentUrl();
		ScreenShot.capture(true);
	}

	@Test(priority = 302, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyProductCodeField() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		boolean chkMaxLengthFlag = false, chkFieldTextCounterFlag = false, chkBlankValues = false;
		WebElement getProductCodeElement = createProduct.getProductCode();

		// Check 1: Max length 50 char.
		createProduct.setProductCode(Utils.generateString(51));
		if (getProductCodeElement.getText().length() > 50) {
			Reporter.log("Max length 50 Character: Fail.");
		} else {
			chkMaxLengthFlag = true;
			Reporter.log("Max length 50 Character: Pass.");
			ScreenShot.capture(true, "testVerifyProductCodeMaxLength");
		}

		// Check 2: Counter.
		getProductCodeElement.clear();
		String counterBefore = createProduct
				.getCounterText(getProductCodeElement);
		createProduct.setProductCode("Counter");
		String counterAfter = createProduct
				.getCounterText(getProductCodeElement);
		if (counterBefore.equalsIgnoreCase(counterAfter)) {
			Reporter.log("Field text counter : Fail.");
		} else {
			chkFieldTextCounterFlag = true;
			Reporter.log("Field text counter : Pass.");
		}

		// Check 3: Blank Value
		getProductCodeElement.clear();
		createProduct.getNextButton().click();
		if (createProduct.hasError(getProductCodeElement)) {
			ScreenShot.capture(true, "testVerifyProductCodeError");
			chkBlankValues = true;
			Reporter.log("No blank value: Pass.");
		} else {
			Reporter.log("No blank value: Fail.");
		}

		if (chkMaxLengthFlag && chkFieldTextCounterFlag && chkBlankValues) {
			Reporter.log("Status: Pass.");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}

	}

	@Test(priority = 303, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyProductNameField() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		boolean chkMaxLengthFlag = false, chkFieldTextCounterFlag = false, chkBlankValues = false;
		WebElement getProductNameElement = createProduct.getProductName();

		// Check 1: Max length 100 char.
		createProduct.setProductName(Utils.generateString(101));
		if (getProductNameElement.getText().length() > 100) {
			Reporter.log("Max length 100 Character: Fail.");
		} else {
			chkMaxLengthFlag = true;
			Reporter.log("Max length 100 Character: Pass.");
			ScreenShot.capture(true, "testVerifyProductNameMaxLength");
		}

		// Check 2: Counter.
		getProductNameElement.clear();
		String counterBefore = createProduct
				.getCounterText(getProductNameElement);
		createProduct.setProductName("Counter");
		String counterAfter = createProduct
				.getCounterText(getProductNameElement);
		if (counterBefore.equalsIgnoreCase(counterAfter)) {
			Reporter.log("Field text counter : Fail.");
		} else {
			chkFieldTextCounterFlag = true;
			Reporter.log("Field text counter : Pass.");
		}

		// Check 3: Blank Value
		getProductNameElement.clear();
		createProduct.getNextButton().click();
		if (createProduct.hasError(getProductNameElement)) {
			ScreenShot.capture(true, "testVerifyProductCodeError");
			chkBlankValues = true;
			Reporter.log("No blank value: Pass.");
		} else {
			Reporter.log("No blank value: Fail.");
		}

		if (chkMaxLengthFlag && chkFieldTextCounterFlag && chkBlankValues) {
			Reporter.log("Status: Pass.");
		} else {
			Reporter.log("Status: Fail.");
			Assert.fail("One or more validation on product name field"
					+ " failed. Check left side for more details.");
		}
	}

	@Test(priority = 304, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyProductDescription() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		boolean chkMaxLengthFlag = false, chkFieldTextCounterFlag = false;
		WebElement productDescriptionElement = createProduct
				.getProductDescription();

		// Check 1: Max length 256 char.
		Element.isVisible(browser, productDescriptionElement);
		createProduct.setProductDescription(Utils.generateString(257));
		if (productDescriptionElement.getText().length() > 256) {
			Reporter.log("Max length 256 Character: Fail.");
		} else {
			chkMaxLengthFlag = true;
			Reporter.log("Max length 256 Character: Pass.");
			ScreenShot.capture(true, "testVerifyProductDescriptionMaxLength");
		}

		// Check 2: Counter.
		productDescriptionElement.clear();
		String counterBefore = createProduct
				.getCounterText(productDescriptionElement);
		createProduct.setProductDescription("Counter");
		String counterAfter = createProduct
				.getCounterText(productDescriptionElement);
		productDescriptionElement.clear();
		if (counterBefore.equalsIgnoreCase(counterAfter)) {
			Reporter.log("Field text counter : Fail.");
		} else {
			chkFieldTextCounterFlag = true;
			Reporter.log("Field text counter : Pass.");
		}

		if (chkMaxLengthFlag && chkFieldTextCounterFlag) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product description"
					+ " field failed. Check left side for more details.");
		}
	}

	@Test(priority = 305, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyProductTagLine() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		boolean chkMaxLengthFlag = false, chkFieldTextCounterFlag = false;
		WebElement productTagLineElement = createProduct.getProductTagLine();

		// Check 1: Max length 100 char.
		Element.isVisible(browser, productTagLineElement);
		createProduct.setProductTagLine(Utils.generateString(101));
		if (productTagLineElement.getText().length() > 100) {
			Reporter.log("Max length 100 Char: Fail.");
			ScreenShot.capture(true, "testVerifyProductDescriptionMaxLength");
		} else {
			chkMaxLengthFlag = true;
			Reporter.log("Max length 100 Char: Pass.");
		}

		// Check 2: Counter.
		productTagLineElement.clear();
		String counterBefore = createProduct
				.getCounterText(productTagLineElement);
		createProduct.setProductTagLine("Counter");
		String counterAfter = createProduct
				.getCounterText(productTagLineElement);
		productTagLineElement.clear();
		if (counterBefore.equalsIgnoreCase(counterAfter)) {
			Reporter.log("Field text counter : Fail.");
		} else {
			chkFieldTextCounterFlag = true;
			Reporter.log("Field text counter : Pass.");
		}

		if (chkMaxLengthFlag && chkFieldTextCounterFlag) {
			Reporter.log("Status: Pass.");
		} else {
			Reporter.log("Status: Fail.");
			Assert.fail("One or more validation on product tag"
					+ " line field failed. Check left side for more details.");
		}
	}

	@Test(priority = 306, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyContractSource() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement contractSource = createProduct.getContractSourceElement();
		contractSource.click();
		WebElement contractSourceSingleSelect = createProduct
				.getInputFieldForSingleSelect(contractSource);
		contractSourceSingleSelect.sendKeys("test");
		createProduct.getProductTypeElement().click();
		if (contractSourceSingleSelect.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text "
					+ "apart from listed in drop down.");
		}
	}

	@Test(priority = 307, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyProductType() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement productTypeElement = createProduct.getProductTypeElement();
		productTypeElement.click();
		WebElement productTypeSingleSelectElement = createProduct
				.getInputFieldForSingleSelect(productTypeElement);
		productTypeSingleSelectElement.sendKeys("test");
		createProduct.getContractSourceElement().click();
		if (productTypeSingleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 308, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyServiceType() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement serviceTypeElement = createProduct.getServiceTypeElement();
		serviceTypeElement.click();
		WebElement serviceTypeSingleSelectElement = createProduct
				.getInputFieldForSingleSelect(serviceTypeElement);
		serviceTypeSingleSelectElement.sendKeys("test");
		createProduct.getContractSourceElement().click();
		if (serviceTypeSingleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 309, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyPricingAssignMethod() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement pricingAssignmentElement = createProduct
				.getPricingAssignmentElement();
		pricingAssignmentElement.click();
		WebElement pricingAssignmentSingleSelectElement = createProduct
				.getInputFieldForSingleSelect(pricingAssignmentElement);
		pricingAssignmentSingleSelectElement.sendKeys("test");
		createProduct.getContractSourceElement().click();
		if (pricingAssignmentSingleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 310, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyRolloverProdutc() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement rolloverProductElement = createProduct
				.getRolloverProductElement();
		rolloverProductElement.click();
		WebElement rolloverProductSingleSelectElement = createProduct
				.getInputFieldForSingleSelect(rolloverProductElement);
		rolloverProductSingleSelectElement.sendKeys("test");
		createProduct.getContractSourceElement().click();
		if (rolloverProductSingleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 311, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyTermsInMonth() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement productTermsInMonthsElement = createProduct
				.getProductTermsInMonthsElement();
		productTermsInMonthsElement.click();
		WebElement productTermsInMonthsSingleSelectElement = createProduct
				.getInputFieldForSingleSelect(productTermsInMonthsElement);
		productTermsInMonthsSingleSelectElement.sendKeys("test");
		createProduct.getRolloverProductElement().click();
		if (productTermsInMonthsSingleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 312, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifySalesChannel() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement salesChannelsElement = createProduct
				.getSalesChannelsElement();
		salesChannelsElement.click();
		WebElement salesChannelsMultipleSelectElement = createProduct
				.getInputFieldForMultipleSelect(salesChannelsElement);
		salesChannelsMultipleSelectElement.sendKeys("test");
		createProduct.getProductTypeElement().click();
		if (salesChannelsMultipleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass.");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 313, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyCustomerTypes() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		WebElement customerTypeElement = createProduct.getCustomerTypeElement();
		customerTypeElement.click();
		WebElement customerTypeMultipleSelectElement = createProduct
				.getInputFieldForMultipleSelect(customerTypeElement);
		customerTypeMultipleSelectElement.sendKeys("test");
		createProduct.getProductTypeElement().click();
		if (customerTypeMultipleSelectElement.getText().isEmpty()) {
			Reporter.log("Accept only dropdown value : Pass");
		} else {
			Assert.fail("Field should not accept any text"
					+ " apart from listed in drop down.");
		}
	}

	@Test(priority = 314, groups = { "napower" }, dependsOnMethods = { "testVerifyAllElementExist" })
	public void testVerifyCommissionAmount() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		AngularDropDown DropDown = new AngularDropDown();

		createProduct.getProductCode().sendKeys("Test");

		createProduct.getProductName().sendKeys("Test");

		createProduct.setNoExpDate();

		createProduct.getContractSourceElement().click();
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

		createProduct.getCustomerTypeElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getCustomerTypeOptions(), 1);

		createProduct.getCommissionTypeElement().click();
		DropDown.selectAngularDropdownOptionByIndex(
				createProduct.getCommissionTypeOptions(), 1);
		WebPage.waitForAngularRequestsToFinish(browser);
		WebElement commisionAmountElement = createProduct
				.getCommissionAmountElement();
		commisionAmountElement.click();
		WebPage.waitForAngularRequestsToFinish(browser);
		createProduct.getCommissionAmountEditBox().sendKeys("123456");
		createProduct.getCustomerTypeElement().click();
		createProduct.getNextButton().click();
		WebPage.waitForAngularRequestsToFinish(browser);
		if (createProduct
				.verifyErrorMessageAndClose("Commission Amount cannot exceed more "
						+ "than 999.99999999")) {
			Reporter.log("Status: Pass.");
		} else {
			Assert.fail("Error message for commission amount for more than 3 digit"
					+ " number is not displayed.");
		}

	}
}