package com.alex.pattern.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alex.pattern.R;
import com.alex.pattern.databinding.ActivityMainBinding;
import com.alex.pattern.base.BaseActivity;

import javax.inject.Inject;

import static com.alex.pattern.utils.AppConstants.IS_CONFIGURATIONS_CHANGED;

/**
 * Created by Alex
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter mPresenter;

    //region values
    private ActivityMainBinding mBinding;
    private boolean mIsConfigurationsChanged = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (savedInstanceState != null) {
            mIsConfigurationsChanged = savedInstanceState.getBoolean(IS_CONFIGURATIONS_CHANGED);
        }
        mPresenter.attachView(this);
        mPresenter.setSupportFragmentManager(getSupportFragmentManager());
        mPresenter.registerBus();
        if (!mIsConfigurationsChanged) {
            mPresenter.openHome();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.unregisterBus();
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(IS_CONFIGURATIONS_CHANGED, true);
        super.onSaveInstanceState(outState);
    }

}