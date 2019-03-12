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
import com.example.pavelkovachev.recipes.ui.activity.base.BaseActivity;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class GeneralMealDescriptionFragment extends BaseFragment implements GeneralMealDescriptionContract.View
        , IngredientsAdapter.ItemListener {

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

    private GeneralMealDescriptionContract.Presenter presenter;
    private IngredientsAdapter ingredientsAdapter;
    private String recipeId;
    private static final String RECIPE_ID = "id";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeId = getArguments().getString(RECIPE_ID);
        presenter.getRandomRecipe(recipeId);
        progressBarVisibility(true);
    }

    public static GeneralMealDescriptionFragment newInstance() {
        return new GeneralMealDescriptionFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_general_meal_description;
    }

    @Override
    public void showRecipe(RecipeModel recipeModel) {
        if (isAdded()) {
            progressBarVisibility(false);
            getActivity().setTitle(recipeModel.getRecipeName());
            recipeName.setText(recipeModel.getRecipeName());
            recipeMealType.setText(getString(R.string.general_meal_description_meal_type) + recipeModel.getRecipeMealType());
            recipeCuisine.setText(getString(R.string.general_meal_description_cuisine) + recipeModel.getRecipeCuisine());
            recipeInstructions.setMovementMethod(new ScrollingMovementMethod());
            recipeInstructions.setText(recipeModel.getRecipeInstructions());
            Picasso.get().load(recipeModel.getRecipeImage()).into(imgMeal);
            ingredientsAdapter = new IngredientsAdapter(initIngredients(recipeModel), getContext(), this);
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
    public void onError() {
        showErrorDialog();
    }

    private List<Ingredient> initIngredients(RecipeModel recipeModel) {
        return Ingredient.convertFromRecipeToList(recipeModel);
    }

    @Override
    public void setPresenter(GeneralMealDescriptionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void progressBarVisibility(boolean isVisible) {
        ((BaseActivity) getActivity()).showProgressBar(isVisible);
    }

    @Override
    public void onItemClick(Ingredient ingredientItem) {
        //NOT USED
    }
}