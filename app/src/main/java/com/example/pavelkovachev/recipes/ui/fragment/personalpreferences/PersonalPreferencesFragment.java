package com.example.pavelkovachev.recipes.ui.fragment.personalpreferences;

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
import com.example.pavelkovachev.recipes.adapter.PersonalPreferencesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalPreferencesFragment extends Fragment {
    @BindView(R.id.viewpager_personal_preferences)
    ViewPager viewPagerFavoritesMyRecipes;
    @BindView(R.id.tab_layout_personal_preferences)
    TabLayout tabLayoutFavoritesMyRecipes;

    public static PersonalPreferencesFragment newInstance() {
        Bundle args = new Bundle();
        PersonalPreferencesFragment fragment = new PersonalPreferencesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_preferences, container, false);
        ButterKnife.bind(this, view);
        PersonalPreferencesAdapter adapter = new PersonalPreferencesAdapter(getActivity().getSupportFragmentManager());
        viewPagerFavoritesMyRecipes.setAdapter(adapter);
        tabLayoutFavoritesMyRecipes.setupWithViewPager(viewPagerFavoritesMyRecipes);
        return view;
    }
}
