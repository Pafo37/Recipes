package com.example.pavelkovachev.recipes.persistence.model.recipelist;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface RecipeListRepository {
    void insertRecipeList(List<RecipeListModel> recipeListModels);

    void deleteRecipeList(RecipeListModel recipeListModel);

    void getAllRecipesList(AsyncTaskResult result);
}