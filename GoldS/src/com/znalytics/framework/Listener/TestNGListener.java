// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.Listener;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.IResultListener;

import com.znalytics.framework.core.Constants;
import com.znalytics.framework.core.Logs;
import com.znalytics.framework.core.ScreenShot;

/**
 * The listener interface for receiving testNG events. The class that is
 * interested in processing a testNG event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addTestNGListener<code> method. When
 * the testNG event occurs, that object's appropriate
 * method is invoked.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 * 
 *        This class listens for TestNG events, and logs them using the standard
 *        java logging facility.
 * 
 *        In order to use this listener, the class name must be provided to
 *        testNG using the -listener option (or specified as the attribute
 *        'listener' in an ant call to testng).
 */
public class TestNGListener implements IResultListener, ITestListener {

	/**
	 * Gets the parameters.
	 *
	 * @param result
	 *            the result
	 * @return the parameters
	 */
	public String getParameters(ITestResult result) {
		String params = "";
		Object[] parameters = result.getParameters();
		if (parameters != null && parameters.length > 0)
			params = "(" + Arrays.deepToString(parameters) + ")";
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IConfigurationListener#onConfigurationFailure(org.testng.
	 * ITestResult)
	 */
	@Override
	public void onConfigurationFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Logs.LOGGER.log(Level.SEVERE,
				"CONFIGURATION FAILED --> " + result.getName(),
				result.getThrowable());
		Logs.LOGGER.info("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.testng.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult
	 * )
	 */
	@Override
	public void onConfigurationSkip(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		if (result.getThrowable() != null) {
			LogRecord r = new LogRecord(Level.INFO,
					"SKIPPED CONFIGURATION --> " + result.getName() + ": "
							+ result.getThrowable().getMessage());

			Logs.LOGGER.log(r);
		} else {
			Logs.LOGGER.log(Level.INFO,
					"Skipping configuration " + result.getName()
							+ ":  Unsatisfied dependency");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IConfigurationListener#onConfigurationSuccess(org.testng.
	 * ITestResult)
	 */
	@Override
	public void onConfigurationSuccess(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Logs.LOGGER.log(Level.FINE, "CONFIGURATION COMPLETED --> "
				+ result.getTestClass().getName() + "." + result.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		// Adjust the report to show only the failed tests instead of showing
		// the multiple failed try.
		Iterator<ITestResult> listOfFailedTests = context.getFailedTests()
				.getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}
		}
		Logs.LOGGER.info("TOTAL PASSED TEST CASES: "
				+ context.getPassedTests().size());
		Logs.LOGGER.info("TOTAL FAILED TEST CASES: "
				+ context.getFailedTests().size());
		if (context.getFailedTests().size() > 0) {
			Constants.TESTRESULTS.add("f");
		} else {
			Constants.TESTRESULTS.add("p");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng
	 * .ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Logs.LOGGER.log(
				Level.SEVERE,
				"Test Failed (but within success percentage): "
						+ result.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		ScreenShot.capture(false, result.getMethod().getMethodName());
		Reporter.setCurrentTestResult(result);
		Throwable err = result.getThrowable();
		LogRecord logRecord = new LogRecord(Level.SEVERE, "TEST FAILED --> : "
				+ result.getName());
		logRecord.setThrown(err);
		Logs.LOGGER.log(logRecord);
		Logs.LOGGER.info("===============================================");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		if (result.getThrowable() != null) {
			LogRecord r = new LogRecord(Level.INFO, "TEST SKIPPED --> "
					+ result.getName() + ": "
					+ result.getThrowable().getMessage());

			Logs.LOGGER.log(r);
		} else {
			Logs.LOGGER.log(Level.INFO, "Skipping Test " + result.getName()
					+ ":  Unsatisfied dependency");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		LogRecord r = new LogRecord(Level.INFO, String.format(
				"STARTING TESTS --> : %s%s", result.getName(),
				getParameters(result)));
		Logs.LOGGER.log(r);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			Logs.LOGGER.log(Level.INFO,
					"Expected exception of " + throwable.getClass().getName()
							+ " '" + throwable.getMessage()
							+ "' was in fact thrown.");
		}
		Logs.LOGGER.log(Level.INFO, String.format("TEST PASSED --> : %s%s",
				result.getName(), getParameters(result)));
		Logs.LOGGER.info("===============================================");
	}

}
