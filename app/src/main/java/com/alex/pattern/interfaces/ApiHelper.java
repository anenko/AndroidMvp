package com.alex.pattern.interfaces;

import com.alex.pattern.data.models.response.SearchResponse;
import com.alex.pattern.data.models.UserModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alex
 */

public interface ApiHelper {

    Observable<List<UserModel>> getUsers(int since);

    Observable<SearchResponse> searchUsers(String name);

    Observable<UserModel> getUserInfo(String login);
}
