package com.vegeta.my.dealer.adapter.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.product.CafeMenu;
import com.vegeta.my.dealer.model.product.HospitalSpecialist;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.List;


public class CafeMenuAdapter extends RecyclerView.Adapter<CafeMenuAdapter.ViewHolder> {

    Context context;
    List<CafeMenu> menu;


    public CafeMenuAdapter(Context context, List<CafeMenu> menu) {
        this.context = context;
        this.menu = menu;
    }


    @NonNull
    @Override
    public CafeMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cafe_menu, parent, false);
        return new CafeMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeMenuAdapter.ViewHolder holder, int position) {

        if(menu.get(position).getImageUrl()!=null)
        {
            Picasso.with(context).load(menu.get(position).getImageUrl()).error(R.drawable.caffee).into(holder.img);

        }
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_menu_img);
        }
    }
}
