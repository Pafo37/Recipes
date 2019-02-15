package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.OnClick;

public class HomeScreenFragment extends BaseFragment implements HomeScreenContract.View {

    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home_screen;
    }

    @OnClick(R.id.cardview_categories)
    void onCategoriesClicked() {
        startActivity(new Intent(getActivity(), CategoriesActivity.class));
    }

    @OnClick(R.id.cardview_random_meal)
    void onRandomMealClicked() {
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
    }

    @OnClick(R.id.cardview_latest_meal)
    void onLatestMealClicked() {
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
    }
}