package com.example.pavelkovachev.recipes.ui.fragment.personalpreferences;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.personalpreferences.PersonalPreferencesAdapter;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.BindView;

public class PersonalPreferencesFragmentHost extends BaseFragment {
    @BindView(R.id.viewpager_personal_preferences)
    ViewPager viewPagerFavoritesMyRecipes;
    @BindView(R.id.tab_layout_personal_preferences)
    TabLayout tabLayoutFavoritesMyRecipes;

    public static PersonalPreferencesFragmentHost newInstance() {
        return new PersonalPreferencesFragmentHost();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PersonalPreferencesAdapter adapter = new PersonalPreferencesAdapter(getActivity().getSupportFragmentManager());
        viewPagerFavoritesMyRecipes.setAdapter(adapter);
        tabLayoutFavoritesMyRecipes.setupWithViewPager(viewPagerFavoritesMyRecipes);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_personal_preferences;
    }
}