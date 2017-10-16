package com.alex.pattern.di.modules;

import com.alex.pattern.di.PerActivity;
import com.alex.pattern.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alex
 */

@Module
public abstract class ActivityBindingsModule {

    @PerActivity
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivityInjector();

}
