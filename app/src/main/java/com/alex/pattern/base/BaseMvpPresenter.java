package com.alex.pattern.base;

/**
 * Created by Alex
 */

public interface BaseMvpPresenter<V> {

    void attachView(V view);

    void detachView();

}