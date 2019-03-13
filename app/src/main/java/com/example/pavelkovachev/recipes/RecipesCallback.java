package com.example.pavelkovachev.recipes;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

public interface RecipesCallback {

    void showRandomMealResult(RecipeModel result);

    NetworkInfo getActiveNetworkInfo();

    void showLatestMealResult(RecipeModel recipeModel);

}