// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.znalytics.framework.core.Logs;

/**
 * The Class Utils.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Utils {

	/**
	 * Compare lists.
	 *
	 * @param prevList
	 *            the prev list
	 * @param modelList
	 *            the model list
	 * @return true, if successful
	 */
	public static boolean compareLists(List<String> prevList,
			List<String> modelList) {
		boolean indicator = false;
		if (prevList != null && modelList != null
				&& prevList.size() == modelList.size()) {
			outerloop: for (String modelListdata : modelList) {
				if (!containsIgnoreCase(prevList, modelListdata)) {
					indicator = false;
					break outerloop;

				} else {
					indicator = true;
				}
			}
		}
		return indicator;
	}

	/**
	 * Contains ignore case.
	 *
	 * @param list
	 *            the list
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean containsIgnoreCase(List<String> list, String name) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	/**
	 * Contains ignore case.
	 *
	 * @param list
	 *            the list
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean containsIgnoreCase(Set<String> list, String name) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	/**
	 * Contains ignore case.
	 *
	 * @param haystack
	 *            the haystack
	 * @param needle
	 *            the needle
	 * @return true, if successful
	 */
	public static boolean containsIgnoreCase(String haystack, String needle) {
		Pattern p = Pattern.compile(needle, Pattern.CASE_INSENSITIVE
				+ Pattern.LITERAL);
		Matcher m = p.matcher(haystack);
		return m.find();
	}

	/**
	 * Execute js.
	 *
	 * @param driver
	 *            the driver
	 * @param code
	 *            the code
	 * @return the string
	 */
	public static String executeJS(WebDriver driver, String code) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript(code);
	}

	/**
	 * Generate number.
	 *
	 * @param length
	 *            the length
	 * @return the string
	 */
	public static String generateNumber(int length) {
		Random rng1 = new Random();
		String characters = "0123456789";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng1.nextInt(characters.length()));
		}
		return new String(text);
	}

	/**
	 * Generate string.
	 *
	 * @param length
	 *            the length
	 * @return the string
	 */
	public static String generateString(int length) {
		Random rng1 = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng1.nextInt(characters.length()));
		}
		return new String(text);
	}

	/**
	 * Generate string.
	 *
	 * @param length
	 *            the length
	 * @return the string
	 */
	public static String generateSpecialCharactersString(int length) {
		Random rng1 = new Random();
		String characters = "!@#$%^&*()_+!@#$%^&*()_+!@#$%^&*()_+!@#$%^&*()_+";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng1.nextInt(characters.length()));
		}
		return new String(text);
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public static String getDay() {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public static String getMonth() {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the previous date.
	 *
	 * @param daysAgo
	 *            the days ago
	 * @return the previous date
	 */
	public static String getPreviousDate(int daysAgo) {
		DateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
		newFormat.setLenient(true);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.DATE, -1 * daysAgo);
		return newFormat.format(cal.getTime());
	}

	/**
	 * Gets the today.
	 *
	 * @return the today
	 */
	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}

	/**
	 * Gets the today.
	 *
	 * @param format
	 *            the format
	 * @return the today
	 */
	public static String getToday(String format) {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the next date.
	 *
	 * @param daysAfter
	 *            the days daysAfter
	 * @return the next date
	 */
	public static String getNextDate(int daysAfter) {
		DateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
		newFormat.setLenient(true);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.DATE, daysAfter);
		return newFormat.format(cal.getTime());
	}

	// Method which return the unique name in date-time format.
	/**
	 * Gets the unique name.
	 *
	 * @return the unique name
	 */
	public static String getUniqueName() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public static String getYear() {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the current browser name.
	 *
	 * @param driver
	 *            the driver
	 * @return the current browser name
	 */
	public static String getCurrentBrowserName(WebDriver driver) {
		if (driver instanceof InternetExplorerDriver) {
			return "IE";
		} else if (driver instanceof FirefoxDriver) {
			return "Firefox";
		} else if (driver instanceof ChromeDriver) {
			return "Chrome";
		} else {
			return null;
		}
	}

	/**
	 * Update testng xml include name value
	 *
	 * @param doc
	 *            , include node name value
	 * 
	 * @return void
	 */
	public static void updateAttributeValue(String testngXML, String testType) {
		File xmlFile = new File(testngXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList run = doc.getElementsByTagName("run");
			Element allInclude = null;
			// loop for each include
			allInclude = (Element) run.item(0);
			Element nodeChange = null;
			NodeList allincludeNodes = allInclude
					.getElementsByTagName("include");
			for (int i = 0; i < allincludeNodes.getLength(); i++)
				nodeChange = (Element) allincludeNodes.item(i);
			nodeChange.setAttribute("name", testType);
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(testngXML));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			Logs.LOGGER
					.info("TestNG XML file updated successfully for testType"
							+ testType);
		} catch (Exception ex) {
			Logs.LOGGER.info(ex.getMessage().toString());
		}
	}

}
