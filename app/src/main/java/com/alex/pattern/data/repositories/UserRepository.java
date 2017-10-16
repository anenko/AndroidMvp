package com.alex.pattern.data.repositories;

import android.databinding.ObservableList;

import com.alex.pattern.data.mappers.UserMapper;
import com.alex.pattern.data.models.UserModel;
import com.alex.pattern.data.models.response.SearchResponse;
import com.alex.pattern.interfaces.ApiHelper;
import com.alex.pattern.interfaces.DatabaseHelper;
import com.alex.pattern.interfaces.IRepository;
import com.alex.pattern.interfaces.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex
 */

@Singleton
public class UserRepository implements IRepository<UserModel> {

    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private UserMapper mMapper = new UserMapper();

    @Inject
    public UserRepository(DatabaseHelper databaseHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mDatabaseHelper = databaseHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void add(UserModel item) {
//        mDatabaseHelper.addWord(mWordMapper.toRealm(completeWord(wordModel)));
    }

    @Override
    public void update(UserModel item) {
//        mDatabaseHelper.addWord(mWordMapper.toRealm(item));
    }

    @Override
    public Single<List<UserModel>> getAll(int take, int skip) {
//        return mDatabaseHelper.getAllWords()
//                .flatMap(Observable::fromIterable)
//                .skip(skip)
//                .take(take)
//                .map(wordRealmModel -> mWordMapper.fromRealm(wordRealmModel))
//                .toList();
        return null;
    }

    @Override
    public void delete(int id) {
//        mDatabaseHelper.deleteWord(id);
    }

    public Observable<List<UserModel>> getUsers(int since) {
        return mApiHelper.getUsers(since);
    }

    public Observable<SearchResponse> searchUsers(String login) {
        return mApiHelper.searchUsers(login);
    }
}
