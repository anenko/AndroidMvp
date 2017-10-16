package com.alex.pattern.data.managers;

import com.alex.pattern.data.models.db.WordRealmModel;
import com.alex.pattern.interfaces.DatabaseHelper;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.alex.pattern.data.models.db.WordRealmModel.ID;

/**
 * Created by Alex
 */

public class DatabaseManager implements DatabaseHelper {

    private Realm mRealm;

    @Inject
    public DatabaseManager(Realm realm) {
        mRealm = realm;
    }

    //region Words
    @Override
    public void addWord(WordRealmModel wordRealmModel) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(wordRealmModel);
        mRealm.commitTransaction();
    }

    @Override
    public void addWordInAnotherThread(WordRealmModel wordRealmModel) {
        Realm realm = Realm.getInstance(mRealm.getConfiguration());
        realm.executeTransaction(transactionRealm -> {
            transactionRealm.copyToRealmOrUpdate(wordRealmModel);
        });
        realm.close();
    }

    @Override
    public void deleteWord(int id) {
        mRealm.beginTransaction();
        WordRealmModel realmModel = getWordById(id);
        realmModel.deleteFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public Observable<RealmResults<WordRealmModel>> getAllWords() {
        return Observable.just(mRealm.where(WordRealmModel.class).findAll()) // if use Realm asObservable(), it will take more time to load data
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<RealmResults<WordRealmModel>> getWordsForMemory(String dateString, String typeBox, int box) {
        return Observable.just(     // if use Realm asObservable(), it will take more time to load data
                mRealm.where(WordRealmModel.class)
                        .lessThan(dateString, Calendar.getInstance().getTimeInMillis())
                        .equalTo(typeBox, box)
                        .findAllSorted(typeBox)
        )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private WordRealmModel getWordById(int id) {
        return mRealm.where(WordRealmModel.class).equalTo(ID, id).findFirst();
    }
    //endregion

}
