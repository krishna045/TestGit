// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import cuanto.adapter.listener.testng.TestNgListener;
import cuanto.adapter.listener.testng.TestNgListenerArguments;
import cuanto.api.CuantoConnector;
import cuanto.api.TestRun;

/**
 * The Class Cuanto.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Cuanto {

	/** The test ng listener. */
	TestNgListener testNgListener = null;

	/** The test properties. */
	Map<String, String> testProperties = null;

	/** The cuanto url. */
	String cuantoURL = null;

	/** The key. */
	String key = null;

	/** The enable test submission. */
	boolean enableTestSubmission = false;

	/**
	 * Instantiates a new cuanto.
	 */
	public Cuanto() {
		this.testProperties = new HashMap<String, String>();
		this.cuantoURL = DataSource.globalConfig.get("cuanto.url");
		this.key = DataSource.globalConfig.get("cuanto.key");
		this.enableTestSubmission = DataSource.globalConfig
				.get("cuanto.enable").equalsIgnoreCase("yes") ? true : false;

	}

	/**
	 * Gets the cuanto listener.
	 *
	 * @return the cuanto listener
	 */
	public TestNgListener getCuantoListener() {
		try {
			if (this.enableTestSubmission) {
				Logs.LOGGER.info("Initializing Cuanto ...");
				this.testNgListener = new TestNgListener();
				TestNgListenerArguments arguments = new TestNgListenerArguments();
				setCuantoReporting();
				Logs.LOGGER.info("Using TestID: " + Constants.CUANTOTESTID);
				arguments.setCuantoUrl(new URI(this.cuantoURL));
				arguments.setProjectKey(this.key);
				arguments.setTestRunId(Constants.CUANTOTESTID);
				arguments.setIncludeConfigDuration(true);
				arguments.setTestProperties(setPropertiese());
				TestNgListener.setTestNgListenerArguments(arguments);
				return this.testNgListener;
			} else {
				Logs.LOGGER.info("Ignoring integration to Cuanto ...");
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "", e);
		}
		return null;
	}

	/**
	 * Gets the list data.
	 *
	 * @param list
	 *            the list
	 * @return the list data
	 */
	private String getListData(List<String> list) {
		String element = "";
		int x = 0;
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (x == 0) {
				element = iterator.next();
			} else {
				element = element + "," + iterator.next();
			}
			x++;
		}
		return element;
	}

	/**
	 * Sets the cuanto reporting.
	 */
	private void setCuantoReporting() {
		if (Constants.CUANTOTESTID == -1) {
			TestRun testRun = new TestRun(new Date());
			CuantoConnector cuanto = CuantoConnector.newInstance(
					this.cuantoURL, this.key);
			cuanto.addTestRun(testRun);
			Constants.CUANTOTESTID = testRun.getId();
		}

		if (Constants.CUANTORESULTURL == null) {
			Constants.CUANTORESULTURL = this.cuantoURL + "/testRun/results/"
					+ Constants.CUANTOTESTID;
			Logs.LOGGER.info("Submitting the test results to: "
					+ Constants.CUANTORESULTURL);
		}

		if (Constants.CUANTOTESTTYPES == null)
			Constants.CUANTOTESTTYPES = new ArrayList<String>();

		if (!Constants.CUANTOTESTTYPES.contains(Constants.CURRENT_TESTING))
			Constants.CUANTOTESTTYPES.add(Constants.CURRENT_TESTING);

	}

	/**
	 * Sets the propertiese.
	 *
	 * @return the map
	 * @throws UnknownHostException
	 *             the unknown host exception
	 */
	private Map<String, String> setPropertiese() throws UnknownHostException {
		this.testProperties.put("Environment",
				DataSource.globalConfig.get("env"));
		this.testProperties.put("TestTypes",
				getListData(Constants.CUANTOTESTTYPES));
		this.testProperties.put("Hostname", InetAddress.getLocalHost()
				.getHostName());
		this.testProperties.put("UserName", System.getProperty("user.name"));
		return this.testProperties;
	}

}
