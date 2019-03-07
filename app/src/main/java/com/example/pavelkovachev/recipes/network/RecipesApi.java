package com.example.pavelkovachev.recipes.network;

import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineListResponse;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeListResponses;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RecipesApi {

    @GET("api/json/v1/1/random.php")
    Call<RandomRecipeListResponse> getRandomRecipeResponse();

    @GET("/api/json/v1/1/latest.php")
    Call<LatestRecipeListResponse> getLatestRecipeResponse();

    @GET("api/json/v1/1/lookup.php")
    Call<RandomRecipeListResponse> getRecipeByIdResponse(@Query("i") String mealId);

    @GET("/api/json/v1/1/filter.php")
    Call<RecipesListResponse> getRecipesListResponse(@QueryMap Map<String,String> queryMap);

    @GET("/api/json/v1/1/list.php?a=list")
    Call<CuisineListResponse> getCuisineResponse();

    @GET("/api/json/v1/1/categories.php")
    Call<MealTypeListResponses> getMealTypesResponse();
}