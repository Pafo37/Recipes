package com.example.pavelkovachev.recipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.pavelkovachev.recipes.presenters.myrecipes.MyRecipesContract;

public class SwipeToDeleteCallBackMyRecipes extends ItemTouchHelper.SimpleCallback {

    private static final int DRAG_DIRS = 0;
    private MyRecipesContract.Presenter myRecipesPresenter;

    public SwipeToDeleteCallBackMyRecipes(MyRecipesContract.Presenter myRecipesPresenter) {
        super(DRAG_DIRS, ItemTouchHelper.LEFT);
        this.myRecipesPresenter = myRecipesPresenter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        myRecipesPresenter.deleteItem(viewHolder.getAdapterPosition());
    }
}
