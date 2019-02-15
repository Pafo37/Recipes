package com.example.pavelkovachev.recipes.ui.fragment.myrecipes;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.addrecipe.AddRecipeDialogFragment;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.OnClick;

public class MyRecipesFragment extends BaseFragment {

    public static MyRecipesFragment newInstance() {
        return new MyRecipesFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_recipes;
    }

    @OnClick(R.id.fab_add_recipes)
    public void onAddButtonClicked() {
        AddRecipeDialogFragment addRecipeDialogFragment = AddRecipeDialogFragment.newInstance();
        addRecipeDialogFragment.show(getFragmentManager(), "add_recipe");
    }
}