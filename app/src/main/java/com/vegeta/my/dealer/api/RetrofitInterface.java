package com.vegeta.my.dealer.api;

import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.RegisterExternalModel;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface RetrofitInterface {
    @POST("api/account3/RegisterExternal3")
    Observable<LoginResponse> UserLoginExternal(@Body RegisterExternalModel body);
    //
}
