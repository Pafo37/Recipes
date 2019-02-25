package com.example.pavelkovachev.recipes.presenters.cuisine;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModelDao;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineService;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public class CuisinePresenter implements CuisineContract.Presenter, DownloadCallback,
        AsyncTaskResult<List<CuisineModel>> {

    private final CuisineContract.View view;

    public CuisinePresenter(CuisineContract.View view) {
        this.view = view;
        view.setPresenter(this);

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }


    private void saveToDatabase(List<CuisineModel> cuisineModel) {
        CuisineModelDao cuisineModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).cuisineModelDao();
        AppExecutor.getInstance().execute(() -> cuisineModelDao.insertCuisine(cuisineModel));
    }

    @Override
    public void showRandomMealResult(RecipeModel result) {
        //NOT USED
    }

    @Override
    public void showLatestMealResult(RecipeModel recipeModel) {
        //NOT USED
    }

    @Override
    public void showCuisineResult(List<CuisineModel> result) {
        if (result != null) {
            saveToDatabase(result);
        }
    }

    @Override
    public void showMealTypeResult(List<MealTypeModel> mealTypeModel) {
        //NOT USED
    }

    @Override
    public void finishDownloading(RecipeModel recipeModel) {
        //NOT USED
    }

    @Override
    public void start() {

    }

    @Override
    public void onSuccess(List<CuisineModel> response) {
        view.setCuisine(response);
    }

    @Override
    public void onError(Exception throwable) {

    }

    @Override
    public void loadCuisine() {
        NetworkUtil.getCuisine(this,"https://www.themealdb.com/api/json/v1/1/list.php?a=list");
    }

    @Override
    public void getCuisine() {
        CuisineModelDao cuisineModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext())
                .cuisineModelDao();
        CuisineService cuisineService = new CuisineService(cuisineModelDao);
        cuisineService.getAllCuisines(this);
    }
}
