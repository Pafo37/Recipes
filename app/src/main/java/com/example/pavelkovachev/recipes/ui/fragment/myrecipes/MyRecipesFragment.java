package com.example.pavelkovachev.recipes.ui.fragment.myrecipes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.addrecipe.AddRecipeDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRecipesFragment extends Fragment {

    public static MyRecipesFragment newInstance() {

        Bundle args = new Bundle();
        MyRecipesFragment fragment = new MyRecipesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_recipes, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fab_add_recipes)
    public void onAddButtonClicked() {
        AddRecipeDialogFragment addRecipeDialogFragment = AddRecipeDialogFragment.newInstance();
        addRecipeDialogFragment.show(getFragmentManager(), "add_recipe");
    }
}