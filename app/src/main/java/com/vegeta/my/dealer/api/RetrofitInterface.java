package com.vegeta.my.dealer.api;

import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.AddTrips.AddResponseModel;
import com.vegeta.my.dealer.model.AddTrips.ProductDriverRequest;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseDensityModel;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseModel;
import com.vegeta.my.dealer.model.UpdateProfile.UpdateProfileRequestModel;
import com.vegeta.my.dealer.model.UpdateProfile.UpdateProfileResponseModel;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.RegisterExternalModel;
import com.vegeta.my.dealer.model.login.UserInfoResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {
    @POST("api/account3/RegisterExternal3")
    Observable<LoginResponse> UserLoginExternal(@Body RegisterExternalModel body);
    //


    @GET("/api/ads/get")
    Observable<ApiAdvertiseModel> getAds();

    @GET("/api/ads/getDensity")
    Observable<ApiAdvertiseDensityModel> getDensity();

    @POST("/api/Profile")
    Observable<UpdateProfileResponseModel> updateProfile(@Body UpdateProfileRequestModel updateProfileRequestModel);

    @POST("/api/journeyDriver")
    Observable<AddResponseModel> journeyDriver(@Body ProductDriverRequest productDriverRequest);



}
