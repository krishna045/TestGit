// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The Class Logs.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Logs {

	/** The logger. */
	public static Logger LOGGER;

	/** The current date time. */
	private static String currentDateTime;

	/** The logfilename. */
	public static FileHandler logfilename = null;

	static {
		try {
			logfilename = new FileHandler(Constants.LOGSDIR + "/"
					+ Constants.LOGFILENAME, true);
			Formatter logformator = new Formatter() {
				@Override
				public String format(LogRecord record) {
					Date now = new Date();
					currentDateTime = DateFormat.getInstance().format(now)
							.toString();
					if (record.getLevel().toString().equalsIgnoreCase("SEVERE")) {
						return "*****************************************"
								+ "********* \n" + currentDateTime + " "
								+ record.getLevel() + "  :  "
								+ record.getSourceClassName() + "."
								+ record.getSourceMethodName() + " >> "
								+ record.getMessage() + "\n"
								+ "****************************************"
								+ "********** \n";
					} else {
						return currentDateTime + " " + record.getLevel()
								+ "  :  " + record.getMessage() + "\n";
					}
				}
			};
			logfilename.setFormatter(logformator);
			LOGGER = Logger.getLogger("Automation");
			// LOGGER.setUseParentHandlers(false);
			LOGGER.addHandler(logfilename);
			LOGGER.setLevel(Level.ALL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
