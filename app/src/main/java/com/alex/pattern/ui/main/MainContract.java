package com.alex.pattern.ui.main;

import android.support.v4.app.FragmentManager;

import com.alex.pattern.base.BaseMvpPresenter;
import com.alex.pattern.base.BaseMvpView;

/**
 * Created by Alex
 */

public interface MainContract {

    interface Presenter extends BaseMvpPresenter<View> {

        void registerBus();

        void unregisterBus();

        void setSupportFragmentManager(FragmentManager supportFragmentManager);

        void openHome();
    }

    interface View extends BaseMvpView {

    }

}
