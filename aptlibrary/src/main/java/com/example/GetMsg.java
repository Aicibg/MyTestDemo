package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DongHao on 2016/9/27.
 * Description:
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface GetMsg {
    int id() default -1;
    String name() default "name";
}
