package com.example.pavelkovachev.recipes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends FragmentActivity implements DownloadCallback {

    private TextView dataText;
    private NetworkUtil networkUtil;
    private boolean isDownloading = false;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);
        dataText = findViewById(R.id.data_text);
       // networkUtil = NetworkUtil.getInstance(getSupportFragmentManager(),
        //        "https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772");
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        imageView = findViewById(R.id.imageTest);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   finishDownloading(recipeModel);
                dataText.setText("");
            }
        });
    }

    private void startDownload() {
        if (!isDownloading && networkUtil != null) {
            // Execute the async download.
            //networkUtil.getRandomMeal();
            isDownloading = true;
        }
    }

    @Override
    public void showRandomMealResult(RecipeModel result) {
        if (result != null) {
            dataText.setText(result.getRecipeName());
            Picasso.get().load(result.getRecipeImage()).into(imageView);
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
    public void finishDownloading(RecipeModel recipeModel) {
        isDownloading = false;
        if (networkUtil != null) {
            networkUtil.cancelRandomMealDownload();
        }
    }

    @Override
    public void showLatestMealResult(RecipeModel recipeModel) {

    }

    @Override
    public void showCuisineResult(List<CuisineModel> cuisineModel) {

    }

    @Override
    public void showMealTypeResult(MealTypeModel mealTypeModel) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}