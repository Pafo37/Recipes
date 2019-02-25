package com.example.pavelkovachev.recipes;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

import java.util.List;


public interface DownloadCallback {

    void showRandomMealResult(RecipeModel result);

    NetworkInfo getActiveNetworkInfo();

    void finishDownloading(RecipeModel recipeModel);

    void showLatestMealResult(RecipeModel recipeModel);

    void showCuisineResult(List<CuisineModel> cuisineModel);

    void showMealTypeResult(List<MealTypeModel> mealTypeModel);

}