package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface MealTypeRepository {
    void insertCuisine(List<MealTypeModel> mealTypeModel);

    void deleteCuisine(MealTypeModel mealTypeModel);

    List<MealTypeModel> getAllMealTypes(AsyncTaskResult result);
}