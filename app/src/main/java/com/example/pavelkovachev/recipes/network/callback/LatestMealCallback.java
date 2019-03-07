package com.example.pavelkovachev.recipes.network.callback;

import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;

public interface LatestMealCallback {

    void onSuccessLatestRecipe(LatestRecipeListResponse recipesResponse);

}
