package com.vegeta.my.dealer.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

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
    private ProfileInterface profileInterface;

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
            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                profileInterface.getProfileData(response.body());
                SharedPreferences sharedPreferences;
                sharedPreferences = context.getSharedPreferences( "settings", Context.MODE_PRIVATE );

                if (response.body() != null && response.body().getImageUrl() != null) {
                    SharedPreferences.Editor editor_Verified = sharedPreferences.edit();
                    editor_Verified.putString("img", String.valueOf(response.body().getImageUrl()));
                    editor_Verified.apply();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                profileInterface.error("عفوا هناك خطاء");
            }
        });
    }
}
