package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeScreenFragment extends BaseFragment implements HomeScreenContract.View {

    private HomeScreenContract.Presenter presenter;

    @BindView(R.id.txt_random_meal_name)
    TextView txtRandomMealName;
    @BindView(R.id.img_general_meal)
    ImageView imgRandomMeal;
    @BindView(R.id.txt_latest_meal_name)
    TextView txtLatestMealName;
    @BindView(R.id.img_latest_meal)
    ImageView imgLatestMeal;

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
    protected int getLayoutResId() {
        return R.layout.fragment_home_screen;
    }

    @OnClick(R.id.cardview_categories)
    void onCategoriesClicked() {
        startActivity(new Intent(getActivity(), CategoriesActivity.class));
    }

    @OnClick(R.id.cardview_random_meal)
    void onRandomMealClicked() {
        Intent intent=new Intent(getActivity(),GeneralMealDescriptionActivity.class);
        intent.putExtra("id",presenter.onRandomCardViewClicked());
        startActivity(intent);
    }

    @OnClick(R.id.cardview_latest_meal)
    void onLatestMealClicked() {
        Intent intent=new Intent(getActivity(),GeneralMealDescriptionActivity.class);
        intent.putExtra("id",presenter.onLatestCardViewClicked());
        startActivity(intent);
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

}