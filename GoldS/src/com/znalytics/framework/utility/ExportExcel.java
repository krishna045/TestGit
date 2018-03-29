// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.SleepCommand;
import com.znalytics.framework.core.XLReader;

/**
 * The Class ExportExcel.
 */
public class ExportExcel {

	/** The export btn. */
	private WebElement exportBtn;

	/** The driver. */
	private WebDriver driver;

	/** The file name. */
	private String fileName;

	/** The file path. */
	private String filePath;

	/** The dir. */
	private String dir;

	/**
	 * Instantiates a new export excel.
	 *
	 * @param exportBtn
	 *            the export btn
	 * @param filePath
	 *            the file path
	 * @param driver
	 *            the driver
	 */
	public ExportExcel(WebElement exportBtn, String filePath, WebDriver driver) {
		this.exportBtn = exportBtn;
		this.filePath = filePath;
		this.driver = driver;
		Path path = new Path(this.filePath);
		this.fileName = path.getFileName();
		this.dir = path.getBaseDir();
	}

	/**
	 * Click export excel.
	 *
	 * @return true, if successful
	 */
	public boolean clickAndWaitForExcel() {
		deleteFile();
		Element.click(driver, exportBtn);
		if (waitForFile())
			return true;
		else
			return false;
	}

	/**
	 * Gets the excel header.
	 *
	 * @return the excel header
	 */
	public List<String> getExcelHeader() {
		List<String> workSheetList = XLReader.getWorkSheets(this.dir
				+ this.fileName);
		return XLReader.getDataHeader(this.dir + this.fileName,
				workSheetList.get(0));
	}

	/**
	 * Gets the excel row count.
	 *
	 * @return the excel row count
	 */
	public int getExcelRowCount() {
		List<String> workSheetList = XLReader.getWorkSheets(this.dir
				+ this.fileName);
		int rowCount = XLReader.getEntireData(this.dir + this.fileName,
				workSheetList.get(0))[0].length;
		Logs.LOGGER.info("Total # of rows: " + rowCount);
		return rowCount;
	}

	/**
	 * Gets the excel col count.
	 *
	 * @return the excel col count
	 */
	public int getExcelColCount() {
		List<String> workSheetList = XLReader.getWorkSheets(this.dir
				+ this.fileName);
		int colCount = XLReader.getDataHeader(this.dir + this.fileName,
				workSheetList.get(0)).size();
		Logs.LOGGER.info("Total # of col: " + colCount);
		return colCount;
	}

	/**
	 * Gets the excel data.
	 *
	 * @return the excel data
	 */
	public String[][] getExcelData() {
		List<String> workSheetList = XLReader.getWorkSheets(this.dir
				+ this.fileName);
		return XLReader.getEntireData(this.dir + this.fileName,
				workSheetList.get(0));
	}

	/**
	 * Wait for file.
	 *
	 * @return true, if successful
	 */
	private boolean waitForFile() {
		Logs.LOGGER.info("Waiting for file to appear ...");
		int counter = 0;
		File directory = new File(this.dir);
		while (counter < Constants.DEFAULTTIME) {
			try {
				SleepCommand.sleepTwoSecond();
				File[] files = directory.listFiles();
				if (null != files) {
					for (File file : files) {
						if (file.getName().equalsIgnoreCase(this.fileName)) {
							Logs.LOGGER.info("File found.");
							if (file.canWrite())
								return true;
						}
					}
				}
			} catch (Exception e) {
				Logs.LOGGER
						.warning("IGNORED -- Exception while searching for file"
								+ e);
			}
			counter++;
		}
		return false;
	}

	/**
	 * Delete file.
	 */
	private void deleteFile() {
		String filePath = this.dir + this.fileName;
		File file = new File(filePath);
		if (file.exists()) {
			Logs.LOGGER.info("Deleting old file: " + filePath);
			Logs.LOGGER.info("Old file last Modified time: "
					+ FileIO.getFileAttributes(FileIO.Attributes.CREATIONTIME,
							filePath));
			if (file.delete())
				Logs.LOGGER.info("Old file deleted.");
			else
				Logs.LOGGER.info("Problem in deleting old file.");
		} else {
			Logs.LOGGER.info("Not able to find the file: " + this.fileName);
		}
	}
}
