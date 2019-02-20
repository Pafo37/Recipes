package com.example.pavelkovachev.recipes.ui.fragment.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.database.DatabaseCreator;
import com.example.pavelkovachev.recipes.persistence.executors.AppExecutor;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModelDao;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenContract;
import com.example.pavelkovachev.recipes.ui.activity.categories.CategoriesActivity;
import com.example.pavelkovachev.recipes.ui.activity.generalmealdescription.GeneralMealDescriptionActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeScreenFragment extends BaseFragment implements HomeScreenContract.View {

    private HomeScreenContract.Presenter presenter;

    @BindView(R.id.txt_random_meal_name)
    TextView txtRandomMealName;
    @BindView(R.id.img_random_meal)
    ImageView imgRandomMeal;
    @BindView(R.id.txt_latest_meal_name)
    TextView txtLatestMealName;
    @BindView(R.id.img_latest_meal)
    ImageView imgLatestMeal;

    public static HomeScreenFragment newInstance() {
        return new HomeScreenFragment();
    }

    public HomeScreenFragment() {
    }

    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter.start();
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
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
    }

    @OnClick(R.id.cardview_latest_meal)
    void onLatestMealClicked() {
        startActivity(new Intent(getActivity(), GeneralMealDescriptionActivity.class));
    }

    @Override
    public void setRandomMeal(RecipeModel recipeModel) {
        Picasso.get().load(recipeModel.getRecipeImage()).into(imgRandomMeal);
        txtRandomMealName.setText(recipeModel.getRecipeName());
    }

    @Override
    public void setLatestMeal(RecipeModel recipeModel) {
        Picasso.get().load(recipeModel.getRecipeImage()).into(imgLatestMeal);
        txtLatestMealName.setText(recipeModel.getRecipeName());
    }

    @Override
    public void saveToDatabase(RecipeModel recipeModel) {
        RecipeModelDao recipeModelDao = DatabaseCreator.getRecipeDatabase(getContext()).recipeDao();
        AppExecutor.getInstance().execute(() -> recipeModelDao.insertRecipe(recipeModel));
    }
}