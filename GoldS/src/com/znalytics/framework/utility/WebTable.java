// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.znalytics.framework.core.Logs;

/**
 * The Class WebTable.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class WebTable {

	/** The web table. */
	private WebElement webTable;
	private WebDriver driver;

	/**
	 * Instantiates a new web table.
	 *
	 * @param webTable
	 *            the web table
	 */
	public WebTable(WebElement webTable, WebDriver driver) {
		this.webTable = webTable;
		this.driver = driver;
	}

	/**
	 * Gets the cell element.
	 *
	 * @param rowIdx
	 *            the row idx
	 * @param colIdx
	 *            the col idx
	 * @return the cell element
	 */
	public WebElement getCellElement(int rowIdx, int colIdx) {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement dataRow = tableRows.get(rowIdx);
		List<WebElement> tableColumns = dataRow.findElements(By.tagName("td"));
		return tableColumns.get(colIdx);
	}

	/**
	 * Gets the header element.
	 *
	 * @param rowIdx
	 *            the row idx
	 * @param colIdx
	 *            the col idx
	 * @return the header element
	 */
	public WebElement getCellElementHeader(int rowIdx, int colIdx) {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement dataRow = tableRows.get(rowIdx);
		List<WebElement> tableColumns = dataRow.findElements(By.tagName("th"));
		return tableColumns.get(colIdx);
	}

	/**
	 * Gets the column count.
	 *
	 * @return the column count
	 */
	public int getColumnCount() {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement headerRow = tableRows.get(0);
		List<WebElement> tableColumns = headerRow
				.findElements(By.tagName("td"));
		int columnCount = tableColumns.size();
		Logs.LOGGER.info("Total # of column in table: " + columnCount);
		return columnCount;
	}

	/**
	 * Gets the column count for header.
	 *
	 * @return the column count
	 */
	public int getColumnCountHeader() {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		WebElement headerRow = tableRows.get(0);
		List<WebElement> tableColumns = headerRow
				.findElements(By.tagName("th"));
		int columnCount = tableColumns.size();
		Logs.LOGGER.info("Total # of column in table header: " + columnCount);
		return columnCount;
	}

	/**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
	public int getRowCount() {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		int rowCount = tableRows.size();
		Logs.LOGGER.info("Total # of row in table: " + rowCount);
		return rowCount;
	}

	/**
	 * Gets the row element.
	 *
	 * @param rowIdx
	 *            the row idx
	 * @return the row element
	 */
	public WebElement getRowElement(int rowIdx) {
		List<WebElement> tableRows = webTable.findElements(By.tagName("tr"));
		return tableRows.get(rowIdx);
	}

	/**
	 * Search data.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean searchData(String text) {
		List<WebElement> rows = webTable.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			List<WebElement> columns = row.findElements(By.tagName("td"));
			Iterator<WebElement> j = columns.iterator();
			while (j.hasNext()) {
				WebElement column = j.next();
				Actions builder = new Actions(driver);
				builder.moveToElement(column).build().perform();
				if (column.getText().trim().equalsIgnoreCase(text)
						|| (column.getText().trim().equalsIgnoreCase("") && text
								.equalsIgnoreCase("0"))) {
					Logs.LOGGER.info("SUCCESS: Found text " + text);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Search partial data.
	 *
	 * @param partialText
	 *            the partial text
	 * @return true, if successful
	 */
	public boolean searchPartialData(String partialText) {
		List<WebElement> rows = webTable.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			List<WebElement> columns = row.findElements(By.tagName("td"));
			Iterator<WebElement> j = columns.iterator();
			while (j.hasNext()) {
				WebElement column = j.next();
				if (column.getText().trim().contains(partialText)) {
					Logs.LOGGER.info("SUCCESS: Found Partial text "
							+ partialText);
					Logs.LOGGER.info(column.getText().trim() + " --> "
							+ partialText);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Search data and get webelement.
	 *
	 * @param text
	 *            the text
	 * @return webelement, if successful
	 */
	public WebElement getDataWebElement(String text) {
		List<WebElement> rows = webTable.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			List<WebElement> columns = row.findElements(By.tagName("td"));
			Iterator<WebElement> j = columns.iterator();
			while (j.hasNext()) {
				WebElement column = j.next();
				if (column.getText().trim().equalsIgnoreCase(text)) {
					Logs.LOGGER
							.info("SUCCESS: Found web element containing text "
									+ text);
					return column;
				}
			}
		}
		return null;
	}

	/**
	 * Search partial data and get webelement.
	 *
	 * @param partialText
	 *            the partial text
	 * @return webelement, if successful
	 */
	public WebElement getPartialDataWebElement(String partialText) {
		List<WebElement> rows = webTable.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			List<WebElement> columns = row.findElements(By.tagName("td"));
			Iterator<WebElement> j = columns.iterator();
			while (j.hasNext()) {
				WebElement column = j.next();
				if (column.getText().trim().contains(partialText)) {
					Logs.LOGGER
							.info("SUCCESS: Found web element containing Partial text "
									+ partialText);
					Logs.LOGGER.info(column.getText().trim() + " --> "
							+ partialText);
					return column;
				}
			}
		}
		return null;
	}
}
