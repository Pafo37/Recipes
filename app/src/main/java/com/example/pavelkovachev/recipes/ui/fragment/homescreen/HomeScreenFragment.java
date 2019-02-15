package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.NetworkUtil;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeScreenFragment extends BaseFragment implements HomeScreenContract.View {

    @BindView(R.id.txt_random_meal_name)
    TextView textView;
    private NetworkUtil networkUtil;

    public static HomeScreenFragment newInstance() {

        Bundle args = new Bundle();

        HomeScreenFragment fragment = new HomeScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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