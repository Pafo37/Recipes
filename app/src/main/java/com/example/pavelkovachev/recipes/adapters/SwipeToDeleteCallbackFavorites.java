package com.example.pavelkovachev.recipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesContract;

public class SwipeToDeleteCallbackFavorites extends ItemTouchHelper.SimpleCallback {

    private static final int DRAG_DIRS = 0;

    private FavoritesContract.Presenter favoritesPresenter;

    public SwipeToDeleteCallbackFavorites(FavoritesContract.Presenter favoritesPresenter) {
        super(DRAG_DIRS, ItemTouchHelper.RIGHT);
        this.favoritesPresenter = favoritesPresenter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        favoritesPresenter.deleteItem(viewHolder.getAdapterPosition());
    }
}