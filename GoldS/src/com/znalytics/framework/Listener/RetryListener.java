// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.Listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * The listener interface for receiving retry events. The class that is
 * interested in processing a retry event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addRetryListener<code> method. When
 * the retry event occurs, that object's appropriate
 * method is invoked.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class RetryListener implements IAnnotationTransformer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IAnnotationTransformer#transform(org.testng.annotations.
	 * ITestAnnotation, java.lang.Class, java.lang.reflect.Constructor,
	 * java.lang.reflect.Method)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(Retry.class);
		}
	}
}
