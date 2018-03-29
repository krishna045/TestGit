// Copyright - Znalytics (http://www.Znalytics.com/)
package com.znalytics.framework.Listener;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

/**
 * The listener interface for receiving methodInterceptor events. The class that
 * is interested in processing a methodInterceptor event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's
 * <code>addMethodInterceptorListener<code> method. When
 * the methodInterceptor event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MethodInterceptorEvent
 */
public class MethodInterceptorListener implements IMethodInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IMethodInterceptor#intercept(java.util.List,
	 * org.testng.ITestContext)
	 */
	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods,
			ITestContext context) {
		Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {
			@Override
			public int compare(IMethodInstance m1, IMethodInstance m2) {
				return getPriority(m1) - getPriority(m2);
			}

			@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
			private int getPriority(IMethodInstance mi) {
				int result = 0;
				Method method = mi.getMethod().getMethod();
				Priority a1 = method.getAnnotation(Priority.class);
				if (a1 != null) {
					result = a1.value();
				} else {
					Class cls = method.getDeclaringClass();
					Priority classPriority = (Priority) cls
							.getAnnotation(Priority.class);
					if (classPriority != null) {
						result = classPriority.value();
					}
				}
				return result;
			}
		};
		IMethodInstance[] array = methods.toArray(new IMethodInstance[methods
				.size()]);
		Arrays.sort(array, comparator);
		return Arrays.asList(array);
	}

}
