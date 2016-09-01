package com.gzfgeh.demo.Present;

public interface Presenter<T> {
    void attachView(T t);

    void detachView();
}