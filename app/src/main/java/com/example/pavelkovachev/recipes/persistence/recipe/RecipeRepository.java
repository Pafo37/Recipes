package com.example.pavelkovachev.recipes.persistence.recipe;

import java.util.List;

public interface RecipeRepository {

    void insertRecipe(RecipeModel recipeModel);

    void deleteRecipe(RecipeModel recipeModel);

    RecipeModel getByName(String recipeName);

    List<RecipeModel> getAllRecipes();
}