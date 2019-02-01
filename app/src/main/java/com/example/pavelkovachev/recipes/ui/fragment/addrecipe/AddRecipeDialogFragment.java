package com.example.pavelkovachev.recipes.ui.fragment.addrecipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pavelkovachev.recipes.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRecipeDialogFragment extends DialogFragment {
    @BindView(R.id.img_meal_favorites)
    ImageView imgPictureTaken;

    public static AddRecipeDialogFragment newInstance() {
        Bundle args = new Bundle();
        AddRecipeDialogFragment fragment = new AddRecipeDialogFragment();
        fragment.setArguments(args);
        return fragment;
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
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult pickResult) {
                        imgPictureTaken.setImageBitmap(pickResult.getBitmap());
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        Log.e("TAG", "Cancel");
                    }
                }).show(getFragmentManager());
    }
}