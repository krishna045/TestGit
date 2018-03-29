// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.Listener;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.znalytics.framework.core.Logs;

/**
 * The Class Retry.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class Retry implements IRetryAnalyzer {

	/** The max retry count. */
	private static int MAX_RETRY_COUNT = 2;

	/** The count. */
	AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

	/**
	 * Checks if is retry available.
	 *
	 * @return true, if is retry available
	 */
	public boolean isRetryAvailable() {
		return (count.intValue() > 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */
	@Override
	public boolean retry(ITestResult result) {
		boolean retry = false;
		if (isRetryAvailable()) {
			Logs.LOGGER.info("Going to retry test case: " + result.getMethod()
					+ ", " + (MAX_RETRY_COUNT - count.intValue() + 1)
					+ " out of " + MAX_RETRY_COUNT);
			retry = true;
			count.decrementAndGet();
		}
		return retry;
	}
}
