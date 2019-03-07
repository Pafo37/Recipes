package com.example.pavelkovachev.recipes.converter;

import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineResponse;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;

public class CuisineConverter {

    public static CuisineModel convertToCuisine(CuisineResponse cuisineResponse){

        return new CuisineModel(cuisineResponse.getStrArea());
    }
}
