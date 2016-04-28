package com.gzfgeh.CustomRxBus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * accept注解 用于标明那些方法是rxBus的
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Accept {
    /**
     * 默认运行在主线程
     */
    AcceptScheduler acceptScheduler() default AcceptScheduler.MAIN_THREAD;

    AcceptType[] value()default {};

}
