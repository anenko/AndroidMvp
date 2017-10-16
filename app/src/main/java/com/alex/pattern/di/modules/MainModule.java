package com.alex.pattern.di.modules;

import com.alex.pattern.di.PerActivity;
import com.alex.pattern.di.PerFragment;
import com.alex.pattern.ui.home.HomeContract;
import com.alex.pattern.ui.home.HomeFragment;
import com.alex.pattern.ui.home.HomePresenter;
import com.alex.pattern.ui.main.MainContract;
import com.alex.pattern.ui.main.MainPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alex
 */

@Module
public abstract class MainModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    //region presenters
    @Provides
    @PerActivity
    static MainContract.Presenter mainPresenter(MainPresenter presenter) {
        return presenter;
    }

    @Binds
    @PerFragment
    abstract HomeContract.Presenter homePresenter(HomePresenter presenter);
    //endregion

}
