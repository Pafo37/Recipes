package com.example.pavelkovachev.recipes.network.callback;

import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;

public interface RecipesListCallback {

    void onSuccessRecipesList(RecipesListResponse recipesListResponse);

    void onErrorRecipesList();
}