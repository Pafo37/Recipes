package com.example.pavelkovachev.recipes.network.response.mealtype;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealTypeListResponses {

    @SerializedName("categories")
    private List<MealTypeResponse> mealTypeResponseList = null;

    public List<MealTypeResponse> getCategories() {
        return mealTypeResponseList;
    }

    public void setCategories(List<MealTypeResponse> mealTypeResponses) {
        this.mealTypeResponseList = mealTypeResponses;
    }
}