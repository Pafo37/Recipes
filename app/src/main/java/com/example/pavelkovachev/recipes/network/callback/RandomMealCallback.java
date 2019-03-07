package com.example.pavelkovachev.recipes.network.callback;

import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;

public interface RandomMealCallback {

    void onSuccessRandomRecipe(RandomRecipeListResponse randomRecipeResponse);

}
