package com.example.pavelkovachev.recipes.presenters.homescreen;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface HomeScreenContract {

    interface View extends BaseView<Presenter> {

        void setRandomMeal(RecipeModel recipeModel);

        void setLatestMeal(RecipeModel recipeModel);

        void showError(int title, int message);

    }

    interface Presenter extends BasePresenter {

        void loadRandomLatestMeals();

        String onLatestCardViewClicked();

        String onRandomCardViewClicked();

    }
}