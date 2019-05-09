package com.example.pavelkovachev.recipes.persistence.model.recipe;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

import io.reactivex.Single;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void insertRecipeList(List<RecipeModel> recipeModelList);

    void deleteRecipe(RecipeModel recipeModel);

    void getById(String recipeId, AsyncTaskResult result);

    Single<List<RecipeModel>> getAllRecipes();

}