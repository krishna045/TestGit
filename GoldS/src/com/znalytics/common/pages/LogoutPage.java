// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.common.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.znalytics.framework.utility.Element;

/**
 * The Class LogoutPage.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * 
 *        purpose of this class is to store all the pages object related to
 *        logout.
 */
public class LogoutPage {

	private WebDriver driver;

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object Logout Dropdown
	@FindBy(xpath = "//a[@data-template='user']")
	private WebElement logoutDropdown;

	// Finding object Logout link
	@FindBy(xpath = ".//a[contains(text(),'Logout')]")
	private WebElement logoutlink;

	// Finding object First Name Last name header
	@FindBy(xpath = "//span[@class='hidden-sm m-l ng-binding']")
	private WebElement firstNameLastNameHeaderLabel;

	// Finding object logout Element
	@FindBy(xpath = "//a[contains(.,'Logout')]")
	private WebElement logoutClick;

	// Finding object Login failure button exist
	public boolean logoutFromApplication() {
		if (Element.isClickable(driver, firstNameLastNameHeaderLabel)) {
			Element.click(driver, firstNameLastNameHeaderLabel);
			Element.click(driver, logoutClick);
		}
		if (Element
				.isElementPresentInstant(
						driver,
						By.xpath("//input[@type='email' and @ng-model='vm.user.username']"))) {
			return true;
		} else {
			return false;
		}
	}
}
