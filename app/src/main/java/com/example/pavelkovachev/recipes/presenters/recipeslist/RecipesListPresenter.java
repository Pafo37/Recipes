package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.converter.RecipesListConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.RecipesListCallback;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;

import java.util.ArrayList;
import java.util.List;


public class RecipesListPresenter implements RecipesListContract.Presenter
        , RecipesListCallback {

    private RecipesListContract.View view;
    private List<RecipeListModel> recipeListModelList = new ArrayList<>();

    public RecipesListPresenter(RecipesListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRecipeList() {
        view.setProgressBarVisibility(true);
        if (view.getCategoryName() != null && view.getCategoryLetter() != null) {
            RecipeApiService.getRecipeApiService()
                    .getRecipesList(view.getCategoryLetter(), view.getCategoryName(), this);
        } else {
            view.showErrorNoArguments();
        }
    }

    @Override
    public void onSuccessRecipesList(RecipesListResponse recipesListResponse) {
        Stream.of(recipesListResponse.getRecipeListResponses()).forEach(
                recipeList ->
                        recipeListModelList.add(RecipesListConverter.convertToRecipesList(recipeList)));
        getRecipeListArray().addAll(recipeListModelList);
        view.loadRecipeListFromApi(recipeListModelList);
    }

    @Override
    public void onErrorRecipesList() {
        view.onError();
    }

    @Override
    public List<RecipeListModel> getRecipeListArray() {
        return recipeListModelList;
    }
}