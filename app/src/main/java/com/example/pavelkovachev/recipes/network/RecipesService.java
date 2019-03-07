package com.example.pavelkovachev.recipes.network;

import com.example.pavelkovachev.recipes.network.callback.CuisineCallback;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.MealTypeCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RecipesListCallback;
import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineListResponse;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeListResponses;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesService {

    private RecipesApiCreator recipesApiCreator;
    private RandomMealCallback apiResultCallback;
    private LatestMealCallback latestMealCallback;
    private MealTypeCallback mealTypeCallback;
    private CuisineCallback cuisineCallback;
    private RecipesListCallback recipesListCallback;

    public RecipesService(RecipesApiCreator recipesApiCreator, RandomMealCallback apiResultCallback, LatestMealCallback latestMealCallback) {
        this.recipesApiCreator = new RecipesApiCreator();
        this.apiResultCallback = apiResultCallback;
        this.latestMealCallback = latestMealCallback;
    }

    public RecipesService(RecipesApiCreator recipesApiCreator, MealTypeCallback mealTypeCallback) {
        this.recipesApiCreator = new RecipesApiCreator();
        this.mealTypeCallback = mealTypeCallback;
    }

    public RecipesService(RecipesApiCreator recipesApiCreator, CuisineCallback cuisineCallback) {
        this.recipesApiCreator = new RecipesApiCreator();
        this.cuisineCallback = cuisineCallback;
    }

    public RecipesService(RecipesApiCreator recipesApiCreator, RecipesListCallback recipesListCallback) {
        this.recipesApiCreator = new RecipesApiCreator();
        this.recipesListCallback = recipesListCallback;
    }

    public void getRandomRecipe() {
        Call<RandomRecipeListResponse> recipesResponseCall = recipesApiCreator.getRecipesApi().getRandomRecipeResponse();
        recipesResponseCall.enqueue(new Callback<RandomRecipeListResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeListResponse> call, Response<RandomRecipeListResponse> response) {
                RandomRecipeListResponse randomRecipesResponse = response.body();
                apiResultCallback.onSuccessRandomRecipe(randomRecipesResponse);
            }

            @Override
            public void onFailure(Call<RandomRecipeListResponse> call, Throwable t) {

            }
        });
    }

    public void getRecipeById(String mealId) {
        Call<RandomRecipeListResponse> recipesResponseCall = recipesApiCreator.getRecipesApi().getRecipeByIdResponse(mealId);
        recipesResponseCall.enqueue(new Callback<RandomRecipeListResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeListResponse> call, Response<RandomRecipeListResponse> response) {
                RandomRecipeListResponse randomRecipesResponse = response.body();
                apiResultCallback.onSuccessRandomRecipe(randomRecipesResponse);
            }

            @Override
            public void onFailure(Call<RandomRecipeListResponse> call, Throwable t) {

            }
        });
    }

    public void getLatestRecipe() {
        Call<LatestRecipeListResponse> recipesResponseCall = recipesApiCreator.getRecipesApi().getLatestRecipeResponse();
        recipesResponseCall.enqueue(new Callback<LatestRecipeListResponse>() {
            @Override
            public void onResponse(Call<LatestRecipeListResponse> call, Response<LatestRecipeListResponse> response) {
                LatestRecipeListResponse latestRecipesResponse = response.body();
                latestMealCallback.onSuccessLatestRecipe(latestRecipesResponse);
            }

            @Override
            public void onFailure(Call<LatestRecipeListResponse> call, Throwable t) {

            }
        });
    }

    public void getMealTypes() {
        Call<MealTypeListResponses> mealTypesResponsesCall = recipesApiCreator.getRecipesApi().getMealTypesResponse();
        mealTypesResponsesCall.enqueue(new Callback<MealTypeListResponses>() {
            @Override
            public void onResponse(Call<MealTypeListResponses> call, Response<MealTypeListResponses> response) {
                MealTypeListResponses mealTypesResponses = response.body();
                mealTypeCallback.onSuccessMealTypes(mealTypesResponses);
            }

            @Override
            public void onFailure(Call<MealTypeListResponses> call, Throwable t) {

            }
        });
    }

    public void getCuisine() {
        Call<CuisineListResponse> cuisineListResponseCall = recipesApiCreator.getRecipesApi().getCuisineResponse();
        cuisineListResponseCall.enqueue(new Callback<CuisineListResponse>() {
            @Override
            public void onResponse(Call<CuisineListResponse> call, Response<CuisineListResponse> response) {
                CuisineListResponse cuisineListResponse = response.body();
                cuisineCallback.onSuccessCuisine(cuisineListResponse);
            }

            @Override
            public void onFailure(Call<CuisineListResponse> call, Throwable t) {

            }
        });
    }

    public void getRecipesList(String urlLetter,String category) {
        Map<String,String> queryData=new HashMap<>();
        queryData.put(urlLetter,category);

        Call<RecipesListResponse> recipesListResponseCall= recipesApiCreator.getRecipesApi().getRecipesListResponse(queryData);
        recipesListResponseCall.enqueue(new Callback<RecipesListResponse>() {
            @Override
            public void onResponse(Call<RecipesListResponse> call, Response<RecipesListResponse> response) {
                RecipesListResponse recipesListResponse=response.body();
                recipesListCallback.onSuccessRecipesList(recipesListResponse);
            }

            @Override
            public void onFailure(Call<RecipesListResponse> call, Throwable t) {

            }
        });

    }
}