// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.productmanagement.tests;

import java.io.File;
import java.io.FileWriter;
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
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 12, 2015
 *
 */
public class ProductManagement {

	public static Hashtable<String, String> app;
	public static Hashtable<String, String> css;
	private String testType;

	public ProductManagement() {
		Logs.LOGGER.info("Loading data for: " + Constants.CURRENT_TESTING);
		ProductManagement.app = TxtDataEngine
				.readTxtFile(DataSource.globalConfig
						.get(Constants.CURRENT_TESTING + ".properties"));
		ProductManagement.css = TxtDataEngine
				.readTxtFile(DataSource.globalConfig
						.get(Constants.CURRENT_TESTING + ".object.repo"));
	}

	public ProductManagement(Hashtable<String, String> app,
			Hashtable<String, String> css) {
		ProductManagement.app = app;
		ProductManagement.css = css;
	}

	public ProductManagement(String appPropertiesFile, String cssPropertiesFile) {
		Logs.LOGGER.info("Loading data for: " + Constants.CURRENT_TESTING);
		ProductManagement.app = TxtDataEngine.readTxtFile(appPropertiesFile);
		ProductManagement.css = TxtDataEngine.readTxtFile(cssPropertiesFile);
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

	public void generateTestNGXML(String testType) {
		Constants.TESTOUTPUT = Constants.TMP + "/" + Utils.getToday() + "/"
				+ Constants.CURRENT_TESTING + "_" + testType + "/";
		try {
			// suite.
			XmlSuite suiteSmoke = new XmlSuite();
			suiteSmoke.setName(Constants.CURRENT_TESTING);
			suiteSmoke.setPreserveOrder("true");
			/*XmlTest smokeTestSearchProduct = new XmlTest(suiteSmoke);
			XmlTest fieldValidationTestSearchProduct = new XmlTest(suiteSmoke);
			XmlTest smokeTestCreateProduct = new XmlTest(suiteSmoke);*/
			XmlTest smokeTestScripts = new XmlTest(suiteSmoke);
			/*XmlTest fieldValidationTestCreateProduct = new XmlTest(suiteSmoke);*/
			// Add Parameter
			smokeTestScripts.addParameter("appUrl",
 					ProductManagement.app.get("test.url"));
			smokeTestScripts.addParameter("username",
 					ProductManagement.app.get("username"));
			smokeTestScripts.addParameter("password",
 					ProductManagement.app.get("password"));
			smokeTestScripts.setName("Znalytics Execution Report - "
 					+ Constants.CURRENT_TESTING + " "
 					+ this.testType.toLowerCase() + " Smoke test scripts");
			        smokeTestScripts.setPreserveOrder("true");
			/*smokeTestSearchProduct.addParameter("appUrl",
					ProductManagement.app.get("test.url"));
			smokeTestSearchProduct.addParameter("username",
					ProductManagement.app.get("username"));
			smokeTestSearchProduct.addParameter("password",
					ProductManagement.app.get("password"));
			fieldValidationTestSearchProduct.addParameter("appUrl",
					ProductManagement.app.get("test.url"));
			fieldValidationTestSearchProduct.addParameter("username",
					ProductManagement.app.get("username"));
			fieldValidationTestSearchProduct.addParameter("password",
					ProductManagement.app.get("password"));
			smokeTestCreateProduct.addParameter("appUrl",
					ProductManagement.app.get("test.url"));
			smokeTestCreateProduct.addParameter("username",
					ProductManagement.app.get("username"));
			smokeTestCreateProduct.addParameter("password",
					ProductManagement.app.get("password"));
			fieldValidationTestCreateProduct.addParameter("appUrl",
					ProductManagement.app.get("test.url"));
			fieldValidationTestCreateProduct.addParameter("username",
					ProductManagement.app.get("username"));
			fieldValidationTestCreateProduct.addParameter("password",
					ProductManagement.app.get("password"));*/
			// Add listener
			List<String> listnerClasses = new ArrayList<String>();
			listnerClasses
					.add("com.znalytics.framework.Listener.MethodInterceptorListener");
			listnerClasses
					.add("com.znalytics.framework.Listener.TestNGListener");
			suiteSmoke.setListeners(listnerClasses);

			// Define test node
			/*fieldValidationTestSearchProduct
					.setName("Znalytics Execution Report - "
							+ Constants.CURRENT_TESTING
							+ " Search Product  Field Validation");
			fieldValidationTestSearchProduct.setPreserveOrder("true");
			fieldValidationTestCreateProduct
					.setName("Znalytics Execution Report - "
							+ Constants.CURRENT_TESTING
							+ " Create Product  Field Validation");
			fieldValidationTestCreateProduct.setPreserveOrder("true");
			smokeTestSearchProduct.setName("Znalytics Execution Report - "
					+ Constants.CURRENT_TESTING + " "
					+ this.testType.toLowerCase() + " Search Product Test");
			smokeTestSearchProduct.setPreserveOrder("true");
			smokeTestCreateProduct.setName("Znalytics Execution Report - "
					+ Constants.CURRENT_TESTING + " "
					+ this.testType.toLowerCase() + " Create Product Test");
			smokeTestCreateProduct.setPreserveOrder("true");
*/
			// get the class names for field Validation for Smoke Search Product
			List<XmlClass> classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
		"com.znalytics.productmanagement.tests.SmokeTestScripts"));
					smokeTestScripts.setXmlClasses(classes);

			// get the class names for field Validation for Create Search
			// Product
			/*classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.productmanagement.tests.SmokeCreateProductFieldValidation"));*/
		//	fieldValidationTestCreateProduct.setXmlClasses(classes);

			// get list of valid class names for running the smoke tests for
			// search product
			classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.productmanagement.tests.SmokeSearchProduct"));
		//	smokeTestSearchProduct.setXmlClasses(classes);

			// get list of valid class names for running the smoke tests for
			// create product
			classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("com.znalytics.common.tests.Login"));
			classes.add(new XmlClass("com.znalytics.common.tests.Logout"));
			classes.add(new XmlClass(
					"com.znalytics.productmanagement.tests.SmokeCreateProduct"));
		//	smokeTestCreateProduct.setXmlClasses(classes);

			// Set the Groups
			List<String> groups = new ArrayList<String>();
			groups.add(Constants.CURRENT_CLIENT);
			groups.add("login");
			groups.add("logout");
		/*	fieldValidationTestSearchProduct.setIncludedGroups(groups);
			fieldValidationTestSearchProduct.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			fieldValidationTestSearchProduct.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");

			fieldValidationTestCreateProduct.setIncludedGroups(groups);
			fieldValidationTestCreateProduct.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			fieldValidationTestCreateProduct.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");
			smokeTestSearchProduct.setIncludedGroups(groups);
			smokeTestSearchProduct.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			smokeTestSearchProduct.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");
			smokeTestCreateProduct.setIncludedGroups(groups);
			smokeTestCreateProduct.addXmlDependencyGroup(
					Constants.CURRENT_CLIENT, "login");
			smokeTestCreateProduct.addXmlDependencyGroup("logout",
					Constants.CURRENT_CLIENT + " login");*/
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
