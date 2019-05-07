package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface RecipesListContract {

    interface View extends BaseView<Presenter> {

        void loadRecipeListFromApi(List<RecipeListModel> recipeListModels);

        String getCategoryName();

        String getCategoryLetter();

        void showError(String title, String message);

    }

    interface Presenter extends BasePresenter {

        void loadRecipeList();

        List<RecipeListModel> getRecipeListArray();
    }
}