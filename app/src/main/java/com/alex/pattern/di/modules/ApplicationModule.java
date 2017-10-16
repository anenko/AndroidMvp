package com.alex.pattern.di.modules;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alex.pattern.interfaces.DatabaseHelper;
import com.alex.pattern.data.managers.DatabaseManager;
import com.alex.pattern.interfaces.ApiHelper;
import com.alex.pattern.data.managers.ApiManager;
import com.alex.pattern.network.ApiService;
import com.alex.pattern.interfaces.PreferencesHelper;
import com.alex.pattern.data.managers.PreferencesManager;
import com.alex.pattern.network.exceptions.RxErrorHandlingCallAdapterFactory;
import com.alex.pattern.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex
 */

@Module
public abstract class ApplicationModule {

    //region Binds
    @Binds
    abstract Context bindContext(Application application);

    @Binds
    @Singleton
    abstract DatabaseHelper provideDbHelper(DatabaseManager databaseManager);

    @Binds
    @Singleton
    abstract PreferencesHelper providePreferencesHelper(PreferencesManager preferencesManager);

    @Binds
    @Singleton
    abstract ApiHelper provideApiHelper(ApiManager apiManager);
    //endregion

    //region Provides
    @Provides
    @Singleton
    static EventBus provideBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    static ApiService provideApiService(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    Response response = chain.proceed(builder.build());
                    int tryCount = 0;
                    while (!response.isSuccessful() && tryCount < NetworkUtils.TRY_COUNT) {
                        Log.d("intercept", "Request is not successful - " + tryCount);
                        tryCount++;
                        response = chain.proceed(builder.build());
                    }
                    return response;
                })
                .cache(new Cache(new File(context.getCacheDir(), "http"), NetworkUtils.DISK_CACHE_SIZE))
                .connectTimeout(NetworkUtils.TIME_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(NetworkUtils.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(NetworkUtils.SERVER_URL)
                .build();
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    static Realm provideRealmConfiguration(Application application) {
        Realm.init(application);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }
    //endregion
}
