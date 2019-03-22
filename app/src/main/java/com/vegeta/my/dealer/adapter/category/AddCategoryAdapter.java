package com.vegeta.my.dealer.adapter.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.category.Category;

import java.util.ArrayList;

public class AddCategoryAdapter extends CategoryAdapter {

    public AddCategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, categories);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_category, viewGroup, false);
        return new CategoryAdapter.ViewHolder(view);
    }
}
