// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
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
 *        Purpose of this class is to execute all test cases for validating
 *        fields on Quote Request page Priority=1-50
 */

public class SmokeIntiateQuoteRequestFieldValidation {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	String getCurrentApplicationURL;
	IntiateQuoteRequestPage intiateQuote;
	String url, legalName, legalAddress1, legalAddress2, legalCity, legalState,
			legalZipCode, legalPhoneNo;

	SmokeIntiateQuoteRequestFieldValidation() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		intiateQuote = PageFactory.initElements(browser,
				IntiateQuoteRequestPage.class);

	}

	@Test(priority = 1, groups = { "napower" })
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
	 * Method to check all CompanyInformation fields.
	 */
	@Test(priority = 2, groups = { "napower" })
	public void testCompanyInfoFields() {
		boolean chkCompanyNameLength = false, chkDBALength = false, chkTaxIdLength = false, chkContactNameLength = false, chkContactEmailLength = false, chkContactPhoneLength = false, chkContactPhoneExtLength = false;
		WebElement companyName = intiateQuote.getCompanyName();

		// Check 1: Max CompanyName length 60 char.
		intiateQuote.setCompanyName(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (companyName.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkCompanyNameLength = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max DBA length 60 char.
		WebElement DBA = intiateQuote.getDBA();
		intiateQuote.setDBA(Utils.generateString(55) + Utils.generateNumber(6));
		if (DBA.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkDBALength = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max TaxId length 9 char.
		WebElement taxId = intiateQuote.getTaxId();
		intiateQuote.setTaxId(Utils.generateNumber(10));
		if (taxId.getText().length() > 9) {
			Reporter.log("Max length 9 Char: Fail.");
		} else {
			chkTaxIdLength = true;
			Reporter.log("Max length 9 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max ContactName length 60 char.
		WebElement contactName = intiateQuote.getContactName();
		intiateQuote.setContactName(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (contactName.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkContactNameLength = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max ContactEmail length 60 char.
		WebElement contactEmail = intiateQuote.getContactEmail();
		intiateQuote.setContactEmail(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (contactEmail.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkContactEmailLength = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max ContactPhone length 9 char.
		WebElement contactPhone = intiateQuote.getContactPhone();
		intiateQuote.setContactPhone(Utils.generateNumber(10));
		if (contactPhone.getText().length() > 9) {
			Reporter.log("Max length 9 Char: Fail.");
		} else {
			chkContactPhoneLength = true;
			Reporter.log("Max length 9 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		// Check 1: Max ContactPhoneExt length 6 char.
		WebElement contactPhoneExt = intiateQuote.getContactPhoneExt();
		intiateQuote.setContactPhoneExt(Utils.generateNumber(6));
		if (contactPhoneExt.getText().length() > 5) {
			Reporter.log("Max length 5 Char: Fail.");
		} else {
			chkContactPhoneExtLength = true;
			Reporter.log("Max length 5 Char: Pass.");
			ScreenShot.capture(true, "testCompanyInfoFields");
		}

		if (chkCompanyNameLength && chkDBALength && chkTaxIdLength
				&& chkContactNameLength && chkContactEmailLength
				&& chkContactPhoneLength && chkContactPhoneExtLength) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}

	}

	/**
	 * Method to check all LegalAddress fields.
	 */
	@Test(priority = 3, groups = { "napower" })
	public void testLegalAddressFields() {
		boolean chkLegalAttentionLength = false, chkLegalAddress1Length = false, chkLegalAddress2Length = false, chkLegalCityLength = false, chkLegalStateLength = false, chkLegalZipCodeLength = false, chkLegalZip4Length = false, chkLegalPhoneNoLength = false, chkLegalPhoneExtLength = false;

		// Check 1: Max LegalAttention length 60 char.
		WebElement legalAttention = intiateQuote.getLegalAttention();
		intiateQuote.setLegalAttention(Utils.generateString(25)
				+ Utils.generateNumber(6));
		if (legalAttention.getText().length() > 30) {
			Reporter.log("Max length 30 Char: Fail.");
		} else {
			chkLegalAttentionLength = true;
			Reporter.log("Max length 30 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalAddress1 length 60 char.
		WebElement legalAddress1 = intiateQuote.getLegalAddress1();
		intiateQuote.setLegalAddress1(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (legalAddress1.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkLegalAddress1Length = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalAddress2 length 60 char.
		WebElement legalAddress2 = intiateQuote.getLegalAddress2();
		intiateQuote.setLegalAddress2(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (legalAddress2.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkLegalAddress2Length = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalCity length 30 char.
		WebElement legalCity = intiateQuote.getLegalCity();
		intiateQuote.setLegalCity(Utils.generateString(25)
				+ Utils.generateNumber(6));
		if (legalCity.getText().length() > 30) {
			Reporter.log("Max length 30 Char: Fail.");
		} else {
			chkLegalCityLength = true;
			Reporter.log("Max length 30 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalState length 2 char.
		WebElement legalState = intiateQuote.getLegalState();
		intiateQuote.setLegalState("PA");
		if (legalState.getText().length() > 2) {
			Reporter.log("Max length 2 Char: Fail.");
		} else {
			chkLegalStateLength = true;
			Reporter.log("Max length 2 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalZipCode length 5 char.
		WebElement legalZipCode = intiateQuote.getLegalZipCode();
		intiateQuote.setLegalZipCode(Utils.generateNumber(6));
		if (legalZipCode.getText().length() > 5) {
			Reporter.log("Max length 5 Char: Fail.");
		} else {
			chkLegalZipCodeLength = true;
			Reporter.log("Max length 5 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalZip4 length 4 char.
		WebElement legalZip4 = intiateQuote.getLegalZip4();
		intiateQuote.setLegalZip4(Utils.generateNumber(5));
		if (legalZip4.getText().length() > 4) {
			Reporter.log("Max length 4 Char: Fail.");
		} else {
			chkLegalZip4Length = true;
			Reporter.log("Max length 4 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalPhoneNo length 10 char.
		WebElement legalPhoneNo = intiateQuote.getLegalPhoneNo();
		intiateQuote.setLegalPhoneNo(Utils.generateNumber(11));
		if (legalPhoneNo.getText().length() > 10) {
			Reporter.log("Max length 10 Char: Fail.");
		} else {
			chkLegalPhoneNoLength = true;
			Reporter.log("Max length 10 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		// Check 1: Max LegalPhoneExt length 5 char.
		WebElement legalPhoneExt = intiateQuote.getLegalPhoneExt();
		intiateQuote.setLegalPhoneExt(Utils.generateNumber(6));
		if (legalPhoneExt.getText().length() > 5) {
			Reporter.log("Max length 6 Char: Fail.");
		} else {
			chkLegalPhoneExtLength = true;
			Reporter.log("Max length 6 Char: Pass.");
			ScreenShot.capture(true, "testLegalAddressFields");
		}

		if (chkLegalAttentionLength && chkLegalAddress1Length
				&& chkLegalAddress2Length && chkLegalCityLength
				&& chkLegalStateLength && chkLegalZipCodeLength
				&& chkLegalZip4Length && chkLegalPhoneNoLength
				&& chkLegalPhoneExtLength) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}

	}

	/**
	 * Validate Billing Address.
	 */
	@Test(priority = 4, groups = { "napower" })
	public void testBillingAddressFields() {
		intiateQuote.clicksameAsLegal().click();
		ScreenShot.capture(true);
	}

	/**
	 * Method to check all QuoteReqSection fields.
	 */
	@Test(priority = 5, groups = { "napower" })
	public void testQuoteReqSection() {
		Element.scrollElementIntoView(browser, intiateQuote.quoteReqSection());
		boolean chkSalesReferenceNoLength = false, chkCurrentSupplierLength = false, chkServiceTypeDropdown = false, chkMarketDropdown = false, chkProductNameDropdown = false, chkBrokerFee = false, chkSweetSpotPrice = false, chkAddComment = false;

		// Check 1: Max SalesReferenceNo length 30 char.
		WebElement salesReferenceNo = intiateQuote.getSalesReferenceNo();
		intiateQuote.setSalesReferenceNo(Utils.generateString(25)
				+ Utils.generateNumber(6));
		if (salesReferenceNo.getText().length() > 30) {
			Reporter.log("Max length 30 Char: Fail.");
		} else {
			chkSalesReferenceNoLength = true;
			Reporter.log("Max length 30 Char: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		}

		// Check 2: Max CurrentSupplier length 6 char.
		WebElement currentSupplier = intiateQuote.getCurrentSupplier();
		intiateQuote.setCurrentSupplier(Utils.generateString(55)
				+ Utils.generateNumber(6));
		if (currentSupplier.getText().length() > 60) {
			Reporter.log("Max length 60 Char: Fail.");
		} else {
			chkCurrentSupplierLength = true;
			Reporter.log("Max length 60 Char: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		}

		// Check 3:ServiceType list is visible.
		if (Element.isVisible(browser, intiateQuote.getServiceType())) {
			Element.highlightElement(browser, intiateQuote.getServiceType());
			intiateQuote.getServiceType().click();
			intiateQuote.getServiceTypeList().click();
			chkServiceTypeDropdown = true;
			ScreenShot.capture(true, "testQuoteReqSection");
		} else {
			Reporter.log("Dropdown values are not present for ServiceType: Fail.");
		}

		// Check 4:Market list is visible.
		if (Element.isVisible(browser, intiateQuote.getMarket())) {
			Element.highlightElement(browser, intiateQuote.getMarket());
			intiateQuote.getMarket().click();
			intiateQuote.getMarketList().click();
			chkMarketDropdown = true;
			Reporter.log("Market selected: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		} else {
			Reporter.log("Dropdown values are not present for Market: Fail.");
		}

		// Check 5:ProductName list is visible.
		if (Element.isVisible(browser, intiateQuote.getProductName())) {
			Element.highlightElement(browser, intiateQuote.getProductName());
			intiateQuote.getProductName().click();
			intiateQuote.getProductList().click();
			chkProductNameDropdown = true;
			Reporter.log("ProductName selected: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		} else {
			Reporter.log("Dropdown values are not present for ProductName: Fail.");
		}

		// Check 6:BrokerFee field visible.
		if (Element.isVisible(browser, intiateQuote.getBrokerFee())) {
			Element.highlightElement(browser, intiateQuote.getBrokerFee());
			chkBrokerFee = true;
			Reporter.log("BrokerFee field is present: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		} else {
			Reporter.log("BrokerFee field is not present: Fail.");
		}

		// Check 7:SweetSpotPrice field visible.
		if (Element.isVisible(browser, intiateQuote.getSweetSpotPrice())) {
			Element.highlightElement(browser, intiateQuote.getSweetSpotPrice());
			chkSweetSpotPrice = true;
			Reporter.log("SweetSpotPrice field is present: Pass.");
			ScreenShot.capture(true, "testQuoteReqSection");
		} else {
			Reporter.log("SweetSpotPrice field is not present: Fail.");
		}

		// Check 8:AddComment field visible.
		Element.scrollElementIntoView(browser, intiateQuote.getAddComment());
		if (Element.isVisible(browser, intiateQuote.getAddComment())) {
			Element.highlightElement(browser, intiateQuote.getAddComment());
			chkAddComment = true;
			Reporter.log("AddComment field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("AddComment field is not present: Fail.");
		}

		if (chkSalesReferenceNoLength && chkCurrentSupplierLength
				&& chkServiceTypeDropdown && chkMarketDropdown
				&& chkProductNameDropdown && chkBrokerFee && chkSweetSpotPrice
				&& chkAddComment) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}
	}

	/**
	 * Method to check all ServiceLocation fields.
	 */
	@Test(priority = 6, groups = { "napower" })
	public void testServiceLocationFields() {
		Element.scrollElementIntoView(browser,
				intiateQuote.documenUploadSection());
		intiateQuote.addNewRecord().click();
		boolean chkUtility = false, chkEnrollType = false, chkUtilityAccountNumber = false, chkEstimatedStartDate = false, chkAnnualUsageRange = false, chkStreetAddress = false, chkCity = false, chkState = false, chkZip = false;

		// Check 1:Utility field visible.
		if (Element.isVisible(browser, intiateQuote.getUtility())) {
			Element.highlightElement(browser, intiateQuote.getUtility());
			chkUtility = true;
			Reporter.log("Utility field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("Utility field is not present: Fail.");
		}

		// Check 2:EnrollType field visible.
		if (Element.isVisible(browser, intiateQuote.getEnrollType())) {
			Element.highlightElement(browser, intiateQuote.getEnrollType());
			chkEnrollType = true;
			Reporter.log("EnrollType field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("EnrollType field is not present: Fail.");
		}

		// Check 3:UtilityAccountNumber field visible.
		if (Element.isVisible(browser, intiateQuote.getUtilityAccountNumber())) {
			Element.highlightElement(browser,
					intiateQuote.getUtilityAccountNumber());
			chkUtilityAccountNumber = true;
			Reporter.log("UtilityAccountNumber field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("UtilityAccountNumber field is not present: Fail.");
		}

		// Check 4:EstimatedStartDate field visible.
		if (Element.isVisible(browser, intiateQuote.getEstimatedStartDate())) {
			Element.highlightElement(browser,
					intiateQuote.getEstimatedStartDate());
			chkEstimatedStartDate = true;
			Reporter.log("EstimatedStartDate field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("EstimatedStartDate field is not present: Fail.");
		}

		// Check 5:AnnualUsageRange field visible.
		if (Element.isVisible(browser, intiateQuote.getAnnualUsageRange())) {
			Element.highlightElement(browser,
					intiateQuote.getAnnualUsageRange());
			chkAnnualUsageRange = true;
			Reporter.log("AnnualUsageRange field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("AnnualUsageRange field is not present: Fail.");
		}

		// Check 6:StreetAddress field visible.
		if (Element.isVisible(browser, intiateQuote.getStreetAddress())) {
			Element.highlightElement(browser, intiateQuote.getStreetAddress());
			chkStreetAddress = true;
			Reporter.log("StreetAddress field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("StreetAddress field is not present: Fail.");
		}

		// Check 7:City field visible.
		if (Element.isVisible(browser, intiateQuote.getCity())) {
			Element.highlightElement(browser, intiateQuote.getCity());
			chkCity = true;
			Reporter.log("City field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("City field is not present: Fail.");
		}

		// Check 8:State field visible.
		if (Element.isVisible(browser, intiateQuote.getState())) {
			Element.highlightElement(browser, intiateQuote.getState());
			chkState = true;
			Reporter.log("State field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("State field is not present: Fail.");
		}

		// Check 9:Zip field visible.
		if (Element.isVisible(browser, intiateQuote.getZip())) {
			Element.highlightElement(browser, intiateQuote.getZip());
			chkZip = true;
			Reporter.log("Zip field is present: Pass.");
			ScreenShot.capture(true, "testServiceLocationFields");
		} else {
			Reporter.log("Zip field is not present: Fail.");
		}

		if (chkUtility && chkEnrollType && chkUtilityAccountNumber
				&& chkEstimatedStartDate && chkAnnualUsageRange
				&& chkStreetAddress && chkCity && chkState && chkZip) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}

	}

	/**
	 * Method to check all DocumentUploadSection fields.
	 */
	@Test(priority = 7, groups = { "napower" })
	public void testDocumentUploadSection() {
		Element.scrollElementIntoView(browser,
				intiateQuote.documenUploadSection());
		boolean chkDragAndDropFile = false, chkFileName = false, chkDocumentType = false, chkFileDescription = false, chkFileSize = false, chkFileAction = false;

		// Check 1:DragAndDropFile field visible.
		if (Element.isVisible(browser, intiateQuote.dragAndDropFile())) {
			Element.highlightElement(browser, intiateQuote.dragAndDropFile());
			chkDragAndDropFile = true;
			Reporter.log("DragAndDropFile field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("DragAndDropFile field is not present: Fail.");
		}

		// Check 2:FileName field visible.
		if (Element.isVisible(browser, intiateQuote.fileName())) {
			Element.highlightElement(browser, intiateQuote.fileName());
			chkFileName = true;
			Reporter.log("FileName field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("FileName field is not present: Fail.");
		}

		// Check 3:DocumentType field visible.
		if (Element.isVisible(browser, intiateQuote.documentType())) {
			Element.highlightElement(browser, intiateQuote.documentType());
			chkDocumentType = true;
			Reporter.log("DocumentType field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("DocumentType field is not present: Fail.");
		}

		// Check 4:FileDescription field visible.
		if (Element.isVisible(browser, intiateQuote.fileDescription())) {
			Element.highlightElement(browser, intiateQuote.fileDescription());
			chkFileDescription = true;
			Reporter.log("FileDescription field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("FileDescription field is not present: Fail.");
		}

		// Check 5:FileSize field visible.
		if (Element.isVisible(browser, intiateQuote.fileSize())) {
			Element.highlightElement(browser, intiateQuote.fileSize());
			chkFileSize = true;
			Reporter.log("FileSize field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("FileSize field is not present: Fail.");
		}

		// Check 6:FileAction field visible.
		if (Element.isVisible(browser, intiateQuote.fileAction())) {
			Element.highlightElement(browser, intiateQuote.fileAction());
			chkFileAction = true;
			Reporter.log("FileAction field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("FileAction field is not present: Fail.");
		}

		if (chkDragAndDropFile && chkFileName && chkDocumentType
				&& chkFileDescription && chkFileSize && chkFileAction) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}
	}

	/**
	 * Method to check SaveQuote and  RequestQuote button validations.
	 */
	@Test(priority = 8, groups = { "napower" })
	public void testSaveAndReqQuteBtn() {
		Element.scrollElementIntoView(browser, intiateQuote.saveQuoteBtn());
		boolean chkSaveQuoteBtn = false, chkRequestQuoteBtn = false;

		// Check 1:SaveQuote field visible.
		if (Element.isVisible(browser, intiateQuote.saveQuoteBtn())) {
			Element.highlightElement(browser, intiateQuote.fileAction());
			chkSaveQuoteBtn = true;
			intiateQuote.saveQuoteBtn().isEnabled();
			Reporter.log("SaveQuote Button field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("SaveQuote Button field is not present: Fail.");
		}

		// Check 2:RequestQuote field visible.
		if (Element.isVisible(browser, intiateQuote.requestQuoteBtn())) {
			Element.highlightElement(browser, intiateQuote.fileAction());
			chkRequestQuoteBtn = true;
			intiateQuote.requestQuoteBtn().isEnabled();
			Reporter.log("RequestQuote Button field is present: Pass.");
			ScreenShot.capture(true, "testDocumentUploadSection");
		} else {
			Reporter.log("RequestQuote Button field is not present: Fail.");
		}

		if (chkSaveQuoteBtn && chkRequestQuoteBtn) {
			Reporter.log("Status: Pass");
		} else {
			Reporter.log("Status: Fail");
			Assert.fail("One or more validation on product code field"
					+ " failed. Check left side for more details.");
		}

	}

}
