package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface MealTypeRepository {

    void insertMealType(List<MealTypeModel> mealTypeModel);

    void deleteMealType(MealTypeModel mealTypeModel);

    void getAllMealTypes(AsyncTaskResult result);
}