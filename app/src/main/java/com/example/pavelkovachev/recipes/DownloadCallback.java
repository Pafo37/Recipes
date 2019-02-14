package com.example.pavelkovachev.recipes;

import android.net.NetworkInfo;

public interface DownloadCallback {

    void updateFromDownload(String result);

    NetworkInfo getActiveNetworkInfo();

    void finishDownloading();

}