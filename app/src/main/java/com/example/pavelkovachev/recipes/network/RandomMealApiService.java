package com.example.pavelkovachev.recipes.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;

import com.example.pavelkovachev.recipes.DownloadCallback;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RandomMealApiService {
    private static DownloadCallback downloadCallback;
    private static RandomMealApiService.RandomMealTask randomMealTask;
    private static String currentValue;
    private static String urlString;

    public static void getRandomMeal(DownloadCallback downloadCallback, String url) {
        cancelRandomMealDownload();
        RandomMealApiService.downloadCallback = downloadCallback;
        randomMealTask = new RandomMealApiService.RandomMealTask();
        urlString = url;
        randomMealTask.execute(urlString);
    }

    public static void cancelRandomMealDownload() {
        if (randomMealTask != null) {
            randomMealTask.cancel(true);
            randomMealTask = null;
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

    private static RecipeModel readRandomMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRandomRecipeData(reader);
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
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient1(currentValue);
                    }
                    break;
                case "strIngredient2":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient2(currentValue);
                    }
                    break;
                case "strIngredient3":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient3(currentValue);
                    }
                    break;
                case "strIngredient4":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient4(currentValue);
                    }
                    break;
                case "strIngredient5":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient5(currentValue);
                    }
                    break;
                case "strIngredient6":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient6(currentValue);
                    }
                    break;
                case "strIngredient7":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient7(currentValue);
                    }
                    break;
                case "strIngredient8":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient8(currentValue);
                    }
                    break;
                case "strIngredient9":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient9(currentValue);
                    }
                    break;
                case "strIngredient10":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient10(currentValue);
                    }
                    break;
                case "strIngredient11":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient11(currentValue);
                    }
                    break;
                case "strIngredient12":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient12(currentValue);
                    }
                    break;
                case "strIngredient13":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient13(currentValue);
                    }
                    break;
                case "strIngredient14":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient14(currentValue);
                    }
                    break;
                case "strIngredient15":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient15(currentValue);
                    }
                    break;
                case "strIngredient16":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient16(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strIngredient17":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient17(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;

                case "strIngredient18":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient18(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strIngredient19":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient19(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strIngredient20":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient20(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strMeasure1":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure1(currentValue);
                    }
                    break;
                case "strMeasure2":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure2(currentValue);
                    }
                    break;
                case "strMeasure3":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure3(currentValue);
                    }
                    break;
                case "strMeasure4":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure4(currentValue);
                    }
                    break;
                case "strMeasure5":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure5(currentValue);
                    }
                    break;
                case "strMeasure6":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure6(currentValue);
                    }
                    break;
                case "strMeasure7":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure7(currentValue);
                    }
                    break;
                case "strMeasure8":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure8(currentValue);
                    }
                    break;
                case "strMeasure9":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure9(currentValue);
                    }
                    break;
                case "strMeasure10":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure10(currentValue);
                    }
                    break;
                case "strMeasure11":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure11(currentValue);
                    }
                    break;
                case "strMeasure12":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure12(currentValue);
                    }
                    break;
                case "strMeasure13":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure13(currentValue);
                    }
                    break;
                case "strMeasure14":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure14(currentValue);
                    }
                    break;
                case "strMeasure15":
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure15(currentValue);
                    }
                    break;
                case "strMeasure16":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure16(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strMeasure17":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure17(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strMeasure18":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure18(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strMeasure19":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure19(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case "strMeasure20":
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure20(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
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