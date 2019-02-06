package com.example.pavelkovachev.recipes.ui.fragment.addrecipe;

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

import com.example.pavelkovachev.recipes.R;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRecipeDialogFragment extends DialogFragment {
    @BindView(R.id.img_meal_favorites)
    ImageView imgPictureTaken;
    @BindView(R.id.txt_favorites_description_body)
    EditText edtMealDescription;

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

    @OnClick(R.id.fab_camera)
    public void onCameraClicked() {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(pickResult -> imgPictureTaken.setImageBitmap(pickResult.getBitmap()))
                .setOnPickCancel(() -> Log.d("TAG", "Cancel")).show(getFragmentManager());
    }
}