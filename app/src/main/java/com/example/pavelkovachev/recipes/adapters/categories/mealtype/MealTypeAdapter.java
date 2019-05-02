package com.example.pavelkovachev.recipes.adapters.categories.mealtype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavelkovachev.recipes.R;
import com.example.pavelkovachev.recipes.persistence.model.mealtype.MealTypeModel;
import com.example.pavelkovachev.recipes.presenters.mealtype.MealTypeContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealTypeAdapter extends RecyclerView.Adapter<MealTypeAdapter.ViewHolderMealType> {

    private List list;
    private mealTypeItemListener mealTypeItemListener;

    public MealTypeAdapter(MealTypeContract.Presenter presenter, MealTypeAdapter.mealTypeItemListener mealTypeItemListener) {
        this.list = presenter.getMealTypeList();
        this.mealTypeItemListener = mealTypeItemListener;
    }

    public class ViewHolderMealType extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_category_mealtype)
        ImageView imgCategoryMealType;
        @BindView(R.id.txt_mealtype_category_name)
        TextView txtCategoryMealTypeTitle;
        @BindView(R.id.txt_mealtype_category_description)
        TextView txtCategoryMealTypeDescription;

        private MealTypeModel mealTypeItem;

        private ViewHolderMealType(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void setMealTypeData(MealTypeModel mealTypeItem) {
            this.mealTypeItem = mealTypeItem;
            txtCategoryMealTypeTitle.setText(mealTypeItem.getTitle());
            txtCategoryMealTypeDescription.setText(mealTypeItem.getDescription());
            Picasso.get().load(mealTypeItem.getImage()).into(imgCategoryMealType);
        }

        @Override
        public void onClick(View view) {
            if (mealTypeItemListener != null) {
                mealTypeItemListener.onMealTypeClick(mealTypeItem);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolderMealType onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolderMealType(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_mealtype, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMealType viewHolderMealType, int position) {
        viewHolderMealType.setMealTypeData((MealTypeModel) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface mealTypeItemListener {

        void onMealTypeClick(MealTypeModel mealTypeItem);

    }
}