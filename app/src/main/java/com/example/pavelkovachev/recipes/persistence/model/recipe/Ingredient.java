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
        if (recipeModel.getRecipeIngredient1() != null && recipeModel.getRecipeMeasure1() != null
                && !recipeModel.getRecipeIngredient1().isEmpty() && !recipeModel.getRecipeMeasure1().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure1(), recipeModel.getRecipeIngredient1()));
        }
        if (recipeModel.getRecipeIngredient2() != null && recipeModel.getRecipeMeasure2() != null
                && !recipeModel.getRecipeIngredient2().isEmpty() && !recipeModel.getRecipeMeasure2().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure2(), recipeModel.getRecipeIngredient2()));
        }
        if (recipeModel.getRecipeIngredient3() != null && recipeModel.getRecipeMeasure3() != null
                && !recipeModel.getRecipeIngredient3().isEmpty() && !recipeModel.getRecipeMeasure3().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure3(), recipeModel.getRecipeIngredient3()));
        }
        if (recipeModel.getRecipeIngredient4() != null && recipeModel.getRecipeMeasure4() != null
                && !recipeModel.getRecipeIngredient4().isEmpty() && !recipeModel.getRecipeMeasure4().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure4(), recipeModel.getRecipeIngredient4()));
        }
        if (recipeModel.getRecipeIngredient5() != null && recipeModel.getRecipeMeasure5() != null
                && !recipeModel.getRecipeIngredient5().isEmpty() && !recipeModel.getRecipeMeasure5().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure5(), recipeModel.getRecipeIngredient5()));
        }
        if (recipeModel.getRecipeIngredient6() != null && recipeModel.getRecipeMeasure6() != null
                && !recipeModel.getRecipeIngredient6().isEmpty() && !recipeModel.getRecipeMeasure6().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure6(), recipeModel.getRecipeIngredient6()));
        }
        if (recipeModel.getRecipeIngredient7() != null && recipeModel.getRecipeMeasure7() != null
                && !recipeModel.getRecipeIngredient7().isEmpty() && !recipeModel.getRecipeMeasure7().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure7(), recipeModel.getRecipeIngredient7()));
        }
        if (recipeModel.getRecipeIngredient8() != null && recipeModel.getRecipeMeasure8() != null
                && !recipeModel.getRecipeIngredient8().isEmpty() && !recipeModel.getRecipeMeasure8().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure8(), recipeModel.getRecipeIngredient8()));
        }
        if (recipeModel.getRecipeIngredient9() != null && recipeModel.getRecipeMeasure9() != null
                && !recipeModel.getRecipeIngredient9().isEmpty() && !recipeModel.getRecipeMeasure9().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure9(), recipeModel.getRecipeIngredient9()));
        }
        if (recipeModel.getRecipeIngredient10() != null && recipeModel.getRecipeMeasure10() != null
                && !recipeModel.getRecipeIngredient10().isEmpty() && !recipeModel.getRecipeMeasure10().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure10(), recipeModel.getRecipeIngredient10()));
        }
        if (recipeModel.getRecipeIngredient11() != null && recipeModel.getRecipeMeasure11() != null
                && !recipeModel.getRecipeIngredient11().isEmpty() && !recipeModel.getRecipeMeasure11().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure11(), recipeModel.getRecipeIngredient11()));
        }
        if (recipeModel.getRecipeIngredient12() != null && recipeModel.getRecipeMeasure12() != null
                && !recipeModel.getRecipeIngredient12().isEmpty() && !recipeModel.getRecipeMeasure12().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure12(), recipeModel.getRecipeIngredient12()));
        }
        if (recipeModel.getRecipeIngredient13() != null && recipeModel.getRecipeMeasure13() != null
                && !recipeModel.getRecipeIngredient13().isEmpty() && !recipeModel.getRecipeMeasure13().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure13(), recipeModel.getRecipeIngredient13()));
        }
        if (recipeModel.getRecipeIngredient14() != null && recipeModel.getRecipeMeasure14() != null
                && !recipeModel.getRecipeIngredient14().isEmpty() && !recipeModel.getRecipeMeasure14().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure14(), recipeModel.getRecipeIngredient14()));
        }
        if (recipeModel.getRecipeIngredient15() != null && recipeModel.getRecipeMeasure15() != null
                && !recipeModel.getRecipeIngredient15().isEmpty() && !recipeModel.getRecipeMeasure15().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure15(), recipeModel.getRecipeIngredient15()));
        }
        if (recipeModel.getRecipeIngredient16() != null && recipeModel.getRecipeMeasure16() != null
                && !recipeModel.getRecipeIngredient16().isEmpty() && !recipeModel.getRecipeMeasure16().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure16(), recipeModel.getRecipeIngredient16()));
        }
        if (recipeModel.getRecipeIngredient17() != null && recipeModel.getRecipeMeasure17() != null
                && !recipeModel.getRecipeIngredient17().isEmpty() && !recipeModel.getRecipeMeasure17().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure17(), recipeModel.getRecipeIngredient17()));
        }
        if (recipeModel.getRecipeIngredient18() != null && recipeModel.getRecipeMeasure18() != null
                && !recipeModel.getRecipeIngredient18().isEmpty() && !recipeModel.getRecipeMeasure18().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure18(), recipeModel.getRecipeIngredient18()));
        }
        if (recipeModel.getRecipeIngredient19() != null && recipeModel.getRecipeMeasure19() != null
                && !recipeModel.getRecipeIngredient19().isEmpty() && !recipeModel.getRecipeMeasure19().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure19(), recipeModel.getRecipeIngredient19()));
        }
        if (recipeModel.getRecipeIngredient20() != null && recipeModel.getRecipeMeasure20() != null
                && !recipeModel.getRecipeIngredient20().isEmpty() && !recipeModel.getRecipeMeasure20().isEmpty()) {
            result.add(new Ingredient(recipeModel.getRecipeMeasure20(), recipeModel.getRecipeIngredient20()));
        }
        return result;
    }
}