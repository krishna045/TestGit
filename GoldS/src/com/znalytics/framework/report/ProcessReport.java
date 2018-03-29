// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.framework.report;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.DataSource;
import com.znalytics.framework.core.Logs;

/**
 * The Class ProcessReport.
 */
public class ProcessReport {

	/**
	 * Process report data.
	 *
	 * @return the string
	 * @throws UnknownHostException
	 */
	public static String ProcessReportData() {
		String reportData = "";
		String testOutputFolder;
		String testNGXML;
		String testModule;
		String testType;
		String testURL;

		reportData += "<h4>Test Details:<h4>";
		reportData += "<table border=\"1\">";
		reportData += "<tr>";
		reportData += "<td>HostName</td>";
		try {
			reportData += "<td>" + InetAddress.getLocalHost().getHostName()
					+ "</td>";
		} catch (UnknownHostException e) {
		}
		reportData += "<tr>";
		reportData += "<tr>";
		reportData += "<td>UserName</td>";
		reportData += "<td>" + System.getProperty("user.name") + "</td>";
		reportData += "<tr>";
		reportData += "<tr>";
		reportData += "<td>TestClient</td>";
		reportData += "<td>" + Constants.CURRENT_CLIENT + "</td>";
		reportData += "<tr>";
		reportData += "<tr>";
		reportData += "<td>Browser</td>";
		reportData += "<td>" + DataSource.globalConfig.get("browser") + "</td>";
		reportData += "<tr>";
		reportData += "<tr>";
		reportData += "<td>Environment</td>";
		reportData += "<td>" + DataSource.globalConfig.get("env") + "</td>";
		reportData += "<tr>";

		Iterator<String> iter = DataSource.report.iterator();
		while (iter.hasNext()) {
			String record = iter.next().toString();
			String[] tokens = record.split(";");
			testOutputFolder = tokens[0].trim();
			testNGXML = tokens[1].trim();
			testModule = tokens[2].trim();
			testType = tokens[3].trim();
			testURL = tokens[4].trim();
			reportData += "<tr>";
			reportData += "<td>Test Type</td>";
			reportData += "<td>" + testType + "</td>";
			reportData += "<tr>";
			reportData += "</table>";
			reportData += getReportHTMLFile(
					testOutputFolder + System.getProperty("file.separator")
							+ testModule, testNGXML);
		}
		return reportData;
	}

	/**
	 * Gets the report html file.
	 *
	 * @param testOutputFolder
	 *            the test output folder
	 * @param testNGXML
	 *            the test ngxml
	 * @return the report html file
	 */
	public static String getReportHTMLFile(String testOutputFolder,
			String testNGXML) {
		String reportData = "";
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(testNGXML));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("test");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				String name = element.getAttribute("name");
				reportData += getData(testOutputFolder
						+ System.getProperty("file.separator") + name + ".html");
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"problem in getting result html file.", e);
		}
		return reportData;
	}

	/**
	 * Gets the data.
	 *
	 * @param reportFileName
	 *            the report file name
	 * @return the data
	 */
	public static String getData(String reportFileName) {
		StringBuilder reportData = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					reportFileName));
			String fileData = null;
			while ((fileData = reader.readLine()) != null) {
				reportData.append(fileData);
			}
			reader.close();
			return addBackGroundColor(reportData.toString());
		} catch (IOException e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in reading report file", e);
		}
		return null;
	}

	/**
	 * Adds the back ground color.
	 *
	 * @param reportData
	 *            the report data
	 * @return the string
	 */
	private static String addBackGroundColor(String reportData) {
		Logs.LOGGER.info("Adding color to the report data.");
		reportData = reportData
				.replace(
						"<tr><td colspan='4' align='center'><b>FAILED TESTS</b></td></tr>",
						"<tr><td colspan='4' align='center' bgcolor='#DD0000'><b>FAILED TESTS</b></td></tr>");
		reportData = reportData
				.replace(
						"<tr><td colspan='4' align='center'><b>PASSED TESTS</b></td></tr>",
						"<tr><td colspan='4' align='center' bgcolor='#00AA00'><b>PASSED TESTS</b></td></tr>");
		reportData = reportData
				.replace(
						"<tr><td colspan='4' align='center'><b>SKIPPED TESTS</b></td></tr>",
						"<tr><td colspan='4' align='center' bgcolor='#CCCC00'><b>SKIPPED TESTS</b></td></tr>");
		reportData = reportData.replaceAll("class='log'", "");
		reportData = reportData.replaceAll("Show output", "");
		reportData = reportData.replaceAll("Show all outputs", "");
		return reportData;
	}
}
