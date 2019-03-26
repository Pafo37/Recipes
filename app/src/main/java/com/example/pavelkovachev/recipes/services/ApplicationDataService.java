package com.example.pavelkovachev.recipes.services;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationDataService {

    private final CuisineService cuisineService;
    private final MealTypeService mealTypeService;
    private final RecipeService recipeService;

    @Inject
    public ApplicationDataService(CuisineService cuisineService, MealTypeService mealTypeService, RecipeService recipeService) {
        this.cuisineService = cuisineService;
        this.mealTypeService = mealTypeService;
        this.recipeService = recipeService;
    }

    public CuisineService getCuisineService() {
        return cuisineService;
    }

    public MealTypeService getMealTypeService() {
        return mealTypeService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }
}