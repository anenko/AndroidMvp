package com.alex.pattern.di.components;

import android.app.Application;

import com.alex.pattern.PatternApplication;
import com.alex.pattern.di.modules.ActivityBindingsModule;
import com.alex.pattern.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by Alex
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingsModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(PatternApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }

}
