package com.example.pavelkovachev.recipes.persistence.model.recipe;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String measures;
    private String ingredient;

    private Ingredient(String measures, String ingredient) {
        this.measures = measures;
        this.ingredient = ingredient;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public static List<Ingredient> convertFromRecipeToList(RecipeModel recipeModel) {
        List<Ingredient> result = new ArrayList<>();

        if (isIngredientEmpty(recipeModel.getRecipeIngredient1(), recipeModel.getRecipeMeasure1())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure1(), recipeModel.getRecipeIngredient1()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient2(), recipeModel.getRecipeMeasure2())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure2(), recipeModel.getRecipeIngredient2()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient3(), recipeModel.getRecipeMeasure3())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure3(), recipeModel.getRecipeIngredient3()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient4(), recipeModel.getRecipeMeasure4())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure4(), recipeModel.getRecipeIngredient4()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient5(), recipeModel.getRecipeMeasure5())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure5(), recipeModel.getRecipeIngredient5()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient6(), recipeModel.getRecipeMeasure6())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure6(), recipeModel.getRecipeIngredient6()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient7(), recipeModel.getRecipeMeasure7())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure7(), recipeModel.getRecipeIngredient7()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient8(), recipeModel.getRecipeMeasure8())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure8(), recipeModel.getRecipeIngredient8()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient9(), recipeModel.getRecipeMeasure9())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure9(), recipeModel.getRecipeIngredient9()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient10(), recipeModel.getRecipeMeasure10())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure10(), recipeModel.getRecipeIngredient10()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient11(), recipeModel.getRecipeMeasure11())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure11(), recipeModel.getRecipeIngredient11()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient12(), recipeModel.getRecipeMeasure12())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure12(), recipeModel.getRecipeIngredient12()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient13(), recipeModel.getRecipeMeasure13())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure13(), recipeModel.getRecipeIngredient13()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient14(), recipeModel.getRecipeMeasure14())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure14(), recipeModel.getRecipeIngredient14()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient15(), recipeModel.getRecipeMeasure15())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure15(), recipeModel.getRecipeIngredient15()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient16(), recipeModel.getRecipeMeasure16())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure16(), recipeModel.getRecipeIngredient16()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient17(), recipeModel.getRecipeMeasure17())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure17(), recipeModel.getRecipeIngredient17()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient18(), recipeModel.getRecipeMeasure18())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure18(), recipeModel.getRecipeIngredient18()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient19(), recipeModel.getRecipeMeasure19())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure19(), recipeModel.getRecipeIngredient19()));
        }
        if (isIngredientEmpty(recipeModel.getRecipeIngredient20(), recipeModel.getRecipeMeasure20())) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure20(), recipeModel.getRecipeIngredient20()));
        }
        return result;
    }

    private static boolean isIngredientEmpty(String ingredient, String measure) {
        return ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty();
    }
}