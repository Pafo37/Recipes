package com.example.pavelkovachev.recipes.persistence.recipe;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void deleteRecipe(RecipeModel recipeModel);

    RecipeModel getByName(String recipeName, AsyncTaskResult result);

    List<RecipeModel> getAllRecipes(AsyncTaskResult result);
}