// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Class XLWriter.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class XLWriter {

	/**
	 * Update excel data.
	 *
	 * @param fileName
	 *            the file name
	 * @param sheetName
	 *            the sheet name
	 * @param content
	 *            the content
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 */
	public static void updateExcelData(String fileName, String sheetName,
			String content, int row, int col) {
		Logs.LOGGER.info("Writing data to cell: " + row + "," + col);
		Workbook workbook = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Cell cell = null;
		try {
			fis = new FileInputStream(fileName);
			workbook = getWorkbookObj(fis, fileName);
			Sheet sheet = workbook.getSheet(sheetName);
			cell = sheet.getRow(row).getCell(col);
			if (null == cell) {
				cell = sheet.getRow(row).createCell(col);
				cell.setCellType(Cell.CELL_TYPE_STRING);
			}
			cell.removeCellComment();
			cell.setCellValue(content);
			fis.close();
			fos = new FileOutputStream(fileName);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	// update the excel file based on 2d array
	/**
	 * Update excel data.
	 *
	 * @param data
	 *            the data
	 * @param fileName
	 *            the file name
	 * @param sheetName
	 *            the sheet name
	 */
	public static void updateExcelData(String[][] data, String fileName,
			String sheetName) {
		Logs.LOGGER.info("Writing data ....");
		Workbook workbook = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Cell cell = null;
		Row row = null;
		try {
			fis = new FileInputStream(fileName);
			workbook = getWorkbookObj(fis, fileName);
			Sheet sheet = workbook.getSheet(sheetName);
			for (int i = 0; i < data.length; i++) {
				int rowNumber = Integer.parseInt(data[i][0]);
				row = sheet.getRow(rowNumber);
				if (null == row) {
					row = sheet.createRow(rowNumber);
				}
				for (int j = 0; j < data[i].length; j++) {
					cell = sheet.getRow(rowNumber).getCell(j,
							Row.CREATE_NULL_AS_BLANK);
					if (null == cell) {
						cell = sheet.getRow(rowNumber).createCell(j);
					}
					cell.removeCellComment();
					cell.setCellValue(data[i][j]);
				}
			}
			fis.close();
			fos = new FileOutputStream(fileName);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	// Create new excel work sheet
	/**
	 * Creates the work sheet.
	 *
	 * @param fileName
	 *            the file name
	 * @param sheetName
	 *            the sheet name
	 */
	public static void createWorkSheet(String fileName, String sheetName) {
		Workbook workbook = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		boolean found = false;
		try {
			fis = new FileInputStream(fileName);
			workbook = getWorkbookObj(fis, fileName);

			// First check is sheet exists ?
			int numberOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				if (workbook.getSheetAt(i).getSheetName()
						.equalsIgnoreCase(sheetName)) {
					found = true;
					break;
				}
			}
			fis.close();
			if (!found) {
				workbook.createSheet(sheetName);
				fos = new FileOutputStream(fileName);
				workbook.write(fos);
				fos.close();
			} else {
				Logs.LOGGER.info("Sheet already present.");
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
	}

	// Get the object based on the excel files
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
}
