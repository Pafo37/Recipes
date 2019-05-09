package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.RecipesListCallback;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipesListPresenter extends BasePresenter
        implements RecipesListContract.Presenter, RecipesListCallback {

    @Inject
    RecipeApiService recipeService;

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
            recipeService.getRecipesList(view.getCategoryLetter(), view.getCategoryName(), this);
        } else {
            view.showError(R.string.alert_dialog_error,
                    R.string.alert_dialog_recipes_list_homescreen);
        }
    }

    @Override
    public void onSuccessRecipesList(RecipesListResponse recipesListResponse) {
        Stream.of(recipesListResponse.getRecipeListResponses()).forEach(
                recipeList ->
                        recipeListModelList.add(RecipeListModel.convertToRecipesList(recipeList)));
        getRecipeListArray().addAll(recipeListModelList);
        view.loadRecipeListFromApi(recipeListModelList);
    }

    @Override
    public void onErrorRecipesList() {
        view.showError(R.string.alert_dialog_error,
                R.string.alert_dialog_recipes_list_homescreen);
    }

    @Override
    public List<RecipeListModel> getRecipeListArray() {
        return recipeListModelList;
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }
}