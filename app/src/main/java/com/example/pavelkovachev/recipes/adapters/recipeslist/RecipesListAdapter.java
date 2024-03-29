package com.example.pavelkovachev.recipes.adapters.recipeslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.recipelist.RecipeListModel;
import com.example.pavelkovachev.recipes.presenters.recipeslist.RecipesListContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListAdapter extends RecyclerView.Adapter<RecipesListAdapter.ViewHolder> {

    private List list;
    private Context context;
    private ItemListener itemListener;

    public RecipesListAdapter(RecipesListContract.Presenter presenter, Context context, ItemListener itemListener) {
        this.list = presenter.getRecipeListArray();
        this.context = context;
        this.itemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_recipe_list_title)
        public TextView txtRecipeListTitle;
        @BindView(R.id.img_gridlayout_categories)
        public ImageView imgCategories;

        private RecipeListModel item;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        private void setData(RecipeListModel item) {
            this.item = item;
            txtRecipeListTitle.setText(item.getRecipeName());
            Picasso.get().load(item.getRecipeImage()).into(imgCategories);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(item);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipes_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData((RecipeListModel) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemListener {
        void onItemClick(RecipeListModel item);
    }
}