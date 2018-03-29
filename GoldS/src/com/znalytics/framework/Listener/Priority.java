// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.Listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface Priority.
 *
 * @author Nikesh Jauhari
 * @Email: njauhari@znalytics.com
 * @Date: 02/20/2015
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
public @interface Priority {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value() default 0;
}
