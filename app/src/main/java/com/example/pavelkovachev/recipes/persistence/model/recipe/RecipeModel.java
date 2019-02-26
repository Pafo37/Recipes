package com.example.pavelkovachev.recipes.persistence.model.recipe;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RecipeModel {

    @NonNull
    @PrimaryKey()
    private String id;

    @ColumnInfo(name = "name")
    private String recipeName;

    @ColumnInfo(name = "instructions")
    private String recipeInstructions;

    @ColumnInfo(name = "mealtype")
    private String recipeMealType;

    @ColumnInfo(name = "cuisine")
    private String recipeCuisine;

    @ColumnInfo(name = "recipeImage")
    public String recipeImage;

    @ColumnInfo(name = "ingredient1")
    private String recipeIngredient1;

    @ColumnInfo(name = "ingredient2")
    private String recipeIngredient2;

    @ColumnInfo(name = "ingredient3")
    private String recipeIngredient3;

    @ColumnInfo(name = "ingredient4")
    private String recipeIngredient4;

    @ColumnInfo(name = "ingredient5")
    private String recipeIngredient5;

    @ColumnInfo(name = "ingredient6")
    private String recipeIngredient6;

    @ColumnInfo(name = "ingredient7")
    private String recipeIngredient7;

    @ColumnInfo(name = "ingredient8")
    private String recipeIngredient8;

    @ColumnInfo(name = "ingredient9")
    private String recipeIngredient9;

    @ColumnInfo(name = "ingredient10")
    private String recipeIngredient10;
    @ColumnInfo(name = "ingredient11")
    private String recipeIngredient11;

    @ColumnInfo(name = "ingredient12")
    private String recipeIngredient12;

    @ColumnInfo(name = "ingredient13")
    private String recipeIngredient13;

    @ColumnInfo(name = "ingredient14")
    private String recipeIngredient14;

    @ColumnInfo(name = "ingredient15")
    private String recipeIngredient15;

    @ColumnInfo(name = "ingredient16")
    private String recipeIngredient16;

    @ColumnInfo(name = "ingredient17")
    private String recipeIngredient17;

    @ColumnInfo(name = "ingredient18")
    private String recipeIngredient18;

    @ColumnInfo(name = "ingredient19")
    private String recipeIngredient19;

    @ColumnInfo(name = "ingredient20")
    private String recipeIngredient20;

    @ColumnInfo(name = "measure1")
    private String recipeMeasure1;

    @ColumnInfo(name = "measure2")
    private String recipeMeasure2;

    @ColumnInfo(name = "measure3")
    private String recipeMeasure3;

    @ColumnInfo(name = "measure4")
    private String recipeMeasure4;

    @ColumnInfo(name = "measure5")
    private String recipeMeasure5;

    @ColumnInfo(name = "measure6")
    private String recipeMeasure6;

    @ColumnInfo(name = "measure7")
    private String recipeMeasure7;

    @ColumnInfo(name = "measure8")
    private String recipeMeasure8;

    @ColumnInfo(name = "measure9")
    private String recipeMeasure9;

    @ColumnInfo(name = "measure10")
    private String recipeMeasure10;

    @ColumnInfo(name = "measure11")
    private String recipeMeasure11;

    @ColumnInfo(name = "measure12")
    private String recipeMeasure12;

    @ColumnInfo(name = "measure13")
    private String recipeMeasure13;

    @ColumnInfo(name = "measure14")
    private String recipeMeasure14;

    @ColumnInfo(name = "measure15")
    private String recipeMeasure15;

    @ColumnInfo(name = "measure16")
    private String recipeMeasure16;

    @ColumnInfo(name = "measure17")
    private String recipeMeasure17;

    @ColumnInfo(name = "measure18")
    private String recipeMeasure18;

    @ColumnInfo(name = "measure19")
    private String recipeMeasure19;

    @ColumnInfo(name = "measure20")
    private String recipeMeasure20;

    public String getRecipeIngredient6() {
        return recipeIngredient6;
    }

    public void setRecipeIngredient6(String recipeIngredient6) {
        this.recipeIngredient6 = recipeIngredient6;
    }

    public String getRecipeIngredient7() {
        return recipeIngredient7;
    }

    public void setRecipeIngredient7(String recipeIngredient7) {
        this.recipeIngredient7 = recipeIngredient7;
    }

    public String getRecipeIngredient8() {
        return recipeIngredient8;
    }

    public void setRecipeIngredient8(String recipeIngredient8) {
        this.recipeIngredient8 = recipeIngredient8;
    }

    public String getRecipeIngredient9() {
        return recipeIngredient9;
    }

    public void setRecipeIngredient9(String recipeIngredient9) {
        this.recipeIngredient9 = recipeIngredient9;
    }

    public String getRecipeIngredient10() {
        return recipeIngredient10;
    }

    public void setRecipeIngredient10(String recipeIngredient10) {
        this.recipeIngredient10 = recipeIngredient10;
    }

    public String getRecipeIngredient11() {
        return recipeIngredient11;
    }

    public void setRecipeIngredient11(String recipeIngredient11) {
        this.recipeIngredient11 = recipeIngredient11;
    }

    public String getRecipeIngredient12() {
        return recipeIngredient12;
    }

    public void setRecipeIngredient12(String recipeIngredient12) {
        this.recipeIngredient12 = recipeIngredient12;
    }

    public String getRecipeIngredient13() {
        return recipeIngredient13;
    }

    public void setRecipeIngredient13(String recipeIngredient13) {
        this.recipeIngredient13 = recipeIngredient13;
    }

    public String getRecipeIngredient14() {
        return recipeIngredient14;
    }

    public void setRecipeIngredient14(String recipeIngredient14) {
        this.recipeIngredient14 = recipeIngredient14;
    }

    public String getRecipeIngredient15() {
        return recipeIngredient15;
    }

    public void setRecipeIngredient15(String recipeIngredient15) {
        this.recipeIngredient15 = recipeIngredient15;
    }

    public String getRecipeIngredient16() {
        return recipeIngredient16;
    }

    public void setRecipeIngredient16(String recipeIngredient16) {
        this.recipeIngredient16 = recipeIngredient16;
    }

    public String getRecipeIngredient17() {
        return recipeIngredient17;
    }

    public void setRecipeIngredient17(String recipeIngredient17) {
        this.recipeIngredient17 = recipeIngredient17;
    }

    public String getRecipeIngredient18() {
        return recipeIngredient18;
    }

    public void setRecipeIngredient18(String recipeIngredient18) {
        this.recipeIngredient18 = recipeIngredient18;
    }

    public String getRecipeIngredient19() {
        return recipeIngredient19;
    }

    public void setRecipeIngredient19(String recipeIngredient19) {
        this.recipeIngredient19 = recipeIngredient19;
    }

    public String getRecipeIngredient20() {
        return recipeIngredient20;
    }

    public void setRecipeIngredient20(String recipeIngredient20) {
        this.recipeIngredient20 = recipeIngredient20;
    }

    public String getRecipeMeasure6() {
        return recipeMeasure6;
    }

    public void setRecipeMeasure6(String recipeMeasure6) {
        this.recipeMeasure6 = recipeMeasure6;
    }

    public String getRecipeMeasure7() {
        return recipeMeasure7;
    }

    public void setRecipeMeasure7(String recipeMeasure7) {
        this.recipeMeasure7 = recipeMeasure7;
    }

    public String getRecipeMeasure8() {
        return recipeMeasure8;
    }

    public void setRecipeMeasure8(String recipeMeasure8) {
        this.recipeMeasure8 = recipeMeasure8;
    }

    public String getRecipeMeasure9() {
        return recipeMeasure9;
    }

    public void setRecipeMeasure9(String recipeMeasure9) {
        this.recipeMeasure9 = recipeMeasure9;
    }

    public String getRecipeMeasure10() {
        return recipeMeasure10;
    }

    public void setRecipeMeasure10(String recipeMeasure10) {
        this.recipeMeasure10 = recipeMeasure10;
    }

    public String getRecipeMeasure11() {
        return recipeMeasure11;
    }

    public void setRecipeMeasure11(String recipeMeasure11) {
        this.recipeMeasure11 = recipeMeasure11;
    }

    public String getRecipeMeasure12() {
        return recipeMeasure12;
    }

    public void setRecipeMeasure12(String recipeMeasure12) {
        this.recipeMeasure12 = recipeMeasure12;
    }

    public String getRecipeMeasure13() {
        return recipeMeasure13;
    }

    public void setRecipeMeasure13(String recipeMeasure13) {
        this.recipeMeasure13 = recipeMeasure13;
    }

    public String getRecipeMeasure14() {
        return recipeMeasure14;
    }

    public void setRecipeMeasure14(String recipeMeasure14) {
        this.recipeMeasure14 = recipeMeasure14;
    }

    public String getRecipeMeasure15() {
        return recipeMeasure15;
    }

    public void setRecipeMeasure15(String recipeMeasure15) {
        this.recipeMeasure15 = recipeMeasure15;
    }

    public String getRecipeMeasure16() {
        return recipeMeasure16;
    }

    public void setRecipeMeasure16(String recipeMeasure16) {
        this.recipeMeasure16 = recipeMeasure16;
    }

    public String getRecipeMeasure17() {
        return recipeMeasure17;
    }

    public void setRecipeMeasure17(String recipeMeasure17) {
        this.recipeMeasure17 = recipeMeasure17;
    }

    public String getRecipeMeasure18() {
        return recipeMeasure18;
    }

    public void setRecipeMeasure18(String recipeMeasure18) {
        this.recipeMeasure18 = recipeMeasure18;
    }

    public String getRecipeMeasure19() {
        return recipeMeasure19;
    }

    public void setRecipeMeasure19(String recipeMeasure19) {
        this.recipeMeasure19 = recipeMeasure19;
    }

    public String getRecipeMeasure20() {
        return recipeMeasure20;
    }

    public void setRecipeMeasure20(String recipeMeasure20) {
        this.recipeMeasure20 = recipeMeasure20;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }


    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeMealType() {
        return recipeMealType;
    }

    public void setRecipeMealType(String recipeMealType) {
        this.recipeMealType = recipeMealType;
    }

    public String getRecipeCuisine() {
        return recipeCuisine;
    }

    public void setRecipeCuisine(String recipeCuisine) {
        this.recipeCuisine = recipeCuisine;
    }

    public String getRecipeIngredient1() {
        return recipeIngredient1;
    }

    public void setRecipeIngredient1(String recipeIngredient1) {
        this.recipeIngredient1 = recipeIngredient1;
    }

    public String getRecipeIngredient2() {
        return recipeIngredient2;
    }

    public void setRecipeIngredient2(String recipeIngredient2) {
        this.recipeIngredient2 = recipeIngredient2;
    }

    public String getRecipeIngredient3() {
        return recipeIngredient3;
    }

    public void setRecipeIngredient3(String recipeIngredient3) {
        this.recipeIngredient3 = recipeIngredient3;
    }

    public String getRecipeIngredient4() {
        return recipeIngredient4;
    }

    public void setRecipeIngredient4(String recipeIngredient4) {
        this.recipeIngredient4 = recipeIngredient4;
    }

    public String getRecipeIngredient5() {
        return recipeIngredient5;
    }

    public void setRecipeIngredient5(String recipeIngredient5) {
        this.recipeIngredient5 = recipeIngredient5;
    }

    public String getRecipeMeasure1() {
        return recipeMeasure1;
    }

    public void setRecipeMeasure1(String recipeMeasure1) {
        this.recipeMeasure1 = recipeMeasure1;
    }

    public String getRecipeMeasure2() {
        return recipeMeasure2;
    }

    public void setRecipeMeasure2(String recipeMeasure2) {
        this.recipeMeasure2 = recipeMeasure2;
    }

    public String getRecipeMeasure3() {
        return recipeMeasure3;
    }

    public void setRecipeMeasure3(String recipeMeasure3) {
        this.recipeMeasure3 = recipeMeasure3;
    }

    public String getRecipeMeasure4() {
        return recipeMeasure4;
    }

    public void setRecipeMeasure4(String recipeMeasure4) {
        this.recipeMeasure4 = recipeMeasure4;
    }

    public String getRecipeMeasure5() {
        return recipeMeasure5;
    }

    public void setRecipeMeasure5(String recipeMeasure5) {
        this.recipeMeasure5 = recipeMeasure5;
    }
}