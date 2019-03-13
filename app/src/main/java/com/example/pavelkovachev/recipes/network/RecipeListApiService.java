package com.example.pavelkovachev.recipes.network;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

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

    private String urlString;
    private RecipeListTask recipeListTask;
    private RecipesListContract.Presenter recipeListContract;
    private static final String MEALS_KEY = "meals";
    private static final String NAME_KEY = "strMeal";
    private static final String ID_KEY = "idMeal";
    private static final String IMAGE_KEY = "strMealThumb";

    public void getRecipeList(RecipesListContract.Presenter contract, String url) {
        cancelRecipeListDownload();
        this.recipeListContract = contract;
        this.recipeListTask = new RecipeListTask();
        this.urlString = url;
        recipeListTask.execute(urlString);
    }

    private void cancelRecipeListDownload() {
        if (recipeListTask != null) {
            recipeListTask.cancel(true);
            recipeListTask = null;
        }
    }

    private class RecipeListTask extends AsyncTask<String, Integer, List<RecipeListModel>> {

        @Override
        protected List<RecipeListModel> doInBackground(String... urls) {
            List<RecipeListModel> recipeListModelList = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    recipeListModelList = recipeListConnection(url);

                } catch (IOException e) {
                    Log.e("Error", e.getMessage());
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

    private List<RecipeListModel> recipeListConnection(URL url) throws IOException {
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

    private List<RecipeListModel> readRecipeListStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRecipeListData(reader);
    }

    private List<RecipeListModel> readRecipeListData(JsonReader reader) throws IOException {
        List<RecipeListModel> recipeListModelList = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case MEALS_KEY:
                    recipeListModelList = readRecipeListFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return recipeListModelList;
    }

    private List<RecipeListModel> readRecipeListFields(JsonReader reader) throws IOException {
        List<RecipeListModel> recipeListModelList = new ArrayList<>();
        RecipeListModel recipeListModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case NAME_KEY:
                    recipeListModel = new RecipeListModel();
                    recipeListModel.setRecipeName(reader.nextString());
                    break;
                case IMAGE_KEY:
                    recipeListModel.setRecipeImage(reader.nextString());
                    break;
                case ID_KEY:
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