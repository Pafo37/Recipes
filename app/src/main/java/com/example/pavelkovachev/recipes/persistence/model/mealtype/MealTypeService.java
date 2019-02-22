package com.example.pavelkovachev.recipes.persistence.model.mealtype;

import android.os.AsyncTask;

import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.ui.interfaces.AsyncTaskResult;

import java.util.List;
import java.util.concurrent.Executor;

public class MealTypeService implements MealTypeRepository {

    private static MealTypeModelDao mealTypeModelDao;
    private Executor appExecutor;

    public MealTypeService(MealTypeModelDao mealTypeModelDao) {
        MealTypeService.mealTypeModelDao = mealTypeModelDao;
        appExecutor = AppExecutor.getInstance();
    }

    @Override
    public void insertCuisine(MealTypeModel mealTypeModel) {
        appExecutor.execute(() -> mealTypeModelDao.insertCuisine(mealTypeModel));
    }

    @Override
    public void deleteCuisine(MealTypeModel mealTypeModel) {
        appExecutor.execute(() -> mealTypeModelDao.deleteCuisine(mealTypeModel));
    }

    @Override
    public List<MealTypeModel> getAllMealTypes(AsyncTaskResult result) {
        new GetAllMealTypesAsyncTask(result).execute();
        return null;
    }

    private static class GetAllMealTypesAsyncTask extends AsyncTask<Void, Void, List<MealTypeModel>> {

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
                asyncTaskResult.onSuccess(mealTypeModel);
            }
        }
    }
}
