package csc312;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

/*
 *  See comment in Week6Assignment on how to complete it
 */

@Inherited
@Retention(RUNTIME)
public @interface AuthorshipInformation {
	String author();
	String course();
}

