package com.example.pavelkovachev.recipes;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtil {
    private static DownloadCallback downloadCallback;
    private static RandomMealTask downloadTask;
    private static String urlString;

    public static void getRandomMeal(DownloadCallback downloadCallback, String url) {
        cancelDownload();
        NetworkUtil.downloadCallback = downloadCallback;
        downloadTask = new RandomMealTask();
        urlString = url;
        downloadTask.execute(urlString);
    }

    public static void getLatestMeal(DownloadCallback downloadCallback, String url) {
        cancelDownload();
        NetworkUtil.downloadCallback = downloadCallback;
        downloadTask = new RandomMealTask();
        urlString = url;
        downloadTask.execute(urlString);
    }

    public static void cancelDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    private static class RandomMealTask extends AsyncTask<String, Integer, RecipeModel> {

        @Override
        protected void onPreExecute() {
            if (downloadCallback != null) {
                NetworkInfo networkInfo = downloadCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    downloadCallback.updateFromDownload(null);
                    // cancel(true);
                }
            }
        }

        @Override
        protected RecipeModel doInBackground(String... urls) {
            RecipeModel recipeModel = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    recipeModel = downloadUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return recipeModel;
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            if (recipeModel != null && downloadCallback != null) {
                downloadCallback.updateFromDownload(recipeModel);
            }
            downloadCallback.finishDownloading(recipeModel);
        }
    }

    private static RecipeModel downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        RecipeModel recipeModel = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                recipeModel = readJsonStream(stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return recipeModel;
    }


    private static RecipeModel readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRecipeData(reader);

    }

    private static RecipeModel readRecipeData(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "meals":
                    recipeModel = readRecipeFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return recipeModel;
    }

    private static RecipeModel readRecipeFields(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strMeal":
                    recipeModel.setRecipeName(reader.nextString());
                    break;
                case "strCategory":
                    recipeModel.setRecipeMealType(reader.nextString());
                    break;
                case "strArea":
                    recipeModel.setRecipeCuisine(reader.nextString());
                    break;
                case "strInstructions":
                    recipeModel.setRecipeDescription(reader.nextString());
                    break;
                case "strMealThumb":
                    recipeModel.setRecipeImage(reader.nextString());
                    break;
                case "strIngredient1":
                    recipeModel.setRecipeIngredient1(reader.nextString());
                    break;
                case "strIngredient2":
                    recipeModel.setRecipeIngredient2(reader.nextString());
                    break;
                case "strIngredient3":
                    recipeModel.setRecipeIngredient3(reader.nextString());
                    break;
                case "strIngredient4":
                    recipeModel.setRecipeIngredient4(reader.nextString());
                    break;
                case "strIngredient5":
                    recipeModel.setRecipeIngredient5(reader.nextString());
                    break;
                case "strMeasure1":
                    recipeModel.setRecipeIngredient1(reader.nextString());
                    break;
                case "strMeasure2":
                    recipeModel.setRecipeIngredient2(reader.nextString());
                    break;
                case "strMeasure3":
                    recipeModel.setRecipeIngredient3(reader.nextString());
                    break;
                case "strMeasure4":
                    recipeModel.setRecipeIngredient4(reader.nextString());
                    break;
                case "strMeasure5":
                    recipeModel.setRecipeIngredient5(reader.nextString());
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        reader.endArray();
        return recipeModel;
    }
}