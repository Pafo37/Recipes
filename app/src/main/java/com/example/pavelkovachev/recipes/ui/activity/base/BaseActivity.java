package com.example.pavelkovachev.recipes.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pavelkovachev.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);
        showProgressBar(false);
    }

    public void showProgressBar(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    protected void commitFragmentTransaction(Fragment target) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.container, target);
        fragmentTransaction.commit();
    }
}