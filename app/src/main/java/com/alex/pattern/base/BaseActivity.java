package com.alex.pattern.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alex.pattern.R;
import com.alex.pattern.utils.CommonUtils;
import com.alex.pattern.utils.NetworkUtils;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Alex
 */

public abstract class BaseActivity extends DaggerAppCompatActivity
        implements BaseMvpView {

    @Override
    public void setToolbar(Toolbar toolbar, boolean isShowBackButton) {
        if (toolbar != null) {
            toolbar.setTitleTextColor(CommonUtils.getColor(this, R.color.white));
            toolbar.setSubtitle("");
            setSupportActionBar(toolbar);
            if (isShowBackButton) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(CommonUtils.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
                toolbar.setNavigationOnClickListener(v -> {
                    hideKeyboard();
                    onBackPressed();
                });
            }
        }
    }

    @Override
    public void onError(String message) {
//        if (message != null) {
//            showSnackBar(message);
//        } else {
//            showSnackBar(getString(R.string.some_error));
//        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(@StringRes int resId) {
        Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(Activity activity, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, requestCode);
        }
    }

}