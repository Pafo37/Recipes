package com.example.pavelkovachev.recipes.ui.fragment.myrecipesdescription;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.myrecipesdescription.MyRecipesDescriptionContract;
import com.example.pavelkovachev.recipes.ui.fragment.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class MyRecipesDescriptionFragment extends BaseFragment
        implements MyRecipesDescriptionContract.View {

    @BindView(R.id.img_my_recipe_description)
    ImageView imgMyRecipe;
    @BindView(R.id.txt_my_recipe_instructions_body)
    TextView txtMyRecipeInstructions;
    @BindView(R.id.txt_my_recipe_ingredients_body)
    TextView txtMyRecipeIngredients;

    private int recipeId;
    private MyRecipesDescriptionContract.Presenter presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            recipeId = getArguments().getInt(Constants.RECIPE_ID);
            presenter.getMyRecipeById(recipeId);
        }
    }

    public static MyRecipesDescriptionFragment newInstance(Bundle bundle) {
        MyRecipesDescriptionFragment fragment = new MyRecipesDescriptionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_recipe_description;
    }

    @Override
    public void setPresenter(MyRecipesDescriptionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    @Override
    public void showMyRecipe(MyRecipesModel myRecipesModel) {
        Picasso.get().load(myRecipesModel.getRecipeImage()).into(imgMyRecipe);
        txtMyRecipeInstructions.setText(myRecipesModel.getRecipeInstructions());
        txtMyRecipeIngredients.setText(myRecipesModel.getRecipeIngredients());
        getActivity().setTitle(myRecipesModel.getRecipeName());
    }

    @Override
    public void showError(String title, String message) {
        showErrorDialog(title, message);
    }
}