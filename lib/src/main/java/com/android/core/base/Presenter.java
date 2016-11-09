package com.android.core.base;
/**
 * Created by bug on 2016/04/10.
 */
public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
