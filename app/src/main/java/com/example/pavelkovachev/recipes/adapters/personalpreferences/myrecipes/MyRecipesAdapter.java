package com.example.pavelkovachev.recipes.adapters.personalpreferences.myrecipes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.myrecipes.MyRecipesModel;
import com.example.pavelkovachev.recipes.presenters.myrecipes.MyRecipesContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecipesAdapter extends RecyclerView.Adapter<MyRecipesAdapter.ViewHolder> {

    private List list;
    private MyRecipesItemListener myRecipesItemListener;

    public MyRecipesAdapter(MyRecipesContract.Presenter presenter, MyRecipesItemListener myRecipesItemListener) {
        this.list = presenter.getMyRecipesList();
        this.myRecipesItemListener = myRecipesItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_my_recipe)
        ImageView imageView;
        @BindView(R.id.txt_my_recipe_name)
        TextView textView;

        private MyRecipesModel item;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        private void setData(MyRecipesModel item) {
            this.item = item;
            Bitmap bitmap = BitmapFactory.decodeFile(item.getRecipeImage());
            imageView.setImageBitmap(bitmap);
            textView.setText(item.getRecipeName());
        }

        @Override
        public void onClick(View v) {
            if (myRecipesItemListener != null) {
                myRecipesItemListener.onMyRecipesClicked(item);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData((MyRecipesModel) list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MyRecipesItemListener {

        void onMyRecipesClicked(MyRecipesModel myRecipesItem);
    }
}