// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.znalytics.framework.core.Logs;

/**
 * The Class RadioButton.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 9, 2015
 */
public class RadioButton {

	/** The radio button list. */
	private List<WebElement> radioButtonList;

	/**
	 * Instantiates a new radio button.
	 *
	 * @param radioButtonList
	 *            the radio button list
	 */
	public RadioButton(List<WebElement> radioButtonList) {
		this.radioButtonList = radioButtonList;
	}

	/**
	 * Gets the radio button.
	 *
	 * @return the radioButton
	 */
	public List<WebElement> getRadioButton() {
		return this.radioButtonList;
	}

	/**
	 * Sets the radio button.
	 *
	 * @param radioButtonList
	 *            the new radio button
	 */
	public void setRadioButton(List<WebElement> radioButtonList) {
		this.radioButtonList = radioButtonList;
	}

	/**
	 * Gets the options count.
	 *
	 * @return the options count
	 */
	public int getOptionsCount() {
		int count = radioButtonList.size();
		Logs.LOGGER.info("Total # of Options in RadioButton: " + count);
		return count;
	}

	/**
	 * Gets the options text.
	 *
	 * @return the options text
	 */
	public List<String> getOptionsText() {
		Logs.LOGGER.info("Getting Radio button Options text ...");
		List<String> radioOptions = new ArrayList<String>();
		for (int i = 0; i < radioButtonList.size(); i++) {
			String option = radioButtonList.get(i).getAttribute("value");
			radioOptions.add(option);
		}
		return radioOptions;
	}

	/**
	 * Gets the selected option.
	 *
	 * @return the selected option
	 */
	public String getSelectedOption() {
		for (int i = 0; i < radioButtonList.size(); i++) {
			if (radioButtonList.get(i).isSelected()) {
				String option = radioButtonList.get(i).getAttribute("value");
				Logs.LOGGER.info("Selected RadioButton Option: " + option);
				return option;
			}
		}
		return null;
	}

	/**
	 * Select option by value.
	 *
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean selectOptionByValue(String value) {
		for (int i = 0; i < radioButtonList.size(); i++) {
			if (radioButtonList.get(i).getAttribute("value")
					.equalsIgnoreCase(value)) {
				Logs.LOGGER
						.info("Selecting Radio Buttion with value: " + value);
				radioButtonList.get(i).click();
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
		radioButtonList.get(index).click();
	}

	/**
	 * Checks if is selected by index.
	 *
	 * @param index
	 *            the index
	 * @return true, if is selected by index
	 */
	public boolean isSelectedByIndex(int index) {
		Boolean selected = radioButtonList.get(index).isSelected();
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
		for (int i = 0; i < radioButtonList.size(); i++) {
			if (radioButtonList.get(i).getAttribute("value")
					.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

}
