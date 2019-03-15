package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.converter.MealTypeConverter;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.RecipesApiCreator;
import com.example.pavelkovachev.recipes.network.callback.MealTypeCallback;
import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeListResponses;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class MealTypePresenter implements MealTypeContract.Presenter,
        AsyncTaskResult<List<MealTypeModel>>, MealTypeCallback {

    private final MealTypeContract.View view;
    private RecipesApiCreator recipesApiCreator;
    private List<MealTypeModel> mealTypeModelList = new ArrayList<>();

    public MealTypePresenter(MealTypeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void showMealTypeResult(List<MealTypeModel> result) {
        if (result != null) {
            saveToDatabase(result);
            view.loadMealTypesFromApi(result);
        }
    }

    @Override
    public void loadMealTypeFromApi() {
        RecipeApiService recipesService = new RecipeApiService(recipesApiCreator, this);
        recipesService.getMealTypes();
    }

    @Override
    public void loadMealTypeFromDb() {
        MealTypeModelDao mealTypeModelDao = DatabaseCreator.
                getRecipeDatabase(App.getInstance().getApplicationContext()).mealTypeModelDao();
        MealTypeService mealTypeService = new MealTypeService(mealTypeModelDao);
        mealTypeService.getAllMealTypes(this);
    }

    @Override
    public void onSuccess(List<MealTypeModel> result) {
        if (view != null) {
            view.showMealTypeFromDb(result);
        }
    }

    @Override
    public void onError() {
        view.onError();
    }

    private void saveToDatabase(List<MealTypeModel> mealTypeModel) {
        MealTypeModelDao mealTypeModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).mealTypeModelDao();
        AppExecutor.getInstance().execute(() -> mealTypeModelDao.insertCuisine(mealTypeModel));
    }

    @Override
    public void onSuccessMealTypes(MealTypeListResponses mealTypesResponses) {
        Stream.of(mealTypesResponses.getCategories()).forEach(
                mealTypeModel ->
                        mealTypeModelList.add(MealTypeConverter.convertToMealType(mealTypeModel)));
        saveToDatabase(mealTypeModelList);
        view.loadMealTypesFromApi(mealTypeModelList);
    }

    @Override
    public void onErrorMealType() {
        view.onError();
    }
}