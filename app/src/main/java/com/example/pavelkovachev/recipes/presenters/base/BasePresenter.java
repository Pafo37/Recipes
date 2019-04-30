package com.example.pavelkovachev.recipes.presenters.base;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.dagger.component.AppComponent;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    protected <T> void subscribeSingle(Single<T> singleObservable, SingleObserver<T> singleObserver) {
        singleObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(singleObserver);
    }
}