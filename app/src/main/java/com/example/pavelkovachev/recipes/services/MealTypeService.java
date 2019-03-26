package com.example.pavelkovachev.recipes.services;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModelDao;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeRepository;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MealTypeService implements MealTypeRepository {

    private final MealTypeModelDao mealTypeModelDao;
    private final AppExecutor appExecutor;

    @Inject
    public MealTypeService(final MealTypeModelDao mealTypeModelDao,
                           final AppExecutor appExecutor) {
        this.mealTypeModelDao = mealTypeModelDao;
        this.appExecutor = appExecutor;
    }

    @Override
    public void insertMealType(List<MealTypeModel> mealTypeModel) {
        appExecutor.execute(() -> mealTypeModelDao.insertCuisine(mealTypeModel));
    }

    @Override
    public void deleteMealType(MealTypeModel mealTypeModel) {
        appExecutor.execute(() -> mealTypeModelDao.deleteCuisine(mealTypeModel));
    }

    @Override
    public void getAllMealTypes(AsyncTaskResult result) {
        new GetAllMealTypesAsyncTask(result).execute();
    }

    private class GetAllMealTypesAsyncTask extends AsyncTask<Void, Void, List<MealTypeModel>> {

        private AsyncTaskResult<List<MealTypeModel>> asyncTaskResult;

        private GetAllMealTypesAsyncTask(AsyncTaskResult<List<MealTypeModel>> asyncTaskResult) {
            this.asyncTaskResult = asyncTaskResult;
        }

        @Override
        protected List<MealTypeModel> doInBackground(Void... voids) {
            return mealTypeModelDao.getAllMealTypes();
        }

        @Override
        protected void onPostExecute(List<MealTypeModel> mealTypeModel) {
            super.onPostExecute(mealTypeModel);
            if (asyncTaskResult != null) {
                insertMealType(mealTypeModel);
                asyncTaskResult.onSuccess(mealTypeModel);
            }
        }
    }
}