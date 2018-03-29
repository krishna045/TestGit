// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.znalytics.framework.utility.Element;

/**
 * The Class CreateProductPage.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * @purpose:Purpose of this class is to store all the pages object related to
 *                  Create Product Page.
 */

public class CreateProductPage {

	private WebDriver driver;

	public CreateProductPage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object Product Code
	@FindBy(id = "ProductCode")
	private WebElement productCode;

	// Finding object Product Name
	@FindBy(id = "ProductName")
	private WebElement productName;

	// Finding object Product Description
	@FindBy(id = "ProductDescription")
	private WebElement productDescription;

	// Finding object Product Tag Line
	@FindBy(id = "ProductTagLine")
	private WebElement productTagLine;

	// Finding object Effective date calendar icon
	@FindBy(xpath = "//span[@aria-controls='dtCtrlEffectiveDate_dateview']/span[@class='k-icon k-i-calendar']")
	private WebElement effectiveDateCalendarIcon;

	// Finding object Effective date calendar div
	@FindBy(xpath = "//div[@id='dtCtrlEffectiveDate_dateview']")
	private WebElement effectiveDateCalendarDiv;

	// Effective date Element
	@FindBy(id = "dtCtrlEffectiveDate")
	private WebElement effectiveDate;

	// Finding object Expiration date calendar icon
	@FindBy(xpath = "//span[@aria-controls='dtCtrlExpirationDate_dateview']/span[@class='k-icon k-i-calendar']")
	private WebElement expirationDateCalendarIcon;

	// Finding object Expiration date calendar div
	@FindBy(xpath = "//div[@id='dtCtrlExpirationDate_dateview']")
	private WebElement expirationDateCalendarDiv;

	// Expiration date Element
	@FindBy(id = "dtCtrlExpirationDate")
	private WebElement expirationDate;

	// Finding object select No Expiration Date
	@FindBy(id = "checkBoxNoExpirationDate")
	private WebElement noExpirationDate;

	// Finding object search contract source click
	@FindBy(xpath = "//div[@id='single_select_ContractSource']")
	private WebElement contractSourceElement;

	// Finding object search contract source Span click
	@FindBy(xpath = "//div[@id='single_select_ContractSource']//span[contains(@aria-label,'Select box activate')]")
	private WebElement contractSourceSpanElement;

	// Finding object select contract source
	@FindBy(xpath = "//*[@id='ui-select-choices-row-0-0']/a")
	private List<WebElement> contractSourceOptions;

	// Product Type Element
	@FindBy(id = "single_select_ProductType")
	private WebElement productTypeElement;

	// Product Type Options
	@FindBy(xpath = "//*[@id='ui-select-choices-row-1-0']/a")
	private List<WebElement> productTypeOptions;

	// Service Type Element
	@FindBy(id = "single_select_ServiceType")
	private WebElement serviceTypeElement;

	// Service Type Options
	@FindBy(xpath = "//*[@id='ui-select-choices-row-2-0']/a")
	private List<WebElement> serviceTypeOptions;

	// Pricing Assignment Element
	@FindBy(id = "single_select_PricingAssignmentMethod")
	private WebElement pricingAssignmentElement;

	// Pricing Assignment Options
	@FindBy(xpath = "//*[@id='ui-select-choices-row-3-0']/a")
	private List<WebElement> pricingAssignmentOptions;

	// Roll-over Product Element
	@FindBy(id = "single_select_RolloverProduct")
	private WebElement rolloverProductElement;

	// Roll-over Product Options
	@FindBy(xpath = ".//div[contains(@id,'ui-select-choices-row-11')]")
	private List<WebElement> rolloverProductOptions;

	// Product Terms In Months Element
	@FindBy(id = "single_select_ProductTermsInMonths")
	private WebElement productTermsInMonthsElement;

	// Product Terms In Months Options
	@FindBy(xpath = ".//*[@id='ui-select-choices-row-5-3']/a")
	private List<WebElement> productTermsInMonthsOptions;

	// Finding object select Check box for Allow promotions
	@FindBy(id = "AllowPromotions")
	private WebElement allowPromotions;

	// Finding object select Check box for Enrollment Only
	@FindBy(id = "EnrollmentOnly")
	private WebElement enrollmentOnly;

	// Finding object select Check box for Auto Renew Only
	@FindBy(id = "AutoRenewOnly")
	private WebElement autoRenewOnly;

	// Guaranteed Savings Element
	@FindBy(id = "Guaranteed Savings")
	private WebElement guaranteedSavings;

	// Guaranteed Savings Element
	@FindBy(id = "Renewables")
	private WebElement renewables;

	// Sales channel Element
	@FindBy(id = "multiple_select_SalesChannels")
	private WebElement salesChannelElement;

	// Sales channel Options
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-13')]")
	private List<WebElement> salesChannelOptions;

	// Customer Type Element
	@FindBy(id = "multiple_select_CustomerTypes")
	private WebElement custTypeElement;

	// Customer Type Options
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-14')]")
	private List<WebElement> custTypeOptions;

	// Commission Type Element
	@FindBy(id = "single_select_CommissionType")
	private WebElement commissionTypeElement;

	// Finding object select Commission Type
	@FindBy(xpath = "//div[contains(@id,'ui-select-choices-row-15')]")
	private List<WebElement> commissionTypeOptions;

	// Finding object Commission Amount
	@FindBy(id = "CommissionAmount")
	private WebElement commissionAmount;

	@FindBy(xpath = "//label[contains(text(),'Commission Amount')]/..//input[1]")
	private WebElement commissionAmountInput;

	// Finding object select Link Rate Details
	@FindBy(id = "LinkRateDetails")
	private WebElement linkRateDetails;

	// Next Button
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement nextButton;

	// Cancel Button
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement cancelButton;

	// Cancel Add Product Button
	@FindBy(xpath = "//button[contains(text(),'Cancel Product Add')]")
	private WebElement cancelAddProductButton;

	// button[contains(text(),'Cancel Product Add')]

	// Error message
	@FindBy(xpath = "//span[contains(text(),'Error')]")
	private WebElement errorMessage;

	// Error message close icon
	@FindBy(xpath = "//span[contains(text(),'Close')]/..")
	private WebElement errorMessageCloseIcon;

	// Error message text
	@FindBy(xpath = "//span[contains(text(),'Error')]/../following-sibling::div//td[2]")
	private WebElement errorMessageText;

	// Add product label
	@FindBy(xpath = "//h4[contains(text(),'Add Product')]")
	private WebElement addProductlabel;

	// Finding object Information pop when no matching record found
	@FindBy(xpath = "//span[contains(.,'Information')]")
	private WebElement informationPopUpErrorMessage;

	// Finding object Information pop up message
	@FindBy(xpath = "//td[@class='p-md ng-binding']")
	private WebElement infoMessage;

	// Finding object Information pop up message close
	@FindBy(xpath = "//span[contains(text(),'Information')]/following-sibling::div/a/span[contains(text(),'Close')]")
	private WebElement infoMessageClose;
	
	@FindBy(xpath="//*[@id='productReviewParent']/div/div[2]/div/div[3]/div[2]/div/div/button")
		private WebElement CloseButton;
	//Add a comment to this line
	
		public WebElement getCloseButton() {
			return CloseButton;
		}
	
		public void setCloseButton(WebElement closeButton) {
			CloseButton = closeButton;
		}
	
		public WebElement getExpirationDateCalendarIcon() {
			return expirationDateCalendarIcon;
		}
	
		public void setExpirationDateCalendarIcon(
				WebElement expirationDateCalendarIcon) {
			this.expirationDateCalendarIcon = expirationDateCalendarIcon;
		}

	// set product code
	public void setProductCode(String var) {
		productCode.clear();
		productCode.sendKeys(var);
	}

	// get add product label
	public WebElement getAddProductLabel() {
		return addProductlabel;
	}

	// get product code
	public WebElement getProductCode() {
		return productCode;
	}

	// set product name
	public void setProductName(String var) {
		productName.clear();
		productName.sendKeys(var);
	}

	// get product name
	public WebElement getProductName() {
		return productName;
	}

	// get product description
	public WebElement getProductDescription() {
		return productDescription;
	}

	// set product description
	public void setProductDescription(String var) {
		productDescription.clear();
		productDescription.sendKeys(var);
	}

	// get product tag line
	public WebElement getProductTagLine() {
		return productTagLine;
	}

	// set product Tag Line
	public void setProductTagLine(String var) {
		productTagLine.clear();
		productTagLine.sendKeys(var);
	}

	// get effective date calendar icon
	public WebElement getEffectiveDateCalendarIcon() {
		return effectiveDateCalendarIcon;
	}

	// set effective date calendar icon
	/*public void setEffectiveDateCalendarIcon(String var) {
		effectiveDateCalendarDiv.findElement(By.xpath("//a[@data-value='"  var  "']")).click();
	}
*/
	// set effective date
	public void setEffectiveDate(String startDate) {
		effectiveDate.clear();
		effectiveDate.sendKeys(startDate);
	}

	// set expiration date
	public void setExpirationDate(String endDate) {
		expirationDate.sendKeys(endDate);
	}

	// select no expiration date
	public void setNoExpDate() {
		noExpirationDate.click();
	}

	// get Contract Source element
	public WebElement getContractSourceElement() {
		return contractSourceElement;
	}

	// get Contract Source Span element
	public WebElement getContractSourceSpanElement() {
		return contractSourceSpanElement;
	}

	// get Contract Source options
	public List<WebElement> getContractSourceOptions() {
		return contractSourceOptions;
	}

	// get Product Type element
	public WebElement getProductTypeElement() {
		return productTypeElement;
	}

	// get Product Type options
	public List<WebElement> getProductTypeOptions() {
		return productTypeOptions;
	}

	// get Service Type element
	public WebElement getServiceTypeElement() {
		return serviceTypeElement;
	}

	// get Service Type options
	public List<WebElement> getServiceTypeOptions() {
		return serviceTypeOptions;
	}

	// get Pricing Assignment element
	public WebElement getPricingAssignmentElement() {
		return pricingAssignmentElement;
	}

	// get Pricing Assignment options
	public List<WebElement> getPricingAssignmentOptions() {
		return pricingAssignmentOptions;
	}

	// get Rollover Product element
	public WebElement getRolloverProductElement() {
		return rolloverProductElement;
	}

	// get Rollover Product options
	public List<WebElement> getRolloverProductOptions() {
		return rolloverProductOptions;
	}

	// get Product Terms In Months element
	public WebElement getProductTermsInMonthsElement() {
		return productTermsInMonthsElement;
	}

	// get Product Terms In Months options
	public List<WebElement> getProductTermsInMonthsOptions() {
		return productTermsInMonthsOptions;
	}

	// get promo allowed element
	public WebElement getPromoAllowedElement() {
		return allowPromotions;
	}

	// get enrollment only element
	public WebElement getEnrollmentOnlyElement() {
		return enrollmentOnly;
	}

	// get auto renew only element
	public WebElement getAutoRenewOnlyElement() {
		return autoRenewOnly;
	}

	// get sales channels element
	public WebElement getSalesChannelsElement() {
		return salesChannelElement;
	}

	// get sales channels options
	public List<WebElement> getSalesChannelsOptions() {
		return salesChannelOptions;
	}

	// get customer type element
	public WebElement getCustomerTypeElement() {
		return custTypeElement;
	}

	// get customer type options
	public List<WebElement> getCustomerTypeOptions() {
		return custTypeOptions;
	}

	// get commission type element
	public WebElement getCommissionTypeElement() {
		return commissionTypeElement;
	}

	// get commission type options
	public List<WebElement> getCommissionTypeOptions() {
		return commissionTypeOptions;
	}

	// get commission type element
	public WebElement getCommissionAmountElement() {
		return commissionAmountInput;
	}

	// get commission type element
	public WebElement getCommissionAmountEditBox() {
		return commissionAmount;
	}

	// get Next button element
	public WebElement getNextButton() {
		return nextButton;
	}

	// get Cancel button element
	public WebElement getCancelButton() {
		return cancelButton;
	}

	// get Cancel Add Product button element
	public WebElement getCancelAddProductButton() {
		return cancelAddProductButton;
	}

	// get all error object count
	public boolean hasError(WebElement el) {
		if (el.findElement(By.xpath("..")).getAttribute("class")
				.contains("has-error")) {
			return true;
		}
		return false;
	}

	public String getCounterText(WebElement el) {
		return el.findElement(By.xpath("following-sibling::span")).getText();
	}

	public boolean verifyErrorMessageAndClose(String errorMsg) {
		if (errorMessage.isDisplayed()
				&& errorMessageText.getText().trim().equalsIgnoreCase(errorMsg)) {
			getErrorClose();
			return true;
		}
		return false;
	}

	// Getting element error message close
	public void getErrorClose() {
		try {
			while (Element.isVisible(driver, errorMessageCloseIcon)) {
				Element.clickJS(driver, errorMessageCloseIcon);
				if (!errorMessageCloseIcon.isDisplayed()) {
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	public WebElement getInputFieldForSingleSelect(WebElement el) {
		return el.findElement(By.xpath("input[1]"));
	}

	public WebElement getInputFieldForMultipleSelect(WebElement el) {
		return el.findElement(By.xpath("div/input"));
	}

	// Information message pop up, message verification
	public boolean informationMessageBox(String infoMsg) {
		if (Element.isVisible(driver, informationPopUpErrorMessage)
				&& infoMessage.getText().contains(infoMsg))
			return true;
		else
			return false;
	}
	
	public void geteffectiveDateCalendar(String date) {
	//	Add a comment to this line
				List<WebElement> columns = effectiveDateCalendarDiv.findElements(By
						.tagName("td"));
				for (WebElement cell : columns) {
					if (cell.getText().equals(date)) {
						cell.findElement(By.linkText(date)).click();
						break;
					}
				}
			}

	// Getting element information message close
	public void infoMessageClose() {
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

	// Highlight All UI Object for Create Product Page
	public void allUiObjectExistAddProduct() {
		Element.highlightElement(driver, productCode);
		Element.highlightElement(driver, productName);
		Element.highlightElement(driver, productDescription);
		Element.highlightElement(driver, productTagLine);
		Element.highlightElement(driver, effectiveDateCalendarIcon);
		Element.highlightElement(driver, expirationDateCalendarIcon);
		Element.highlightElement(driver, noExpirationDate);
		Element.highlightElement(driver, contractSourceElement);
		Element.highlightElement(driver, productTypeElement);
		Element.highlightElement(driver, serviceTypeElement);
		Element.highlightElement(driver, pricingAssignmentElement);
		Element.highlightElement(driver, rolloverProductElement);
		Element.highlightElement(driver, productTermsInMonthsElement);
		Element.highlightElement(driver, allowPromotions);
		Element.highlightElement(driver, enrollmentOnly);
		Element.highlightElement(driver, autoRenewOnly);
		Element.highlightElement(driver, guaranteedSavings);
		Element.highlightElement(driver, renewables);
		Element.highlightElement(driver, salesChannelElement);
		Element.highlightElement(driver, custTypeElement);
		Element.highlightElement(driver, commissionTypeElement);
		Element.highlightElement(driver, commissionAmount);
		Element.highlightElement(driver, linkRateDetails);
		Element.highlightElement(driver, nextButton);
	}
}