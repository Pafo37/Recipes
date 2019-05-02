package com.example.pavelkovachev.recipes.adapters.personalpreferences.favorites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.example.pavelkovachev.recipes.presenters.favorites.FavoritesContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List list;
    private FavoritesItemListener favoritesItemListener;

    public FavoritesAdapter(FavoritesContract.Presenter presenter, FavoritesItemListener favoritesItemListener) {
        this.list = presenter.getFavoritesList();
        this.favoritesItemListener = favoritesItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_favorites)
        ImageView imgFavorites;

        @BindView(R.id.txt_favorite_name)
        TextView txtFavoriteRecipeName;

        private FavoritesModel item;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        private void setData(FavoritesModel item) {
            this.item = item;
            txtFavoriteRecipeName.setText(item.getFavoriteRecipeName());
            Picasso.get().load(item.getFavoritesImage())
                    .placeholder(R.drawable.placeholder_recipe).into(imgFavorites);
        }

        @Override
        public void onClick(View v) {
            if (favoritesItemListener != null) {
                favoritesItemListener.onFavoritesClicked(item);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorites, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData((FavoritesModel) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface FavoritesItemListener {

        void onFavoritesClicked(FavoritesModel favoritesItem);
    }
}