package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.BuildConfig;
import com.example.pavelkovachev.recipes.network.RecipeListApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class RecipesListPresenter implements RecipesListContract.Presenter,
        AsyncTaskResult<List<RecipeListModel>> {

    private RecipesListContract.View view;
    private List<RecipeListModel> recipeListArray = new ArrayList<>();

    public RecipesListPresenter(RecipesListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRecipeList() {
        view.showProgressBar(true);
        RecipeListApiService recipeListApiService = new RecipeListApiService();
        recipeListApiService.getRecipeList(this,
                String.format(BuildConfig.RECIPES_LIST_URL, view.getCategoryLetter(), view.getCategoryName()));
    }

    @Override
    public void getRecipeList() {
        RecipeListModelDao recipeListModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext())
                .recipeListModelDao();
        RecipeListService recipeListService = new RecipeListService(recipeListModelDao);
        recipeListService.getAllRecipesList(this);
    }

    @Override
    public void showRecipeListResult(List<RecipeListModel> result) {
        if (result != null) {
            RecipeListService.saveToDatabase(result);
            getRecipeListArray().addAll(result);
            view.loadRecipeListFromApi(result);
        }
    }

    @Override
    public List<RecipeListModel> getRecipeListArray() {
        return recipeListArray;
    }

    @Override
    public void onSuccess(List<RecipeListModel> result) {
        if (view != null) {
            getRecipeListArray().addAll(result);
            view.showRecipeListFromDb(result);
        }
    }

    @Override
    public void onError(Exception throwable) {
    }
}