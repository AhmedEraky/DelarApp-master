package com.vegeta.my.dealer.presenter;

import android.content.Context;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.LoginDataInterface;
import com.vegeta.my.dealer.api.retrofitinterface.UserInfoInterface;
import com.vegeta.my.dealer.model.login.UserInfoResponse;
import com.vegeta.my.dealer.model.product.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoPresenter {
    Context context;
    UserInfoInterface userInfoInterface;

    public UserInfoPresenter(Context context, UserInfoInterface userInfoInterface) {
        this.context = context;
        this.userInfoInterface=userInfoInterface;
    }
    public void getInfo(){
        Service service= Client.getClient().create(Service.class);
        Call<UserInfoResponse> call;
        call = service.getUserInfo();
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                userInfoInterface.LoginUser(response.body());
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                userInfoInterface.error("هناك خطاء");
            }
        });
    }
}
