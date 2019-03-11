package com.example.pavelkovachev.recipes.converter;

import com.example.pavelkovachev.recipes.network.response.latestrecipe.LatestRecipeResponse;
import com.example.pavelkovachev.recipes.network.response.randomrecipe.RandomRecipeResponse;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;

public class RecipeConverter {

    public static RecipeModel convertRandomRecipe(RandomRecipeResponse randomRecipeResponse) {

        return new RecipeModel(randomRecipeResponse.getIdMeal(), randomRecipeResponse.getStrMeal(),
                randomRecipeResponse.getStrInstructions(), randomRecipeResponse.getStrCategory(),
                randomRecipeResponse.getStrArea(), randomRecipeResponse.getStrMealThumb(),
                randomRecipeResponse.getStrIngredient1(), randomRecipeResponse.getStrIngredient2(),
                randomRecipeResponse.getStrIngredient3(), randomRecipeResponse.getStrIngredient4(),
                randomRecipeResponse.getStrIngredient5(), randomRecipeResponse.getStrIngredient6(),
                randomRecipeResponse.getStrIngredient7(), randomRecipeResponse.getStrIngredient8(),
                randomRecipeResponse.getStrIngredient9(), randomRecipeResponse.getStrIngredient10(),
                randomRecipeResponse.getStrIngredient11(), randomRecipeResponse.getStrIngredient12(),
                randomRecipeResponse.getStrIngredient13(), randomRecipeResponse.getStrIngredient14(),
                randomRecipeResponse.getStrIngredient15(), randomRecipeResponse.getStrIngredient16(),
                randomRecipeResponse.getStrIngredient17(), randomRecipeResponse.getStrIngredient18(),
                randomRecipeResponse.getStrIngredient19(), randomRecipeResponse.getStrIngredient20(),
                randomRecipeResponse.getStrMeasure1(), randomRecipeResponse.getStrMeasure2(),
                randomRecipeResponse.getStrMeasure3(), randomRecipeResponse.getStrMeasure4(),
                randomRecipeResponse.getStrMeasure5(), randomRecipeResponse.getStrMeasure6(),
                randomRecipeResponse.getStrMeasure7(), randomRecipeResponse.getStrMeasure8(),
                randomRecipeResponse.getStrMeasure9(), randomRecipeResponse.getStrMeasure10(),
                randomRecipeResponse.getStrMeasure11(), randomRecipeResponse.getStrMeasure12(),
                randomRecipeResponse.getStrMeasure13(), randomRecipeResponse.getStrMeasure14(),
                randomRecipeResponse.getStrMeasure15(), randomRecipeResponse.getStrMeasure16(),
                randomRecipeResponse.getStrMeasure17(), randomRecipeResponse.getStrMeasure18(),
                randomRecipeResponse.getStrMeasure19(), randomRecipeResponse.getStrMeasure20());
    }

    public static RecipeModel convertLatestRecipe(LatestRecipeResponse latestRecipeResponse) {

        return new RecipeModel(latestRecipeResponse.getIdMeal(), latestRecipeResponse.getStrMeal(),
                latestRecipeResponse.getStrInstructions(), latestRecipeResponse.getStrCategory(),
                latestRecipeResponse.getStrArea(), latestRecipeResponse.getStrMealThumb(),
                latestRecipeResponse.getStrIngredient1(), latestRecipeResponse.getStrIngredient2(),
                latestRecipeResponse.getStrIngredient3(), latestRecipeResponse.getStrIngredient4(),
                latestRecipeResponse.getStrIngredient5(), latestRecipeResponse.getStrIngredient6(),
                latestRecipeResponse.getStrIngredient7(), latestRecipeResponse.getStrIngredient8(),
                latestRecipeResponse.getStrIngredient9(), latestRecipeResponse.getStrIngredient10(),
                latestRecipeResponse.getStrIngredient11(), latestRecipeResponse.getStrIngredient12(),
                latestRecipeResponse.getStrIngredient13(), latestRecipeResponse.getStrIngredient14(),
                latestRecipeResponse.getStrIngredient15(), latestRecipeResponse.getStrIngredient16(),
                latestRecipeResponse.getStrIngredient17(), latestRecipeResponse.getStrIngredient18(),
                latestRecipeResponse.getStrIngredient19(), latestRecipeResponse.getStrIngredient20(),
                latestRecipeResponse.getStrMeasure1(), latestRecipeResponse.getStrMeasure2(),
                latestRecipeResponse.getStrMeasure3(), latestRecipeResponse.getStrMeasure4(),
                latestRecipeResponse.getStrMeasure5(), latestRecipeResponse.getStrMeasure6(),
                latestRecipeResponse.getStrMeasure7(), latestRecipeResponse.getStrMeasure8(),
                latestRecipeResponse.getStrMeasure9(), latestRecipeResponse.getStrMeasure10(),
                latestRecipeResponse.getStrMeasure11(), latestRecipeResponse.getStrMeasure12(),
                latestRecipeResponse.getStrMeasure13(), latestRecipeResponse.getStrMeasure14(),
                latestRecipeResponse.getStrMeasure15(), latestRecipeResponse.getStrMeasure16(),
                latestRecipeResponse.getStrMeasure17(), latestRecipeResponse.getStrMeasure18(),
                latestRecipeResponse.getStrMeasure19(), latestRecipeResponse.getStrMeasure20());
    }
}