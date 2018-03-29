// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.WaitUtils;

/**
 * The Class SearchProductPage.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * @purpose: purpose of this class is to store all the pages object related to
 *           Search Product Page.
 */

public class SearchProductPage {

	private WebDriver driver;

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object Partial Product Name or Code
	@FindBy(xpath = "//input[@id='searchProductCodeOrName']")
	private WebElement searchProductCodeOrName;

	// Finding object Markets
	@FindBy(xpath = "//div[@id='multiple_select_searchMarkets']//input[@type='text']")
	private WebElement selectMarkets;

	// Finding object utility
	@FindBy(xpath = "//div[@id='multiple_select_searchUtilities']//input[@type='text']")
	private WebElement selectUtility;

	// Finding object search utility drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchUtilities']")
	private WebElement selectUtilityOption;

	// Finding object search market drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchMarkets']")
	private WebElement selectMarketOption;

	// Finding object search market drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-0')]")
	private List<WebElement> selectMarketOptionElement;

	// Finding object search utilities drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-1')]")
	private List<WebElement> selectUtilityOptionElement;

	// Finding object search zones drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-2')]")
	private List<WebElement> selectZoneOptionElement;

	// Finding object search segment drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-3')]")
	private List<WebElement> selectSegmentOptionElement;

	// Finding object search sales channel drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-4')]")
	private List<WebElement> selectSalesChannelOptionElement;

	// Finding object search Contract Sources drop down value
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-5')]")
	private List<WebElement> selectContractSourcesOptionElement;

	// Finding object search zone drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchZones']")
	private WebElement selectZoneOption;

	// Finding object search search Segments drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchSegments']")
	private WebElement selectSegmentOption;

	// Finding object search Sales Channels drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchSalesChannels']")
	private WebElement selectSalesChannelOption;

	// Finding object search Contract Sources drop down value
	@FindBy(xpath = "//div[@id='multiple_select_searchContractSources']")
	private WebElement selectContractSourcesOption;

	// Finding object zone
	@FindBy(xpath = "//div[@id='multiple_select_searchZones']//input[@type='text']")
	private WebElement selectZone;

	// Finding object segment
	@FindBy(xpath = "//div[@id='multiple_select_searchSegments']//input[@type='text']")
	private WebElement selectSegment;

	// Finding object Sales channel
	@FindBy(xpath = "//div[@id='multiple_select_searchSalesChannels']//input[@type='text']")
	private WebElement selectChannel;

	// Finding object Contract Sources
	@FindBy(xpath = "//div[@id='multiple_select_searchContractSources']//input[@type='text']")
	private WebElement selectContractSource;

	// Finding object Search Promo Allowed Activate
	@FindBy(xpath = "//span[@aria-label='Select box activate']")
	private WebElement searchPromoAllowedActivate;

	// Finding object Promo Allowed
	@FindBy(xpath = "//div[@id='single_select_searchPromoAllowed']/input[@type='text']")
	private WebElement selectPromoAllowed;

	// Finding object Promo Allowed select
	@FindBy(xpath = "//input[contains(@id,'ui-select-choices-row-6')]/a")
	private WebElement searchPromoAllowed;

	// Finding object Search
	@FindBy(xpath = "//button[@type='submit' and contains(text(),'Search')]")
	private WebElement selectSearch;

	// Finding object reset
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Clear')]")
	private WebElement selectReset;

	// Finding object Product Code
	@FindBy(xpath = "//th[@data-title='Product Code']")
	private WebElement productCode;

	// Finding object Product Name
	@FindBy(xpath = "//th[@data-title='Product Name']")
	private WebElement productName;

	// Finding object Product Type
	@FindBy(xpath = "//th[@data-title='Product Type']")
	private WebElement productType;

	// Finding object Service Type
	@FindBy(xpath = "//th[@data-title='Service Type']")
	private WebElement serviceType;

	// Finding object Effective Date
	@FindBy(xpath = "//th[@data-title='Effective Date']")
	private WebElement effectiveDate;

	// Finding object Expiration Date
	@FindBy(xpath = "//th[@data-title='Expiration Date']")
	private WebElement expirationDate;

	// Finding object Promo Allowed
	@FindBy(xpath = "//th[@data-title='Promo Allowed']")
	private WebElement promoAllowedWebTable;

	// Finding object Linked Rate Schedules
	@FindBy(xpath = "//th[@data-title='Linked Rate Schedules']")
	private WebElement linkedRateSchedules;

	// Finding object drop target
	@FindBy(xpath = "//div[@data-role='droptarget']")
	private WebElement dropTarget;

	// Finding object Active
	@FindBy(xpath = "//th[@data-title='Active']")
	private WebElement active;

	// Finding object Webtable result unlocked
	@FindBy(xpath = "//div[@class='k-grid-content']/table[@role='grid']/tbody")
	private WebElement webtable;

	// Finding object Webtable result locked
	@FindBy(xpath = "//div[@class='k-grid-content-locked']/table/tbody")
	private WebElement webtablelocked;

	// Finding object Webtable header Locked
	@FindBy(xpath = "//div[@class='k-grid-header']//thead")
	private WebElement webTableHeaderLocked;

	// Finding object Webtable header wrap
	@FindBy(xpath = "//div[@class='k-grid-header-wrap']//thead")
	private WebElement webTableHeaderWrap;

	// Finding object Error select one search criteria
	@FindBy(xpath = "//div[contains(@class,'k-widget') and @data-role='draggable']//span[contains(text(),'Error')]")
	private WebElement errorSelectSearchcriteria;

	// Finding object Information pop when no matching record found
	@FindBy(xpath = "//span[contains(.,'Information')]")
	private WebElement informationPopUpErrorMessage;

	// Finding object Error pop up message
	@FindBy(xpath = "//div[contains(@class,'k-widget') and @data-role='draggable']//td/span[contains(@class,'error-msg-icon')]/ancestor::td/following-sibling::td")
	private WebElement errorMessage;

	// Finding object Information pop up message
	@FindBy(xpath = "//td[@class='p-md ng-binding']")
	private WebElement infoMessage;

	// Finding object Error pop up message close
	@FindBy(xpath = "//span[contains(text(),'Error')]/following-sibling::div/a/span[contains(text(),'Close')]")
	private WebElement errorMessageClose;

	// Finding object Information pop up message close
	@FindBy(xpath = "//span[contains(text(),'Information')]/following-sibling::div/a/span[contains(text(),'Close')]")
	private WebElement infoMessageClose;

	// Finding object Group By delete
	@FindBy(xpath = "//div[@id='productSearchResults']//span[contains(@class,'k-group-delete')]")
	private WebElement errorGroupDelete;

	// Finding object Export to excel
	@FindBy(xpath = "//a[contains(text(),'Export to Excel') and contains(@class,'k-grid-excel')]")
	private WebElement exportToExcel;

	// Finding object label product to filter
	@FindBy(xpath = "//div[contains(text(),'Product Filter Criteria')]")
	private WebElement productFilterCriteria;

	// Finding Page label product search
	@FindBy(xpath = "//h4[contains(text(),'Product Search')]")
	private WebElement productSearchLabel;
	
	// Finding object editRateScheduleIcon.
		@FindBy(xpath = "//button[contains(@class,'btn btn-xs btn-primary')]")
		private WebElement editRateScheduleIcon;
		
		// Finding object rateScheduleButton.
		@FindBy(xpath = "//a[contains(text(),'Rate Schedule')]")
		private WebElement rateScheduleButton;
		
		// Finding object editRateScheduleLink.
		@FindBy(xpath = "//*[@id='TRow0']/td[1]/span")
		private WebElement editRatescheduleLink;
		
		// Finding object editRateScheduleLink1.
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div/div/div/div/table/tbody/tr[1]/td[2]/span")
		private WebElement editRateScheudleLink1;
		
		// Finding object rateScheduleName.
		@FindBy(xpath = "//*[@id='single_select_RateScheduleMarket']/div/span[contains(@aria-label,'Select box activate')]")
		private WebElement rateScehduleName;
		
		// Finding object rateScheduleLabel.
		@FindBy(xpath = "//*[@id='rateScheduleParentDiv']/div[1]/h4")
		private WebElement rateScheduleLabel;
		
		// Finding object rateScheduleNextButton.
		@FindBy(xpath = "//*[@id='rateScheduleParentDiv']/div[2]/form/div[3]/div[2]/div/div/button[2]")
		private WebElement rateScheduleNextButton;
		
		// Finding object rateScheduleNextButton.
		@FindBy(xpath = "//*[@id='productReviewParent']/div/div[1]/div/div[2]/button")
		private WebElement modifyProductAttributes;
		
		// Finding object rateScheduleMarketList.
		@FindBy(xpath = "//*[@id='single_select_RateScheduleMarket']/div/span")
		private WebElement rateScheduleMarketspan;
	
		// Finding object rateScheduleMarketOptions.
		@FindBy(xpath = "//*[@id='ui-select-choices-row-1-0']/a")
		private List<WebElement> rateScheduleMarketOptions;
		
		// Finding object rateScheduleUtilityList.
		@FindBy(xpath = "//*[@id='single_select_RateScheduleUtility']/div/span[contains(@aria-label,'Select box activate')]")
		private WebElement rateScheduleUtilityElement;
		
		// Finding object rateScheduleUtilityOption
		@FindBy(xpath = "//*[@id='ui-select-choices-row-2-2']/a/span")
		private List<WebElement> rateScheduleUtilityOptions;
		
		// Finding object rateSchedulePassThroughSpanList.
		@FindBy(xpath = "//*[@id='single_select_RateScheduleSegmentTemplate']/div/span[contains(@aria-label,'Select box activate')]")
		private WebElement rateSchedulePassThroughSpan;
		
		// Finding object rateSchedulePassThroughSpanOption.
		@FindBy(xpath = "//*[@id='ui-select-choices-row-5-1']/a/span")
		private List<WebElement> rateSchedulePassThroughOptions;
		
		// Finding object addChargesButton.
		@FindBy(xpath = "//*[@id='rateSegmentDiv']/div/div/div/div[1]/div[2]/div/div[4]/span/button")
		private WebElement addChargesBut;
		
	    //Finding object chargeDescriptionList
		@FindBy(xpath = "//*[@id='RateScheduleChargeDescription']/div/span")
		private WebElement chargeDescriptionSpan;
		
		@FindBy(xpath = "//select[@id='RateScheduleChargeDescription']/option[3]")
		 private WebElement chargeDescriptionElement;
		
	    public WebElement getChargeDescriptionElement() {
	    	chargeDescriptionElement.click();
			return chargeDescriptionElement;
		}
	
		public void setChargeDescriptionElement(WebElement chargeDescriptionElement) {
			this.chargeDescriptionElement = chargeDescriptionElement;
		}
		//Finding object chargeDescriptionOptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-17-1']/a")
		private List<WebElement> chargeDescriptionOptions;
		
		//Finding object indexTypeSpanElementList
		@FindBy(xpath = "//*[@id='single_select_RateScheduleIndexType']/div/span")
		private WebElement indexTypeSpanElement;
	    
		//Finding object saveButton;
		@FindBy(xpath = "//*[@id='rateSegmentDiv']/div/div/div/div[2]/div[2]/div/div[2]/button[2]")
		private WebElement saveButton;
		
	    //Finding object moreoptionsbutton;
		@FindBy(xpath = "//*[@id='rateScheduleAttributeCongigDiv']/div[2]/button")
		private WebElement moreOptionsButton;
		
		//Finding object earlyTerminationFeeList;
		@FindBy(id = "//*[@id='single_select_RateScheduleSegmentTemplate']/div/span")
		private WebElement earlyTerminationFee;
		
	    //Finding object earlyTerminationOptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-11-1']/a")
		private List<WebElement> earlyTerminationOptions;
		
	    //Finding object earlyTerminationAmountTextField
		@FindBy(xpath = "//input[@class='form-control ng-pristine ng-untouched ng-valid']")
		private WebElement earlyTerminationAmount;
		
	    //Finding object rateIdentificationMethod.
		@FindBy(xpath = "html/body/div[3]/div/div/div[2]/div/div[3]/div/div/div[2]/div/div/span")
		private WebElement rateIdentificationMethod;
	
		//Finding object popupSave
		@FindBy(xpath = "html/body/div[3]/div/div/div[3]/button[2]")
		private WebElement popupSave;
	    
		//Finding object rateIdentificationOptions.
		@FindBy(xpath = "//*[@id='ui-select-choices-row-12-1']/a")
		private List<WebElement> rateIdentifiationOptions;
		
	    //Finding object menuapplication
		@FindBy(xpath = "//*[@id='hideBtn']")
		private WebElement menuApplication;
		
	    //Finding object reateScheduleClose
		@FindBy(xpath = "//div[@id='rateschedule']/div/div/div[2]/div/div/div/button[contains(text(),'Close')]")
		private WebElement reateScheduleClose;
		
	    //Finding object products
		@FindBy(xpath = "//*[@id='aside']/div/div/div/accordion/div/div/div[1]/h4/a/span/span")
		private WebElement products;
		
	    //Finding object cancelButton.
		@FindBy(xpath = "//*[@id='rateScheduleParentDiv']/div[2]/form/div[3]/div[2]/div/div/button[1]")
		private WebElement CancelButton;
		
	    //Finding object cancelDialog.
		@FindBy(xpath = "html/body/div[3]/div/div/div[3]/button[1]")
		private WebElement CancelDialog;
	
		//Finding object okDialog
		@FindBy(xpath = "html/body/div[3]/div/div/div[3]/button[2]")
		private WebElement OkDialog;
		
	    //Finding object submit button
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div[2]/div/div/button[2]")
		private WebElement SubmitButton;
		
	    //Finding object meterRegisterOptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-9-0']/a")
		private List<WebElement> meterRegisterOptions;
		
	    //Finding object rateScheduleChargeCal
		@FindBy(xpath = "//*[@id='single_select_RateScheduleChargeCalculator']/div/span")
		private WebElement rateScheduleChargeCal;
	    
		//Finding object ChargeCalculatorOptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-10-1']/a")
		private List<WebElement> ChargeCalculatorOptions;
		
	    //Finding object addProduct
		@FindBy(xpath = "//*[@id='aside']/div/div/div/accordion/div/div/div[2]/div/accordion/div/div[2]/div[1]/h4/a/span/a/span")
		private WebElement addProduct;
	
		//Finding object indexTypeoptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-7-0']/a")
		private List<WebElement> indexTypeOptions;
	
		//Finding object rateScheduleUOM
		@FindBy(xpath = "//*[@id='single_select_RateScheduleUOM']/div/span")
		private WebElement rateScheduleUOM;
	
		//Finding object rateScheduleUOMoptions
		@FindBy(xpath = "//*[@id='ui-select-choices-row-8-7']/a")
		private List<WebElement> rateScheduleUOMoptions;
	
		//Finding object meterRegister
		@FindBy(xpath = "//*[@id='single_select_RateScheduleMeterRegister']/div/span")
		private WebElement meterRegister;
	
		public WebElement getSubmitButton() {
			return SubmitButton;
		}
	
		public void setSubmitButton(WebElement submitButton) {
			SubmitButton = submitButton;
		}
	
		public WebElement getOkDialog() {
			return OkDialog;
		}
	
		public void setOkDialog(WebElement okDialog) {
			OkDialog = okDialog;
		}
	
		public WebElement getCancelDialog() {
			return CancelDialog;
		}
	
		public void setCancelDialog(WebElement cancelDialog) {
			CancelDialog = cancelDialog;
		}
	
		public WebElement getCancelButton() {
			return CancelButton;
		}
	
		public void setCancelButton(WebElement cancelButton) {
			CancelButton = cancelButton;
		}
	
		public WebElement getAddProduct() {
			return addProduct;
		}
	
		public void setAddProduct(WebElement addProduct) {
			this.addProduct = addProduct;
		}
	
		public WebElement getProducts() {
			WaitUtils.waitForElement(driver, products);
			return products;
		}
	
		public void setProducts(WebElement products) {
			this.products = products;
		}
	
		public WebElement getReateScheduleClose() {
			return reateScheduleClose;
		}
	
		public void setReateScheduleClose(WebElement reateScheduleClose) {
			this.reateScheduleClose = reateScheduleClose;
		}
	
		public WebElement getMenuApplication() {
			return menuApplication;
		}
	
		public void setMenuApplication(WebElement menuApplication) {
			this.menuApplication = menuApplication;
		}
	
		public List<WebElement> getRateIdentifiationOptions() {
			return rateIdentifiationOptions;
		}
	
		public void setRateIdentifiationOptions(
				List<WebElement> rateIdentifiationOptions) {
			this.rateIdentifiationOptions = rateIdentifiationOptions;
		}
	
		public WebElement getPopupSave() {
			return popupSave;
		}
	
		public void setPopupSave(WebElement popupSave) {
			this.popupSave = popupSave;
		}
	
		public WebElement getRateIdentificationMethod() {
			return rateIdentificationMethod;
		}
	
		public void setRateIdentificationMethod(WebElement rateIdentificationMethod) {
			this.rateIdentificationMethod = rateIdentificationMethod;
		}
	
		public WebElement getEarlyTerminationAmount() {
			return earlyTerminationAmount;
		}
	
		public void setEarlyTerminationAmount(WebElement earlyTerminationAmount) {
			this.earlyTerminationAmount = earlyTerminationAmount;
		}
	
		public List<WebElement> getEarlyTerminationOptions() {
			return earlyTerminationOptions;
		}
	
		public void setEarlyTerminationOptions(
				List<WebElement> earlyTerminationOptions) {
			this.earlyTerminationOptions = earlyTerminationOptions;
		}
	
		public WebElement getEarlyTerminationFee() {
			return earlyTerminationFee;
		}
	
		public void setEarlyTerminationFee(WebElement earlyTerminationFee) {
			this.earlyTerminationFee = earlyTerminationFee;
		}
	
		public WebElement getMoreOptionsButton() {
			return moreOptionsButton;
		}
	
		public void setMoreOptionsButton(WebElement moreOptionsButton) {
			this.moreOptionsButton = moreOptionsButton;
		}
	
		public WebElement getSaveButton() {
			return saveButton;
		}
	
		public void setSaveButton(WebElement saveButton) {
			this.saveButton = saveButton;
		}
	
		public WebElement getIndexTypeSpanElement() {
			return indexTypeSpanElement;
		}
	
		public void setIndexTypeSpanElement(WebElement indexTypeSpanElement) {
			this.indexTypeSpanElement = indexTypeSpanElement;
		}
	
		public WebElement getMeterRegister() {
			return meterRegister;
		}
	
		public void setMeterRegister(WebElement meterRegister) {
			this.meterRegister = meterRegister;
		}
	
		public void setEarlyTerminationAmount(String var) {
			earlyTerminationAmount.sendKeys(var);
		}
	
		public List<WebElement> getChargeCalculatorOptions() {
			return ChargeCalculatorOptions;
		}
	
		public void setChargeCalculatorOptions(
				List<WebElement> chargeCalculatorOptions) {
			ChargeCalculatorOptions = chargeCalculatorOptions;
		}
	
		public WebElement getRateScheduleChargeCal() {
			return rateScheduleChargeCal;
		}
	
		public void setRateScheduleChargeCal(WebElement rateScheduleChargeCal) {
			this.rateScheduleChargeCal = rateScheduleChargeCal;
		}
	
		public List<WebElement> getMeterRegisterOptions() {
			return meterRegisterOptions;
		}
	
		public void setMeterRegisterOptions(List<WebElement> meterRegisterOptions) {
			this.meterRegisterOptions = meterRegisterOptions;
		}
	
		public List<WebElement> getChargeDescriptionOptions() {
			return chargeDescriptionOptions;
		}
	
		public void setChargeDescriptionOptions(
				List<WebElement> chargeDescriptionOptions) {
			this.chargeDescriptionOptions = chargeDescriptionOptions;
		}
	
		public List<WebElement> getIndexTypeOptions() {
			return indexTypeOptions;
		}
	
		public void setIndexTypeOptions(List<WebElement> indexTypeOptions) {
			this.indexTypeOptions = indexTypeOptions;
		}
	
		public List<WebElement> getRateScheduleUOMoptions() {
			return rateScheduleUOMoptions;
		}
	
		public void setRateScheduleUOMoptions(
				List<WebElement> rateScheduleUOMoptions) {
			this.rateScheduleUOMoptions = rateScheduleUOMoptions;
		}
	
		public WebElement getRateScheduleUOM() {
			return rateScheduleUOM;
		}
	
		public void setRateScheduleUOM(WebElement rateScheduleUOM) {
			this.rateScheduleUOM = rateScheduleUOM;
		}
	
		public WebElement getChargeDescriptionSpan() {
			return chargeDescriptionSpan;
		}
	
		public void setChargeDescriptionSpan(WebElement chargeDescriptionSpan) {
			this.chargeDescriptionSpan = chargeDescriptionSpan;
		}
	
		public WebElement getAddChargesBut() {
			return addChargesBut;
		}
	
		public void setAddChargesBut(WebElement addChargesBut) {
			this.addChargesBut = addChargesBut;
		}
	
		public List<WebElement> getRateSchedulePassThroughOptions() {
			return rateSchedulePassThroughOptions;
		}
	
		public void setRateSchedulePassThroughOptions(
				List<WebElement> rateSchedulePassThroughOptions) {
			this.rateSchedulePassThroughOptions = rateSchedulePassThroughOptions;
		}
	
		public WebElement getRateSchedulePassThroughSpan() {
			return rateSchedulePassThroughSpan;
		}
	
		public void setRateSchedulePassThroughSpan(
				WebElement rateSchedulePassThroughSpan) {
			this.rateSchedulePassThroughSpan = rateSchedulePassThroughSpan;
		}
	
		public List<WebElement> getRateScheduleUtilityOptions() {
			return rateScheduleUtilityOptions;
		}
	
		public void setRateScheduleUtilityOptions(
				List<WebElement> rateScheduleUtilityOptions) {
			this.rateScheduleUtilityOptions = rateScheduleUtilityOptions;
		}
	
		public WebElement getRateScheduleUtilityElement() {
			return rateScheduleUtilityElement;
		}
	
		public void setRateScheduleUtilityElement(
				WebElement rateScheduleUtilityElement) {
			this.rateScheduleUtilityElement = rateScheduleUtilityElement;
		}
	
		public List<WebElement> getRateScheduleMarketOptions() {
			return rateScheduleMarketOptions;
		}
	
		public void setRateScheduleMarketOptions(
				List<WebElement> rateScheduleMarketOptions) {
			this.rateScheduleMarketOptions = rateScheduleMarketOptions;
		}
	
		public WebElement getRateScheduleMarketspan() {
			return rateScheduleMarketspan;
		}
	
		public void setRateScheduleMarketspan(WebElement rateScheduleMarketspan) {
			this.rateScheduleMarketspan = rateScheduleMarketspan;
		}
	
		public WebElement getModifyProductAttributes() {
			WaitUtils.waitForElement(driver, modifyProductAttributes);
			return modifyProductAttributes;
		}
	
		public void setModifyProductAttributes(WebElement modifyProductAttributes) {
			this.modifyProductAttributes = modifyProductAttributes;
		}
	
		public WebElement getRateScheduleNextButton() {
			return rateScheduleNextButton;
		}
	
		public void setRateScheduleNextButton(WebElement rateScheduleNextButton) {
			this.rateScheduleNextButton = rateScheduleNextButton;
		}
	
		public WebElement getRateScheduleLabel() {
			return rateScheduleLabel;
		}
	
		public void setRateScheduleLabel(WebElement rateScheduleLabel) {
			this.rateScheduleLabel = rateScheduleLabel;
		}
	
		public void setRateScheduleName(String var) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", rateScehduleName);
			rateScehduleName.click();
			rateScehduleName.sendKeys(var);
		}
	
		public WebElement getRateScehduleName() {
			return rateScehduleName;
		}
	
		public void setRateScehduleName(WebElement rateScehduleName) {
			this.rateScehduleName = rateScehduleName;
		}
	
		public WebElement getEditRateScheudleLink1() {
			WaitUtils.waitForElement(driver, editRateScheudleLink1);
			return editRateScheudleLink1;
		}
	
		public void setEditRateScheudleLink1(WebElement editRateScheudleLink1) {
			this.editRateScheudleLink1 = editRateScheudleLink1;
		}
	
		public WebElement getEditRatescheduleLink() {
			return editRatescheduleLink;
		}
	
		public void setEditRatescheduleLink(WebElement editRatescheduleLink) {
			this.editRatescheduleLink = editRatescheduleLink;
		}
	
		public WebElement getRateScheduleButton() {
			WaitUtils.waitForElement(driver, rateScheduleButton);
			return rateScheduleButton;
		}
	
		public void setRateScheduleButton(WebElement rateScheduleButton) {
			this.rateScheduleButton = rateScheduleButton;
		}
	
		public WebElement getEditRateScheduleIcon() {
			return editRateScheduleIcon;
		}
	
		public void setEditRateScheduleIcon(WebElement editRateScheduleIcon) {
			this.editRateScheduleIcon = editRateScheduleIcon;
		}
	
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div/div/div/div/table/thead/tr[1]/th/span[2]/div[1]/i[1]")
		private WebElement exportingExcel;
	
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div/div/div/div/table/thead/tr[1]/th/input")
		private WebElement searchProductTextField;
	
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div/div/div/div/table/tbody/tr/td[2]/span")
		private WebElement spanElement;
	
		public WebElement getSearchProductTextField() {
			return searchProductTextField;
		}
	
		public void setSearchProductTextField(WebElement searchProductTextField) {
			this.searchProductTextField = searchProductTextField;
		}
	
		public void clickonWebPage() {
			Actions builder = new Actions(driver);
			builder.moveToElement(spanElement).click().perform();
		}
	
		public void setProductValue(String var) {
			searchProductTextField.clear();
			searchProductTextField.sendKeys(var);
			searchProductTextField.sendKeys(Keys.ENTER);
	
		}
	
		@FindBy(xpath = "//*[@id='content']/div[2]/form/div/div/div/div/table/tbody/tr/td[2]/span")
		private WebElement searchProductCode;
	
		public WebElement getSearchProductCode() {
			return searchProductCode;
		}
//	Add a comment to this line
	
		public void setSearchProductCode(WebElement searchProductCode) {
			this.searchProductCode = searchProductCode;
		}

	// Setting up productName
	public void setProductName(String productName) {
		searchProductCodeOrName.clear();
		searchProductCodeOrName.sendKeys(productName);
	}

	// Getting element productName
	public WebElement getProductNameElement() {
		return searchProductCodeOrName;
	}

	// Get Product Search Label
	public WebElement getProductSearchLabel() {
		return productSearchLabel;
	}

	// Getting element product Filter Criteria
	public WebElement getProductFilterCriteria() {
		return productFilterCriteria;
	}

	// Getting element market
	public WebElement getSelectMarketsElement() {
		return selectMarketOption;
	}

	// Getting element market text box
	public WebElement getSelectMarketsElementTextBox() {
		return selectMarkets;
	}

	// Getting element market option value
	public List<WebElement> getSelectMarketsOptionElement() {
		return selectMarketOptionElement;
	}

	// Getting element utility option value
	public List<WebElement> getSelectUtilityOptionElement() {
		return selectUtilityOptionElement;
	}

	// Getting element zone option value
	public List<WebElement> getSelectZoneOptionElement() {
		return selectZoneOptionElement;
	}

	// Getting element segment option value
	public List<WebElement> getSelectSegmentOptionElement() {
		return selectSegmentOptionElement;
	}

	// Getting element Sales Channel option value
	public List<WebElement> getSelectSalesChannelOptionElement() {
		return selectSalesChannelOptionElement;
	}

	// Getting element Contract Sources option value
	public List<WebElement> getSelectContractSourcesOptionElement() {
		return selectContractSourcesOptionElement;
	}

	// Getting element utility
	public WebElement getSelectUtilitiesElement() {
		return selectUtilityOption;
	}

	// Getting element utility text box
	public WebElement getSelectUtilitiesElementTextBox() {
		return selectUtility;
	}

	// Getting element zone text box
	public WebElement getSelectZoneElementTextBox() {
		return selectZone;
	}

	// Getting element zone
	public WebElement getSelectZonesElement() {
		return selectZoneOption;
	}

	// Getting element segment
	public WebElement getSelectSegmentElement() {
		return selectSegmentOption;
	}

	// Getting element segment text box
	public WebElement getSelectSegmentElementTextBox() {
		return selectSegment;
	}

	// Getting element sales channel
	public WebElement getSelectSalesChannelElement() {
		return selectSalesChannelOption;
	}

	// Getting element sales channel text box
	public WebElement getSelectSalesChannelElementTextBox() {
		return selectChannel;
	}

	// Getting element contract source text box
	public WebElement getSelectContractSourceElementTextBox() {
		return selectContractSource;
	}

	// Getting element Contract Sources
	public WebElement getSelectContractSourcesElement() {
		return selectContractSourcesOption;
	}

	// Getting element Promo Allowed
	public WebElement getselectPromoAllowedElement() {
		searchPromoAllowedActivate.click();
		return selectPromoAllowed;
	}

	// Getting element Search
	public WebElement getSelectSearchElement() {
		return selectSearch;
	}

	// Getting element WebTable
	public WebElement getWebTable() {
		return webtable;
	}

	// Getting element WebTable locked result
	public WebElement getWebTableLocked() {
		return webtablelocked;
	}

	// Getting element Dropobject
	public WebElement getDropObject() {
		return dropTarget;
	}

	// Getting element Reset
	public void clickReset() {
		selectReset.click();
	}

	// Getting element table Header locked
	public WebElement getWebTableHeaderLocked() {
		return webTableHeaderLocked;
	}

	// Getting element table Header wrap
	public WebElement getWebTableHeaderWrap() {
		return webTableHeaderWrap;
	}

	// Getting element Market Div
	public WebElement getMarketDiv() {
		return selectMarketOption;
	}

	// Getting Export to Excel
	public WebElement getExportToExcel() {
		return exportToExcel;
	}

	// Getting element Utility Div
	public WebElement getUtilityDiv() {
		return selectUtilityOption;
	}

	// Getting element Zone Div
	public WebElement getZoneDiv() {
		return selectZoneOption;
	}

	// Getting element Segment Div
	public WebElement getSegmentDiv() {
		return selectSegmentOption;
	}

	// Getting element Sales Channel Div
	public WebElement getSalesChannelDiv() {
		return selectSalesChannelOption;
	}

	// Getting element Contract Source Div
	public WebElement getContractSourceDiv() {
		return selectContractSourcesOption;
	}

	// Clear Drop Down Form Element
	public void clearDropDownElement(WebElement div, String value) {
		div.findElement(
				By.xpath("//span[contains(text(),'"+  value
						+ "')]/../preceding-sibling::span")).click();
	}

	// Angular Result table Column removal header option
	public void columnRemovalHeader(WebElement ColumnHeader) {
		ColumnHeader.findElement(
				By.xpath("/a/span[contains(@class,'arrowhead')]")).click();
		if (Element
				.isVisible(
						driver,
						By.xpath("//span[contains(text(),'Columns')]/span[contains(@class,'arrow')]"))) {
			driver.findElement(
					By.xpath("//span[contains(text(),'Columns')]/span[contains(@class,'arrow')]"))
					.click();
		}
	}

	// Clear Drop Target Element Exist
	public boolean chkDropTargetElementExist(String value) {
		if (Element.isVisible(
				driver,
				dropTarget.findElement(By.xpath("div[@data-title='"+  value
						 +"']")))) {
			return true;
		} else {
			return false;
		}
	}

	// Getting element Error message when no field selected for search
	public boolean getNoFieldSelectedErrorMessageElement(String errorMsg) {
		if (Element.isVisible(driver, errorSelectSearchcriteria)
				&& errorMessage.getText().contains(errorMsg))
			return true;
		else
			return false;
	}

	// Getting element Information pop up if result returns 0 records
	public boolean getNoFieldSelectedInformationMessageElement(String infoMsg) {
		if (Element.isVisible(driver, informationPopUpErrorMessage)
				&& infoMessage.getText().contains(infoMsg))
			return true;
		else
			return false;
	}

	// Getting Error Element

	public WebElement getErrorElement() {
		return errorMessageClose;
	}

	// Getting info element

	public WebElement getInfoElement() {
		return infoMessageClose;
	}

	// Getting element error message close
	public void getErrorClose() {
		try {
			while (Element.isVisible(driver, errorMessageClose)) {
				Element.clickJS(driver, errorMessageClose);
				if (!errorMessageClose.isDisplayed()) {
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	// Getting element information message close
	public void getInfoClose() {
		try {
			while (Element.isVisible(driver, infoMessageClose)) {
				Element.clickJS(driver, infoMessageClose);
				if (!infoMessageClose.isDisplayed()) {
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	// Getting element group delete
	public void getGroupDelete() {
		if (Element.isVisible(driver, errorGroupDelete)) {
			errorGroupDelete.click();
		}
	}

}
