// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.logging.Handler;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;

import com.znalytics.framework.maintenance.HouseKeeping;
/**
 * The Class Start.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Start {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			if (checkAndCreateFolder(Constants.LOGSDIR)
					&& checkAndCreateFolder(Constants.TESTOUTPUT)
					&& checkAndCreateFolder(Constants.TMPDIR)
					&& checkAndCreateFolder(Constants.DOWNLOAD)) {
				Constants.LOGFILENAME = getLogFileName();
				Logs.LOGGER
						.info("-------------------------------------------------");
				Logs.LOGGER.info("Starting Zbra Automation ...");
				Logs.LOGGER.info("Loading Internal Data Structure ....");
				DataSource.report = new ArrayList<String>();
				DataSource.globalConfig = new Hashtable<String, String>();

				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("service=grid")) {
						

					}
					if (args[0].equalsIgnoreCase("service=slave")) {
						// TODO
					}
				} else {
					Logs.LOGGER.info("Starting ZBRA in standalone mode ...");
					DataSource.globalConfig = TxtDataEngine
							.readTxtFile(Constants.GLOBALCONFIGPATH);
					parseClientsAndTests();
				}
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Something bad happened.", e);
		} finally {
			HouseKeeping houseKeeping = new HouseKeeping();
			houseKeeping
					.cleanFiles(Constants.LOGSDIR, Long
							.parseLong(DataSource.globalConfig
									.get("log.rotation")));
			houseKeeping
					.cleanFiles(Constants.TMPDIR, Long
							.parseLong(DataSource.globalConfig
									.get("tmp.rotation")));
			houseKeeping.cleanFiles(Constants.DOWNLOAD,
					Long.parseLong(DataSource.globalConfig
							.get("download.rotation")));
			houseKeeping.cleanFiles(Constants.TESTOUTPUT, Long
					.parseLong(DataSource.globalConfig.get("report.rotation")));
			if (Constants.DRIVERINIT) {
				Logs.LOGGER.info("Shutting down Selenium driver ...");
				WebDriver driver = Setup.getInstance().getDriver();
				Setup.getInstance().unRegisterEventListner(driver);
				driver.quit();
				com.znalytics.framework.utility.Process
						.killOrphanDrivers(driver);
			}
			Logs.LOGGER.info("Shutting down ZBra Logs service ...");
			for (Handler handler : Logs.LOGGER.getHandlers()) {
				handler.flush();
				handler.close();
			}
		}
	}

	/**
	 * Parses the clients and tests.
	 */
	private static void parseClientsAndTests() {
		if (DataSource.globalConfig.get("client").contains(",")) {
			String[] clients = DataSource.globalConfig.get("client").split(",");
			for (String client : clients) {
				Constants.CURRENT_CLIENT = client.trim().toLowerCase();
				DataSource.globalConfig.put("client_running",
						Constants.CURRENT_CLIENT);
				if (DataSource.globalConfig.get("test").contains(",")) {
					String[] tests = DataSource.globalConfig.get("test").split(
							",");
					for (String test : tests) {
						Constants.CURRENT_TESTING = test.trim().toLowerCase();
						TestModules testModule = new TestModules(
								Constants.CURRENT_TESTING);
						testModule.executeModules();
					}
				} else {
					Constants.CURRENT_TESTING = DataSource.globalConfig.get(
							"test").toLowerCase();
					TestModules testModule = new TestModules(
							Constants.CURRENT_TESTING);
					testModule.executeModules();
				}
			}
		} else {
			Constants.CURRENT_CLIENT = DataSource.globalConfig.get("client")
					.toLowerCase();
			DataSource.globalConfig.put("client_running",
					Constants.CURRENT_CLIENT);
			if (DataSource.globalConfig.get("test").contains(",")) {
				String[] tests = DataSource.globalConfig.get("test").split(",");
				for (String test : tests) {
					Constants.CURRENT_TESTING = test.trim().toLowerCase();
					TestModules testModule = new TestModules(
							Constants.CURRENT_TESTING);
					testModule.executeModules();
				}
			} else {
				Constants.CURRENT_TESTING = DataSource.globalConfig.get("test")
						.toLowerCase();
				TestModules testModule = new TestModules(
						Constants.CURRENT_TESTING);
				testModule.executeModules();
			}
		}
	}

	/**
	 * Check and create folder.
	 *
	 * @param folderName
	 *            Method which creates all the required folders for the
	 *            framework to work.
	 * @return true, if successful
	 */
	private static boolean checkAndCreateFolder(String folderName) {
		File folder = new File(folderName);
		try {
			if (folder.exists()) {
				return true;
			} else {
				if (folder.mkdirs()) {
					return true;
				} else {
					System.out.println("Problem in creating " + folderName
							+ "folder, please check the permission.");
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Problem in creating " + folderName + " : "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * Get the name for the logfile.
	 *
	 * @return the log file name
	 * @throws UnknownHostException
	 *             the unknown host exception
	 */
	private static String getLogFileName() throws UnknownHostException {
		String DATE_FORMAT_NOW = "yyyy-MM-dd";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DATE_FORMAT_NOW);
		return "Zbra_" + InetAddress.getLocalHost().getHostName() + "_"
				+ simpleDateFormat.format(cal.getTime()) + ".log";
	}
}
