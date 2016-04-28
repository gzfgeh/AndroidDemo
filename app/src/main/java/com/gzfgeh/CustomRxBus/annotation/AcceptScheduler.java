package com.gzfgeh.CustomRxBus.annotation;

/**
 * 枚举RxJava的线程类型
 */
public enum AcceptScheduler {

    MAIN_THREAD,
    NEW_THREAD,
    IO,
    COMPUTATION,
    TRAMPOLINE,
    IMMEDIATE,
    EXECUTOR,
    HANDLER

}
