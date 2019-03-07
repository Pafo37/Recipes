package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.converter.RecipeConverter;
import com.example.pavelkovachev.recipes.network.RecipesApiCreator;
import com.example.pavelkovachev.recipes.network.RecipesService;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, DownloadCallback , RandomMealCallback, LatestMealCallback {

    private final HomeScreenContract.View view;
    private RecipesApiCreator recipesApiCreator;

    public String CURRENT_RANDOM_MEAL_ID;
    public String CURRENT_LATEST_MEAL_ID;

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
            //TODO pass the id with intent
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
            //TODO pass the id with intent
        }
    }

    @Override
    public void loadRandomLatestMeals() {
        RecipesService recipeService=new RecipesService(recipesApiCreator,this,this);
        recipeService.getRandomRecipe();
        recipeService.getLatestRecipe();
    }

    @Override
    public String onLatestCardViewClicked() {
        return CURRENT_LATEST_MEAL_ID;
    }

    @Override
    public String onRandomCardViewClicked() {
        return CURRENT_RANDOM_MEAL_ID;
    }

    @Override
    public void onSuccessRandomRecipe(RandomRecipeListResponse randomRecipeResponse) {
        view.setRandomMeal(RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        saveToDatabase(RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        CURRENT_RANDOM_MEAL_ID = RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)).getId();
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse recipesResponse) {
        view.setLatestMeal(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        saveToDatabase(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        CURRENT_LATEST_MEAL_ID = RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)).getId();
    }
}