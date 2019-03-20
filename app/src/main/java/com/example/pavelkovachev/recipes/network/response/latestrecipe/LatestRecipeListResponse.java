package com.example.pavelkovachev.recipes.network.response.latestrecipe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatestRecipeListResponse {

    @SerializedName("meals")
    private List<LatestRecipeResponse> latestRecipeResponseList = null;

    public List<LatestRecipeResponse> getLatestRecipeResponseList() {
        return latestRecipeResponseList;
    }

    public void setMeals(List<LatestRecipeResponse> latestRecipeResponseList) {
        this.latestRecipeResponseList = latestRecipeResponseList;
    }
}