package com.example.pavelkovachev.recipes.persistence.model.myrecipes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class MyRecipesModel implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String recipeName;
    private String recipeInstructions;
    private String recipeIngredients;
    private String recipeImage;

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public MyRecipesModel(String recipeName, String recipeInstructions, String recipeIngredients, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
        this.recipeImage = recipeImage;
    }

    @Ignore
    private MyRecipesModel(Parcel parcel) {
        this.recipeName = parcel.readString();
        this.recipeInstructions = parcel.readString();
        this.recipeIngredients = parcel.readString();
        this.recipeImage = parcel.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
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

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public int describeContents() {
        int result = 1;
        result ^= recipeName.hashCode();
        result ^= recipeImage.hashCode();
        result ^= recipeIngredients.hashCode();
        result ^= recipeInstructions.hashCode();
        return result;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(recipeName);
        parcel.writeString(recipeImage);
        parcel.writeString(recipeIngredients);
        parcel.writeString(recipeInstructions);
    }

    public static final Parcelable.Creator<MyRecipesModel> CREATOR = new Parcelable.Creator<MyRecipesModel>() {

        @Override
        public MyRecipesModel createFromParcel(Parcel source) {
            return new MyRecipesModel(source);
        }

        @Override
        public MyRecipesModel[] newArray(int size) {
            return new MyRecipesModel[0];
        }
    };
}