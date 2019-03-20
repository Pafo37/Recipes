package com.example.pavelkovachev.recipes.presenters;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void setProgressBarVisibility(boolean isVisible);

}