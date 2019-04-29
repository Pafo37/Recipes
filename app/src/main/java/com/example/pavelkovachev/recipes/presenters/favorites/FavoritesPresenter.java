package com.example.pavelkovachev.recipes.presenters.favorites;

import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import javax.inject.Inject;

public class FavoritesPresenter extends BasePresenter implements FavoritesContract.Presenter {

    @Inject
    ApplicationDataService dataService;

    private FavoritesContract.View view;

    public FavoritesPresenter(FavoritesContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }
}
