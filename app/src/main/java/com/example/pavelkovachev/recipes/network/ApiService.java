package com.example.pavelkovachev.recipes.network;

import android.support.annotation.NonNull;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.BuildConfig;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
public class ApiService extends BaseService {

    private static final int DEFAULT_TIMEOUT = 60;

    private RecipesApi recipesApi;

    @Inject
    ApiService() {
    }

    @NonNull
    private OkHttpClient createOkHttp() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(App.getInstance()))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    RecipesApi getRecipesApi() {
        if (recipesApi == null) {
            Retrofit retrofit = initRetrofit(BuildConfig.BASE_URL, createOkHttp());
            recipesApi = retrofit.create(RecipesApi.class);
        }
        return recipesApi;
    }
}