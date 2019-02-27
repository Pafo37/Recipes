package com.example.pavelkovachev.recipes.ui.fragment.generalmealdescription;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.ingredients.IngredientsAdapter;
import com.example.pavelkovachev.recipes.persistence.model.recipe.Ingredient;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.generalmealdescription.GeneralMealDescriptionContract;
import com.example.pavelkovachev.recipes.presenters.homescreen.HomeScreenPresenter;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.example.pavelkovachev.recipes.ui.fragment.homescreen.HomeScreenFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class GeneralMealDescriptionFragment extends BaseFragment implements GeneralMealDescriptionContract.View
        , IngredientsAdapter.ItemListener {

    public static String CURRENT_RECIPE_NAME;

    @BindView(R.id.img_general_meal)
    ImageView imgMeal;
    @BindView(R.id.txt_meal_title)
    TextView recipeName;
    @BindView(R.id.txt_general_meal_type)
    TextView recipeMealType;
    @BindView(R.id.txt_general_meal_cuisine)
    TextView recipeCuisine;
    @BindView(R.id.txt_general_meal_instructions_body)
    TextView recipeInstructions;
    @BindView(R.id.recycler_view_general_meal_ingredients)
    RecyclerView recyclerView;

    GeneralMealDescriptionContract.Presenter presenter;
    IngredientsAdapter ingredientsAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //FIXME
        if (HomeScreenFragment.isLatestMealClicked) {
            presenter.loadRecipe(HomeScreenPresenter.CURRENT_LATEST_MEAL_ID);
        }
        if (HomeScreenFragment.isRandomMealClicked) {
            presenter.loadRecipe(HomeScreenPresenter.CURRENT_RANDOM_MEAL_ID);
        }
    }

    public static GeneralMealDescriptionFragment newInstance() {
        return new GeneralMealDescriptionFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_general_meal_description;
    }

    @Override
    public void showRecipe(RecipeModel model) {
        if (isAdded()) {
            getActivity().setTitle(model.getRecipeName());
            recipeName.setText(model.getRecipeName());
            recipeMealType.setText("Meal Type: " + model.getRecipeMealType());
            recipeCuisine.setText("Cuisine: " + model.getRecipeCuisine());
            recipeInstructions.setMovementMethod(new ScrollingMovementMethod());
            recipeInstructions.setText(model.getRecipeInstructions());
            Picasso.get().load(model.getRecipeImage()).into(imgMeal);
            ingredientsAdapter = new IngredientsAdapter(initIngredients(model), getContext(), this);
            recyclerView.setAdapter(ingredientsAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        }
    }

    private List<Ingredient> initIngredients(RecipeModel recipeModel) {
        return Ingredient.convertFromRecipeToList(recipeModel);
    }

    @Override
    public void setPresenter(GeneralMealDescriptionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(Ingredient ingredientItem) {

    }
}