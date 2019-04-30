package com.example.pavelkovachev.recipes.presenters.favorites;

import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.base.BasePresenter;
import com.example.pavelkovachev.recipes.services.ApplicationDataService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class FavoritesPresenter extends BasePresenter implements FavoritesContract.Presenter {

    @Inject
    ApplicationDataService dataService;

    private FavoritesModel recentlyDeletedItem;
    private int recentlyDeletedItemPosition;

    private FavoritesContract.View view;
    private List<FavoritesModel> favoritesModelList = new ArrayList<>();

    public FavoritesPresenter(FavoritesContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    protected void inject() {
        provideAppComponent().inject(this);
    }

    public void getFavoriteRecipes() {
        subscribeSingle(dataService.getFavoritesService().getFavoriteRecipes(),
                new SingleObserver<List<FavoritesModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //NOT USED
                    }

                    @Override
                    public void onSuccess(List<FavoritesModel> favoritesModels) {
                        favoritesModelList.addAll(favoritesModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onErrorShown();
                    }
                });
    }

    @Override
    public List<FavoritesModel> getFavoritesList() {
        return favoritesModelList;
    }

    public void deleteItem(int position) {
        recentlyDeletedItem = getFavoritesList().get(position);
        recentlyDeletedItemPosition = position;
        dataService.getFavoritesService().deleteFavorites(getFavoritesList().get(position));
        getFavoritesList().remove(position);
        view.getFavoritesAdapter().notifyItemRemoved(position);
        view.showSnackbar();
    }

    @Override
    public void undoDelete() {
        dataService.getFavoritesService().insertFavorites(recentlyDeletedItem);
        getFavoritesList().add(recentlyDeletedItemPosition, recentlyDeletedItem);
        view.getFavoritesAdapter().notifyItemInserted(recentlyDeletedItemPosition);
    }
}