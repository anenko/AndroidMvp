package com.alex.pattern.network;

import com.alex.pattern.data.models.response.SearchResponse;
import com.alex.pattern.data.models.UserModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alex
 */

public interface ApiService {

    @GET("/users")
    Observable<List<UserModel>> getUsers(@Query("since") int since);

    @GET("/search/users")
    Observable<SearchResponse> searchUsers(@Query("q") String name);

    @GET("/users/{user}")
    Observable<UserModel> getUserInfo(@Path("user") String login);

}