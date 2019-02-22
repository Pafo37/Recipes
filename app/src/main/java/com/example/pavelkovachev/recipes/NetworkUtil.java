package com.example.pavelkovachev.recipes;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtil {
    private static DownloadCallback downloadCallback;
    private static RandomMealTask downloadTask;
    private static LatestMealTask latestMealTask;
    private static CuisineTask cuisineTask;
    private static String urlString;

    public static void getRandomMeal(DownloadCallback downloadCallback, String url) {
        cancelRandomMealDownload();
        NetworkUtil.downloadCallback = downloadCallback;
        downloadTask = new RandomMealTask();
        urlString = url;
        downloadTask.execute(urlString);
    }

    public static void getLatestMeal(DownloadCallback downloadCallback, String url) {
        cancelLatestMealDownload();
        NetworkUtil.downloadCallback = downloadCallback;
        latestMealTask = new LatestMealTask();
        urlString = url;
        latestMealTask.execute(urlString);
    }

    public static void getCuisine(DownloadCallback downloadCallback, String url) {
        cancelCuisineDownload();
        NetworkUtil.downloadCallback = downloadCallback;
        cuisineTask = new CuisineTask();
        urlString = url;
        cuisineTask.execute(urlString);
    }

    public static void cancelRandomMealDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    public static void cancelLatestMealDownload() {
        if (latestMealTask != null) {
            latestMealTask.cancel(true);
            latestMealTask = null;
        }
    }

    private static void cancelCuisineDownload() {
        if (cuisineTask != null) {
            cuisineTask.cancel(true);
            cuisineTask = null;
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
                    downloadCallback.showRandomMealResult(null);
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
                    recipeModel = randomMealConnection(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return recipeModel;
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            if (recipeModel != null && downloadCallback != null) {
                downloadCallback.showRandomMealResult(recipeModel);
            }
            downloadCallback.finishDownloading(recipeModel);
        }
    }

    private static class LatestMealTask extends AsyncTask<String, Integer, RecipeModel> {

        @Override
        protected void onPreExecute() {
            if (downloadCallback != null) {
                NetworkInfo networkInfo = downloadCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    downloadCallback.showLatestMealResult(null);
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
                    recipeModel = latestMealConnection(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return recipeModel;
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            if (recipeModel != null && downloadCallback != null) {
                downloadCallback.showLatestMealResult(recipeModel);
            }
            downloadCallback.finishDownloading(recipeModel);
        }
    }

    private static class CuisineTask extends AsyncTask<String, Integer, List<CuisineModel>> {

        @Override
        protected void onPreExecute() {
            if (downloadCallback != null) {
                NetworkInfo networkInfo = downloadCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    downloadCallback.showCuisineResult(null);
                }
            }
        }

        @Override
        protected List<CuisineModel> doInBackground(String... urls) {
            List<CuisineModel> cuisineModelList = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    cuisineModelList = cuisineConnection(url);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cuisineModelList;
        }

        @Override
        protected void onPostExecute(List<CuisineModel> cuisineModel) {
            if (cuisineModel != null && downloadCallback != null) {
                downloadCallback.showCuisineResult(cuisineModel);
            }
        }
    }

    private static RecipeModel randomMealConnection(URL url) throws IOException {
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
                recipeModel = readRandomMealStream(stream);
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

    private static RecipeModel latestMealConnection(URL url) throws IOException {
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
                recipeModel = readLatestMealStream(stream);
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

    private static List<CuisineModel> cuisineConnection(URL url) throws IOException {
        List<CuisineModel> cuisineModelList = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
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
                cuisineModelList = readCuisineStream(stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return cuisineModelList;
    }

    private static List<CuisineModel> readCuisineStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readCuisineData(reader);
    }

    private static RecipeModel readRandomMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRandomRecipeData(reader);

    }

    private static RecipeModel readLatestMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readLatestRecipeData(reader);
    }

    private static List<CuisineModel> readCuisineData(JsonReader reader) throws IOException {
        List<CuisineModel> cuisineModelList = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "meals":
                    cuisineModelList = readCuisineFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return cuisineModelList;
    }


    private static RecipeModel readRandomRecipeData(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "meals":
                    recipeModel = readRandomRecipeFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
            break;
        }
        reader.endObject();
        return recipeModel;
    }

    private static RecipeModel readLatestRecipeData(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "meals":
                    recipeModel = readLatestRecipeFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
            break;
        }
        return recipeModel;
    }

    private static RecipeModel readRandomRecipeFields(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "idMeal":
                    recipeModel.setId(reader.nextString());
                    break;
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
                    recipeModel.setRecipeInstructions(reader.nextString());
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
                    recipeModel.setRecipeMeasure1(reader.nextString());
                    break;
                case "strMeasure2":
                    recipeModel.setRecipeMeasure2(reader.nextString());
                    break;
                case "strMeasure3":
                    recipeModel.setRecipeMeasure3(reader.nextString());
                    break;
                case "strMeasure4":
                    recipeModel.setRecipeMeasure4(reader.nextString());
                    break;
                case "strMeasure5":
                    recipeModel.setRecipeMeasure5(reader.nextString());
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        reader.endArray();
        return recipeModel;
    }

    private static List<CuisineModel> readCuisineFields(JsonReader reader) throws IOException {
        List<CuisineModel> cuisineModelList = new ArrayList<>();
        CuisineModel cuisineModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strCategory":
                    cuisineModel=new CuisineModel();
                    cuisineModel.setCountry(reader.nextString());
                    cuisineModelList.add(cuisineModel);
                    break;

                default:
                    reader.skipValue();
            }
            reader.endObject();
            if (reader.hasNext()) {
                reader.beginObject();
            }
        }

        reader.endArray();
        return cuisineModelList;
    }

    private static RecipeModel readLatestRecipeFields(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "idMeal":
                    recipeModel.setId(reader.nextString());
                    break;
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
                    recipeModel.setRecipeInstructions(reader.nextString());
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
        return recipeModel;
    }
}