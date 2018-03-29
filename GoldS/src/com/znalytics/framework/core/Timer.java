// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

/**
 * The Class Timer.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Timer {

	/** The start time. */
	private String startTime;

	/** The end time. */
	private String endTime;

	/**
	 * End.
	 */
	public void end() {
		this.endTime = getCurrentTime();
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * Gets the total time.
	 *
	 * @return the total time
	 */
	public String getTotalTime() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			return calTimeDiff(format.parse(getStartTime()),
					format.parse(getStartTime()));
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"Problem in getting time difference.", e);
			return null;
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		this.startTime = getCurrentTime();
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	private String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	/**
	 * Cal time diff.
	 *
	 * @param d1
	 *            the d1
	 * @param d2
	 *            the d2
	 * @return the string
	 */
	private String calTimeDiff(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (diffDays + " days, " + diffHours + " hours, " + diffMinutes
				+ " minutes, " + diffSeconds + " seconds.");
	}
}
