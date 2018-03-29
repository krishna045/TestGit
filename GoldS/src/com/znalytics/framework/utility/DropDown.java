// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.znalytics.framework.core.Logs;

/**
 * The Class DropDown.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class DropDown {

	/** The drop list. */
	private Select dropList;

	/**
	 * Instantiates a new drop down.
	 *
	 * @param dropList
	 *            the drop list
	 */
	public DropDown(Select dropList) {
		this.dropList = dropList;
	}

	/**
	 * Gets the drop list.
	 *
	 * @return the drop list
	 */
	public Select getDropList() {
		return this.dropList;
	}

	/**
	 * Sets the drop list.
	 *
	 * @param dropList
	 *            the new drop list
	 */
	public void setDropList(Select dropList) {
		this.dropList = dropList;
	}

	/**
	 * Gets the drop down option count.
	 *
	 * @return the drop down option count
	 */
	public int getDropDownOptionCount() {
		int count = dropList.getOptions().size();
		Logs.LOGGER.info("Total # of Options in dropdown: " + count);
		return count;
	}

	/**
	 * Gets the drop down options.
	 *
	 * @return the drop down options
	 */
	public List<WebElement> getDropDownOptions() {
		Logs.LOGGER.info("Options in dropdown: " + dropList.getOptions());
		return dropList.getOptions();
	}

	/**
	 * Gets the selected option for dropdown.
	 *
	 * @return the selected option for dropdown
	 */
	public String getSelectedOptionForDropdown() {
		if (null == dropList.getFirstSelectedOption())
			return null;
		return dropList.getFirstSelectedOption().getText();
	}

	/**
	 * Checks if is multi select drop down.
	 *
	 * @return true, if is multi select drop down
	 */
	public boolean isMultiSelectDropDown() {
		return dropList.isMultiple();
	}

	/**
	 * Checks if is option text present for dropdown.
	 *
	 * @param textToFind
	 *            the text to find
	 * @return true, if is option text present for dropdown
	 */
	public boolean isOptionTextPresentForDropdown(String textToFind) {
		List<WebElement> options = getDropDownOptions();
		boolean found = false;
		if (options != null & !options.isEmpty()) {
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(textToFind)) {
					found = true;
					break;
				}
			}
		}
		return found;
	}

	/**
	 * Select dropdown option by index.
	 *
	 * @param index
	 *            the index
	 */
	public void selectDropdownOptionByIndex(int index) {
		Logs.LOGGER.info("Selecting Dropdown option (index) : " + index);
		dropList.selectByIndex(index);
	}

	/**
	 * Select dropdown option by text.
	 *
	 * @param optionText
	 *            the option text
	 */
	public void selectDropdownOptionByText(String optionText) {
		Logs.LOGGER.info("Selecting Dropdown option (text) : " + optionText);
		dropList.selectByVisibleText(optionText);
	}

	/**
	 * Select dropdown option by value.
	 *
	 * @param value
	 *            the value
	 */
	public void selectDropdownOptionByValue(String value) {
		Logs.LOGGER.info("Selecting Dropdown option (value) : " + value);
		dropList.selectByValue(value);
	}

}
