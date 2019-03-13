package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.RecipesCallback;
import com.example.pavelkovachev.recipes.network.RandomMealApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

public class GeneralMealDescriptionPresenter implements GeneralMealDescriptionContract.Presenter,
        AsyncTaskResult<RecipeModel>, RecipesCallback {

    private GeneralMealDescriptionContract.View view;
    private String URL = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=%s";

    public GeneralMealDescriptionPresenter(GeneralMealDescriptionContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getRecipeByIdFromApi() {
        RandomMealApiService randomMealApiService=new RandomMealApiService();
        randomMealApiService.getRandomMeal(this,
                String.format(URL, view.getRecipeId()));
    }

    @Override
    public void getRandomRecipe(String id) {
        view.showProgressBar(true);
        RecipeModelDao recipeModelDao = DatabaseCreator.
                getRecipeDatabase(App.getInstance().getApplicationContext()).recipeDao();
        RecipeService recipeService = new RecipeService(recipeModelDao);
        recipeService.getById(id, this);
    }

    @Override
    public void onSuccess(RecipeModel result) {
        if (result != null) {
            view.showRecipe(result);
        } else {
            getRecipeByIdFromApi();
        }
    }

    @Override
    public void onError(Exception throwable) {
    }

    @Override
    public void showRandomMealResult(RecipeModel result) {
        view.showRecipe(result);
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        //NOT USED
        return null;
    }

    @Override
    public void showLatestMealResult(RecipeModel recipeModel) {
        //NOT USED
    }
}