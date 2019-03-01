package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.network.MealTypeApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

public class MealTypePresenter implements MealTypeContract.Presenter,
        AsyncTaskResult<List<MealTypeModel>> {

    private final MealTypeContract.View view;

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
    public void loadMealType() {
        MealTypeApiService.getMealType(this, "https://www.themealdb.com/api/json/v1/1/categories.php");
    }

    @Override
    public void getMealType() {
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
    public void onError(Exception throwable) {
    }

    private void saveToDatabase(List<MealTypeModel> mealTypeModel) {
        MealTypeModelDao mealTypeModelDao = DatabaseCreator.getRecipeDatabase(App.getInstance().getApplicationContext()).mealTypeModelDao();
        AppExecutor.getInstance().execute(() -> mealTypeModelDao.insertCuisine(mealTypeModel));
    }
}