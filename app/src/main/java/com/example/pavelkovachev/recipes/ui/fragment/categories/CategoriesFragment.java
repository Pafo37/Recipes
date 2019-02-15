package com.example.pavelkovachev.recipes.ui.fragment.categories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.categories.CategoriesAdapter;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.BindView;

public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.viewpager_categories)
    ViewPager viewPagerCategories;
    @BindView(R.id.tab_layout_categories)
    TabLayout tabLayoutCategories;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoriesAdapter adapter = new CategoriesAdapter(getActivity().getSupportFragmentManager());
        viewPagerCategories.setAdapter(adapter);
        tabLayoutCategories.setupWithViewPager(viewPagerCategories);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_categories_host;
    }
}