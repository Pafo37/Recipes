package com.example.pavelkovachev.recipes.network;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.BuildConfig;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RecipesApiCreator extends BaseService {

    private static RecipesApi recipesApi;
    private static final int DEFAULT_TIMEOUT = 60;
    private static final Object LOCK = new Object();

    public static RecipesApi getRecipesApi() {
        if (recipesApi == null) {
            synchronized (LOCK) {
                Retrofit retrofit = initRetrofit(BuildConfig.BASE_URL, createOkHttpClient());
                recipesApi = retrofit.create(RecipesApi.class);
            }
        }
        return recipesApi;
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(App.getInstance()))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}