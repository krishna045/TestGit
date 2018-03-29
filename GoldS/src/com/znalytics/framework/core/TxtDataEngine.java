// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;

/**
 * The Class TxtDataEngine.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class TxtDataEngine {

	/**
	 * Read txt file.
	 *
	 * @param fileName
	 *            the file name
	 * @return the hashtable
	 */
	public static Hashtable<String, String> readTxtFile(String fileName) {
		Hashtable<String, String> insert = new Hashtable<String, String>();
		Logs.LOGGER.info("Reading file: " + fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String fileData = null;
			while ((fileData = reader.readLine()) != null) {
				fileData = fileData.trim();
				if (!fileData.startsWith("#") && fileData.length() > 0) {
					String key = fileData.substring(0, fileData.indexOf('='))
							.trim();
					String value = fileData.substring(
							fileData.indexOf('=') + 1, fileData.length())
							.trim();
					insert.put(key, value);
				}
			}
			reader.close();
		} catch (IOException e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in reading property file.",
					e);
		}
		Logs.LOGGER.info("Total data loaded: " + insert.size());
		return insert;
	}
}
