package com.my.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import groovy.lang.DelegatesTo;

/**
 * Created by  on 2021/9/9.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotation {
}
