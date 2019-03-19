package com.vegeta.my.dealer.adapter.product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductDriver;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.ArrayList;

public class DriverAdapter  extends RecyclerView.Adapter<DriverAdapter.ViewHolder>  {
    Context context;
    ArrayList<ProductDriver> products;
    ProductClick onClickProdutActivity;

    public DriverAdapter(Context context, ArrayList<ProductDriver> products) {
        this.context = context;
        this.products = products;
    }

    public void onClick(ProductClick onClickProdutActivity) {
        this.onClickProdutActivity = onClickProdutActivity;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drive, parent, false);
        return new DriverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.ViewHolder holder, int position) {
        holder.driverName.setText(products.get(position).journeyDriver.name);
        holder.driverCar.setText(products.get(position).journeyDriver.userName);
        holder.driverSeat.setText(Integer.toString(products.get(position).numOfSeats));
        holder.driverStart.setText(products.get(position).sourceAdress);
        holder.driverDestinatin.setText(products.get(position).destinationAddress);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView driverImg;
        TextView driverName,driverCar,driverSeat,driverStart,driverDestinatin;

        public ViewHolder(View itemView) {
            super(itemView);
            driverImg = itemView.findViewById(R.id.driver_img);
            driverName = itemView.findViewById(R.id.driver_name);
            driverCar = itemView.findViewById(R.id.driver_car);
            driverSeat=itemView.findViewById(R.id.driver_seat);
            driverStart=itemView.findViewById(R.id.driver_start);
            driverDestinatin=itemView.findViewById(R.id.driver_destination);
        }
    }
}