package com.example.pavelkovachev.recipes.persistence.model.cuisine;

import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public interface CuisineRepository {

    void insertCuisine(List<CuisineModel> cuisineModel);

    void deleteCuisine(CuisineModel cuisineModel);

    void getAllCuisines(AsyncTaskResult result);
}