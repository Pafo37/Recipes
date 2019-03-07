package com.example.pavelkovachev.recipes.network.response.recipelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesListResponse {
    @SerializedName("meals")
    private List<RecipeListResponse> recipeListResponses = null;

    public List<RecipeListResponse> getRecipeListResponses() {
        return recipeListResponses;
    }

    public void setMeals(List<RecipeListResponse> recipeListResponses) {
        this.recipeListResponses = recipeListResponses;
    }
}