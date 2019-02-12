package com.example.pavelkovachev.recipes.ui.interfaces;

public interface AsyncTaskResult<T> {

    T onSuccess(T response);

    void onError(Exception throwable);
}
