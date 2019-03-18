package com.example.pavelkovachev.recipes.persistence.model.recipe;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void deleteRecipe(RecipeModel recipeModel);

    void getById(String recipeId, AsyncTaskResult result);

    void getAllRecipes(AsyncTaskResult result);
}