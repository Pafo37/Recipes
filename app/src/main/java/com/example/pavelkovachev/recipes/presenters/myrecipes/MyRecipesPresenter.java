package com.example.pavelkovachev.recipes.presenters.myrecipes;

import android.util.Log;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MyRecipesPresenter extends BasePresenter implements MyRecipesContract.Presenter {

    @Inject
    ApplicationDataService dataService;

    private MyRecipesModel recentlyDeletedItem;
    private int recentlyDeletedItemPosition;

    private MyRecipesContract.View view;
    private List<MyRecipesModel> myRecipesModelList = new ArrayList<>();

    public MyRecipesPresenter(MyRecipesContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }

    @Override
    public void getRecipesFromDb() {
        subscribeSingle(dataService.getMyRecipesService().getMyRecipes(), new SingleObserver<List<MyRecipesModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<MyRecipesModel> myRecipesModels) {
                myRecipesModelList.addAll(myRecipesModels);
                view.notifyRecyclerView();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tag", "tag");
            }
        });
    }

    @Override
    public List<MyRecipesModel> getMyRecipesList() {
        return myRecipesModelList;
    }

    @Override
    public int getRecentlyDeletedItemPosition() {
        return recentlyDeletedItemPosition;
    }

    @Override
    public void deleteItem(int position) {
        recentlyDeletedItem = getMyRecipesList().get(position);
        recentlyDeletedItemPosition = position;
        dataService.getMyRecipesService().deleteMyRecipe(getMyRecipesList().get(position));
        getMyRecipesList().remove(position);
        view.notifyItemDeleted();
    }

    @Override
    public void undoDelete() {
        dataService.getMyRecipesService().insertMyRecipe(recentlyDeletedItem);
        getMyRecipesList().add(recentlyDeletedItemPosition, recentlyDeletedItem);
        view.notifyItemRestored();
    }
}
