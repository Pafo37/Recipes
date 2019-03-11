package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.BasePresenter;
import com.example.pavelkovachev.recipes.presenters.BaseView;

import java.util.List;

public interface RecipesListContract {

    interface View extends BaseView<Presenter> {
        void loadRecipeListFromApi(List<RecipeListModel> recipeListModels);

        void showRecipeListFromDb(List<RecipeListModel> recipeListModels);

        String getCategoryName();

        String getCategoryLetter();
    }

    interface Presenter extends BasePresenter {

        void loadRecipeList();

        void getRecipeList();

        void showRecipeListResult(List<RecipeListModel> recipeListModels);
    }
}