package com.alex.pattern.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Alex
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
