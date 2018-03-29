// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import com.znalytics.framework.utility.WebTable;
import com.znalytics.productmanagement.pages.CreateProductPage;
import com.znalytics.productmanagement.pages.SearchProductPage;

/**
 * @author: Naveen
 * @mail: nrachha@znalytics.com
 * @date: Nov 18, 2015
 * @purpose: contains all smoke test cases for creating product Priority = 201
 *           to 300
 * 
 */

@Test(groups = { "napower" })
public class SmokeTestScripts {

	SearchProductPage searchProduct;
	WebTable webTab;
	WebTable webTabLocked;
	WebTable webTabHeaderLocked;
	WebTable webTabHeaderWrap;
	AngularDropDown DropDown;

	WebDriver browser = Setup.getInstance().getDriver();
	NavigationPage navigate;
	CreateProductPage createProduct;
	String getCurrentApplicationURL;
	// Product related values.
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

	SmokeTestScripts() {
		searchProduct = PageFactory.initElements(browser,
				SearchProductPage.class);
		DropDown = new AngularDropDown();

		navigate = PageFactory.initElements(browser, NavigationPage.class);
		createProduct = PageFactory.initElements(browser,
				CreateProductPage.class);
		// Product related values.
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
	public void testAddProduct() throws InterruptedException {
		navigate.navigateAddProductPage();
				WebPage.waitForPageToLoad(browser);
		AngularDropDown DropDown = new AngularDropDown();

		if (Element.isVisible(browser, createProduct.getProductCode())) {
			Element.highlightElement(browser, createProduct.getProductCode());
			createProduct.getProductCode().isEnabled();
			createProduct.setProductCode(Utils.generateString(10));
			Reporter.log("ProuctCode text field is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProuctCode text field is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getProductName())) {
			Element.highlightElement(browser, createProduct.getProductName());
			createProduct.getProductName().isEnabled();
			createProduct.setProductName(Utils.generateString(15));
			Reporter.log("ProuctName text field is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProuctName text field is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getProductDescription())) {
			Element.highlightElement(browser, createProduct.getProductDescription());
			createProduct.getProductDescription().isEnabled();
			createProduct.setProductDescription(Utils.generateString(30));
			Reporter.log("ProuctDescription text field is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProuctDescription text field is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getProductTagLine())) {
			Element.highlightElement(browser, createProduct.getProductTagLine());
			createProduct.getProductTagLine().isEnabled();
			createProduct.setProductTagLine(Utils.generateString(10));
			Reporter.log("ProductTagLine text field is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProductTagLine text field is not present: Fail.");
		}

		if (Element.isVisible(browser,
				createProduct.getEffectiveDateCalendarIcon())) {
			Element.highlightElement(browser, createProduct.getEffectiveDateCalendarIcon());
			createProduct.getEffectiveDateCalendarIcon().isEnabled();
			createProduct.getEffectiveDateCalendarIcon().click();
			createProduct.geteffectiveDateCalendar(Utils.getToday("dd"));
			Reporter.log("EffectiveDateCalendarIcon is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProductTagLine text field is not present: Fail.");
		}

		JavascriptExecutor executor = (JavascriptExecutor) browser;
		if (Element.isVisible(browser,
				createProduct.getContractSourceSpanElement())) {
			Element.highlightElement(browser, createProduct.getContractSourceSpanElement());
			createProduct.getContractSourceSpanElement().isEnabled();
			executor.executeScript("window.scrollTo(0,"
										+ (createProduct.getContractSourceSpanElement()
												.getLocation().y - 70) + ")");
								createProduct.getContractSourceSpanElement().click();
								DropDown.selectAngularDropdownOptionByIndex(
										createProduct.getContractSourceOptions(), 1);

			Reporter.log("ContractSourceSpanElement option is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ContractSourceSpanElement option is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getProductTypeElement())) {
			Element.highlightElement(browser, createProduct.getProductTypeElement());
			createProduct.getProductTypeElement().isEnabled();
			createProduct.getProductTypeElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					createProduct.getProductTypeOptions(), 1);
			Reporter.log("ProductTypeElement is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProductTypeElement is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getServiceTypeElement())) {
			Element.highlightElement(browser, createProduct.getServiceTypeElement());
			createProduct.getServiceTypeElement().isEnabled();
			createProduct.getServiceTypeElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					createProduct.getServiceTypeOptions(), 1);
			Reporter.log("ServiceTypeElement is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ServiceTypeElement is not present: Fail.");
		}

		if (Element.isVisible(browser,
				createProduct.getPricingAssignmentElement())) {
			Element.highlightElement(browser, createProduct.getPricingAssignmentElement());
			createProduct.getPricingAssignmentElement().isEnabled();
			createProduct.getPricingAssignmentElement().click();
			DropDown.selectAngularDropdownOptionByIndex(
					createProduct.getPricingAssignmentOptions(), 1);
			Reporter.log("PricingAssignmentElement is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("PricingAssignmentElement is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getNextButton())) {
			Element.highlightElement(browser, createProduct.getNextButton());
			createProduct.getNextButton().isEnabled();
			createProduct.getNextButton().click();
			Reporter.log("NextButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("NextButton is not present: Fail.");
		}

		if (Element.isVisible(browser, searchProduct.getCancelButton())) {
			Element.highlightElement(browser, createProduct.getCancelButton());
			createProduct.getCancelButton().isEnabled();
			createProduct.getCancelButton().click();
			Reporter.log("CancelButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("CancelButton is not present: Fail.");
		}

		if (Element.isVisible(browser, searchProduct.getOkDialog())) {
			Element.highlightElement(browser, searchProduct.getOkDialog());
			searchProduct.getOkDialog().isEnabled();
			searchProduct.getOkDialog().click();
			Reporter.log("OkDialog is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("OkDialog is not present: Fail.");
		}
		
		

	}
	
		
	@Test(priority = 202, groups = { "napower" })
	public void testNavigateToSearchPage() {
		String productValue = "CI_TX_MATRIX ";
		if (Element.isVisible(browser,
				searchProduct.getSearchProductTextField())) {
			Element.highlightElement(browser, searchProduct.getSearchProductTextField());
			searchProduct.getSearchProductTextField().isEnabled();
			searchProduct.getSearchProductTextField().click();
			searchProduct.setProductValue(productValue);
			Reporter.log("SearchProductTextField is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("SearchProductTextField is not present: Fail.");
		}

		if (Element.isVisible(browser, searchProduct.getSearchProductCode())) {
			Element.highlightElement(browser, searchProduct.getSearchProductCode());
			searchProduct.getSearchProductCode().isEnabled();
			searchProduct.getSearchProductCode().click();
			Reporter.log("SearchProductCode is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("SearchProductCode is not present: Fail.");
		}

		if (Element.isVisible(browser, createProduct.getCloseButton())) {
			Element.highlightElement(browser, createProduct.getCloseButton());
			createProduct.getCloseButton().isEnabled();
			createProduct.getCloseButton().click();
			Reporter.log("CloseButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("CloseButton is not present: Fail.");
		}

	}

	@Test(priority = 203, groups = { "napower" })
	public void testExportExcel() {
		if (Element.isVisible(browser, searchProduct.getProductSearchLabel())) {
			Reporter.log("ProductSearchLabel is present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ProductSearchLabel is not present: Fail.");
		}

		
		if (Element.isVisible(browser, searchProduct.getExportToExcel())) {
			Element.highlightElement(browser, searchProduct.getExportToExcel());
			searchProduct.getExportToExcel().isEnabled();
			searchProduct.getExportToExcel().click();
			Reporter.log("ExportToExcel is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ExportToExcel is not present: Fail.");
		}
	
	}

	@Test(priority = 204, groups = { "napower" })
	public void testNavigateToViewRateSchedule() {
		navigate.navigateSearchProductPage();
		if (Element.isTextPresent(browser,
				searchProduct.getProductSearchLabel(), "View Products")) {
			if (Element.isVisible(browser, searchProduct.getEditRateScheduleIcon())) {
				searchProduct.getEditRateScheduleIcon().isEnabled();
				searchProduct.getEditRateScheduleIcon().click();
				Reporter.log("EditRateScheduleIcon is enabled and present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("EditRateScheduleIcon is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getRateScheduleButton())) {
				searchProduct.getRateScheduleButton().isEnabled();
				searchProduct.getRateScheduleButton().click();
				Reporter.log("RateScheduleButton is enabled and present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateScheduleButton is not present: Fail.");
			}
			searchProduct.getReateScheduleClose().isEnabled();
			if (Element.isVisible(browser, searchProduct.getReateScheduleClose())) {
				searchProduct.getReateScheduleClose().isEnabled();
				searchProduct.getReateScheduleClose().click();
				Reporter.log("ReateScheduleClose Button is enabled and present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("ReateScheduleClose Button is not present: Fail.");
			}
		} else {
			Reporter.log("Not able to navigate to search record.");
		}
	}

	@Test(priority = 205, groups = { "napower" })
	public void testNavigateToViewProductPage() {
		
		if (Element.isVisible(browser, searchProduct.getEditRateScheudleLink1())) {
			searchProduct.getEditRateScheudleLink1().isEnabled();
			searchProduct.getEditRateScheudleLink1().click();
			Reporter.log("EditRateScheudle Link is  present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("EditRateScheudleLink is not present: Fail.");
		}
		
		if (Element.isVisible(browser, searchProduct.getModifyProductAttributes())) {
			searchProduct.getModifyProductAttributes().isEnabled();
			searchProduct.getModifyProductAttributes().click();
			Reporter.log("ModifyProductAttributes is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ModifyProductAttributes is not present: Fail.");
		}
		

		if (Element.isVisible(browser, createProduct.getNextButton())) {
			createProduct.getNextButton().isEnabled();
			createProduct.getNextButton().click();
			Reporter.log("NextButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("NextButton is not present: Fail.");
		}
		

		if (Element.isVisible(browser, createProduct.getCloseButton())) {
			createProduct.getCloseButton().isEnabled();
			createProduct.getCloseButton().click();
			Reporter.log("CloseButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("CloseButton is not present: Fail.");
		}
	}

	@Test(priority = 206, groups = { "napower" })
	public void testNavigateToEditRateSchedule() {

		if (Element.isVisible(browser, searchProduct.getEditRateScheudleLink1())) {
			searchProduct.getEditRateScheudleLink1().isEnabled();
			searchProduct.getEditRateScheudleLink1().click();
			Reporter.log("EditRateScheudle Link is  present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("EditRateScheudleLink is not present: Fail.");
		}
		
		if (Element.isVisible(browser, searchProduct.getRateScheduleButton())) {
			searchProduct.getRateScheduleButton().isEnabled();
			searchProduct.getRateScheduleButton().click();
			Reporter.log("RateScheduleButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("getRateScheduleButton is not present: Fail.");
		}
		
		searchProduct.getReateScheduleClose().isEnabled();
		if (Element.isVisible(browser, searchProduct.getReateScheduleClose())) {
			searchProduct.getReateScheduleClose().isEnabled();
			searchProduct.getReateScheduleClose().click();
			Reporter.log("ReateScheduleClose Button is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("ReateScheduleClose Button is not present: Fail.");
		}
		
	}

	@Test(priority = 207, groups = { "napower" })
	public void testNavigateToEditRateScheduleItems() {
		String earlyTerminationAmount = "200";
		

		if (Element.isVisible(browser, searchProduct.getEditRateScheudleLink1())) {
			searchProduct.getEditRateScheudleLink1().isEnabled();
			searchProduct.getEditRateScheudleLink1().click();
			Reporter.log("EditRateScheudle Link is  present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("EditRateScheudleLink is not present: Fail.");
		}
		
		if (Element.isVisible(browser, searchProduct.getRateScheduleButton())) {
			searchProduct.getRateScheduleButton().isEnabled();
			searchProduct.getRateScheduleButton().click();
			Reporter.log("RateScheduleButton is enabled and present: Pass.");
			ScreenShot.capture(true, "testAddProductPage");
		} else {
			Reporter.log("getRateScheduleButton is not present: Fail.");
		}	
			if (Element.isVisible(browser, searchProduct.getEditRatescheduleLink())) {
				searchProduct.getEditRatescheduleLink().isEnabled();
				searchProduct.getEditRatescheduleLink().click();
				Reporter.log("RateSchedule Label is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateScheduleLabel is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getAddChargesBut())) {
				searchProduct.getAddChargesBut().isEnabled();
				searchProduct.getAddChargesBut().click();
				Reporter.log("RateSchedule Label is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateScheduleLabel is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getChargeDescriptionSpan())) {
				searchProduct.getChargeDescriptionSpan().isEnabled();
				JavascriptExecutor executor = (JavascriptExecutor) browser;
								executor.executeScript("window.scrollTo(0,"
										+ (searchProduct.getChargeDescriptionSpan().getLocation().y - 70)
										+ ")");
								searchProduct.getChargeDescriptionSpan().click();
								DropDown.selectAngularDropdownOptionByIndex(
										searchProduct.getChargeDescriptionOptions(), 1);
				Reporter.log("ChargeDescription List is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("ChargeDescription List is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getIndexTypeSpanElement())) {
				searchProduct.getIndexTypeSpanElement().isEnabled();
				searchProduct.getIndexTypeSpanElement().click();
				DropDown.selectAngularDropdownOptionByIndex(
						searchProduct.getIndexTypeOptions(), 1);
				Reporter.log("IndexType List is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("IndexType List is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getRateScheduleUOM())) {
				searchProduct.getRateScheduleUOM().isEnabled();
				searchProduct.getRateScheduleUOM().click();
				DropDown.selectAngularDropdownOptionByIndex(
						searchProduct.getRateScheduleUOMoptions(), 1);
				Reporter.log("RateScheduleUOM List is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateScheduleUOM List is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getMeterRegister())) {
				searchProduct.getMeterRegister().isEnabled();
				searchProduct.getMeterRegister().click();
				DropDown.selectAngularDropdownOptionByIndex(
						searchProduct.getMeterRegisterOptions(), 1);
				Reporter.log("MeterRegister List is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("MeterRegister List is not present: Fail.");
			}
		
			if (Element.isVisible(browser, searchProduct.getRateScheduleChargeCal())) {
				searchProduct.getRateScheduleChargeCal().isEnabled();
				searchProduct.getRateScheduleChargeCal().click();
				DropDown.selectAngularDropdownOptionByIndex(
						searchProduct.getChargeCalculatorOptions(), 1);
				Reporter.log("MeterRegister List is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("MeterRegister List is not present: Fail.");
			}
		
			if (Element.isVisible(browser, searchProduct.getSaveButton())) {
				searchProduct.getSaveButton().isEnabled();
				searchProduct.getSaveButton().click();
				Reporter.log("SaveButton  is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("SaveButton List is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getMoreOptionsButton())) {
				searchProduct.getMoreOptionsButton().isEnabled();
				searchProduct.getMoreOptionsButton().click();
				Reporter.log("MoreOptionsButton  is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("MoreOptionsButton List is not present: Fail.");
			}
		
			if (Element.isVisible(browser, searchProduct.getEarlyTerminationAmount())) {
				searchProduct.getEarlyTerminationAmount().isEnabled();
				searchProduct.setEarlyTerminationAmount(earlyTerminationAmount);
				Reporter.log("EarlyTerminationAmount text field  is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("EarlyTerminationAmount text field is not present: Fail.");
			}
			

			if (Element.isVisible(browser, searchProduct.getRateIdentificationMethod())) {
				searchProduct.getRateIdentificationMethod().isSelected();
				searchProduct.getRateIdentificationMethod().click();
				DropDown.selectAngularDropdownOptionByIndex(
						  searchProduct.getRateIdentifiationOptions(), 1);
				Reporter.log("RateIdentification list  is  present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateIdentification list is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getPopupSave())) {
				searchProduct.getPopupSave().isEnabled();
				searchProduct.getPopupSave().click();
				Reporter.log("PopupSave Button is enabled and present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("PopupSave Button is not present: Fail.");
			}
			
			if (Element.isVisible(browser, searchProduct.getRateScheduleNextButton())) {
				searchProduct.getRateScheduleNextButton().isEnabled();
				searchProduct.getRateScheduleNextButton().click();
				Reporter.log("RateSchedule Button is enabled and present: Pass.");
				ScreenShot.capture(true, "testAddProductPage");
			} else {
				Reporter.log("RateSchedule Button is not present: Fail.");
			}
			searchProduct.getRateScheduleNextButton().click();
//Add a comment to this line

		
	}

}