package com.example.pavelkovachev.recipes.network;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.example.pavelkovachev.recipes.RecipesCallback;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RandomMealApiService {
    private RecipesCallback recipesCallback;
    private RandomMealApiService.RandomMealTask randomMealTask;
    private String currentValue;
    private String urlString;
    private static final String MEALS_KEY = "meals";
    private static final String NAME_KEY = "strMeal";
    private static final String ID_KEY = "idMeal";
    private static final String IMAGE_KEY = "strMealThumb";
    private static final String CUISINE_KEY = "strArea";
    private static final String MEAL_TYPE_KEY = "strCategory";
    private static final String INSTRUCTIONS_KEY = "strInstructions";
    private static final String INGREDIENT_1_KEY = "strIngredient1";
    private static final String INGREDIENT_2_KEY = "strIngredient2";
    private static final String INGREDIENT_3_KEY = "strIngredient3";
    private static final String INGREDIENT_4_KEY = "strIngredient4";
    private static final String INGREDIENT_5_KEY = "strIngredient5";
    private static final String INGREDIENT_6_KEY = "strIngredient6";
    private static final String INGREDIENT_7_KEY = "strIngredient7";
    private static final String INGREDIENT_8_KEY = "strIngredient8";
    private static final String INGREDIENT_9_KEY = "strIngredient9";
    private static final String INGREDIENT_10_KEY = "strIngredient10";
    private static final String INGREDIENT_11_KEY = "strIngredient11";
    private static final String INGREDIENT_12_KEY = "strIngredient12";
    private static final String INGREDIENT_13_KEY = "strIngredient13";
    private static final String INGREDIENT_14_KEY = "strIngredient14";
    private static final String INGREDIENT_15_KEY = "strIngredient15";
    private static final String INGREDIENT_16_KEY = "strIngredient16";
    private static final String INGREDIENT_17_KEY = "strIngredient17";
    private static final String INGREDIENT_18_KEY = "strIngredient18";
    private static final String INGREDIENT_19_KEY = "strIngredient19";
    private static final String INGREDIENT_20_KEY = "strIngredient20";
    private static final String MEASURE_1_KEY = "strMeasure1";
    private static final String MEASURE_2_KEY = "strMeasure2";
    private static final String MEASURE_3_KEY = "strMeasure3";
    private static final String MEASURE_4_KEY = "strMeasure4";
    private static final String MEASURE_5_KEY = "strMeasure5";
    private static final String MEASURE_6_KEY = "strMeasure6";
    private static final String MEASURE_7_KEY = "strMeasure7";
    private static final String MEASURE_8_KEY = "strMeasure8";
    private static final String MEASURE_9_KEY = "strMeasure9";
    private static final String MEASURE_10_KEY = "strMeasure10";
    private static final String MEASURE_11_KEY = "strMeasure11";
    private static final String MEASURE_12_KEY = "strMeasure12";
    private static final String MEASURE_13_KEY = "strMeasure13";
    private static final String MEASURE_14_KEY = "strMeasure14";
    private static final String MEASURE_15_KEY = "strMeasure15";
    private static final String MEASURE_16_KEY = "strMeasure16";
    private static final String MEASURE_17_KEY = "strMeasure17";
    private static final String MEASURE_18_KEY = "strMeasure18";
    private static final String MEASURE_19_KEY = "strMeasure19";
    private static final String MEASURE_20_KEY = "strMeasure20";

    public void getRandomMeal(RecipesCallback recipesCallback, String url) {
        cancelRandomMealDownload();
        this.recipesCallback = recipesCallback;
        this.randomMealTask = new RandomMealApiService.RandomMealTask();
        this.urlString = url;
        randomMealTask.execute(urlString);
    }

    private void cancelRandomMealDownload() {
        if (randomMealTask != null) {
            randomMealTask.cancel(true);
            randomMealTask = null;
        }
    }

    private class RandomMealTask extends AsyncTask<String, Integer, RecipeModel> {

        @Override
        protected RecipeModel doInBackground(String... urls) {
            RecipeModel recipeModel = null;
            if (!isCancelled() && urls != null && urls.length > 0) {
                String urlString = urls[0];
                try {
                    URL url = new URL(urlString);
                    recipeModel = randomMealConnection(url);
                } catch (IOException e) {
                    Log.e("Error", e.getMessage());
                }
            }
            return recipeModel;
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            if (recipeModel != null && recipesCallback != null) {
                recipesCallback.showRandomMealResult(recipeModel);
            }
        }
    }

    private RecipeModel randomMealConnection(URL url) throws IOException {
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

    private RecipeModel readRandomMealStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        return readRandomRecipeData(reader);
    }

    private RecipeModel readRandomRecipeData(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case MEALS_KEY:
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

    private RecipeModel readRandomRecipeFields(JsonReader reader) throws IOException {
        RecipeModel recipeModel = new RecipeModel();
        reader.beginArray();
        reader.beginObject();
        while (reader.hasNext()) {
            String token = reader.nextName();
            switch (token) {
                case ID_KEY:
                    recipeModel.setId(reader.nextString());
                    break;
                case NAME_KEY:
                    recipeModel.setRecipeName(reader.nextString());
                    break;
                case MEAL_TYPE_KEY:
                    recipeModel.setRecipeMealType(reader.nextString());
                    break;
                case CUISINE_KEY:
                    recipeModel.setRecipeCuisine(reader.nextString());
                    break;
                case INSTRUCTIONS_KEY:
                    recipeModel.setRecipeInstructions(reader.nextString());
                    break;
                case IMAGE_KEY:
                    recipeModel.setRecipeImage(reader.nextString());
                    break;
                case INGREDIENT_1_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient1(currentValue);
                    }
                    break;
                case INGREDIENT_2_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient2(currentValue);
                    }
                    break;
                case INGREDIENT_3_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient3(currentValue);
                    }
                    break;
                case INGREDIENT_4_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient4(currentValue);
                    }
                    break;
                case INGREDIENT_5_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient5(currentValue);
                    }
                    break;
                case INGREDIENT_6_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient6(currentValue);
                    }
                    break;
                case INGREDIENT_7_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient7(currentValue);
                    }
                    break;
                case INGREDIENT_8_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient8(currentValue);
                    }
                    break;
                case INGREDIENT_9_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient9(currentValue);
                    }
                    break;
                case INGREDIENT_10_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient10(currentValue);
                    }
                    break;
                case INGREDIENT_11_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient11(currentValue);
                    }
                    break;
                case INGREDIENT_12_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient12(currentValue);
                    }
                    break;
                case INGREDIENT_13_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient13(currentValue);
                    }
                    break;
                case INGREDIENT_14_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient14(currentValue);
                    }
                    break;
                case INGREDIENT_15_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeIngredient15(currentValue);
                    }
                    break;
                case INGREDIENT_16_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient16(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case INGREDIENT_17_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient17(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;

                case INGREDIENT_18_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient18(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case INGREDIENT_19_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient19(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case INGREDIENT_20_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeIngredient20(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case MEASURE_1_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure1(currentValue);
                    }
                    break;
                case MEASURE_2_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure2(currentValue);
                    }
                    break;
                case MEASURE_3_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure3(currentValue);
                    }
                    break;
                case MEASURE_4_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure4(currentValue);
                    }
                    break;
                case MEASURE_5_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure5(currentValue);
                    }
                    break;
                case MEASURE_6_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure6(currentValue);
                    }
                    break;
                case MEASURE_7_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure7(currentValue);
                    }
                    break;
                case MEASURE_8_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure8(currentValue);
                    }
                    break;
                case MEASURE_9_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure9(currentValue);
                    }
                    break;
                case MEASURE_10_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure10(currentValue);
                    }
                    break;
                case MEASURE_11_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure11(currentValue);
                    }
                    break;
                case MEASURE_12_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure12(currentValue);
                    }
                    break;
                case MEASURE_13_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure13(currentValue);
                    }
                    break;
                case MEASURE_14_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure14(currentValue);
                    }
                    break;
                case MEASURE_15_KEY:
                    currentValue = reader.nextString();
                    if (!TextUtils.isEmpty(currentValue)) {
                        recipeModel.setRecipeMeasure15(currentValue);
                    }
                    break;
                case MEASURE_16_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure16(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case MEASURE_17_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure17(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case MEASURE_18_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure18(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case MEASURE_19_KEY:
                    if (reader.peek() != JsonToken.NULL) {
                        currentValue = reader.nextString();
                        if (!TextUtils.isEmpty(currentValue)) {
                            recipeModel.setRecipeMeasure19(currentValue);
                        }
                    } else {
                        reader.nextNull();
                    }
                    break;
                case MEASURE_20_KEY:
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