package com.example.pavelkovachev.recipes;

import android.net.NetworkInfo;

import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModel;

public interface DownloadCallback {

    void updateFromDownload(RecipeModel result);

    NetworkInfo getActiveNetworkInfo();

    void finishDownloading();

}