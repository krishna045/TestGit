// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.WebPage;

/**
 * The Class NavigationPage.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * 
 *        purpose of this class is to store all the pages object related to
 *        Navigation.
 */
public class NavigationPage {

	private WebDriver driver;

	public NavigationPage(WebDriver driver) {
		this.driver = driver;
	}

	// ---------Block for all Marketing related Navigation object-----//

	// Finding object Marketing
	@FindBy(xpath = "//i[contains(@class,'ui-icon fa fa-building fa-2x')]")
	private WebElement marketing;

	// Finding object Product Admin
	@FindBy(xpath = "//span[contains(@class,'ng-binding ng-scope')]")
	private WebElement productAdmin;

	// Finding object Product Search
	@FindBy(xpath = "//a/span[contains(text(),'Product Search')]")
	private WebElement productSearch;

	// Finding object Add Product
	@FindBy(xpath = "//a/span[contains(text(),'Add Product')]")
	private WebElement addProduct;

	// Finding object Logo
	@FindBy(xpath = "//img[contains(@src,'logo')]")
	private WebElement logo;

	// Finding object Sales.
	@FindBy(xpath = "//a[@class='p-lg bg-warning item inline m-xs']/span[@class='p-xs p-h-sm bottom text-xs hidden-xs ng-scope']")
	private WebElement salesModuleBtn;

	// Finding object Quote Request.
	@FindBy(xpath = "//span[contains(text(),'Quote Request')]")
	private WebElement quoteRequestTab;

	// Navigate to Search Product Page
	public void navigateSearchProductPage() {
		Element.click(this.driver, marketing);
		WebPage.waitForPageToLoad(this.driver);
		Element.click(this.driver, productAdmin);
		WebPage.waitForPageToLoad(this.driver);
		Element.click(this.driver, productSearch);
		WebPage.waitForPageToLoad(driver);
	}

	public void navigateQuoteRequestPage() {
		Element.click(this.driver, salesModuleBtn);
		WebPage.waitForPageToLoad(this.driver);
		Element.click(this.driver, quoteRequestTab);
		WebPage.waitForPageToLoad(this.driver);
	}
	
	public void navigateSalesDashboard(){
		Element.click(this.driver, salesModuleBtn);
		WebPage.waitForPageToLoad(this.driver);
	}

	// Navigate to Add Product Page
	public void navigateAddProductPage() {
		WebPage.waitForAngularRequestsToFinish(this.driver);
		Element.click(this.driver, marketing);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (d.getCurrentUrl().toLowerCase().contains("dashboard"));
			}
		};
		WebPage.waitForPageToLoad(this.driver);
		wait.until(e);
		Element.click(this.driver, productAdmin);
		WebPage.waitForAngularRequestsToFinish(this.driver);
		Element.click(this.driver, addProduct);
		WebPage.waitForPageToLoad(driver);
	}

	// Navigate to Add Product Page from search page
	public void navigateAddProductPageFromSearchPage() {
		Element.click(this.driver, addProduct);
		WebPage.waitForPageToLoad(driver);
	}

	// Open Menu
	public void openHeaderMenu() {
		Element.click(this.driver, logo);
		WebPage.waitForPageToLoad(driver);
	}

	// ---------End Of Block-----------------------------------------//

	// Finding object Sales
	@FindBy(xpath = "//span[contains(text(),'Sales')]")
	private WebElement sales;

	// Finding object Service
	@FindBy(xpath = "//span[contains(text(),'Service')]")
	private WebElement service;

	// Finding object Reports
	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	private WebElement reports;

	// Finding object Tenant Setup
	@FindBy(xpath = "//span[contains(text(),'Tenant Setup')]")
	private WebElement tenantSetup;

	// Finding object Communications
	@FindBy(xpath = "//span[contains(text(),'Communications')]")
	private WebElement communications;

}
