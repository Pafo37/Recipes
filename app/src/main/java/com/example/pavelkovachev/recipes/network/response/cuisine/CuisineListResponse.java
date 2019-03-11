package com.example.pavelkovachev.recipes.network.response.cuisine;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisineListResponse {

    @SerializedName("meals")
    private List<CuisineResponse> cuisinesResponseList = null;

    public List<CuisineResponse> getCuisinesResponseList() {
        return cuisinesResponseList;
    }

    public void setCuisinesResponseList(List<CuisineResponse> cuisinesResponseList) {
        this.cuisinesResponseList = cuisinesResponseList;
    }
}