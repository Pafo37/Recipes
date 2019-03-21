package com.example.pavelkovachev.recipes.presenters.mealtype;

import com.annimon.stream.Stream;
import com.example.pavelkovachev.recipes.App;
import com.example.pavelkovachev.recipes.converter.MealTypeConverter;
import com.example.pavelkovachev.recipes.dagger.component.AppComponent;
import com.example.pavelkovachev.recipes.network.RecipeApiService;
import com.example.pavelkovachev.recipes.network.callback.MealTypeCallback;
import com.example.pavelkovachev.recipes.network.response.mealtype.MealTypeListResponses;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MealTypePresenter implements MealTypeContract.Presenter,
        AsyncTaskResult<List<MealTypeModel>>, MealTypeCallback {

    @Inject
    ApplicationDataService dataService;
    AppComponent appComponent;
    private final MealTypeContract.View view;
    private List<MealTypeModel> mealTypeModelList = new ArrayList<>();

    public MealTypePresenter(MealTypeContract.View view) {
        this.view = view;
        view.setPresenter(this);
        appComponent = App.getInstance().getAppComponent();
        appComponent.inject(this);
    }

    @Override
    public void loadMealTypeFromApi() {
        RecipeApiService recipeApiService = RecipeApiService.getRecipeApiService();
        recipeApiService.getMealTypes(this);
    }

    @Override
    public void loadMealTypeFromDb() {
        dataService.getMealTypeService().getAllMealTypes(this);
    }

    @Override
    public List<MealTypeModel> getMealTypeList() {
        return mealTypeModelList;
    }

    @Override
    public void onSuccess(List<MealTypeModel> result) {
        if (view != null) {
            if (result.size() == 0) {
                loadMealTypeFromApi();
            } else {
                getMealTypeList().addAll(result);
                view.showMealTypeFromDb(result);
            }
        }
    }

    @Override
    public void onError() {
        view.onError();
    }

    @Override
    public void onSuccessMealTypes(MealTypeListResponses mealTypesResponses) {
        Stream.of(mealTypesResponses.getCategories()).forEach(
                mealTypeModel ->
                        mealTypeModelList.add(MealTypeConverter.convertToMealType(mealTypeModel)));
        view.showMealTypesFromApi(mealTypeModelList);
    }

    @Override
    public void onErrorMealType() {
        view.onError();
    }

}