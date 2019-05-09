package com.example.pavelkovachev.recipes.ui.fragment.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavelkovachev.recipes.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message);
        builder.setNeutralButton(getString(R.string.ok_message), (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    protected void showErrorDialog(int titleId, int messageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(getString(titleId))
                .setMessage(getString(messageId));
        builder.setNeutralButton(getString(R.string.ok_message), (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public void showSnackbar(ConstraintLayout layout, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(layout,
                getString(R.string.recipe_was_removed), Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.undo_button), onClickListener);
        snackbar.show();
    }
}