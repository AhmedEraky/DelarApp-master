package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.RegistrationActivity;
import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.RegistrationInterface;
import com.vegeta.my.dealer.model.registration.RegistrationBody;
import com.vegeta.my.dealer.model.registration.RegistrationResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eraky on 1/19/2019.
 */

public class RegistrationPresenter {
    Context context;
    RegistrationInterface registrationInterface;

    public RegistrationPresenter(Context context, RegistrationInterface registrationInterface) {
        this.context = context;
        this.registrationInterface = registrationInterface;
    }

    public void registUser(RegistrationBody body)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put("Email",body.getEmail());
        map.put("UserName",body.getUserName());
        map.put("IsProvider",Boolean.toString(body.isIsProvider()));
        map.put("Password",body.getPassword());
        map.put("ConfirmPassword",body.getConfirmPassword());
        Service service = Client.getClient().create( Service.class );
        Call<RegistrationResponse> call=service.userRegist(map);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.code()==400) {
                    Gson gson = new Gson();

                    RegistrationResponse registrationResponse= gson.fromJson(response.errorBody().charStream(),RegistrationResponse.class);
                    registrationInterface.getRegisterData(registrationResponse);
                }
                else {
                    registrationInterface.getRegisterData(null);
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                registrationInterface.getRegisterData(null);
            }
        });
    }
}
