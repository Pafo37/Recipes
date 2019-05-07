package com.example.pavelkovachev.recipes.presenters.myrecipesdescription;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MyRecipesDescriptionPresenter extends BasePresenter
        implements MyRecipesDescriptionContract.Presenter {

    @Inject
    ApplicationDataService dataService;

    private MyRecipesDescriptionContract.View view;
    private MyRecipesModel recipesModel;

    public MyRecipesDescriptionPresenter(MyRecipesDescriptionContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }

    @Override
    public void getMyRecipeById(int recipeId) {
        subscribeSingle(dataService.getMyRecipesService().getMyRecipeById(recipeId),
                new SingleObserver<MyRecipesModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //NOT USED
                    }

                    @Override
                    public void onSuccess(MyRecipesModel myRecipesModel) {
                        view.showMyRecipe(myRecipesModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(App.getInstance().getResources().getString(R.string.alert_dialog_error),
                                App.getInstance().getResources().getString(R.string.alert_dialog_my_recipes_description));
                    }
                });
    }
}