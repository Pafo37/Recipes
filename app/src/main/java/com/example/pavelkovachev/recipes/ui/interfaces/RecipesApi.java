package com.example.pavelkovachev.recipes.ui.interfaces;

import com.example.pavelkovachev.recipes.network.response.cuisine.CuisinesResponse;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipesResponse;
import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypesResponses;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipesResponse;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipesApi {

    @GET("api/json/v1/1/random.php")
    Call<RandomRecipesResponse> getRandomRecipeResponse();

    @GET("/api/json/v1/1/latest.php")
    Call<LatestRecipesResponse> getLatestRecipeResponse();

    @GET("/api/json/v1/1/filter.php?{categoryLetter}={category}")
    Call<RecipesListResponse> getRecipesListResponse(@Path("categoryLetter") String categoryUrlLetter,
                                                     @Path("category") String category);

    @GET("/api/json/v1/1/list.php?a=list")
    Call<CuisinesResponse> getCuisineResponse();

    @GET("/api/json/v1/1/categories.php")
    Call<MealTypesResponses> getMealTypesResponse();
}