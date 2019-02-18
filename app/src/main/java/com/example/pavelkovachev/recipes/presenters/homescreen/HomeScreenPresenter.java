package com.example.pavelkovachev.recipes.presenters.homescreen;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.ui.fragment.homescreen.HomeScreenFragment;

public class HomeScreenPresenter implements HomeScreenContract.Presenter, DownloadCallback {

    private final HomeScreenContract.View view;
    private DownloadCallback downloadCallback;
    private HomeScreenFragment homeScreenFragment;

    public HomeScreenPresenter(HomeScreenContract.View view) {
        this.view = view;
        view.setPresenter(this);
        homeScreenFragment=HomeScreenFragment.newInstance();
    }

    @Override
    public void start() {
        NetworkUtil.getRandomMeal(this,
                "https://www.themealdb.com/api/json/v1/1/random.php");
        //NetworkUtil.getLatestMeal(this,"https://www.themealdb.com/api/json/v1/1/latest.php");
    }

    @Override
    public void updateFromDownload(RecipeModel result) {
        if (result != null) {
            view.setRandomMeal(result);


        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }

    @Override
    public void finishDownloading(RecipeModel recipeModel) {

    }

    @Override
    public void updateFromDownload2(RecipeModel result) {
        if (result != null) {
            view.setLatestMeal(result);
        }
    }
}