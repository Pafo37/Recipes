package com.example.pavelkovachev.recipes.presenters.homescreen;

import com.example.pavelkovachev.recipes.converter.RecipeConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.RecipesApiCreator;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeDbService;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, RandomMealCallback, LatestMealCallback {

    private final HomeScreenContract.View view;
    private RecipesApiCreator recipesApiCreator;

    private String CURRENT_RANDOM_MEAL_ID;
    private String CURRENT_LATEST_MEAL_ID;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRandomLatestMeals() {
        RecipeApiService recipeService = new RecipeApiService(recipesApiCreator, this, this);
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
        RecipeDbService.saveToDatabase(RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        CURRENT_RANDOM_MEAL_ID = RecipeConverter.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)).getId();
    }

    @Override
    public void onErrorRandomRecipe() {
        view.onError();
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse recipesResponse) {
        view.setLatestMeal(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        RecipeDbService.saveToDatabase(RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        CURRENT_LATEST_MEAL_ID = RecipeConverter.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)).getId();
    }

    @Override
    public void onErrorLatestRecipe() {
        view.onError();
    }
}
