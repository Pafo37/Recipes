package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.activity.personalpreferences.PersonalPreferencesActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeScreenFragment extends BaseFragment implements HomeScreenContract.View {

    @BindView(R.id.txt_random_meal_name)
    TextView txtRandomMealName;
    @BindView(R.id.img_general_meal)
    ImageView imgRandomMeal;
    @BindView(R.id.txt_latest_meal_name)
    TextView txtLatestMealName;
    @BindView(R.id.img_latest_meal)
    ImageView imgLatestMeal;

    private HomeScreenContract.Presenter presenter;

    public static HomeScreenFragment newInstance() {
        return new HomeScreenFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadRandomLatestMeals();
    }

    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        ((BaseActivity) getActivity()).showProgressBar(isVisible);
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
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.putExtra(Constants.RECIPE_ID, presenter.onRandomCardViewClicked());
        startActivity(intent);
    }

    @OnClick(R.id.cardview_latest_meal)
    void onLatestMealClicked() {
        Intent intent = new Intent(getActivity(), GeneralMealDescriptionActivity.class);
        intent.putExtra(Constants.RECIPE_ID, presenter.onLatestCardViewClicked());
        startActivity(intent);
    }

    @OnClick(R.id.fab_homescreen_favorite)
    void onFabClicked() {
        startActivity(new Intent(getActivity(), PersonalPreferencesActivity.class));
    }

    @Override
    public void setRandomMeal(RecipeModel recipeModel) {
        if (isAdded()) {
            Picasso.get().load(recipeModel.getRecipeImage()).into(imgRandomMeal);
            txtRandomMealName.setText(recipeModel.getRecipeName());
        }
    }

    @Override
    public void setLatestMeal(RecipeModel recipeModel) {
        if (isAdded()) {
            Picasso.get().load(recipeModel.getRecipeImage()).into(imgLatestMeal);
            txtLatestMealName.setText(recipeModel.getRecipeName());
        }
    }

    @Override
    public void showError(String title, String message) {
        showErrorDialog(title, message);
    }
}