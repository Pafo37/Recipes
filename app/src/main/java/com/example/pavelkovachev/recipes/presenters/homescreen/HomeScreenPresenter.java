package com.example.pavelkovachev.recipes.presenters.homescreen;

import com.example.pavelkovachev.recipes.converter.RecipeConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeDbService;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, RandomMealCallback, LatestMealCallback {

    private final HomeScreenContract.View view;

    private String currentRandomMealId;
    private String currentLatestMealId;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRandomLatestMeals() {
        RecipeApiService recipeService = RecipeApiService.getRecipeApiService();
        recipeService.getRandomRecipe(this);
        recipeService.getLatestRecipe(this);
    }

    @Override
    public String onLatestCardViewClicked() {
        return currentLatestMealId;
    }

    @Override
    public String onRandomCardViewClicked() {
        return currentRandomMealId;
    }

    @Override
    public void onSuccessRandomRecipe(RandomRecipeListResponse randomRecipeResponse) {
        view.setRandomMeal(RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        RecipeDbService.saveToDatabase(RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        currentRandomMealId = RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)).getId();
    }

    @Override
    public void onErrorRandomRecipe() {
        view.onError();
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse recipesResponse) {
        view.setLatestMeal(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        RecipeDbService.saveToDatabase(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        currentLatestMealId = RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)).getId();
    }

    @Override
    public void onErrorLatestRecipe() {
        view.onError();
    }
}
