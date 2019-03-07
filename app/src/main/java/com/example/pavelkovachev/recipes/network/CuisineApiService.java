package com.example.pavelkovachev.recipes.network;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.pavelkovachev.recipes.persistence.model.cuisine.CuisineModel;
import com.example.pavelkovachev.recipes.presenters.cuisine.CuisineContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class CuisineApiService {
    private static CuisineContract.Presenter cuisineContract;
    private static CuisineTask cuisineTask;
    private static String urlString;

    public static void getCuisine(CuisineContract.Presenter contract, String url) {
        cancelCuisineDownload();
        CuisineApiService.cuisineContract = contract;
        cuisineTask = new CuisineTask();
        urlString = url;
        cuisineTask.execute(urlString);
    }

    private static void cancelCuisineDownload() {
        if (cuisineTask != null) {
            cuisineTask.cancel(true);
            cuisineTask = null;
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

    private static List<CuisineModel> readCuisineFields(JsonReader reader) throws IOException {
        List<CuisineModel> cuisineModelList = new ArrayList<>();
        CuisineModel cuisineModel = null;
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case "strArea":
                  //  cuisineModel = new CuisineModel();
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
}