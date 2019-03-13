package com.example.pavelkovachev.recipes.network;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypeContract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MealTypeApiService {
    private MealTypeTask mealTypeTask;
    private MealTypeContract.Presenter mealTypeContract;
    private String urlString;
    private static final String CATEGORIES_KEY = "categories";

    public void getMealType(MealTypeContract.Presenter contract, String url) {
        cancelMealTypeDownload();
        this.mealTypeContract = contract;
        this.mealTypeTask = new MealTypeTask();
        this.urlString = url;
        mealTypeTask.execute(urlString);
    }

    private void cancelMealTypeDownload() {
        if (mealTypeTask != null) {
            mealTypeTask.cancel(true);
            mealTypeTask = null;
        }
    }

    private class MealTypeTask extends AsyncTask<String, Integer, List<MealTypeModel>> {

        @Override
        protected List<MealTypeModel> doInBackground(String... urls) {
            List<MealTypeModel> mealTypeModelList = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    mealTypeModelList = mealTypeConnection(url);

                } catch (IOException e) {
                    Log.e("Error", e.getMessage());
                }
            }
            return mealTypeModelList;
        }

        @Override
        protected void onPostExecute(List<MealTypeModel> mealTypeModel) {
            if (mealTypeModel != null && mealTypeContract != null) {
                mealTypeContract.showMealTypeResult(mealTypeModel);
            }
        }
    }

    private List<MealTypeModel> mealTypeConnection(URL url) throws IOException {
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

    private List<MealTypeModel> readMealTypeStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readMealTypeData(reader);
    }

    private List<MealTypeModel> readMealTypeData(JsonReader reader) throws IOException {
        List<MealTypeModel> mealTypeModelList = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case CATEGORIES_KEY:
                    mealTypeModelList = readMealTypeFields(reader);
                    break;
                default:
                    reader.skipValue();
            }
        }
        reader.endObject();
        return mealTypeModelList;
    }

    private List<MealTypeModel> readMealTypeFields(JsonReader reader) throws IOException {
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
}