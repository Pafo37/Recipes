package com.example.pavelkovachev.recipes.network.response.randomrecipe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomRecipeListResponse {

    @SerializedName("meals")
    private List<RandomRecipeResponse> meals = null;

    public List<RandomRecipeResponse> getMeals() {
        return meals;
    }

    public void setMeals(List<RandomRecipeResponse> meals) {
        this.meals = meals;
    }

    public RandomRecipeListResponse withMeals(List<RandomRecipeResponse> meals) {
        this.meals = meals;
        return this;
    }
}