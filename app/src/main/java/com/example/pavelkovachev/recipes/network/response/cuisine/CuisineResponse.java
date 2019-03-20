package com.example.pavelkovachev.recipes.network.response.cuisine;

import com.google.gson.annotations.SerializedName;

public class CuisineResponse {

    @SerializedName("strArea")
    private String strArea;

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}