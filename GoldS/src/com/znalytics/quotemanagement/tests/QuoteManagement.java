// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.quotemanagement.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Cuanto;
import com.znalytics.framework.core.DataSource;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.TxtDataEngine;
import com.znalytics.framework.utility.Utils;

/**
 * @author: Shilpi Malpani
 * @mail: smalpani@znalytics.com
 * @date: April 22, 2015
 *
 */

public class QuoteManagement {
	public static Hashtable<String, String> app;
	public static Hashtable<String, String> css;
	private String testType;

	public QuoteManagement() {
		Logs.LOGGER.info("Loading data for: " + Constants.CURRENT_TESTING);
		QuoteManagement.app = TxtDataEngine.readTxtFile(DataSource.globalConfig
				.get(Constants.CURRENT_TESTING + ".properties"));
		QuoteManagement.css = TxtDataEngine.readTxtFile(DataSource.globalConfig
				.get(Constants.CURRENT_TESTING + ".object.repo"));
	}

	public QuoteManagement(Hashtable<String, String> app,
			Hashtable<String, String> css) {
		QuoteManagement.app = app;
		QuoteManagement.css = css;
	}

	public QuoteManagement(String appPropertiesFile, String cssPropertiesFile) {
		Logs.LOGGER.info("Loading data for: " + Constants.CURRENT_TESTING);
		QuoteManagement.app = TxtDataEngine.readTxtFile(appPropertiesFile);
		QuoteManagement.css = TxtDataEngine.readTxtFile(cssPropertiesFile);
	}

	public void loadData() {
		Logs.LOGGER.info("Starting test --> " + Constants.CURRENT_CLIENT
				+ " : " + Constants.CURRENT_TESTING);
		this.testType = app.get("test.type");
		Logs.LOGGER.info(Constants.CURRENT_TESTING + " test type: " + testType);
		try {
			generateTestNGXML(this.testType);
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Something bad happened while creating TestNG.xml.", e);
		}
	}

	public void generateTestNGXML(String testType) throws IOException {
		Constants.TESTOUTPUT = Constants.TMP + "/" + Utils.getToday() + "/"
				+ Constants.CURRENT_TESTING + "_" + testType + "/";
		try {
			// suite.
			XmlSuite suiteSmoke = new XmlSuite();
			suiteSmoke.setName(Constants.CURRENT_TESTING);
			suiteSmoke.setPreserveOrder("true");
			XmlTest smokeTestSearchQuote = new XmlTest(suiteSmoke);
			XmlTest fieldValidationTestSearchQuote = new XmlTest(suiteSmoke);
			XmlTest smokeTestIntiateQuoteRequest = new XmlTest(suiteSmoke);
			XmlTest fieldValidationTestIntiateQuoteRequest = new XmlTest(suiteSmoke);
			// Add Parameter
			smokeTestSearchQuote.addParameter("appUrl",
					QuoteManagement.app.get("test.url"));
			smokeTestSearchQuote.addParameter("username",
					QuoteManagement.app.get("username"));
			smokeTestSearchQuote.addParameter("password",
					QuoteManagement.app.get("password"));
			fieldValidationTestSearchQuote.addParameter("appUrl",
					QuoteManagement.app.get("test.url"));
			fieldValidationTestSearchQuote.addParameter("username",
					QuoteManagement.app.get("username"));
			fieldValidationTestSearchQuote.addParameter("password",
					QuoteManagement.app.get("password"));
			smokeTestIntiateQuoteRequest.addParameter("appUrl",
					QuoteManagement.app.get("test.url"));
			smokeTestIntiateQuoteRequest.addParameter("username",
					QuoteManagement.app.get("username"));
			smokeTestIntiateQuoteRequest.addParameter("password",
					QuoteManagement.app.get("password"));
			fieldValidationTestIntiateQuoteRequest.addParameter("appUrl",
					QuoteManagement.app.get("test.url"));
			fieldValidationTestIntiateQuoteRequest.addParameter("username",
					QuoteManagement.app.get("username"));
			fieldValidationTestIntiateQuoteRequest.addParameter("password",
					QuoteManagement.app.get("password"));
			// Add listener
			List<String> listnerClasses = new ArrayList<String>();
			listnerClasses
					.add("com.znalytics.framework.Listener.MethodInterceptorListener");
			listnerClasses
					.add("com.znalytics.framework.Listener.TestNGListener");
			suiteSmoke.setListeners(listnerClasses);

			// Define test node
			fieldValidationTestSearchQuote
					.setName("Znalytics Execution Report - "
							+ Constants.CURRENT_TESTING
							+ " Search Quote  Field Validation");
			fieldValidationTestSearchQuote.setPreserveOrder("true");
			fieldValidationTestIntiateQuoteRequest
					.setName("Znalytics Execution Report - "
							+ Constants.CURRENT_TESTING
							+ " Initiate Quote Request  Field Validation");
			fieldValidationTestIntiateQuoteRequest.setPreserveOrder("true");
			smokeTestSearchQuote.setName("Znalytics Execution Report - "
					+ Constants.CURRENT_TESTING + " "
					+ this.testType.toLowerCase() + " Search Quote Test");
			smokeTestSearchQuote.setPreserveOrder("true");
			smokeTestIntiateQuoteRequest.setName("Znalytics Execution Report - "
					+ Constants.CURRENT_TESTING + " "
					+ this.testType.toLowerCase() + " Intiate Quote Test");
			smokeTestIntiateQuoteRequest.setPreserveOrder("true");

			// get the class names for field Validation for Smoke Search Quote
			List<XmlClass> classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.quotemanagement.tests.SmokeSearchQuoteFieldvalidation"));
			fieldValidationTestSearchQuote.setXmlClasses(classes);

			// get the class names for field Validation for IntiateQuote Request 
			// Quote
			classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.quotemanagement.tests.SmokeIntiateQuoteRequestFieldValidation"));
			fieldValidationTestIntiateQuoteRequest.setXmlClasses(classes);

			// get list of valid class names for running the smoke tests for
			// search quote
			classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.quotemanagement.tests.SmokeSearchQuote"));
			smokeTestSearchQuote.setXmlClasses(classes);

			// get list of valid class names for running the smoke tests for
			// initiate quote
			classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.quotemanagement.tests.SmokeIntiateQuoteRequest"));
			smokeTestIntiateQuoteRequest.setXmlClasses(classes);

			// Set the Groups
			List<String> groups = new ArrayList<String>();
			groups.add(Constants.CURRENT_CLIENT);
			groups.add("login");
			groups.add("logout");
			fieldValidationTestSearchQuote.setIncludedGroups(groups);
			fieldValidationTestSearchQuote.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			fieldValidationTestSearchQuote.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");

			fieldValidationTestIntiateQuoteRequest.setIncludedGroups(groups);
			fieldValidationTestIntiateQuoteRequest.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			fieldValidationTestIntiateQuoteRequest.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");
			smokeTestSearchQuote.setIncludedGroups(groups);
			smokeTestSearchQuote.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			smokeTestSearchQuote.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");
			smokeTestIntiateQuoteRequest.setIncludedGroups(groups);
			smokeTestIntiateQuoteRequest.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			smokeTestIntiateQuoteRequest.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");
			// Save the suite
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suiteSmoke);

			// Save the file for reference
			String testNGXML = Constants.TMPDIR + "/"
					+ this.getClass().getSimpleName() + "_"
					+ Utils.getUniqueName() + ".xml";
			FileWriter writer = new FileWriter(new File(testNGXML));
			writer.write(suiteSmoke.toXml());
			writer.flush();
			writer.close();

			// log the xml into log file.
			Logs.LOGGER.info(suiteSmoke.toXml());

			DataSource.report.add(Constants.TESTOUTPUT + ";"
					+ suiteSmoke.toXml() + ";" + Constants.CURRENT_TESTING
					+ ";" + testType + ";" + app.get("test.url"));
			DataSource.report.trimToSize();

			TestNG testng = new TestNG();
			testng.setOutputDirectory(Constants.TESTOUTPUT);
			testng.addListener(new Cuanto().getCuantoListener());
			testng.setXmlSuites(suites);
			testng.run();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in generating XML file.", e);
		}
	}
}
