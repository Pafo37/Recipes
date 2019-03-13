package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.RecipesCallback;
import com.example.pavelkovachev.recipes.network.LatestMealApiService;
import com.example.pavelkovachev.recipes.network.RandomMealApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, RecipesCallback {

    private final HomeScreenContract.View view;
    private static final String LATEST_MEAL_URL = "https://www.themealdb.com/api/json/v1/1/latest.php";
    private static final String RANDOM_MEAL_URL = "https://www.themealdb.com/api/json/v1/1/random.php";
    private String CURRENT_RANDOM_MEAL_ID;
    private String CURRENT_LATEST_MEAL_ID;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void showRandomMealResult(RecipeModel result) {
        if (result != null) {
            view.setRandomMeal(result);
            saveToDatabase(result);
            CURRENT_RANDOM_MEAL_ID = result.getId();
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
    public void showLatestMealResult(RecipeModel result) {
        if (result != null) {
            view.setLatestMeal(result);
            saveToDatabase(result);
            CURRENT_LATEST_MEAL_ID = result.getId();
        }
    }

    @Override
    public void loadRandomLatestMeals() {
        RandomMealApiService randomMealApiService = new RandomMealApiService();
        randomMealApiService.getRandomMeal(this, RANDOM_MEAL_URL);
        LatestMealApiService latestMealApiService = new LatestMealApiService();
        latestMealApiService.getLatestMeal(this, LATEST_MEAL_URL);
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