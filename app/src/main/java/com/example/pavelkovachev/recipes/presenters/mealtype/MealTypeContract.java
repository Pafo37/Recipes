package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface MealTypeContract {

    interface View extends BaseView<Presenter> {

        void loadMealTypesFromApi(List<MealTypeModel> mealTypeList);

        void showMealTypeFromDb(List<MealTypeModel> result);

        void onError();
    }

    interface Presenter extends BasePresenter {

        void loadMealTypeFromApi();

        void showMealTypeResult(List<MealTypeModel> mealTypeModel);

        void loadMealTypeFromDb();
    }
}