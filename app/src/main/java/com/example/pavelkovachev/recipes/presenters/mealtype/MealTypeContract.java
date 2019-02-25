package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface MealTypeContract {

    interface View extends BaseView<Presenter> {
        void setMealType(List<MealTypeModel> mealTypeList);

    }

    interface Presenter extends BasePresenter {
        void loadMealType();
        void getMealType();
    }
}
