package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

public interface GeneralMealDescriptionContract {

    interface View extends BaseView<Presenter> {

        void showRecipe(RecipeModel recipeModel);

        String getRecipeId();

        void onError();

        void showErrorNoArguments();

        RecipeModel getRecipe();
    }

    interface Presenter extends BasePresenter {

        void getRandomRecipeFromDb(String id);

        void getRecipeByIdFromApi();

        void addToFavorites(RecipeModel recipeModel);
    }
}