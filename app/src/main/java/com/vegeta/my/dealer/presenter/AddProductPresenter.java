package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.fragment.upload.UploadDataFragment;
import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.product.ProductAddBody;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductPresenter {
    Context context;
    UploadDataFragment uploadDataFragment;

    public AddProductPresenter(Context context, UploadDataFragment uploadDataFragment) {
        this.context = context;
        this.uploadDataFragment = uploadDataFragment;
    }

    public void registUser(ProductAddBody body)
    {
        Map<String,Object> map=new HashMap<>(  );
        map.put("id", 0);
        map.put("Name", body.getName());
        map.put("Address", body.getAddress());
        map.put("MobileNo", body.getMobileNo());
        map.put("IsAcceptedByManager", "false");
        map.put("MoreInformation", body.getMoreInformation());
        map.put("PhoneNo", body.getPhoneNo());
        map.put("Location", body.getLocation());
        map.put("IsAvailable", "false");



        if(body.getId()==6){

           map.put("HospitalSpecialists", body.getAddHospitalSpecialist());


        }
        else {
            map.put("Username", "");

            map.put("SpecialistId", 0);
            map.put("Specialist", body.getSpecialist());
        }
        Service service = Client.getClient().create( Service.class );
        Call<AddResponse> call;

        switch (body.getId()) {
            case 1:/*
                activityTitle.setText("ماركت");*/
                call = service.AddMarketData(map);

                break;
            case 2:/*
                activityTitle.setText("كافيهات");*/
                call = service.AddCafeData(map);


                break;
            case 3:/*
                activityTitle.setText("مطاعم");*/
                call = service.AddResturantData(map);

                break;
            case 4:/*
                activityTitle.setText("محلات ملابس");*/
                call = service.AddClothesShopData(map);

                break;
            case 5:
                /*activityTitle.setText("عيادلت");*/
                call = service.AddClinicData(map);

                break;
            case 6:/*
                activityTitle.setText("مستشفيات");*/
                call = service.AddHospitalData(map);

                break;
            case 7:
                /*activityTitle.setText("معارض");*/
                call = service.AddGalleryData(map);

                break;
            case 8:
//                activityTitle.setText("موبيلات");
                call = service.AddElectronicData(map);

                break;
            case 9:
//                activityTitle.setText("مشاوير");
                call = service.AddJourneyDriverData(map);

                break;
            case 10:
                //activityTitle.setText("خدمات اخري");
                call = service.AddRayCenterData(map);

                break;
            case 11:
                call = service.addLibaray(map);

                break;
            default:
                call = service.AddHallData(map);
                break;
        }

        call.enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {

                uploadDataFragment.AddNewProduct(response.body());
            }

            @Override
            public void onFailure(Call<AddResponse> call, Throwable t) {
                uploadDataFragment.error("عفوا هناك خطاء");
            }
        });
    }
}
