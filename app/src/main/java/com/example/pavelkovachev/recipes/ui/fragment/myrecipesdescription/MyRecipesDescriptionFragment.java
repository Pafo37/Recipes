package com.example.pavelkovachev.recipes.ui.fragment.myrecipesdescription;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import butterknife.BindView;

public class MyRecipesDescriptionFragment extends BaseFragment implements MyRecipesDescriptionContract.View {

    @BindView(R.id.img_my_recipe_description)
    ImageView imgMyRecipe;
    @BindView(R.id.txt_my_recipe_instructions_body)
    TextView txtMyRecipeInstructions;
    @BindView(R.id.txt_my_recipe_ingredients_body)
    TextView txtMyRecipeIngredients;

    private int recipeId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeId = getArguments().getInt(Constants.RECIPE_ID);
        presenter.getMyRecipeById(recipeId);

    }

    public static MyRecipesDescriptionFragment newInstance(Bundle bundle) {
        MyRecipesDescriptionFragment fragment = new MyRecipesDescriptionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private MyRecipesDescriptionContract.Presenter presenter;

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
        Bitmap bitmap = BitmapFactory.decodeFile(myRecipesModel.getRecipeImage());
        imgMyRecipe.setImageBitmap(bitmap);
        txtMyRecipeInstructions.setText(myRecipesModel.getRecipeInstructions());
        txtMyRecipeIngredients.setText(myRecipesModel.getRecipeIngredients());
        getActivity().setTitle(myRecipesModel.getRecipeName());
    }
}