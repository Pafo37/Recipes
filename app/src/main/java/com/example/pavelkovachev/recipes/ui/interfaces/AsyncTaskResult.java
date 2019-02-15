package com.example.pavelkovachev.recipes.ui.interfaces;

public interface AsyncTaskResult<T> {
    void onSuccess(T response);

    void onError(Exception throwable);
}