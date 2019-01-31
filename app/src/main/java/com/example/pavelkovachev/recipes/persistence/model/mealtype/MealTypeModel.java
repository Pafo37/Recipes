package com.example.pavelkovachev.recipes.persistence.model.mealtype;

public class MealTypeModel {
    private String title;
    private String description;
    private int imgMeal;

    public MealTypeModel(String title, String description, int imgMeal) {
        this.title = title;
        this.description = description;
        this.imgMeal = imgMeal;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImgMeal() {
        return imgMeal;
    }
}
