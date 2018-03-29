// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.znalytics.common.pages.NavigationPage;
import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.ScreenShot;
import com.znalytics.framework.core.Setup;
import com.znalytics.framework.utility.AngularDropDown;
import com.znalytics.framework.utility.DragAndDrop;
import com.znalytics.framework.utility.Element;
import com.znalytics.framework.utility.ExportExcel;
import com.znalytics.framework.utility.Utils;
import com.znalytics.framework.utility.WebPage;
import com.znalytics.framework.utility.WebTable;
import com.znalytics.quotemanagement.pages.SearchQuotePage;

/**
 * @author: Shilpi Malpani
 * @mail: smalpani@znalytics.com
 * @date: April 22, 2015
 * 
 *        purpose of this class is to execute all test cases for search
 *        quotes Priority=60-80
 */

@Test(groups = { "napower" })
public class SmokeSearchQuote {

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	SearchQuotePage searchQuote;
	String getCurrentApplicationURL;
	WebTable webTab;
	WebTable webTabHeader;
	WebTable webLabelTabHeader;
	AngularDropDown DropDown;
	String url;

	SmokeSearchQuote() {
		navigate = PageFactory.initElements(browser, NavigationPage.class);
		searchQuote = PageFactory.initElements(browser,
				com.znalytics.quotemanagement.pages.SearchQuotePage.class);
		webTab = new WebTable(searchQuote.getWebTable(), browser);
		webTabHeader = new WebTable(searchQuote.getWebTableHeader(),
				browser);
		webLabelTabHeader = new WebTable(
				searchQuote.getLabelTableHeader(), browser);
		DropDown = new AngularDropDown();

	}

	
	@Test(priority = 61, groups = { "napower" })
	public void testNavigateQouteRequestPage() {
		navigate.navigateSalesDashboard();
		if (Element.isTextPresent(browser, searchQuote.getSalesLabel(),
				"Sales Dashboard")) {
			getCurrentApplicationURL = browser.getCurrentUrl();
		} else {
			Assert.fail("Not able to navigate to qoute request page.");
		}
		ScreenShot.capture(true);
	}

	/**
	 * Method to Export to Excel.
	 */
	@Test(priority = 62, groups = { "napower" })
	public void testExportExcel() {
		Element.highlightElement(browser, searchQuote.getExportToExcel());
		ExportExcel exportExcelData = new ExportExcel(
				searchQuote.getExportToExcel(), Constants.TMPDIR
						+ "/Sales Dashboard.xlsx", browser);
		exportExcelData.clickAndWaitForExcel();
		List<String> headerfromXLData = exportExcelData.getExcelHeader();
		List<String> headerfromUIData = new ArrayList<String>();
		for (int col = 0; col < webTabHeader.getColumnCountHeader(); col++) {
			headerfromUIData.add(webTabHeader.getCellElementHeader(0, col)
					.getText());
		}
		if (Utils.compareLists(headerfromXLData, headerfromUIData)) {
			Assert.fail("UI and Downloaded excel header is not matching");
		}
		String[][] XLData = new String[exportExcelData.getExcelRowCount() + 1][exportExcelData
				.getExcelColCount() + 1];
		XLData = exportExcelData.getExcelData();
		for (int searchCounterRow = 0; searchCounterRow < XLData.length; searchCounterRow++) {
			for (int searchCounterCol = 0; searchCounterCol < XLData.length; searchCounterCol++) {
				if (!webTab
						.searchData(XLData[searchCounterRow][searchCounterCol + 1])) {
					Assert.fail("Excel values are not matching with UI");
				}
			}
		}
		ScreenShot.capture(true);
	}

	/**
	 * Test Drop Quotes Code.
	 */
	@Test(priority = 63, groups = { "napower" })
	public void testQuoteCode() {
		Element.scrollElementIntoView(browser, searchQuote.getSalesLabel());
		searchQuote.getDropObject().isDisplayed();
		for (int col = 0; col < webTabHeader.getColumnCountHeader(); col++) {
			WebPage.waitForAngularRequestsToFinish(browser);
			Element.isVisible(browser,
					webTabHeader.getCellElementHeader(0, col));
			DragAndDrop dragAndDrop = new DragAndDrop(
					webTabHeader.getCellElementHeader(0, col),
					searchQuote.getDropObject(), browser);
			dragAndDrop.dragAndDropElement();
			WebPage.waitForAngularRequestsToFinish(browser);
			WebPage.waitForPageToLoad(browser);
			if (!searchQuote.chkDropTargetElementExist(webTabHeader
					.getCellElementHeader(0, col + 1).getText())) {
				Assert.fail("Coloumn name didn't add in drop location.");
			}
			searchQuote.getGroupDelete();
			WebPage.waitForAngularRequestsToFinish(browser);
			Element.isVisible(browser,
					webTabHeader.getCellElementHeader(0, col));
		}
		ScreenShot.capture(true);
		browser.navigate().refresh();
	}
	
}
