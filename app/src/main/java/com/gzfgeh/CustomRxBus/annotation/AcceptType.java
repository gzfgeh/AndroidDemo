package com.gzfgeh.CustomRxBus.annotation;

/**
 * RxBus所需参数
 */

public @interface AcceptType {
    /*
        标明调用者是谁
     */
    String tag() default "";

    /*
        标明传递的参数是什么类型
     */
    Class clazz() default Object.class;


}
