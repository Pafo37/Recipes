package com.example.pavelkovachev.recipes.presenters.recipeslist;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.network.RecipeListApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public class RecipesListPresenter implements RecipesListContract.Presenter,
        AsyncTaskResult<List<RecipeListModel>> {

    private RecipesListContract.View view;

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
        RecipeListApiService.getRecipeList(this,
                String.format("https://www.themealdb.com/api/json/v1/1/filter.php?%s=%s", view.getCategoryLetter(), view.getCategoryName()));
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
}