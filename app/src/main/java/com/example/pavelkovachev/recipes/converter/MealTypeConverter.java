package com.example.pavelkovachev.recipes.converter;

import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeResponse;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;

public class MealTypeConverter {

    public static MealTypeModel convertToMealType(MealTypeResponse mealTypeResponse){

        return new MealTypeModel(mealTypeResponse.getStrCategory(),mealTypeResponse.getStrCategoryDescription(),
                mealTypeResponse.getStrCategoryThumb());
    }
}