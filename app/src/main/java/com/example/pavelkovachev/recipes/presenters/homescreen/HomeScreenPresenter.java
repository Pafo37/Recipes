package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;

import java.util.List;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, DownloadCallback {

    private final HomeScreenContract.View view;

    public String CURRENT_RANDOM_MEAL_ID;
    public String CURRENT_LATEST_MEAL_ID;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void showRandomMealResult(RecipeModel result) {
        if (result != null) {
            view.setRandomMeal(result);
            saveToDatabase(result);
            CURRENT_RANDOM_MEAL_ID=result.getId();
        }
    }

    private void saveToDatabase(RecipeModel recipeModel) {
        RecipeModelDao recipeModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).recipeDao();
        AppExecutor.getInstance().execute(() -> recipeModelDao.insertRecipe(recipeModel));
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }

    @Override
    public void finishDownloading(RecipeModel recipeModel) {

    }

    @Override
    public void showLatestMealResult(RecipeModel result) {
        if (result != null) {
            view.setLatestMeal(result);
            saveToDatabase(result);
            CURRENT_LATEST_MEAL_ID=result.getId();
        }
    }

    @Override
    public void showCuisineResult(List<CuisineModel> cuisineModel) {
//NOT USED
    }

    @Override
    public void showMealTypeResult(List<MealTypeModel> mealTypeModel) {
        //NOT USED
    }

    @Override
    public void loadRandomLatestMeals() {
        NetworkUtil.getRandomMeal(this,
                "https://www.themealdb.com/api/json/v1/1/random.php");
        NetworkUtil.getLatestMeal(this, "https://www.themealdb.com/api/json/v1/1/latest.php");

    }

    @Override
    public String onLatestCardViewClicked() {
        return CURRENT_LATEST_MEAL_ID;
    }

    @Override
    public String onRandomCardViewClicked() {
        return CURRENT_RANDOM_MEAL_ID;
    }
}