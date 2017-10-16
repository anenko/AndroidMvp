package com.alex.pattern.data.managers;

import com.alex.pattern.network.ApiService;
import com.alex.pattern.data.models.response.SearchResponse;
import com.alex.pattern.data.models.UserModel;
import com.alex.pattern.interfaces.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alex
 */

public class ApiManager implements ApiHelper {

    private ApiService mApiService;

    @Inject
    public ApiManager(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<List<UserModel>> getUsers(int since) {
        return mApiService.getUsers(since)
                .compose(applySchedulers());
    }

    public Observable<SearchResponse> searchUsers(String name) {
        return mApiService.searchUsers(name)
                .compose(applySchedulers());
    }

    public Observable<UserModel> getUserInfo(String login) {
        return mApiService.getUserInfo(login)
                .compose(applySchedulers());
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
