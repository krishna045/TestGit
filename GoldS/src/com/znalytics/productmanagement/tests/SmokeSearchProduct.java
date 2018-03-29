// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.LoginPage;
import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.AngularDropDown;
import com.znalytics.framework.utility.DragAndDrop;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.ExportExcel;
import com.znalytics.framework.utility.Utils;
import com.znalytics.framework.utility.WebPage;
import com.znalytics.framework.utility.WebTable;
import com.znalytics.productmanagement.pages.SearchProductPage;

/**
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 19, 2015
 * @purpose:This Class is to execute all test cases for searching product
 *               Priority=100
 * 
 */
@Test(groups = { "napower" })
public class SmokeSearchProduct {
	WebDriver browser = Setup.getInstance().getDriver();;
	NavigationPage navigate;
	LoginPage login;
	SearchProductPage searchProduct;
	WebTable webTab;
	WebTable webTabLocked;
	WebTable webTabHeaderLocked;
	WebTable webTabHeaderWrap;
	AngularDropDown DropDown;
	String getCurrentApplicationURL;

	SmokeSearchProduct() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		login = PageFactory.initElements(browser, LoginPage.class);
		searchProduct = PageFactory.initElements(browser,
				SearchProductPage.class);
		webTab = new WebTable(searchProduct.getWebTable(), browser);
		webTabLocked = new WebTable(searchProduct.getWebTableLocked(), browser);
		webTabHeaderLocked = new WebTable(
				searchProduct.getWebTableHeaderLocked(), browser);
		webTabHeaderWrap = new WebTable(searchProduct.getWebTableHeaderWrap(),
				browser);
		DropDown = new AngularDropDown();
	}

	/*
	 * Login in application and navigate to respective page
	 */

	@Test(priority = 1, groups = { "napower" })
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

	/*
	 * Test Error message while no data for search parameter
	 */

	@Test(priority = 2, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testErrorMessageWithNoData() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.clickReset();
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

	/*
	 * Test Export to Excel
	 */
	@Test(priority = 3, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testExportExcel() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		Element.scrollElementIntoView(browser,
				searchProduct.getProductNameElement());
		String productNameToSearch = "Product";
		searchProduct.setProductName(productNameToSearch);
		searchProduct.getSelectSearchElement().click();
		Element.highlightElement(browser, searchProduct.getExportToExcel());
		Element.scrollElementIntoView(browser,
				searchProduct.getProductFilterCriteria());
		ExportExcel exportExcelData = new ExportExcel(
				searchProduct.getExportToExcel(), Constants.TMPDIR
						+ "/ProductSearchResults.xlsx", browser);
		exportExcelData.clickAndWaitForExcel();
		List<String> headerfromXLData = exportExcelData.getExcelHeader();
		List<String> headerfromUIData = new ArrayList<String>();
		for (int col = 0; col < webTabHeaderLocked.getColumnCountHeader(); col++) {
			headerfromUIData.add(webTabHeaderLocked
					.getCellElementHeader(0, col).getText());
		}
		for (int col = 0; col < webTabHeaderWrap.getColumnCountHeader(); col++) {
			Actions builder = new Actions(browser);
			builder.moveToElement(webTabHeaderWrap.getCellElementHeader(0, col))
					.build().perform();
			headerfromUIData.add(webTabHeaderWrap.getCellElementHeader(0, col)
					.getText());
		}
		if (!Utils.compareLists(headerfromXLData, headerfromUIData)) {
			Assert.fail("UI and Downloaded excel header is not matching.");
		}
		String[][] XLData = new String[exportExcelData.getExcelRowCount() + 2][exportExcelData
				.getExcelColCount() + 2];
		XLData = exportExcelData.getExcelData();
		for (int searchCounterRow = 0; searchCounterRow < webTabLocked
				.getRowCount(); searchCounterRow++) {
			for (int searchCounterCol = 0; searchCounterCol < webTabLocked
					.getColumnCount() + webTab.getColumnCount(); searchCounterCol++) {
				boolean resultFound = false;

				if (XLData[searchCounterRow][searchCounterCol + 1]
						.matches("([0-9]{2})-[a-zA-Z]{3}-([0-9]{4})")) {
					try {
						Date oldDate = new SimpleDateFormat("dd-MMM-yyyy")
								.parse(XLData[searchCounterRow][searchCounterCol + 1]);
						SimpleDateFormat newDateFormat = new SimpleDateFormat(
								"MM/dd/yyyy");
						newDateFormat.setLenient(false);
						XLData[searchCounterRow][searchCounterCol + 1] = newDateFormat
								.format(oldDate);

					} catch (Exception e) {
						Logs.LOGGER
								.severe("Not able to conevrt date in correct format"
										+ e);
					}
				}
				if (XLData[searchCounterRow][searchCounterCol + 1]
						.matches("[a-zA-Z]{3}-([0-9]{2})-([0-9]{4})")) {
					try {
						Date oldDate = new SimpleDateFormat("MMM-dd-yyyy")
								.parse(XLData[searchCounterRow][searchCounterCol + 1]);
						XLData[searchCounterRow][searchCounterCol + 1] = new SimpleDateFormat(
								"MM/dd/yyyy").format(oldDate);
					} catch (Exception e) {
						Logs.LOGGER
								.severe("Not able to conevrt date in correct format"
										+ e);
					}
				}
				if (XLData[searchCounterRow][searchCounterCol + 1]
						.equalsIgnoreCase("true")) {
					XLData[searchCounterRow][searchCounterCol + 1] = "Yes";
				}
				if (XLData[searchCounterRow][searchCounterCol + 1]
						.equalsIgnoreCase("false")) {
					XLData[searchCounterRow][searchCounterCol + 1] = "No";
				}

				if (webTabLocked
						.searchData(XLData[searchCounterRow][searchCounterCol + 1]))
					resultFound = true;
				else if (webTab
						.searchData(XLData[searchCounterRow][searchCounterCol + 1]))
					resultFound = true;
				if (!resultFound) {
					Assert.fail("Excel values are not matching with UI.");
				}
			}
		}
		ScreenShot.capture(true);
	}

	/*
	 * Test product name search
	 */
	@Test(priority = 4, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testProductNameSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		String productNameToSearch = "Product";
		searchProduct.setProductName(productNameToSearch);
		searchProduct.getSelectSearchElement().click();
		outerloop: for (int row = 0; row < webTab.getRowCount(); row++) {
			for (int col = 0; col < webTab.getColumnCount(); col = col + 8) {
				if (!webTabLocked.getCellElement(row, col).getText()
						.contains(productNameToSearch) ? true : false) {
					Assert.fail("data is not matching in webtable.");
				} else {
					break outerloop;
				}
			}
		}

		ScreenShot.capture(true);
		searchProduct.clickReset();
	}

	/*
	 * Market Search
	 */
	@Test(priority = 5, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMarketSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectMarketsElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectMarketsOptionElement()) < 1) {
			Assert.fail("No Options available in Market Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectMarketsOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getMarketDiv());
		searchProduct.clickReset();
	}

	/*
	 * Utilities Search
	 */
	@Test(priority = 6, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testUtilitySearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectUtilitiesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectUtilityOptionElement()) < 1) {
			Assert.fail("No Options available in utility Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectUtilityOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getUtilityDiv());
		searchProduct.clickReset();
	}

	/*
	 * Zone Search
	 */
	@Test(priority = 7, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testZoneSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectZonesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectZoneOptionElement()) < 1) {
			Assert.fail("No Options available in Zone Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectZoneOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getZoneDiv());
		searchProduct.clickReset();
	}

	/*
	 * Segments Search
	 */
	@Test(priority = 8, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testSegmentsSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSegmentElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectSegmentOptionElement()) < 1) {
			Assert.fail("No Options available in Segment Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectSegmentOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getSegmentDiv());
		searchProduct.clickReset();
	}

	/*
	 * Sales Channel Search
	 */
	@Test(priority = 9, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testSalesChannel() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSalesChannelElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectSalesChannelOptionElement()) < 1) {
			Assert.fail("No Options available in Sales Channel Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectSalesChannelOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getSalesChannelDiv());
		searchProduct.clickReset();
	}

	/*
	 * Contract Source Search
	 */
	@Test(priority = 10, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testContractSources() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectContractSourcesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectContractSourcesOptionElement()) < 1) {
			Assert.fail("No Options available in Contract Source Search dropdown.");
		}
		DropDown.selectAngularDropdownOptionByIndex(
				searchProduct.getSelectContractSourcesOptionElement(), 1);
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		DropDown.clearDropDownElement(searchProduct.getContractSourceDiv());
		searchProduct.clickReset();
	}

	/*
	 * Promo Allowed Search
	 */
	@Test(priority = 11, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testPromoAllowed() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		DropDown.selectAngularDropdownOptionByValue(
				searchProduct.getselectPromoAllowedElement(), "Yes");
		searchProduct.getSelectSearchElement().click();
		if (webTab.getRowCount() > 0) {
			ScreenShot.capture(true);
		}
		searchProduct.clickReset();
	}

	/*
	 * Test Drop Product Code
	 */
	@Test(priority = 12, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testDropProductCode() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		String productNameToSearch = "Product";
		searchProduct.setProductName(productNameToSearch);
		searchProduct.getSelectSearchElement().click();
		searchProduct.getDropObject().isDisplayed();
		for (int col = 0; col < webTabHeaderLocked.getColumnCountHeader(); col++) {
			WebPage.waitForAngularRequestsToFinish(browser);
			Element.isVisible(browser,
					webTabHeaderLocked.getCellElementHeader(0, col));
			DragAndDrop dragAndDrop = new DragAndDrop(
					webTabHeaderLocked.getCellElementHeader(0, col),
					searchProduct.getDropObject(), browser);
			dragAndDrop.dragAndDropElement();
			WebPage.waitForAngularRequestsToFinish(browser);
			WebPage.waitForPageToLoad(browser);
			if (!searchProduct.chkDropTargetElementExist(webTabHeaderLocked
					.getCellElementHeader(0, col + 1).getText())) {
				Assert.fail("Coloumn name didn't add in drop location.");
			}
			searchProduct.getGroupDelete();
			WebPage.waitForAngularRequestsToFinish(browser);
			Element.isVisible(browser,
					webTabHeaderLocked.getCellElementHeader(0, col));
		}
		for (int col = 0; col < webTabHeaderWrap.getColumnCountHeader(); col++) {
			WebPage.waitForAngularRequestsToFinish(browser);
			Actions builder = new Actions(browser);
			builder.moveToElement(webTabHeaderWrap.getCellElementHeader(0, col))
					.build().perform();
			Element.isVisible(browser,
					webTabHeaderWrap.getCellElementHeader(0, col));
			DragAndDrop dragAndDrop = new DragAndDrop(
					webTabHeaderWrap.getCellElementHeader(0, col),
					searchProduct.getDropObject(), browser);
			dragAndDrop.dragAndDropElement();
			WebPage.waitForAngularRequestsToFinish(browser);
			builder.moveToElement(webTabHeaderWrap.getCellElementHeader(0, col))
					.build().perform();
			WebPage.waitForPageToLoad(browser);
			if (!searchProduct.chkDropTargetElementExist(webTabHeaderWrap
					.getCellElementHeader(0, col).getText())) {
				Assert.fail("Coloumn name didn't add in drop location.");
			}
			searchProduct.getGroupDelete();
			WebPage.waitForAngularRequestsToFinish(browser);
			builder.moveToElement(webTabHeaderWrap.getCellElementHeader(0, col))
					.build().perform();
			Element.isVisible(browser,
					webTabHeaderWrap.getCellElementHeader(0, col));
		}
		ScreenShot.capture(true);
		browser.navigate().refresh();
	}

	/*
	 * Multiple Utilities Search
	 */
	@Test(priority = 13, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleUtilitiesSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectUtilitiesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectUtilityOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectUtilityOptionElement(), 1);
			searchProduct.getSelectUtilitiesElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectUtilityOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();

			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getMarketDiv());
		} else {
			Logs.LOGGER
					.info("Only single value is present in Utility dropdown");
		}
		searchProduct.clickReset();
	}

	/*
	 * Multiple Zone Search
	 */
	@Test(priority = 14, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleZonesSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectZonesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectZoneOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectZoneOptionElement(), 1);
			searchProduct.getSelectZonesElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectZoneOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();
			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getZoneDiv());
		} else {
			Logs.LOGGER.info("Only single value is present in Zone dropdown");
		}
		searchProduct.clickReset();
	}

	/*
	 * Multiple Sales Channel Search
	 */
	@Test(priority = 15, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleSalesChannel() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSalesChannelElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectSalesChannelOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectSalesChannelOptionElement(), 1);
			searchProduct.getSelectSalesChannelElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectSalesChannelOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();

			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getSalesChannelDiv());
		} else {
			Logs.LOGGER
					.info("Only single value is present in Sales Channel dropdown");
		}
		searchProduct.clickReset();
	}

	/*
	 * Multiple Market Search
	 */
	@Test(priority = 16, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleMarketSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectMarketsElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectMarketsOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectMarketsOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectMarketsOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();
			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getMarketDiv());
		} else {
			Logs.LOGGER
					.info("Only single value is present in Market search dropdown");
		}
		searchProduct.clickReset();
	}

	/*
	 * Segments Search
	 */
	@Test(priority = 17, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleSegmentsSearch() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectSegmentElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectSegmentOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectSegmentOptionElement(), 1);
			searchProduct.getSelectSegmentElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectSegmentOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();
			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getSegmentDiv());
		} else {
			Logs.LOGGER
					.info("Only single value is present in Segment search dropdown");
		}
		searchProduct.clickReset();
	}

	/*
	 * Multiple Contract Source Search
	 */
	@Test(priority = 18, groups = { "napower" }, dependsOnMethods = { "testNavigateToSearchProductPage" })
	public void testMultipleContractSources() {
		if (!getCurrentApplicationURL.equalsIgnoreCase(browser.getCurrentUrl())) {
			browser.get(getCurrentApplicationURL);
			WebPage.waitForAngularRequestsToFinish(browser);
		}
		searchProduct.getSelectContractSourcesElement().click();
		if (DropDown.getAngularDropDownOptionCount(searchProduct
				.getSelectContractSourcesOptionElement()) > 1) {
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectContractSourcesOptionElement(), 1);
			searchProduct.getSelectContractSourcesElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					searchProduct.getSelectContractSourcesOptionElement(), 1);
			searchProduct.getSelectSearchElement().click();
			if (webTab.getRowCount() > 0) {
				ScreenShot.capture(true);
			}
			DropDown.clearDropDownElement(searchProduct.getContractSourceDiv());
		} else {
			Logs.LOGGER
					.info("Only single value is present in Contract Source dropdown");
		}
		searchProduct.clickReset();
	}
}