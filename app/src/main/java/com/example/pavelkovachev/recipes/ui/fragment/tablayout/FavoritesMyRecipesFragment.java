package com.example.pavelkovachev.recipes.ui.fragment.tablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapter.AdapterFavoritesMyRecipes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesMyRecipesFragment extends Fragment {
    @BindView(R.id.viewpager_favorites_my_recipes)
    ViewPager viewPagerFavoritesMyRecipes;
    @BindView(R.id.tab_layout_favorites_my_recipes)
    TabLayout tabLayoutFavoritesMyRecipes;

    public static FavoritesMyRecipesFragment newInstance() {
        Bundle args = new Bundle();
        FavoritesMyRecipesFragment fragment = new FavoritesMyRecipesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_my_recipes, container, false);
        ButterKnife.bind(this, view);
        AdapterFavoritesMyRecipes adapter = new AdapterFavoritesMyRecipes(getActivity().getSupportFragmentManager());
        viewPagerFavoritesMyRecipes.setAdapter(adapter);
        tabLayoutFavoritesMyRecipes.setupWithViewPager(viewPagerFavoritesMyRecipes);
        return view;
    }
}
