package com.example.pavelkovachev.recipes.presenters.myrecipesdescription;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface MyRecipesDescriptionContract {

    interface View extends BaseView<Presenter> {

        void showMyRecipe(MyRecipesModel myRecipesModel);

        void showError(int title, int message);

    }

    interface Presenter extends BasePresenter {

        void getMyRecipeByName(String recipeName);

    }
}