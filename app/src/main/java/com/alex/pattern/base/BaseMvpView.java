package com.alex.pattern.base;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

/**
 * Created by Alex
 */

public interface BaseMvpView {

    void setToolbar(Toolbar toolbar, boolean isShowBackButton);

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void showToast(String text);

    void showToast(@StringRes int resId);

}
