package com.example.pavelkovachev.recipes.ui.fragment.categories;

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
import com.example.pavelkovachev.recipes.adapters.categories.CategoriesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends Fragment {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;

    public static CategoriesFragment newInstance() {

        Bundle args = new Bundle();

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_host, container, false);
        ButterKnife.bind(this, view);
        CategoriesAdapter adapter = new CategoriesAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
