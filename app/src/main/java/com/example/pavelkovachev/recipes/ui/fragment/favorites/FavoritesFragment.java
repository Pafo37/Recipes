package com.example.pavelkovachev.recipes.ui.fragment.favorites;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

public class FavoritesFragment extends BaseFragment {

    public static FavoritesFragment newInstance() {
        return  new FavoritesFragment();
    }
    
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_favorites;
    }
}