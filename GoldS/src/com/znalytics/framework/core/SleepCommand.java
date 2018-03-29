// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.util.concurrent.TimeUnit;

/**
 * The Class SleepCommand.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class SleepCommand {

	/**
	 * Sleep five minute.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepFiveMinute() throws InterruptedException {
		TimeUnit.MINUTES.sleep(3);
	}

	/**
	 * Sleep five second.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepFiveSecond() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
	}

	/**
	 * Sleep one minute.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepOneMinute() throws InterruptedException {
		TimeUnit.MINUTES.sleep(1);
	}

	/**
	 * Sleep one second.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepOneSecond() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

	/**
	 * Sleep two minute.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepTwoMinute() throws InterruptedException {
		TimeUnit.MINUTES.sleep(2);
	}

	/**
	 * Sleep two second.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void sleepTwoSecond() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
	}

}
