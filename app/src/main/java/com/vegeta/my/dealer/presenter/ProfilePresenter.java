package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.GetSpecialistInterface;
import com.vegeta.my.dealer.api.retrofitinterface.ProfileInterface;
import com.vegeta.my.dealer.model.product.Specialist;
import com.vegeta.my.dealer.model.profile.Profile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    Context context;
    ProfileInterface profileInterface;

    public ProfilePresenter(Context context, ProfileInterface profileInterface) {
        this.context = context;
        this.profileInterface = profileInterface;
    }

    public void getProfile(){
        Service service= Client.getClient().create(Service.class);
        Call<Profile> call;
        call=service.getProfile();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                profileInterface.getProfileData(response.body());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                profileInterface.error("عفوا هناك خطاء");
            }
        });
    }
}
