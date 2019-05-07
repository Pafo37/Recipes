package com.example.pavelkovachev.recipes.presenters.homescreen;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import javax.inject.Inject;

public class HomeScreenPresenter extends BasePresenter implements HomeScreenContract.Presenter, RandomMealCallback, LatestMealCallback {

    private final HomeScreenContract.View view;

    @Inject
    ApplicationDataService dataService;
    @Inject
    RecipeApiService recipeService;

    private String currentRandomMealId;
    private String currentLatestMealId;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadRandomLatestMeals() {
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
        view.setRandomMeal(RecipeModel.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        dataService.getRecipeService().insertRecipe(RecipeModel.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)));
        currentRandomMealId = RecipeModel.convertRandomRecipe(randomRecipeResponse.getMeals().get(0)).getId();
    }

    @Override
    public void onErrorRandomRecipe() {
        view.showError(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                App.getInstance().getResources().getString(R.string.alert_dialog_recipes_list_homescreen));
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse recipesResponse) {
        view.setLatestMeal(RecipeModel.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        dataService.getRecipeService().insertRecipe(RecipeModel.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)));
        currentLatestMealId = RecipeModel.convertLatestRecipe(recipesResponse.getLatestRecipeResponseList().get(0)).getId();
    }

    @Override
    public void onErrorLatestRecipe() {
        view.showError(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                App.getInstance().getResources().getString(R.string.alert_dialog_recipes_list_homescreen));
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }
}