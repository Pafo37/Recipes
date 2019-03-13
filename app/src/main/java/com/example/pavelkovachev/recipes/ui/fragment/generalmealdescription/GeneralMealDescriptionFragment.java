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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.adapters.ingredients.IngredientsAdapter;
import com.example.pavelkovachev.recipes.persistence.model.recipe.Ingredient;
import com.example.pavelkovachev.recipes.persistence.model.recipe.RecipeModel;
import com.example.pavelkovachev.recipes.presenters.generalmealdescription.GeneralMealDescriptionContract;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class GeneralMealDescriptionFragment extends BaseFragment implements GeneralMealDescriptionContract.View {

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
    @BindView(R.id.progress_bar_general_meal_description)
    ProgressBar progressBar;

    private GeneralMealDescriptionContract.Presenter presenter;
    private IngredientsAdapter ingredientsAdapter;
    private String recipeId;
    private static final String RECIPE_ID = "id";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeId = getArguments().getString(RECIPE_ID);
        presenter.getRandomRecipe(recipeId);
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
            showProgressBar(false);
            getActivity().setTitle(model.getRecipeName());
            recipeName.setText(model.getRecipeName());
            recipeMealType.setText(getString(R.string.general_meal_description_meal_type) + model.getRecipeMealType());
            recipeCuisine.setText(getString(R.string.general_meal_description_cuisine) + model.getRecipeCuisine());
            recipeInstructions.setMovementMethod(new ScrollingMovementMethod());
            recipeInstructions.setText(model.getRecipeInstructions());
            Picasso.get().load(model.getRecipeImage()).into(imgMeal);
            ingredientsAdapter = new IngredientsAdapter(initIngredients(model), getContext());
            recyclerView.setAdapter(ingredientsAdapter);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        }
    }

    @Override
    public String getRecipeId() {
        return recipeId = getArguments().getString(RECIPE_ID);
    }

    @Override
    public void showProgressBar(Boolean isVisible) {
        if (isAdded()) {
            progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    private List<Ingredient> initIngredients(RecipeModel recipeModel) {
        return Ingredient.convertFromRecipeToList(recipeModel);
    }

    @Override
    public void setPresenter(GeneralMealDescriptionContract.Presenter presenter) {
        this.presenter = presenter;
    }
}