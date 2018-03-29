// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.maintenance;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.DataSource;
import com.znalytics.framework.core.Logs;

/**
 * The Class HouseKeeping.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class HouseKeeping {

	/**
	 * Clean files.
	 *
	 * @param location
	 *            the location
	 * @param duration
	 *            the duration
	 */
	public void cleanFiles(String location, Long duration) {
		Logs.LOGGER.info("Starting housekeeping  ...");
		Logs.LOGGER.info("Removing files older than " + duration
				+ " days from : " + location);
		try {
			File folder = new File(location);
			if (folder.exists()) {
				File[] files = folder.listFiles();
				if (null != files) {
					for (int i = 0; i < files.length; i++) {
						long diff = new Date().getTime()
								- files[i].lastModified();
						if (diff > duration * 24 * 60 * 60 * 1000) {
							if (!files[i].delete()) {
								Logs.LOGGER
										.warning("Problem in deleting file: "
												+ files[i].getAbsolutePath()
														.toString());
							} else {
								Logs.LOGGER.info("   : " + files[i].getName());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"No worries, will try again next time.", e);
		}
	}

	/**
	 * Clean report folder.
	 */
	public void cleanReportFolder() {
		Iterator<String> iter = DataSource.report.iterator();
		while (iter.hasNext()) {
			try {
				String record = iter.next().toString();
				String[] Tokens = record.split(";");
				File[] files = new File(Tokens[0])
						.listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								return (name.endsWith(".xml")
										|| name.endsWith(".html")
										|| name.endsWith(".png")
										|| name.endsWith(".js")
										|| name.endsWith(".gif") || name
										.endsWith(".css"));
							}
						});
				for (File file : files) {
					file.delete();
				}
				FileUtils
						.deleteDirectory(new File(Tokens[0] + "/junitreports"));
				FileUtils.deleteDirectory(new File(Tokens[0] + "/old"));
			} catch (Exception ex) {
				Logs.LOGGER.log(Level.WARNING,
						"Problem in removing the files.", ex);
			}
		}
	}

	/**
	 * Clean test output folder.
	 *
	 * @param duration
	 *            the duration
	 */
	public void cleanTestOutputFolder(Long duration) {
		Logs.LOGGER.info("Starting housekeeping for TestReports ...");
		Logs.LOGGER.info("Removing files older than " + duration
				+ " days from : " + Constants.TMP);
		File dir = new File(Constants.TMP);
		File[] subDirs = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		for (File subDir : subDirs) {
			long diff = new Date().getTime() - subDir.lastModified();
			if (diff > duration * 24 * 60 * 60 * 1000) {
				try {
					Logs.LOGGER.info("   : " + subDir.getName());
					FileUtils.deleteDirectory(subDir);
				} catch (Exception e) {
				}
			}
		}
	}
}
