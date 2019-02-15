package com.example.pavelkovachev.recipes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.JsonReader;

import com.example.pavelkovachev.recipes.persistence.database.model.RecipeModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkFragment extends Fragment {
    private static final String TAG = "NetworkFragment";
    private static final String URL_KEY = "UrlKey";

    private DownloadCallback downloadCallback;
    private DownloadTask downloadTask;
    private String urlString;

    public static NetworkFragment getInstance(FragmentManager fragmentManager, String url) {
        NetworkFragment networkFragment = (NetworkFragment) fragmentManager.findFragmentByTag(NetworkFragment.TAG);
        if (networkFragment == null) {
            networkFragment = new NetworkFragment();
            Bundle args = new Bundle();
            args.putString(URL_KEY, url);
            networkFragment.setArguments(args);
            fragmentManager.beginTransaction().add(networkFragment, TAG).commit();
        }
        return networkFragment;
    }

    public void startDownload() {
        cancelDownload();
        downloadTask = new DownloadTask();
        downloadTask.execute(urlString);
    }

    public void cancelDownload() {
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        urlString = getArguments().getString(URL_KEY);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        downloadCallback = (DownloadCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        downloadCallback = null;
    }

    @Override
    public void onDestroy() {
        cancelDownload();
        super.onDestroy();
    }

    private class DownloadTask extends AsyncTask<String, Integer, RecipeModel> {

        @Override
        protected void onPreExecute() {
            if (downloadCallback != null) {
                NetworkInfo networkInfo = downloadCallback.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() ||
                        (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
                    downloadCallback.updateFromDownload(null);
                    cancel(true);
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
            downloadCallback.finishDownloading();
        }
    }

    private RecipeModel downloadUrl(URL url) throws IOException {
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

    private String readStream(InputStream stream) throws IOException {
        String result = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String input = "";
        while ((input = bufferedReader.readLine()) != null) {
            result += input;
        }
        return result;
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