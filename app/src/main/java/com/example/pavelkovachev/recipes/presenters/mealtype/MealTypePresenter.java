package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.BuildConfig;
import com.example.pavelkovachev.recipes.network.MealTypeApiService;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

public class MealTypePresenter implements MealTypeContract.Presenter,
        AsyncTaskResult<List<MealTypeModel>> {

    private final MealTypeContract.View view;
    private List<MealTypeModel> mealTypeModelList = new ArrayList<>();

    public MealTypePresenter(MealTypeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void showMealTypeResult(List<MealTypeModel> result) {
        if (result != null) {
            MealTypeService.saveToDatabase(result);
            getMealTypeList().addAll(result);
            view.loadMealTypesFromApi(result);
        }
    }

    @Override
    public void loadMealType() {
        MealTypeApiService mealTypeApiService = new MealTypeApiService();
        mealTypeApiService.getMealType(this, BuildConfig.MEAL_TYPE_URL);
    }

    @Override
    public void getMealType() {
        MealTypeModelDao mealTypeModelDao = DatabaseCreator.
                getRecipeDatabase(App.getInstance().getApplicationContext()).mealTypeModelDao();
        MealTypeService mealTypeService = new MealTypeService(mealTypeModelDao);
        mealTypeService.getAllMealTypes(this);
    }

    @Override
    public List<MealTypeModel> getMealTypeList() {
        return mealTypeModelList;
    }

    @Override
    public void onSuccess(List<MealTypeModel> result) {
        if (view != null) {
            if (result.size() == 0) {
                loadMealType();
            } else {
                getMealTypeList().addAll(result);
                view.showMealTypeFromDb(result);
            }
        }
    }

    @Override
    public void onError(Exception throwable) {
    }
}