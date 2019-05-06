package com.example.pavelkovachev.recipes.presenters.myrecipesdescription;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface MyRecipesDescriptionContract {

    interface View extends BaseView<Presenter> {

        void showMyRecipe(MyRecipesModel myRecipesModel);

    }

    interface Presenter extends BasePresenter {

        void getMyRecipeById(int recipeId);

    }
}