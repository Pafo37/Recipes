package com.example.pavelkovachev.recipes.persistence.model.cuisine;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineResponse;

@Entity
public class CuisineModel {

    @NonNull
    @PrimaryKey()
    private String country;

    public CuisineModel(@NonNull String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static CuisineModel convertToCuisine(CuisineResponse cuisineResponse) {

        return new CuisineModel(cuisineResponse.getStrArea());
    }
}