// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.znalytics.framework.core.Logs;

/**
 * The Class CheckBox.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 9, 2015
 */
public class CheckBox {

	/** The check box list. */
	private List<WebElement> checkBoxList;

	/**
	 * Instantiates a new check box.
	 *
	 * @param checkBoxList
	 *            the check box list
	 */
	public CheckBox(List<WebElement> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

	/**
	 * Gets the check box list.
	 *
	 * @return the checkBoxList
	 */
	public List<WebElement> getCheckBoxList() {
		return this.checkBoxList;
	}

	/**
	 * Sets the check box list.
	 *
	 * @param checkBoxList
	 *            the checkBoxList to set
	 */
	public void setCheckBoxList(List<WebElement> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}

	/**
	 * Gets the options count.
	 *
	 * @return the options count
	 */
	public int getOptionsCount() {
		int count = checkBoxList.size();
		Logs.LOGGER.info("Total # of Options in checkBox: " + count);
		return count;
	}

	/**
	 * Gets the options text.
	 *
	 * @return the options text
	 */
	public List<String> getOptionsText() {
		Logs.LOGGER.info("Getting Radio button Options text ...");
		List<String> checkBoxOptions = new ArrayList<String>();
		for (int i = 0; i < checkBoxList.size(); i++) {
			String option = checkBoxList.get(i).getAttribute("value");
			checkBoxOptions.add(option);
		}
		return checkBoxOptions;
	}

	/**
	 * Gets the selected option.
	 *
	 * @return the selected option
	 */
	public List<String> getSelectedOption() {
		List<String> checkBoxOptions = new ArrayList<String>();
		for (int i = 0; i < checkBoxList.size(); i++) {
			if (checkBoxList.get(i).isSelected()) {
				String option = checkBoxList.get(i).getAttribute("value");
				checkBoxOptions.add(option);
			}
		}
		Logs.LOGGER.info("Total # of selected checkbox: "
				+ checkBoxOptions.size());
		return checkBoxOptions;
	}

	/**
	 * Select option by value.
	 *
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean selectOptionByValue(String value) {
		for (int i = 0; i < checkBoxList.size(); i++) {
			if (checkBoxList.get(i).getAttribute("value")
					.equalsIgnoreCase(value)) {
				Logs.LOGGER
						.info("Selecting Radio Buttion with value: " + value);
				checkBoxList.get(i).click();
				return true;
			}
		}
		return false;
	}

	/**
	 * Select option by index.
	 *
	 * @param index
	 *            the index
	 */
	public void selectOptionByIndex(int index) {
		Logs.LOGGER.info("Selecting Radio Buttion with index: " + index);
		checkBoxList.get(index).click();
	}

	/**
	 * Checks if is selected by index.
	 *
	 * @param index
	 *            the index
	 * @return true, if is selected by index
	 */
	public boolean isSelectedByIndex(int index) {
		Boolean selected = checkBoxList.get(index).isSelected();
		Logs.LOGGER.info("Check if Radio Buttion with index: " + index);
		return selected;
	}

	/**
	 * Checks if is selected by value.
	 *
	 * @param value
	 *            the value
	 * @return true, if is selected by value
	 */
	public boolean isSelectedByValue(String value) {
		for (int i = 0; i < checkBoxList.size(); i++) {
			if (checkBoxList.get(i).getAttribute("value")
					.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

}
