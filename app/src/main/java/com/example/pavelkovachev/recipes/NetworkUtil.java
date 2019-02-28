package com.example.pavelkovachev.recipes;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisineContract;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypeContract;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtil {
    private static DownloadCallback downloadCallback;
    private static MealTypeContract.Presenter mealTypeContract;
    private static CuisineContract.Presenter cuisineContract;
    private static RecipesListContract.Presenter recipeListContract;
    private static RandomMealTask downloadTask;
    private static LatestMealTask latestMealTask;
    private static CuisineTask cuisineTask;
    private static MealTypeTask mealTypeTask;
    private static RecipeListTask recipeListTask;
    private static String urlString;
    private static String currentValue;

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

    public static void getCuisine(CuisineContract.Presenter contract, String url) {
        cancelCuisineDownload();
        NetworkUtil.cuisineContract = contract;
        cuisineTask = new CuisineTask();
        urlString = url;
        cuisineTask.execute(urlString);
    }

    public static void getMealType(MealTypeContract.Presenter contract, String url) {
        cancelMealTypeDownload();
        NetworkUtil.mealTypeContract = contract;
        mealTypeTask = new MealTypeTask();
        urlString = url;
        mealTypeTask.execute(urlString);
    }

    public static void getRecipeList(RecipesListContract.Presenter contract, String url) {
        cancelRecipeListDownload();
        NetworkUtil.recipeListContract = contract;
        recipeListTask = new RecipeListTask();
        urlString = url;
        recipeListTask.execute(urlString);
    }

    public static void cancelRandomMealDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    public static void cancelRecipeListDownload() {
        if (recipeListTask != null) {
            recipeListTask.cancel(true);
            recipeListTask = null;
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

    private static void cancelMealTypeDownload() {
        if (mealTypeTask != null) {
            mealTypeTask.cancel(true);
            mealTypeTask = null;
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
//                    downloadCallback.showRandomMealResult(null);
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
            //downloadCallback.finishDownloading(recipeModel);
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
        }
    }

    private static class CuisineTask extends AsyncTask<String, Integer, List<CuisineModel>> {

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
            if (cuisineModel != null && cuisineContract != null) {
                cuisineContract.showCuisineResult(cuisineModel);
            }
        }
    }

    private static class MealTypeTask extends AsyncTask<String, Integer, List<MealTypeModel>> {

        @Override
        protected List<MealTypeModel> doInBackground(String... urls) {
            List<MealTypeModel> mealTypeModelList = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    mealTypeModelList = mealTypeConnection(url);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return mealTypeModelList;
        }

        @Override
        protected void onPostExecute(List<MealTypeModel> mealTypeModel) {
            if (mealTypeModel != null && downloadCallback != null) {
                mealTypeContract.showMealTypeResult(mealTypeModel);
            }
        }
    }

    private static class RecipeListTask extends AsyncTask<String, Integer, List<RecipeListModel>> {

        @Override
        protected List<RecipeListModel> doInBackground(String... urls) {
            List<RecipeListModel> recipeListModelList = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    recipeListModelList = recipeListConnection(url);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return recipeListModelList;
        }

        @Override
        protected void onPostExecute(List<RecipeListModel> recipeListModels) {
            if (recipeListModels != null && downloadCallback != null) {
                recipeListContract.showRecipeListResult(recipeListModels);
            }
        }
    }

    private static List<RecipeListModel> recipeListConnection(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        List<RecipeListModel> recipeListModelList = null;
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
                recipeListModelList = readRecipeListStream(stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return recipeListModelList;
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

    private static List<MealTypeModel> mealTypeConnection(URL url) throws IOException {
        List<MealTypeModel> mealTypeModelList = null;
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
                mealTypeModelList = readMealTypeStream(stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return mealTypeModelList;
    }

    private static List<RecipeListModel> readRecipeListStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRecipeListData(reader);
    }

    private static List<CuisineModel> readCuisineStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readCuisineData(reader);
    }

    private static List<MealTypeModel> readMealTypeStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readMealTypeData(reader);
    }

    private static RecipeModel readRandomMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRandomRecipeData(reader);
    }

    private static RecipeModel readLatestMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readLatestRecipeData(reader);
    }

    private static List<RecipeListModel> readRecipeListData(JsonReader reader) throws IOException {
        List<RecipeListModel> recipeListModelList = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "meals":
                    recipeListModelList = readRecipeListFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return recipeListModelList;
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

    private static List<MealTypeModel> readMealTypeData(JsonReader reader) throws IOException {
        List<MealTypeModel> mealTypeModelList = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "categories":
                    mealTypeModelList = readMealTypeFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return mealTypeModelList;
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

    private static List<RecipeListModel> readRecipeListFields(JsonReader reader) throws IOException {
        List<RecipeListModel> recipeListModelList = new ArrayList<>();
        RecipeListModel recipeListModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strMeal":
                    recipeListModel = new RecipeListModel();
                    recipeListModel.setRecipeName(reader.nextString());
                    break;
                case "strMealThumb":
                    recipeListModel.setRecipeImage(reader.nextString());
                    break;
                case "idMeal":
                    recipeListModel.setRecipeId(reader.nextString());
                    break;
                default:
                    reader.skipValue();
            }
            if (!reader.hasNext()) {
                recipeListModelList.add(recipeListModel);
                reader.endObject();
                if (reader.hasNext()) {
                    reader.beginObject();
                }
            }
        }
        reader.endArray();
        return recipeListModelList;
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

    private static List<CuisineModel> readCuisineFields(JsonReader reader) throws IOException {
        List<CuisineModel> cuisineModelList = new ArrayList<>();
        CuisineModel cuisineModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strArea":
                    cuisineModel = new CuisineModel();
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

    private static List<MealTypeModel> readMealTypeFields(JsonReader reader) throws IOException {
        List<MealTypeModel> mealTypeModelList = new ArrayList<>();
        MealTypeModel mealTypeModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();

            switch (token) {
                case "strCategory":
                    mealTypeModel = new MealTypeModel();
                    mealTypeModel.setTitle(reader.nextString());
                    break;
                case "strCategoryThumb":
                    mealTypeModel.setImage(reader.nextString());
                    break;
                case "strCategoryDescription":
                    mealTypeModel.setDescription(reader.nextString());
                    break;
                default:
                    reader.skipValue();
            }
            if (!reader.hasNext()) {
                mealTypeModelList.add(mealTypeModel);
                reader.endObject();
                if (reader.hasNext()) {
                    reader.beginObject();
                }
            }
        }
        reader.endArray();
        return mealTypeModelList;
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
        return recipeModel;
    }
}