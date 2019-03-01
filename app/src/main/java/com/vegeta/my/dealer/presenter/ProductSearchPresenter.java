package com.vegeta.my.dealer.presenter;

import android.content.Context;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.ProductDataInterface;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Eraky on 1/18/2019.
 */

public class ProductSearchPresenter {
    Context context;
    ProductDataInterface productInterface;

    public ProductSearchPresenter(Context context, ProductDataInterface productInterface) {
        this.context = context;
        this.productInterface = productInterface;
    }

    public void getProductData(int id,String searchText){

        Service service= Client.getClient().create(Service.class);
        Call<ArrayList<Product>> call;
        switch (id) {
            case 1:/*
                activityTitle.setText("ماركت");*/
                call = service.getMarketData(searchText,0);

                break;
            case 2:/*
                activityTitle.setText("كافيهات");*/
                call = service.getCafeData(searchText,0);


                break;
            case 3:/*
                activityTitle.setText("مطاعم");*/
                call = service.getResturantData(searchText,0);

                break;
            case 4:/*
                activityTitle.setText("توصيله");*/
                call = service.getQuickLiftDriverData(searchText,0);

                break;
            case 5:
                /*activityTitle.setText("عيادلت");*/
                call = service.getClinicData(searchText,0);

                break;
            case 6:/*
                activityTitle.setText("مستشفيات");*/
                call = service.getHospitalData(searchText,0);

                break;
            case 7:
                /*activityTitle.setText("معارض");*/
                call = service.getGalleryData(searchText,0);

                break;
            case 8:
//                activityTitle.setText("موبيلات");
                call = service.getElectronicData(searchText,0);

                break;
            case 9:
//                activityTitle.setText("مشاوير");
                call = service.getJourneyDriverData(searchText,0);

                break;
            case 10:
                //activityTitle.setText("خدمات اخري");
                call = service.getRayCenterData(searchText,0);

                break;
            case 11:
                call = service.getLibraryData(searchText,0);

                break;
            default:
                call = service.getHallData(searchText,0);
                break;
        }
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productInterface.getProductData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                productInterface.error(context.getString(R.string.response_fail));
            }
        });

    }
}
