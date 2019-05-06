package com.example.pavelkovachev.recipes.ui.fragment.addrecipe;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pavelkovachev.recipes.Constants;
import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.addrecipe.AddRecipeContract;
import com.example.pavelkovachev.recipes.presenters.addrecipe.AddRecipePresenter;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRecipeDialogFragment extends DialogFragment implements AddRecipeContract.View {

    @BindView(R.id.img_meal_favorites)
    ImageView imgPictureTaken;
    @BindView(R.id.edt_favorites_instructions_body)
    EditText edtMealInstructions;
    @BindView(R.id.txt_favorites_meal_title)
    EditText edtMealTitle;
    @BindView(R.id.edt_favorites_ingredients_body)
    EditText edtMealIngredients;

    private AddRecipeContract.Presenter presenter;
    private String recipeName;
    private String recipeInstructions;
    private String recipeIngredients;
    private String recipeImage;
    MyRecipesModel recipeModel;

    public static AddRecipeDialogFragment newInstance() {
        return new AddRecipeDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_favorites, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AddRecipePresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @OnClick(R.id.fab_camera)
    public void onCameraClicked() {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(pickResult -> {
                    recipeImage = pickResult.getPath();
                    imgPictureTaken.setImageBitmap(pickResult.getBitmap());
                })
                .setOnPickCancel(() -> Log.d("TAG", "Cancel")).show(getFragmentManager());
    }

    @Override
    public void setPresenter(AddRecipeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        //NOT USED
    }

    @OnClick(R.id.btn_save)
    public void saveRecipe() {
        recipeName = edtMealTitle.getText().toString();
        recipeInstructions = edtMealInstructions.getText().toString();
        recipeIngredients = edtMealIngredients.getText().toString();
        recipeModel = new MyRecipesModel(recipeName, recipeInstructions, recipeIngredients, recipeImage);
        presenter.addRecipeToDb();
        Intent intent = new Intent();
        intent.putExtra(Constants.PARCELABLE_KEY, recipeModel);
        getTargetFragment().onActivityResult(getTargetRequestCode(), 0, intent);
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void closeDialogFragment() {
        dismiss();
    }

    @Override
    public MyRecipesModel getRecipe() {
        return recipeModel;
    }
}