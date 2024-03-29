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
import android.widget.Toast;

import com.example.pavelkovachev.recipes.Constants;
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
import butterknife.OnClick;

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

    private String recipeId;
    private RecipeModel recipeModel;
    private IngredientsAdapter ingredientsAdapter;
    private GeneralMealDescriptionContract.Presenter presenter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeId = getArguments().getString(Constants.RECIPE_ID);
        presenter.getRandomRecipeFromDb(recipeId);
        setProgressBarVisibility(true);
    }

    public static GeneralMealDescriptionFragment newInstance(Bundle bundle) {
        GeneralMealDescriptionFragment fragment = new GeneralMealDescriptionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_general_meal_description;
    }

    @Override
    public void showRecipe(RecipeModel recipeModel) {
        if (isAdded()) {
            setProgressBarVisibility(false);
            getActivity().setTitle(recipeModel.getRecipeName());
            recipeName.setText(recipeModel.getRecipeName());
            recipeMealType.setText(getString(R.string.general_meal_description_meal_type) + recipeModel.getRecipeMealType());
            recipeCuisine.setText(getString(R.string.general_meal_description_cuisine) + recipeModel.getRecipeCuisine());
            recipeInstructions.setMovementMethod(new ScrollingMovementMethod());
            recipeInstructions.setText(recipeModel.getRecipeInstructions());
            Picasso.get().load(recipeModel.getRecipeImage()).placeholder(R.drawable.placeholder_recipe).into(imgMeal);
            initRecyclerView(recipeModel);
            this.recipeModel = recipeModel;
        }
    }

    @Override
    public String getRecipeId() {
        if (getArguments() != null && getArguments().containsKey(Constants.RECIPE_ID)) {
            return getArguments().getString(Constants.RECIPE_ID);
        } else {
            return null;
        }
    }

    @Override
    public void showError(int title, int message) {
        showErrorDialog(title, message);
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getContext(), getString(message), Toast.LENGTH_SHORT).show();
    }

    private List<Ingredient> initIngredients(RecipeModel recipeModel) {
        return Ingredient.convertFromRecipeToList(recipeModel);
    }

    @Override
    public void setPresenter(GeneralMealDescriptionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        ((BaseActivity) getActivity()).showProgressBar(isVisible);
    }

    private void initRecyclerView(RecipeModel model) {
        ingredientsAdapter = new IngredientsAdapter(initIngredients(model), getContext());
        recyclerView.setAdapter(ingredientsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @OnClick(R.id.fab_general_meal_description_favorite)
    public void onFabGeneralMealDescriptionClicked() {
        presenter.addToFavorites(RecipeModel.convertToFavoriteRecipe(recipeModel));
    }
}