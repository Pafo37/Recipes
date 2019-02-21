package com.example.pavelkovachev.recipes.presenters.generalmealdescription;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

public class GeneralMealDescriptionPresenter implements GeneralMealDescriptionContract.Presenter,
        AsyncTaskResult<RecipeModel> {

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
    public void onSuccess(RecipeModel response) {
        view.showRecipe(response);
    }

    @Override
    public void onError(Exception throwable) {

    }
}