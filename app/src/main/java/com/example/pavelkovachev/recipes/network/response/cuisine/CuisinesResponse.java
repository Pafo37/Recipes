package com.example.pavelkovachev.recipes.network.response.cuisine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisinesResponse {
    @SerializedName("meals")
    @Expose
    private List<CuisinesResponse> cuisinesResponseList = null;

    public List<CuisinesResponse> getCuisinesResponseList() {
        return cuisinesResponseList;
    }

    public void setCuisinesResponseList(List<CuisinesResponse> cuisinesResponseList) {
        this.cuisinesResponseList = cuisinesResponseList;
    }
}