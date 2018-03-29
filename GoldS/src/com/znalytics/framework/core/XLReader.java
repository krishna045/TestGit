// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

/**
 * The Class XLReader.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class XLReader {

	/**
	 * Gets the test data.
	 *
	 * @return the test data
	 */
	@DataProvider(name = "ExcelDataLoader")
	public static Object[][] getTestData() {
		String[][] data = null;
		if (null != (data = getEntireData())) {
			return getValidTestData(data);
		} else {
			Logs.LOGGER.info("No data found in excel file.");
			return null;
		}
	}

	/**
	 * Gets the work sheets.
	 *
	 * @param fileName
	 *            the file name
	 * @return the work sheets
	 */
	public static List<String> getWorkSheets(String fileName) {
		List<String> list = null;
		FileInputStream fis = null;
		try {
			Logs.LOGGER.info("Starting Reading file: " + fileName);
			fis = new FileInputStream(fileName);
			Workbook workbook = getWorkbookObj(fis, fileName);
			int numberOfSheets = workbook.getNumberOfSheets();
			list = new ArrayList<String>();
			for (int i = 0; i < numberOfSheets; i++) {
				list.add(workbook.getSheetAt(i).getSheetName());
			}
			fis.close();
			Logs.LOGGER.info("List of sheets : " + list);
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return list;
	}

	/**
	 * Gets the data header.
	 *
	 * @param fileName
	 *            the file name
	 * @param sheetName
	 *            the sheet name
	 * @return the data header
	 */
	public static List<String> getDataHeader(String fileName, String sheetName) {
		List<String> header = null;
		FileInputStream fis = null;
		try {
			Logs.LOGGER.info("Starting Reading file for header : " + fileName);
			fis = new FileInputStream(fileName);
			Workbook workbook = getWorkbookObj(fis, fileName);
			Sheet sheet = workbook.getSheet(sheetName);
			header = new ArrayList<String>();
			Row row = sheet.getRow(0);
			if (null != row) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					header.add(getCellDataToString(row.getCell(j)));
				}
			}
			fis.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		Logs.LOGGER.info("Header list : " + header);
		return header;
	}

	/**
	 * Gets the entire data.
	 *
	 * @return the entire data
	 */
	public static String[][] getEntireData(String fileName, String sheetName) {
		Logs.LOGGER.info("Starting Reading file: " + fileName);
		String[][] data = null;
		Workbook workbook = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			workbook = getWorkbookObj(fis, fileName);
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][colCount + 1];
			Logs.LOGGER.info("Total # of Data rows: " + rowCount);
			for (int i = 1, x = 0; i <= rowCount; i++, x++) {
				Row row = sheet.getRow(i);

				if (null != row) {
					for (int j = 0; j < colCount + 1; j++) {
						if (j == 0) {
							data[x][j] = row.getRowNum() + 1 + "";
						} else {
							// row.getCell(j-1).setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
							data[x][j] = getCellDataToString(row.getCell(j - 1));
						}
					}
				}
			}
			fis.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return data;
	}

	/**
	 * Gets the entire data.
	 *
	 * @return the entire data
	 */
	public static String[][] getEntireData() {
		Logs.LOGGER.info("Starting Reading file: " + Constants.DATAFILE);
		String[][] data = null;
		Workbook workbook = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(Constants.DATAFILE);
			workbook = getWorkbookObj(fis, Constants.DATAFILE);
			Sheet sheet = workbook.getSheet(Constants.DATASHEET);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][colCount + 1];
			Logs.LOGGER.info("Total # of Data rows: " + rowCount);
			for (int i = 1, x = 0; i <= rowCount; i++, x++) {
				Row row = sheet.getRow(i);
				if (null != row) {
					for (int j = 0; j < colCount + 1; j++) {
						if (j == 0) {
							data[x][j] = row.getRowNum() + 1 + "";
						} else {
							data[x][j] = getCellDataToString(row
									.getCell(
											j - 1,
											org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK));
						}
					}
				}
			}
			fis.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return data;
	}

	/**
	 * Gets the valid test data.
	 *
	 * @param data
	 *            the data
	 * @return the valid test data
	 */
	public static String[][] getValidTestData(String[][] data) {
		Logs.LOGGER.info("Reading excel data rows for tests ...");
		int row = 0;
		String[][] tmp = null;
		try {
			for (int x = 0; x < data.length; x++) {
				if ((null != data[x][1]) && (null != data[x][3])) {
					if (data[x][1].equalsIgnoreCase("yes")
							|| data[x][1].equalsIgnoreCase("y")) {
						row++;
					}
				}
			}
			Logs.LOGGER.info("Total # of valid Test Rows: " + row);
			tmp = new String[row][data[0].length];
			int i = 0;
			for (int x = 0; x < data.length; x++) {
				if (null != data[x][1]) {
					if (data[x][1].equalsIgnoreCase("yes")
							|| data[x][1].equalsIgnoreCase("y")) {
						for (int y = 0; y < data[0].length; y++) {
							tmp[i][y] = data[x][y];
						}
						i++;
					}
				}
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return tmp;
	}

	/**
	 * Gets the workbook obj.
	 *
	 * @param fis
	 *            the fis
	 * @param fileName
	 *            the file name
	 * @return the workbook obj
	 */
	private static Workbook getWorkbookObj(FileInputStream fis, String fileName) {
		try {
			String fileExtensionName = fileName
					.substring(fileName.indexOf("."));
			if (fileExtensionName.equalsIgnoreCase(".xlsx")) {
				return new XSSFWorkbook(fis);
			} else if (fileExtensionName.equalsIgnoreCase(".xls")) {
				return new HSSFWorkbook(fis);
			} else {
				Logs.LOGGER.severe("Invalid file type.");
				Logs.LOGGER
						.severe("We support excel files with extension .xls or .xlsx");
				return null;
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
			return null;
		}
	}

	/**
	 * Gets the cell data to string.
	 *
	 * @param cell
	 *            the cell
	 * @return the cell data to string
	 */
	private static String getCellDataToString(Cell cell) {
		if (null != cell) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue()).trim();

			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue().trim();

			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					if (cell.getDateCellValue().toString() == "") {
						return "";
					} else {
						return cell.toString() + "";
					}
				} else {
					Double doubleValue = cell.getNumericCellValue();
					Long longValue = doubleValue.longValue();
					return new String(longValue.toString().trim());
				}
			default:
				return "";
			}
		} else {
			return "";
		}
	}
}
