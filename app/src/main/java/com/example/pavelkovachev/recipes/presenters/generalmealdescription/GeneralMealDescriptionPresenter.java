package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.LatestMealCallback;
import com.example.pavelkovachev.recipes.network.callback.RandomMealCallback;
import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeListResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeListResponse;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class GeneralMealDescriptionPresenter extends BasePresenter implements GeneralMealDescriptionContract.Presenter,
        AsyncTaskResult<RecipeModel>, RandomMealCallback, LatestMealCallback {

    @Inject
    ApplicationDataService dataService;
    @Inject
    RecipeApiService recipeService;

    private GeneralMealDescriptionContract.View view;
    private boolean isRecipeAdded = false;

    public GeneralMealDescriptionPresenter(GeneralMealDescriptionContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getRecipeByIdFromApi() {
        if (view.getRecipeId() != null) {
            recipeService.getRecipeById(view.getRecipeId(), this);
        } else {
            view.showError(R.string.alert_dialog_error, R.string.alert_dialog_general_meal_description);
        }
    }

    @Override
    public void addToFavorites(FavoritesModel favoritesModel) {
        getAllRecipes(favoritesModel);
    }

    @Override
    public void getAllRecipes(FavoritesModel favoritesModel) {
        subscribeSingle(dataService.getFavoritesService().getFavoriteRecipes(), new SingleObserver<List<FavoritesModel>>() {

            @Override
            public void onSubscribe(Disposable d) {
                //NOT USED
            }

            @Override
            public void onSuccess(List<FavoritesModel> favoritesModels) {
                for (FavoritesModel model : favoritesModels) {
                    if (model.getFavoriteRecipeId().equals(favoritesModel.getFavoriteRecipeId())) {
                        isRecipeAdded = true;
                        break;
                    } else {
                        isRecipeAdded = false;
                        dataService.getFavoritesService().insertFavorites(favoritesModel);
                    }
                }
                if (isRecipeAdded) {
                    view.showToast(R.string.recipe_already_added);
                } else {
                    view.showToast(R.string.recipe_added_to_favorites);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showError(R.string.alert_dialog_error,
                        R.string.alert_dialog_add_recipe_to_favorites);
            }
        });
    }

    @Override
    public void getRandomRecipeFromDb(String id) {
        view.setProgressBarVisibility(true);
        dataService.getRecipeService().getById(id, this);
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
        view.showError(R.string.alert_dialog_error, R.string.alert_dialog_general_meal_description);
    }

    @Override
    public void onSuccessRandomRecipe(RandomRecipeListResponse randomRecipesResponse) {
        view.showRecipe(RecipeModel.convertRandomRecipe(randomRecipesResponse.getMeals().get(0)));
    }

    @Override
    public void onErrorRandomRecipe() {
        view.showError(R.string.alert_dialog_error, R.string.alert_dialog_general_meal_description);
    }

    @Override
    public void onSuccessLatestRecipe(LatestRecipeListResponse latestRecipesResponse) {
        view.showRecipe(RecipeModel.convertLatestRecipe(latestRecipesResponse.getLatestRecipeResponseList().get(0)));
    }

    @Override
    public void onErrorLatestRecipe() {
        view.showError(R.string.alert_dialog_error, R.string.alert_dialog_general_meal_description);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }
}