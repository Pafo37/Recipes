package com.example.pavelkovachev.recipes.adapters.favorites;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.favorites.FavoritesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List list;
    private Context context;

    public FavoritesAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_favorites)
        ImageView imgFavorites;

        @BindView(R.id.txt_favorite_name)
        TextView txtFavoriteRecipeName;

        private FavoritesModel item;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(FavoritesModel item) {
            this.item = item;
            txtFavoriteRecipeName.setText(item.getFavoriteRecipeName());
            Picasso.get().load(item.getFavoriteRecipeName());
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_favorites, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData((FavoritesModel) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
