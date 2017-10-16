package com.alex.pattern.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alex
 */

public abstract class BasePreferences {

    private static final String PREF_NAME = "patter.mvp";

    private SharedPreferences mPrefs;

    protected BasePreferences(Context context) {
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    protected void savePreference(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    protected int getPreference(String key, int defaultValue) {
        return mPrefs.getInt(key, defaultValue);
    }

    protected void savePreference(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    protected boolean getPreference(String key, boolean defaultValue) {
        return mPrefs.getBoolean(key, defaultValue);
    }

    protected void savePreference(String key, float value) {
        mPrefs.edit().putFloat(key, value).apply();
    }

    protected float getPreference(String key, float defaultValue) {
        return mPrefs.getFloat(key, defaultValue);
    }

    protected void savePreference(String key, long value) {
        mPrefs.edit().putLong(key, value).apply();
    }

    protected long getPreference(String key, long defaultValue) {
        return mPrefs.getLong(key, defaultValue);
    }

    protected void savePreference(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    protected String getPreference(String key, String defaultValue) {
        return mPrefs.getString(key, defaultValue);
    }

}
