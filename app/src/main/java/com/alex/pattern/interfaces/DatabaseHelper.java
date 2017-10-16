package com.alex.pattern.interfaces;

import com.alex.pattern.data.models.db.WordRealmModel;

import io.realm.RealmResults;
import io.reactivex.Observable;

/**
 * Created by Alex
 */

public interface DatabaseHelper {

    //region Words
    void addWord(WordRealmModel wordRealmModel);

    void addWordInAnotherThread(WordRealmModel wordRealmModel);

    void deleteWord(int id);

    Observable<RealmResults<WordRealmModel>> getAllWords();

    Observable<RealmResults<WordRealmModel>> getWordsForMemory(String dateString, String typeBox, int box);
    //endregion

}
