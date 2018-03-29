// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.znalytics.framework.Listener.DriverEventListener;

// TODO: Auto-generated Javadoc
/**
 * The Class Setup.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Setup {

	/**
	 * Gets the single instance of Setup.
	 *
	 * @return selenium object
	 */
	public static synchronized Setup getInstance() {
		if (null == instance) {
			System.setProperty("atu.reporter.config",
					"Resources/Framework/atu.properties");
			instance = new Setup();
		}
		return instance;
	}

	/**
	 * Re initialize.
	 */
	public static void reInitialize() {
		if (null != instance) {
			Logs.LOGGER.info("Re-Initialize webdriver ...");
			instance.unRegisterEventListner(selenium);
			Constants.DRIVERINIT = false;
			selenium = null;
			instance = null;
		}
	}

	/** The instance. */
	private static Setup instance = null;

	/** The selenium. */
	private static WebDriver selenium = null;

	/**
	 * Instantiates a new setup.
	 */
	private Setup() {
		try {
			String browser = DataSource.globalConfig.get("browser")
					.toLowerCase();
			Logs.LOGGER.info("Starting browser - " + browser);
			switch (browser) {
			case "chrome":
			default:
				Logs.LOGGER.info("Initializing Google Chrome Driver");
				System.setProperty("webdriver.chrome.driver",
						"Resources/Framework/drivers/chromedriver.exe");
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", new File(
						Constants.TMPDIR).getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments(Arrays.asList("--start-maximized",
						"--test-type", "--ignore-certificate-errors",
						"--disable-popup-blocking",
						"--allow-running-insecure-content",
						"--disable-translate", "--always-authorize-plugins"));
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				selenium = new ChromeDriver(cap);
				break;

			case "firefox":
				Logs.LOGGER.info("Initializing FireFox Driver");
				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(false);
				profile.setPreference("javascript.enabled", true);
				profile.setPreference("dom.max_script_run_time", 0);
				profile.setPreference("dom.max_chrome_script_run_time", 0);
				profile.setPreference("browser.download.dir", new File(
						Constants.TMPDIR).getAbsolutePath());
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference(
						"browser.download.manager.showWhenStarting", false);
				profile.setPreference(
						"browser.helperApps.neverAsk.saveToDisk",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,"
								+ "application/pdf,application/vnd.openxmlformats-"
								+ "officedocument.wordprocessingml.document,image/jpeg");
				profile.setPreference(
						"browser.download.manager.showWhenStarting", false);
				profile.setPreference(
						"browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.helperApps.alwaysAsk.force",
						false);
				profile.setPreference(
						"browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.closeWhenDone",
						true);
				profile.setPreference(
						"browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.useWindow",
						false);
				profile.setPreference("pdfjs.disabled", true);
				selenium = new FirefoxDriver(profile);
				break;

			case "ie":
				Logs.LOGGER.info("Initializing Internet Explorer Driver");
				System.setProperty("webdriver.ie.driver",
						"Resources/Framework/drivers/IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities
						.internetExplorer();
				ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
						true);
				ieCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS,
						true);
				ieCapabilities.setJavascriptEnabled(true);
				ieCapabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				ieCapabilities.setCapability(
						InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				ieCapabilities.setCapability(
						InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				selenium = new InternetExplorerDriver(ieCapabilities);
				break;

			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Something went wrong with the selenium setup", e);
		}

		if (null != selenium) {
			// Setting default timeout for finding the element instead of 0 sec.
			Logs.LOGGER.info("Setting default selenium timout: "
					+ Constants.DEFAULTTIME);
			registerEventListner(selenium);
			selenium.manage().timeouts()
					.implicitlyWait(Constants.DEFAULTTIME, TimeUnit.SECONDS);
			selenium.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);
			selenium.manage().timeouts()
					.setScriptTimeout(120, TimeUnit.SECONDS);
			selenium.manage().window().maximize();
			Constants.DRIVERINIT = true;
			Logs.LOGGER.info("Selenium server setup done successfully ...");
			Logs.LOGGER.info("");
		}
	}

	/**
	 * Gets the selenium.
	 *
	 * @return selenium object
	 */
	public WebDriver getDriver() {
		return selenium;
	}

	/**
	 * Register event listner.
	 *
	 * @param driver
	 *            the driver
	 */
	private void registerEventListner(WebDriver driver) {
		try {
			Logs.LOGGER.info("Register Webdriver EventListner.");
			new EventFiringWebDriver(driver)
					.register(new DriverEventListener());
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * Un register event listner.
	 *
	 * @param driver
	 *            the driver
	 */
	public void unRegisterEventListner(WebDriver driver) {
		try {
			Logs.LOGGER.info("UnRegister Webdriver EventListner.");
			new EventFiringWebDriver(driver)
					.unregister(new DriverEventListener());
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}
}
