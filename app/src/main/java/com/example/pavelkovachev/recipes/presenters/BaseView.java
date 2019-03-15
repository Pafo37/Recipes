package com.example.pavelkovachev.recipes.presenters;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void progressBarVisibility(boolean isVisible);
}