package com.example.pavelkovachev.recipes.persistence.model.recipe;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void insertRecipeList(List<RecipeModel> recipeModelList);

    void deleteRecipe(RecipeModel recipeModel);

    void getById(String recipeId, AsyncTaskResult result);

    void getAllRecipes(AsyncTaskResult result);
}