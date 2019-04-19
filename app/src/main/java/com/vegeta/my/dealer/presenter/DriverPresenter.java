package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.DriverInterface;
import com.vegeta.my.dealer.model.product.ProductDriver;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverPresenter {
    Context context;
    DriverInterface productInterface;

    public DriverPresenter(Context context, DriverInterface productInterface) {
        this.context = context;
        this.productInterface = productInterface;
    }

    public void getProductData(int pageNumber) {

        Service service = Client.getClient().create(Service.class);
        Call<ArrayList<ProductDriver>> call = null;
        call = service.getJourneyDriverData(pageNumber,20);
        call.enqueue(new Callback<ArrayList<ProductDriver>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductDriver>> call, Response<ArrayList<ProductDriver>> response) {
                productInterface.getDriverData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ProductDriver>> call, Throwable t) {

            }
        });


    }
}
