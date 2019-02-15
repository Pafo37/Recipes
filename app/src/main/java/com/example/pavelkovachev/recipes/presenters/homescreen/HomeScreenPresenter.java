package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, DownloadCallback {


    @Override
    public void start() {
        NetworkUtil.startDownload(this,
                "https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772");
    }

    @Override
    public void updateFromDownload(RecipeModel result) {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }

    @Override
    public void finishDownloading(RecipeModel recipeModel) {

    }
}