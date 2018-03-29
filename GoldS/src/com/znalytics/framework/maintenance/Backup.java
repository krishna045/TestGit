// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.maintenance;

import java.io.File;
import java.util.Iterator;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.DataSource;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.utility.Utils;

/**
 * The Class Backup.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 * 
 *        purpose of this class is to take the back of the report folder.
 */
public class Backup {

	/** The HouseKeeping object. */
	HouseKeeping hk = new HouseKeeping();

	/**
	 * Backup reports.
	 */
	public void backupReports() {
		hk.cleanReportFolder();
		if (DataSource.globalConfig.get("backup.reports").equalsIgnoreCase(
				"yes")) {
			Logs.LOGGER.info("Taking backup of reports ....");
			if (DataSource.report.isEmpty()) {
				Logs.LOGGER.info("No Data found to take backup.");
			} else {
				File destination = new File(
						DataSource.globalConfig.get("backup.reports.location")
								+ "/" + Utils.getToday() + "/"
								+ Constants.CURRENT_CLIENT);
				File source = null;
				Iterator<String> iter = DataSource.report.iterator();
				while (iter.hasNext()) {
					try {
						String record = iter.next().toString();
						Logs.LOGGER.info(record);
						String[] Tokens = record.split(";");
						Logs.LOGGER.info("Starting the reports backup >> "
								+ Tokens[5]);
						source = new File(Tokens[5]);
						if (!destination.exists()) {
							if (destination.mkdirs()) {
								FileUtils.copyDirectoryToDirectory(source,
										destination);
							} else {
								Logs.LOGGER
										.info("Problem in creating required dir structure.");
							}
						} else {
							FileUtils.copyDirectoryToDirectory(source,
									destination);
						}
						Logs.LOGGER.info("Copy done successfully.");
					} catch (Exception ex) {
						Logs.LOGGER
								.warning("Something went wrong in report backup to >> "
										+ DataSource.globalConfig
												.get("backup.reports.location"));
						Logs.LOGGER.log(Level.WARNING, "", ex);
					}
				}
			}
		} else {
			Logs.LOGGER.info("No configuration found for report backup.");
		}
	}
}
