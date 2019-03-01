package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.GetSpecialistInterface;
import com.vegeta.my.dealer.model.product.Specialist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSpecialistPresenter {
    Context context;
    GetSpecialistInterface specialistInterface;

    public GetSpecialistPresenter(Context context, GetSpecialistInterface specialistInterface) {
        this.context = context;
        this.specialistInterface = specialistInterface;
    }

    public void getSpecialist(){
        Service service= Client.getClient().create(Service.class);
        Call<ArrayList<Specialist>> call;
        call=service.getSpecialist();
        call.enqueue(new Callback<ArrayList<Specialist>>() {
            @Override
            public void onResponse(Call<ArrayList<Specialist>> call, Response<ArrayList<Specialist>> response) {
                specialistInterface.getSpecialistData(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Specialist>> call, Throwable t) {
                specialistInterface.error("هناك خطاء");
            }
        });

    }
}
