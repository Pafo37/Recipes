package com.example.pavelkovachev.recipes.network.callback;

import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeListResponses;

public interface MealTypeCallback {

    void onSuccessMealTypes(MealTypeListResponses mealTypesResponses);
}
