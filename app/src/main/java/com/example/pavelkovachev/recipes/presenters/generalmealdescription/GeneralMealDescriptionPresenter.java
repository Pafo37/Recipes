package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.converter.RecipeConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeDbService;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

public class GeneralMealDescriptionPresenter implements GeneralMealDescriptionContract.Presenter,
        AsyncTaskResult<RecipeModel>, RandomMealCallback, LatestMealCallback {

    private GeneralMealDescriptionContract.View view;

    public GeneralMealDescriptionPresenter(GeneralMealDescriptionContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getRecipeByIdFromApi() {
        if (view.getRecipeId() != null) {
            RecipeApiService recipeService = RecipeApiService.getRecipeApiService();
            recipeService.getRecipeById(view.getRecipeId(), this);
        } else {
            view.showErrorNoArguments();
        }
    }

    @Override
    public void getRandomRecipeFromDb(String id) {
        view.setProgressBarVisibility(true);
        RecipeModelDao recipeModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).recipeDao();
        RecipeDbService recipeService = new RecipeDbService(recipeModelDao);
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
    public void onError() {
        view.onError();
    }

    @Override
    public void onSuccessRandomRecipe(RandomRecipeListResponse randomRecipesResponse) {
        view.showRecipe(RecipeConverter.convertRandomRecipe(randomRecipesResponse.getMeals().get(0)));
    }

    @Override
    public void onErrorRandomRecipe() {
        view.onError();
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse latestRecipesResponse) {
        view.showRecipe(RecipeConverter.convertLatestRecipe(latestRecipesResponse.getLatestRecipeResponseList().get(0)));
    }

    @Override
    public void onErrorLatestRecipe() {
        view.onError();
    }

}