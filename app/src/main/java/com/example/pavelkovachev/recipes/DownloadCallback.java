package com.example.pavelkovachev.recipes;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;


public interface DownloadCallback {

    void updateFromDownload(RecipeModel result);

    NetworkInfo getActiveNetworkInfo();

    void finishDownloading();

}