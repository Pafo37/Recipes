package com.example.pavelkovachev.recipes.persistence.database.repository.base;

import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModel;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);
    void insertAllRecipes(RecipeModel recipeModels);
    void deleteRecipe(RecipeModel recipeModel);
}