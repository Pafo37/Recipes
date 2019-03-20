package com.example.pavelkovachev.recipes.network.callback;

import com.example.pavelkovachev.recipes.network.response.cuisine.CuisineListResponse;

public interface CuisineCallback {

    void onSuccessCuisine(CuisineListResponse cuisineListResponse);

    void onErrorCuisine();
}