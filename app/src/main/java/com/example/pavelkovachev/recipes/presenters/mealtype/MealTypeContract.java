package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface MealTypeContract {

    interface View extends BaseView<Presenter> {

        void showMealTypesFromApi(List<MealTypeModel> mealTypeList);

        void showMealTypeFromDb(List<MealTypeModel> result);

        void showError(String title, String message);
    }

    interface Presenter extends BasePresenter {

        void loadMealTypeFromApi();

        void loadMealTypeFromDb();

        List<MealTypeModel> getMealTypeList();
    }
}