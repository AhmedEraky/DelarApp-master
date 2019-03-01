package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.LoginDataInterface;
import com.vegeta.my.dealer.model.login.LoginResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eraky on 1/19/2019.
 */

public class LoginPresenter {
    Context context;
    LoginDataInterface loginDataInterface;

    public LoginPresenter(Context context, LoginDataInterface loginDataInterface) {
        this.context = context;
        this.loginDataInterface = loginDataInterface;
    }

    public  void login(String body){
        Service service= Client.getClient().create(Service.class);
        RequestBody requestBody=RequestBody.create(MediaType.parse("text/plain"),body);
        Call<LoginResponse>call=service.UserLogin(requestBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code()==400)
                    loginDataInterface.LoginUser(null);
                else
                    loginDataInterface.LoginUser(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginDataInterface.error(context.getString(R.string.response_fail));
            }
        });
    }
}
