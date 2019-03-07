package com.example.pavelkovachev.recipes.network;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class RecipeListApiService {
    private static String urlString;
    private static RecipeListTask recipeListTask;
    private static RecipesListContract.Presenter recipeListContract;

    public static void getRecipeList(RecipesListContract.Presenter contract, String url) {
        cancelRecipeListDownload();
        RecipeListApiService.recipeListContract = contract;
        recipeListTask = new RecipeListTask();
        urlString = url;
        recipeListTask.execute(urlString);
    }

    public static void cancelRecipeListDownload() {
        if (recipeListTask != null) {
            recipeListTask.cancel(true);
            recipeListTask = null;
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
            if (recipeListModels != null && recipeListContract != null) {
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

    private static List<RecipeListModel> readRecipeListStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRecipeListData(reader);
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

    private static List<RecipeListModel> readRecipeListFields(JsonReader reader) throws IOException {
        List<RecipeListModel> recipeListModelList = new ArrayList<>();
        RecipeListModel recipeListModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strMeal":
                   // recipeListModel = new RecipeListModel();
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
}