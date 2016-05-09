package com.gzfgeh.CustomDagger;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * Description: @ActivityScope 是一个自定义的范围注解，
 *              作用是允许对象被记录在正确的组件中，
 *              当然这些对象的生命周期应该遵循 Activity 的生命周期。
 * Created by guzhenfu on 16/5/9.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {

}
