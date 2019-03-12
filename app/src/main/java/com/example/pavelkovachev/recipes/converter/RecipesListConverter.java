package com.example.pavelkovachev.recipes.converter;

import com.example.pavelkovachev.recipes.network.response.recipelist.RecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;

public class RecipesListConverter {

    public static RecipeListModel convertToRecipesList(RecipeListResponse recipeListResponse) {

        return new RecipeListModel(recipeListResponse.getIdMeal(), recipeListResponse.getStrMeal(),
                recipeListResponse.getStrMealThumb());
    }
}