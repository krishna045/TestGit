// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.WaitUtils;

/**
 * The Class SearchQuotePage.
 *
 * @author: Shilpi Malpani
 * @mail: smalpanig@znalytics.com
 * @date: April 22, 2015
 * 
 *        purpose of this class is to store all the pages object related to
 *        Search Quote Page.
 */

public class SearchQuotePage {

	private WebDriver driver;

	public SearchQuotePage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object Sales label.
	@FindBy(xpath = "//h4[contains(@class,'no-margin')]")
	private WebElement salesLabel;

	// Finding object Sales.
	@FindBy(xpath = "//a[@class='p-lg bg-warning item inline m-xs']/span[@class='p-xs p-h-sm bottom text-xs hidden-xs ng-scope']")
	private WebElement salesModuleBtn;

	// Finding object Company Name.
	@FindBy(xpath = "//a[contains(text(),'Company Name')]")
	private WebElement companyName;

	// Finding object ContractNumber.
	@FindBy(xpath = "//a[contains(text(),'Contract Number')]")
	private WebElement contractNumber;

	// Finding object Market.
	@FindBy(xpath = "//a[contains(text(),'Market')]")
	private WebElement market;

	// Finding object Reference Number.
	@FindBy(xpath = "//a[contains(text(),'Reference Number')]")
	private WebElement referenceNumber;

	// Finding object Last Updated.
	@FindBy(xpath = "//a[contains(text(),'Last Updated')]")
	private WebElement lastUpdated;

	// Finding object Service Request Status.
	@FindBy(xpath = "//a[contains(text(),'Service Request Status')]")
	private WebElement serviceRequestStatus;

	// Finding object Offer Expiration Date.
	@FindBy(xpath = "//a[contains(text(),'Offer Expiration Date')]")
	private WebElement offerExpirationDate;

	// Finding object Webtable header wrap
	@FindBy(xpath = "//div[@class='k-grid-header-wrap']//thead")
	private WebElement webTableHeaderWrap;

	// Finding object drop target
	@FindBy(xpath = "//div[@data-role='droptarget']")
	private WebElement dropTarget;

	// Finding object Export to excel
	@FindBy(xpath = "//a[contains(text(),'Export to Excel') and contains(@class,'k-grid-excel')]")
	private WebElement exportToExcel;

	// Finding object Group By delete
	@FindBy(xpath = "//div[@id='SalesDashBoard']//span[contains(@class,'k-group-delete')]")
	private WebElement errorGroupDelete;

	// Finding object Group By labelTableHeader
	@FindBy(xpath = "//div[@class='k-grid-header']//table[@role='grid']//thead")
	private WebElement labelTableHeader;

	// Finding object Webtable result
	@FindBy(xpath = "//div[@class='k-grid-content']/table[@role='grid']/tbody")
	private WebElement webtable;

	// Finding object Webtable header
	@FindBy(xpath = "//div[@class='k-grid-header']//thead")
	private WebElement webTableHeader;

	// Click on Sales module button.
	public WebElement getSalesLabel() {
		return salesLabel;
	}

	// Click on Sales module button.
	public void clickSalesBtn() {
		Element.click(driver, salesModuleBtn);
		WaitUtils
				.waitForElement(
						driver,
						By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));

	}

	// Check Company name label is displayed.
	public WebElement getComanyName() {
		return companyName;
	}

	// Check ContractNumber label is displayed.
	public WebElement getContractNumber() {
		return contractNumber;
	}

	// Check market label is displayed.
	public WebElement getMarket() {
		return market;
	}

	// Check ReferenceNumber label is displayed.
	public WebElement getReferenceNumber() {
		return referenceNumber;
	}

	// Check LastUpdated label is displayed.
	public WebElement getLastUpdated() {
		return lastUpdated;
	}

	// Check ServiceRequestStatus label is displayed.
	public WebElement getServiceRequestStatus() {
		return serviceRequestStatus;
	}

	// Check OfferExpirationDate label is displayed.
	public WebElement getOfferExpirationDate() {
		return offerExpirationDate;
	}

	// Getting element Dropobject
	public WebElement getDropObject() {
		return dropTarget;
	}

	// Getting Export to Excel
	public WebElement getExportToExcel() {
		return exportToExcel;
	}

	// Getting element WebTable
	public WebElement getWebTable() {
		return webtable;
	}

	// Getting element Header
	public WebElement getWebTableHeader() {
		return webTableHeader;
	}

	// Getting element Header
	public WebElement getLabelTableHeader() {
		return labelTableHeader;
	}

	// Clear Drop Target Element Exist
	public boolean chkDropTargetElementExist(String value) {
		if (Element.isVisible(
				driver,
				dropTarget.findElement(By.xpath("div[@data-title='" + value
						+ "']")))) {
			return true;
		} else {
			return false;
		}
	}

	// Getting element group delete
	public void getGroupDelete() {
		if (Element.isVisible(driver, errorGroupDelete)) {
			errorGroupDelete.click();
		}
	}

	// Getting element table Header wrap
	public WebElement getWebTableHeaderWrap() {
		return webTableHeaderWrap;
	}

}
