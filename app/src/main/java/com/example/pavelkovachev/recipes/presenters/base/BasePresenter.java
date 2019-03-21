package com.example.pavelkovachev.recipes.presenters.base;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.dagger.component.AppComponent;

public abstract class BasePresenter {

    private AppComponent appComponent;

    public BasePresenter() {
        appComponent = App.getInstance().getAppComponent();
        inject();
    }

    protected abstract void inject();

    protected AppComponent provideAppComponent() {
        return appComponent;
    }
}