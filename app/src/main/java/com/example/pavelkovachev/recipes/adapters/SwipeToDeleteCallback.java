package com.example.pavelkovachev.recipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesContract;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private FavoritesContract.Presenter favoritesPresenter;

    public SwipeToDeleteCallback(FavoritesContract.Presenter favoritesPresenter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.favoritesPresenter = favoritesPresenter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        favoritesPresenter.deleteItem(position);
    }
}
