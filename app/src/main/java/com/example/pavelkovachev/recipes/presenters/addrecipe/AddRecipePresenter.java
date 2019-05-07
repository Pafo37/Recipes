package com.example.pavelkovachev.recipes.presenters.addrecipe;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import javax.inject.Inject;

public class AddRecipePresenter extends BasePresenter implements AddRecipeContract.Presenter {

    @Inject
    ApplicationDataService dataService;

    private AddRecipeContract.View view;

    public AddRecipePresenter(AddRecipeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }

    @Override
    public void addRecipeToDb(MyRecipesModel myRecipesModel) {
        dataService.getMyRecipesService().insertMyRecipe(myRecipesModel);
    }
}