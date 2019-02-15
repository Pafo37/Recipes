package com.example.pavelkovachev.recipes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModel;

public class MainActivity extends FragmentActivity implements DownloadCallback {

    private TextView dataText;
    private NetworkFragment networkFragment;
    private boolean isDownloading = false;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);
        dataText = findViewById(R.id.data_text);
        networkFragment = NetworkFragment.getInstance(getSupportFragmentManager(),
                "https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772");
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishDownloading();
                dataText.setText("");
            }
        });
    }

    private void startDownload() {
        if (!isDownloading && networkFragment != null) {
            // Execute the async download.
            networkFragment.startDownload();
            isDownloading = true;
        }
    }


    @Override
    public void updateFromDownload(RecipeModel result) {
        if(result!=null){
            dataText.setText(result.getRecipeName());
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void finishDownloading() {
        isDownloading = false;
        if (networkFragment != null) {
            networkFragment.cancelDownload();
        }
    }
}