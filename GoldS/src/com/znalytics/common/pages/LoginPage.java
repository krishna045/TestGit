// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.common.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.znalytics.framework.utility.Element;

/**
 * The Class LoginPage.
 *
 * @author: Yogi Garg
 * @mail: ygarg@znalytics.com
 * @date: Mar 18, 2015
 * 
 *        purpose of this class is to store all the pages object related to
 *        login.
 */
public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Finding object username
	@FindBy(xpath = "//input[@type='email' and @ng-model='vm.user.username']")
	private WebElement userName;

	// Finding object password
	@FindBy(xpath = "//input[@type='password' and @ng-model='vm.user.password']")
	private WebElement password;

	// Finding object signin button
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signin;

	// Finding object login error failure
	@FindBy(xpath = "//div[@id='errorPanel']/div[contains(.,'Login failed.')]")
	private WebElement loginError;

	// Finding object First Name Last name header
	@FindBy(xpath = "//span[@class='hidden-sm m-l ng-binding']")
	private WebElement firstNameLastNameHeaderLabel;

	// Finding object logout Element
	@FindBy(xpath = "//a[contains(.,'Logout')]")
	private WebElement logoutClick;

	// Setting up username
	public void setUserName(String username) {
		userName.clear();
		userName.sendKeys(username);
	}

	// Setting up password
	public void setPassword(String txtPassword) {
		password.clear();
		password.sendKeys(txtPassword);
	}

	// Finding object signin button exist
	public WebElement getSignIn() {
		Element.isClickable(driver, signin);
		return signin;
	}

	// Finding object Login failure button exist
	public boolean loginFailureErrorExist() {
		if (Element
				.isElementPresentInstant(
						driver,
						By.xpath("//div[@id='errorPanel']/div[contains(.,'Login failed.')]")))
			return true;
		else
			return false;
	}

}
