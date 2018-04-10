package com.example.zhangyang.retrofitdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangyang on 2018/3/19.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface TestAnnotation {
    int id() default -1;
    String msg();
}
