package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

public class GeneralMealDescriptionPresenter implements GeneralMealDescriptionContract.Presenter,
        AsyncTaskResult<RecipeModel>, DownloadCallback {

    private GeneralMealDescriptionContract.View view;

    public GeneralMealDescriptionPresenter(GeneralMealDescriptionContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadRecipe(String id) {

        getRandomRecipe(id);
//        switch () {
//            case LATEST:
//                getLatestRecipe();
//                break;
//            case RANDOM:
//                getRandomRecipe();
//                break;
//            case CLICKED:
//                getRecipe(id);
//                break;
//        }
    }

    @Override
    public void getRecipeByIdFromApi() {
        String pesho=view.getRecipeId();
        NetworkUtil.getRandomMeal(this,
                String.format("https://www.themealdb.com/api/json/v1/1/lookup.php?i=%s", pesho));

    }

    private void getRandomRecipe(String id) {
        RecipeModelDao recipeModelDao = DatabaseCreator.
                getRecipeDatabase(App.getInstance().getApplicationContext()).recipeDao();
        RecipeService recipeService = new RecipeService(recipeModelDao);
        recipeService.getById(id, this);
    }


    private void getLatestRecipe() {

    }

    private void getRecipe(String id) {

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