package com.example.pavelkovachev.recipes.persistence.model.recipe;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void deleteRecipe(RecipeModel recipeModel);

    RecipeModel getById(String recipeName, AsyncTaskResult result);

    List<RecipeModel> getAllRecipes(AsyncTaskResult result);
}