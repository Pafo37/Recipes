package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.BuildConfig;
import com.example.pavelkovachev.recipes.RecipesCallback;
import com.example.pavelkovachev.recipes.network.LatestMealApiService;
import com.example.pavelkovachev.recipes.network.RandomMealApiService;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeService;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, RecipesCallback {

    private final HomeScreenContract.View view;
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
            RecipeService.saveToDatabase(result);
            CURRENT_RANDOM_MEAL_ID = result.getId();
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }

    @Override
    public void showLatestMealResult(RecipeModel result) {
        if (result != null) {
            view.setLatestMeal(result);
            RecipeService.saveToDatabase(result);
            CURRENT_LATEST_MEAL_ID = result.getId();
        }
    }

    @Override
    public void loadRandomLatestMeals() {
        RandomMealApiService randomMealApiService = new RandomMealApiService();
        randomMealApiService.getRandomMeal(this, BuildConfig.RANDOM_MEAL_URL);
        LatestMealApiService latestMealApiService = new LatestMealApiService();
        latestMealApiService.getLatestMeal(this, BuildConfig.LATEST_MEAL_URL);
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