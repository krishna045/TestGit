// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * The Class InitiateQuoteRequest.
 *
 * @author: Shilpi Malpani
 * @mail: smalpanig@znalytics.com
 * @date: April 23, 2015
 * 
 *        purpose of this class is to store all the pages object related to
 *        Quote Request Page.
 */
public class IntiateQuoteRequestPage {

	@SuppressWarnings("unused")
	private WebDriver driver;

	public IntiateQuoteRequestPage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object Quote label.
	@FindBy(xpath = "//h4[contains(@class,'no-margin ng-binding')]")
	private WebElement quoteLabel;

	// Finding object CompanyName.
	@FindBy(xpath = "//input[@id='companyName']")
	private WebElement companyName;

	// Finding object DBA.
	@FindBy(xpath = "//input[@id='DBA']")
	private WebElement DBA;

	// Finding object TaxId.
	@FindBy(xpath = "//input[@id='TaxId']")
	private WebElement TaxId;

	// Finding object ContactName.
	@FindBy(xpath = "//input[@id='ContactName']")
	private WebElement contactName;

	// Finding object ContactEmail.
	@FindBy(xpath = "//input[@id='ContactEmail']")
	private WebElement contactEmail;

	// Finding object ContactPhone.
	@FindBy(xpath = "//input[@id='ContactPhone']")
	private WebElement contactPhone;

	// Finding object ContactPhoneExt.
	@FindBy(xpath = "//input[@id='ContactPhoneExt']")
	private WebElement contactPhoneExt;

	// Finding object LegalName.
	@FindBy(xpath = "//input[@id='LegalName']")
	private WebElement legalAttention;

	// Finding object LegalAddress1.
	@FindBy(xpath = "//input[@id='LegalAddress1']")
	private WebElement legalAddress1;

	// Finding object LegalAddress2.
	@FindBy(xpath = "//input[@id='LegalAddress2']")
	private WebElement legalAddress2;

	// Finding object LegalCity.
	@FindBy(xpath = "//input[@id='LegalCity']")
	private WebElement legalCity;

	// Finding object LegalState.
	@FindBy(xpath = "//select[@id='LegalState']")
	private WebElement legalState;

	// Finding object LegalZip5.
	@FindBy(xpath = "//input[@id='LegalZip5']")
	private WebElement legalZipCode;

	// Finding object LegalZip4.
	@FindBy(xpath = "//input[@id='LegalZip4']")
	private WebElement legalZip4;

	// Finding object LegalPhone.
	@FindBy(xpath = "//input[@id='LegalPhone']")
	private WebElement legalPhoneNo;

	// Finding object LegalPhoneExt.
	@FindBy(xpath = "//input[@id='LegalPhoneExt']")
	private WebElement legalPhoneExt;

	// Finding object SameAsLegal.
	@FindBy(xpath = "//input[@id='check_Sames_as_Legal']")
	private WebElement sameAsLegal;

	// Finding object BillingName.
	@FindBy(xpath = "//input[@id='BillingName']")
	private WebElement billingName;

	// Finding object BillingAddress1.
	@FindBy(xpath = "//input[@id='BillingAddress1']")
	private WebElement billingAddress1;

	// Finding object BillingAddress2.
	@FindBy(xpath = "//input[@id='BillingAddress2']")
	private WebElement billingAddress2;

	// Finding object BillingCity.
	@FindBy(xpath = "//input[@id='BillingCity']")
	private WebElement billingCity;

	// Finding object BillingState.
	@FindBy(xpath = "//input[@id='BillingState']")
	private WebElement billingState;

	// Finding object BillingZip.
	@FindBy(xpath = "//input[@id='BillingZipZip4']")
	private WebElement billingZipCode;

	// Finding object BillingPhone.
	@FindBy(xpath = "//input[@id='BillingPhone']")
	private WebElement billingPhoneNo;

	// Finding object BillingPhoneExt.
	@FindBy(xpath = "//input[@id='BillingPhoneExt']")
	private WebElement billingPhoneExt;

	// Finding object QuoteReqSection.
	@FindBy(xpath = "//li[@id='panelQuoteRequest']/span")
	private WebElement quoteReqSection;

	// Finding object ReferenceNumber.
	@FindBy(xpath = "//input[@id='ReferenceNumber']")
	private WebElement salesReferenceNumber;

	// Finding object CurrentSupplier.
	@FindBy(xpath = "//input[@id='CurrentSupplier']")
	private WebElement currentSupplierName;

	// Finding object CurrentContractEndDate.
	@FindBy(xpath = "//input[@id='CurrentContractEndDate']")
	private WebElement currentContractEndDate;

	// Finding object ServiceType.
	@FindBy(xpath = "//div[@id='single_select_serviceType']/div/span")
	private WebElement serviceType;

	// Finding object ServiceTypeList.
	@FindBy(xpath = "//div[@id='ui-select-choices-row-1-0']")
	private WebElement serviceTypeList;

	// Finding object MarketList.
	@FindBy(xpath = "//div[@id='ui-select-choices-row-2-0']")
	private WebElement marketList;

	// Finding object Market.
	@FindBy(xpath = "//div[@id='multiple_select_Markets']")
	private WebElement market;

	// Finding object ProductList.
	@FindBy(xpath = "//div[@id='ui-select-choices-row-3-0']")
	private WebElement productList;

	// Finding object ProductName.
	@FindBy(xpath = "//div[@id='single_select_productName']/div/span")
	private WebElement productName;

	// Finding object BrokerFee.
	@FindBy(xpath = "//div[4]//span[@class='k-widget k-numerictextbox']/span[@class='k-numeric-wrap k-state-default']")
	private WebElement brokerFee;

	// Finding object SweetSpotPrice.
	@FindBy(xpath = "//div[5]//span[@class='k-widget k-numerictextbox']/span[@class='k-numeric-wrap k-state-default']")
	private WebElement sweetSpotPrice;

	// Finding object Utility.
	@FindBy(xpath = "//a[contains(text(),'Utility')]")
	private WebElement utility;

	// Finding object UtilityList.
	@FindBy(xpath = "//ul[@id='gUtility_listbox']/li")
	private List<WebElement> utilityList;

	// Finding object EnrollType.
	@FindBy(xpath = "//a[contains(text(),'Enroll Type')]")
	private WebElement enrollType;

	// Finding object Utility Account Number.
	@FindBy(xpath = "//a[contains(text(),'Utility Account Number')]")
	private WebElement utilityAccountNo;

	// Finding object Estimated Start Date.
	@FindBy(xpath = "//a[contains(text(),'Estimated Start Date')]")
	private WebElement estimatedStartDate;

	// Finding object Annual Usage Range.
	@FindBy(xpath = "//a[contains(text(),'Annual Usage Range')]")
	private WebElement annualUsageRange;

	// Finding object Street Address.
	@FindBy(xpath = "//a[contains(text(),'Street Address')]")
	private WebElement streetAddress;

	// Finding object City.
	@FindBy(xpath = "//a[contains(text(),'City')]")
	private WebElement city;

	// Finding object State.
	@FindBy(xpath = "//a[contains(text(),'State')]")
	private WebElement state;

	// Finding object Zip.
	@FindBy(xpath = "//a[contains(text(),'Zip')]")
	private WebElement zip;

	// Finding object Add Service Location.
	@FindBy(xpath = "//a[contains(text(),'Add Service Location')]")
	private WebElement addNewRecord;

	// Finding object DocumenUploadSection.
	@FindBy(xpath = "//li[@id='panelDocumentUpload']/span")
	private WebElement documenUploadSection;

	// Finding object AddComment.
	@FindBy(xpath = "//textarea[@id='QuoteRequestComment']")
	private WebElement addComment;

	// Finding object DocumentSections.
	@FindBy(xpath = "//span[(contains(text(),'Documents'))]")
	private WebElement documentSections;

	// Finding object DragAndDopFiles.
	@FindBy(xpath = "//div[@id='filedrop']/div")
	private WebElement dragAndDopFiles;

	// Finding object FileName.
	@FindBy(xpath = "//table[@class='col-md-12 k-grid-header fa-border']//th[contains(text(),'Name')]")
	private WebElement fileName;

	// Finding object DocumentType.
	@FindBy(xpath = "//table[@class='col-md-12 k-grid-header fa-border']//th[contains(text(),'Document Type')]")
	private WebElement documentType;

	// Finding object FileDescription.
	@FindBy(xpath = "//table[@class='col-md-12 k-grid-header fa-border']//th[contains(text(),'Description')]")
	private WebElement fileDescription;

	// Finding object FileSize.
	@FindBy(xpath = "//table[@class='col-md-12 k-grid-header fa-border']//th[contains(text(),'Size')]")
	private WebElement fileSize;

	// Finding object FileAction.
	@FindBy(xpath = "//table[@class='col-md-12 k-grid-header fa-border']//th[contains(text(),'Action')]")
	private WebElement fileAction;

	// Finding object SaveQuoteBtn.
	@FindBy(xpath = "//button[contains(text(),'Save Quote')]")
	private WebElement saveQuoteBtn;

	// Finding object RequestQuoteBtn.
	@FindBy(xpath = "//button[contains(text(),'Request Quote')]")
	private WebElement requestQuoteBtn;

	// Get Quote Label.
	public WebElement getQuoteLabel() {
		return quoteLabel;
	}

	// Get WebElement SameAsLegal .
	public WebElement clicksameAsLegal() {
		return sameAsLegal;
	}

	// Set Company Name.
	public void setCompanyName(String CompanyName) {
		companyName.clear();
		companyName.sendKeys(CompanyName);
	}

	// Set DBA.
	public void setDBA(String DBAName) {
		DBA.clear();
		DBA.sendKeys(DBAName);
	}

	// Set TaxId.
	public void setTaxId(String Taxid) {
		TaxId.clear();
		TaxId.sendKeys(Taxid);
	}

	// Set Contact Name.
	public void setContactName(String ContactName) {
		contactName.clear();
		contactName.sendKeys(ContactName);
	}

	// Set ContactEmail .
	public void setContactEmail(String ContactEmail) {
		contactEmail.clear();
		contactEmail.sendKeys(ContactEmail);
	}

	// Set ContactPhone.
	public void setContactPhone(String ContactPhone) {
		contactPhone.clear();
		contactPhone.sendKeys(ContactPhone);
	}

	// Set ContactPhoneExt.
	public void setContactPhoneExt(String ContactPhoneExt) {
		contactPhoneExt.clear();
		contactPhoneExt.sendKeys(ContactPhoneExt);
	}

	// Set LegalName.
	public void setLegalAttention(String LegalName) {
		legalAttention.clear();
		legalAttention.sendKeys(LegalName);
	}

	// Set LegalAddress1 .
	public void setLegalAddress1(String LegalAddress1) {
		legalAddress1.clear();
		legalAddress1.sendKeys(LegalAddress1);
	}

	// Set LegalAddress2.
	public void setLegalAddress2(String LegalAddress2) {
		legalAddress2.clear();
		legalAddress2.sendKeys(LegalAddress2);
	}

	// Set LegalState.
	public void setLegalState(String LegalState) {
		Select rpct = new Select(legalState);
		if (LegalState.equalsIgnoreCase("PA")) {
			rpct.selectByVisibleText(LegalState);
		}
	}

	// Set LegalCity.
	public void setLegalCity(String LegalCity) {
		legalCity.clear();
		legalCity.sendKeys(LegalCity);
	}

	// Set LegalZipCode.
	public void setLegalZipCode(String LegalZipCode) {
		legalZipCode.clear();
		legalZipCode.sendKeys(LegalZipCode);
	}

	// Set LegalZip4.
	public void setLegalZip4(String LegalZip4) {
		legalZip4.clear();
		legalZip4.sendKeys(LegalZip4);
	}

	// Set LegalPhoneNo.
	public void setLegalPhoneNo(String LegalPhoneNo) {
		legalPhoneNo.clear();
		legalPhoneNo.sendKeys(LegalPhoneNo);
	}

	// Set LegalPhoneExt.
	public void setLegalPhoneExt(String LegalPhoneExt) {
		legalPhoneExt.clear();
		legalPhoneExt.sendKeys(LegalPhoneExt);
	}

	// Set SalesReferenceNo.
	public void setSalesReferenceNo(String SalesReferenceNo) {
		salesReferenceNumber.clear();
		salesReferenceNumber.sendKeys(SalesReferenceNo);
	}

	// Set CurrentSupplierName.
	public void setCurrentSupplier(String CurrentSupplierName) {
		currentSupplierName.clear();
		currentSupplierName.sendKeys(CurrentSupplierName);
	}

	// Set CurrentContractEndDate.
	public void setCurrentContractEndDate(String CurrentContractEndDate) {
		currentContractEndDate.clear();
		currentContractEndDate.sendKeys(CurrentContractEndDate);
	}

	// Get WebElement Company name.
	public WebElement getCompanyName() {
		return companyName;
	}

	// Get DBA.
	public WebElement getDBA() {
		return DBA;
	}

	// Get TaxId.
	public WebElement getTaxId() {
		return TaxId;
	}

	// Get ContactName.
	public WebElement getContactName() {
		return contactName;
	}

	// Get ContactEmail.
	public WebElement getContactEmail() {
		return contactEmail;
	}

	// Get ContactPhone.
	public WebElement getContactPhone() {
		return contactPhone;
	}

	// Get ContactPhoneExt.
	public WebElement getContactPhoneExt() {
		return contactPhoneExt;
	}

	// Get LegalAttention .
	public WebElement getLegalAttention() {
		return legalAttention;
	}

	// Get LegalAddress1.
	public WebElement getLegalAddress1() {
		return legalAddress1;
	}

	// Get LegalAddress2.
	public WebElement getLegalAddress2() {
		return legalAddress2;
	}

	// Get LegalState.
	public WebElement getLegalState() {
		return legalState;
	}

	// Get LegalCity.
	public WebElement getLegalCity() {
		return legalCity;
	}

	// Get LegalZipCode.
	public WebElement getLegalZipCode() {
		return legalZipCode;
	}

	// Get LegalZip4.
	public WebElement getLegalZip4() {
		return legalZip4;
	}

	// Get LegalPhoneNo.
	public WebElement getLegalPhoneNo() {
		return legalPhoneNo;
	}

	// Get LegalPhoneExt.
	public WebElement getLegalPhoneExt() {
		return legalPhoneExt;
	}

	// Get BillinglName .
	public WebElement getBillinglName() {
		return billingName;
	}

	// Get BillinglAddress1.
	public WebElement getBillinglAddress1() {
		return billingAddress1;
	}

	// Get BillingAddress2.
	public WebElement getBillingAddress2() {
		return billingAddress2;
	}

	// Get BillingCity.
	public WebElement getBillingCity() {
		return billingCity;
	}

	// Get BillingState.
	public WebElement getBillingState() {
		return billingState;
	}

	// Get BillingZipCode.
	public WebElement getBillingZipCode() {
		return billingZipCode;
	}

	// Get BillingPhoneNo.
	public WebElement getBillingPhoneNo() {
		return billingPhoneNo;
	}

	// Get BillingPhoneExt.
	public WebElement getBillingPhoneExt() {
		return billingPhoneExt;
	}

	// Get SalesReferenceNo.
	public WebElement getSalesReferenceNo() {
		return salesReferenceNumber;
	}

	// Get CurrentSupplier.
	public WebElement getCurrentSupplier() {
		return currentSupplierName;
	}

	// Get CurrentContractEndDate.
	public WebElement getCurrentContractEndDate() {
		return currentContractEndDate;
	}

	// Get ServiceType.
	public WebElement getServiceType() {
		return serviceType;
	}

	// Get ServiceTypeList.
	public WebElement getServiceTypeList() {
		return serviceTypeList;
	}

	// Get Market.
	public WebElement getMarket() {
		return market;
	}

	// Get MarketList.
	public WebElement getMarketList() {
		return marketList;
	}

	// Get ProductList.
	public WebElement getProductList() {
		return productList;
	}

	// Get ProductName.
	public WebElement getProductName() {
		return productName;
	}

	// Get BrokerFee.
	public WebElement getBrokerFee() {
		return brokerFee;
	}

	// Get SweetSpotPrice.
	public WebElement getSweetSpotPrice() {
		return sweetSpotPrice;
	}

	// Get Utility.
	public WebElement getUtility() {
		return utility;
	}

	// Get EnrollType.
	public WebElement getEnrollType() {
		return enrollType;
	}

	// Get UtilityAccountNumber.
	public WebElement getUtilityAccountNumber() {
		return utilityAccountNo;
	}

	// Get EstimatedStartDate.
	public WebElement getEstimatedStartDate() {
		return estimatedStartDate;
	}

	// Get AnnualUsageRange.
	public WebElement getAnnualUsageRange() {
		return annualUsageRange;
	}

	// Get StreetAddress.
	public WebElement getStreetAddress() {
		return streetAddress;
	}

	// Get City.
	public WebElement getCity() {
		return city;
	}

	// Get State.
	public WebElement getState() {
		return state;
	}

	// Get Zip.
	public WebElement getZip() {
		return zip;
	}

	// Get AddComment.
	public WebElement getAddComment() {
		return addComment;
	}

	// Get dragAndDropFile.
	public WebElement dragAndDropFile() {
		return dragAndDopFiles;
	}

	// Get fileName.
	public WebElement fileName() {
		return fileName;
	}

	// Get fileDescription.
	public WebElement fileDescription() {
		return fileDescription;
	}

	// Get documentType.
	public WebElement documentType() {
		return documentType;
	}

	// Get fileSize.
	public WebElement fileSize() {
		return fileSize;
	}

	// Get fileAction.
	public WebElement fileAction() {
		return fileAction;
	}

	// Get saveQuoteBtn.
	public WebElement saveQuoteBtn() {
		return saveQuoteBtn;
	}

	// Get requestQuoteBtn.
	public WebElement requestQuoteBtn() {
		return requestQuoteBtn;
	}

	// Get addNewRecord.
	public WebElement addNewRecord() {
		return addNewRecord;
	}

	// Get documenUploadSection.
	public WebElement documenUploadSection() {
		return documenUploadSection;
	}

	// Get quoteReqSection.
	public WebElement quoteReqSection() {
		return quoteReqSection;
	}

}
