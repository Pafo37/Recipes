package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeScreenFragment extends Fragment implements HomeScreenContract.View {


    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.cardview_categories)
    void onCategoriesClicked(){
        startActivity(new Intent(getActivity(), CategoriesActivity.class));
    }

    @OnClick(R.id.cardview_random_meal)
    void onRandomMealClicked(){
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
    }

    @OnClick(R.id.cardview_latest_meal)
    void onLatestMealClicked(){
        startActivity(new Intent(getActivity(),GeneralMealDescriptionActivity.class));
    }


}
