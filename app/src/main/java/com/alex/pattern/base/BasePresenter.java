package com.alex.pattern.base;

/**
 * Created by Alex
 */

public abstract class BasePresenter<V> implements BaseMvpPresenter<V> {

    private V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    protected V getView() {
        return mView;
    }

    protected boolean isViewAttach() {
        return mView != null;
    }

}