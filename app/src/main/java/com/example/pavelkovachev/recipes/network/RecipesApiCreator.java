package com.example.pavelkovachev.recipes.network;

import retrofit2.Retrofit;

public class RecipesApiCreator extends BaseService {

    private String baseUrl = "https://www.themealdb.com/";
    private RecipesApi recipesApi;

    public RecipesApi getRecipesApi() {
        if (recipesApi == null) {
            Retrofit retrofit = initRetrofit(baseUrl);
            recipesApi = retrofit.create(RecipesApi.class);
        }
        return recipesApi;
    }
}