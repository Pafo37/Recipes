package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.converter.RecipesListConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.RecipesApiCreator;
import com.example.pavelkovachev.recipes.network.callback.RecipesListCallback;
import com.example.pavelkovachev.recipes.network.response.recipelist.RecipesListResponse;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class RecipesListPresenter implements RecipesListContract.Presenter,
        AsyncTaskResult<List<RecipeListModel>>, RecipesListCallback {

    private RecipesListContract.View view;
    private RecipesApiCreator recipesApiCreator;

    public RecipesListPresenter(RecipesListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    private void saveToDatabase(List<RecipeListModel> recipeListModels) {
        RecipeListModelDao recipeListModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).recipeListModelDao();
        AppExecutor.getInstance().execute(() -> recipeListModelDao.insertRecipeList(recipeListModels));
    }

    @Override
    public void loadRecipeList() {
        view.progressBarVisibility(true);
        RecipeApiService recipesService = new RecipeApiService(recipesApiCreator, this);
        recipesService.getRecipesList(view.getCategoryLetter(), view.getCategoryName());
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
            saveToDatabase(result);
            view.loadRecipeListFromApi(result);
        }
    }

    @Override
    public void onSuccess(List<RecipeListModel> result) {
        if (view != null) {
            view.showRecipeListFromDb(result);
        }
    }

    @Override
    public void onError(Exception throwable) {
    }

    @Override
    public void onSuccessRecipesList(RecipesListResponse recipesListResponse) {
        List<RecipeListModel> recipeListModelList = new ArrayList<>();
        for (int i = 0; i < recipesListResponse.getRecipeListResponses().size(); i++) {
            recipeListModelList.add(RecipesListConverter.convertToRecipesList(recipesListResponse.getRecipeListResponses().get(i)));
        }
        saveToDatabase(recipeListModelList);
        view.loadRecipeListFromApi(recipeListModelList);

    }
}