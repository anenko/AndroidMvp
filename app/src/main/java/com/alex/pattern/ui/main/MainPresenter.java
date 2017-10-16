package com.alex.pattern.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.alex.pattern.R;
import com.alex.pattern.events.TransitionEvent;
import com.alex.pattern.base.BasePresenter;
import com.alex.pattern.ui.home.HomeFragment;
import com.alex.pattern.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


/**
 * Created by Alex
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    //region values
    private FragmentManager mFragmentManager;
    private EventBus mBus;
    private String mCurrentFragment;
    //endregion

    @Inject
    public MainPresenter(EventBus bus) {
        mBus = bus;
    }

    //region methods
    @Override
    public void registerBus() {
        mBus.register(this);
    }

    @Override
    public void unregisterBus() {
        mBus.unregister(this);
    }

    @Override
    public void setSupportFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void openHome() {
        switchFragment(new HomeFragment(), false, false);
    }

    private void switchFragment(Fragment fragment, boolean addToBackStack, boolean addInsteadOfReplace) {
        mCurrentFragment = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(mCurrentFragment);
        }
        if (addInsteadOfReplace) {
            fragmentTransaction.add(R.id.main_container, fragment, mCurrentFragment);
        } else {
            fragmentTransaction.replace(R.id.main_container, fragment, mCurrentFragment);
        }
        getView().hideKeyboard();
        fragmentTransaction.commit();
    }

    private void clearBackStack() {
        FragmentManager manager = mFragmentManager;
        FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
        manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    //endregion

    //region events
    @Subscribe
    public void onTransition(TransitionEvent event) {
        try {
            Fragment fragment = new Fragment();
            boolean addToBackStack = true;
            switch (event.getIdFragment()) {
                case AppConstants.HOME_FRAGMENT:
                    clearBackStack();
                    addToBackStack = false;
                    fragment = new HomeFragment();
                    break;
            }
            switchFragment(fragment, addToBackStack, false);
        } catch (Exception e) {
                Log.e(TAG, "onTransition " + e);
        }
    }
    //endregion

}
