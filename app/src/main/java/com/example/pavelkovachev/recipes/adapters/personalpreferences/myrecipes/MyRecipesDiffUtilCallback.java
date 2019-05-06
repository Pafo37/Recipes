package com.example.pavelkovachev.recipes.adapters.personalpreferences.myrecipes;

import android.support.v7.util.DiffUtil;

import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;

import java.util.List;

public class MyRecipesDiffUtilCallback extends DiffUtil.Callback {

    private List<MyRecipesModel> oldList;
    private List<MyRecipesModel> newList;

    public MyRecipesDiffUtilCallback(List<MyRecipesModel> oldList, List<MyRecipesModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).getRecipeName().compareTo(oldList.get(oldItemPosition).getRecipeName());
        return result == 0;
    }
}