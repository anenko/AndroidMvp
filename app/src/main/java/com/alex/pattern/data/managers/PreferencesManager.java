package com.alex.pattern.data.managers;

import android.content.Context;

import com.alex.pattern.base.BasePreferences;
import com.alex.pattern.interfaces.PreferencesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Alex
 */

@Singleton
public class PreferencesManager extends BasePreferences implements PreferencesHelper {

    private static final String WORD_LAST_ID = "word_last_id";

    @Inject
    public PreferencesManager(Context context) {
        super(context);
    }

    @Override
    public int getWordLastId() {
        return getPreference(WORD_LAST_ID, 0);
    }

    @Override
    public void setWordLastId(int lastId) {
        savePreference(WORD_LAST_ID, lastId);
    }

}
