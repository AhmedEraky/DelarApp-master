package com.vegeta.my.dealer.api;

import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.RegisterExternalModel;
import com.vegeta.my.dealer.model.login.UserInfoResponse;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductDriver;
import com.vegeta.my.dealer.model.product.ProductResponse;
import com.vegeta.my.dealer.model.product.Specialist;
import com.vegeta.my.dealer.model.product.UploadImageResponse;
import com.vegeta.my.dealer.model.profile.Profile;
import com.vegeta.my.dealer.model.registration.RegistrationResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Eraky on 1/18/2019.
 */

public interface Service {
    @GET("api/clinic")
    Call<ArrayList<Product>>  getClinicData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/cafe")
    Call<ArrayList<Product>>  getCafeData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/clothesShop")
    Call<ArrayList<Product>>  getClothesShopData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/electronic")
    Call<ArrayList<Product>>  getElectronicData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/gallery")
    Call<ArrayList<Product>>  getGalleryData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/hall")
    Call<ArrayList<Product>>  getHallData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/hospital")
    Call<ArrayList<Product>>  getHospitalData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/journeyDriver")
    Call<ArrayList<ProductDriver>>  getJourneyDriverData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/labCenter")
    Call<ArrayList<Product>>  getLabCenterData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/library")
    Call<ArrayList<Product>>  getLibraryData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/market")
    Call<ArrayList<Product>>  getMarketData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/pharmacy")
    Call<ArrayList<Product>>  getPharmacyData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/quickLiftDriver")
    Call<ArrayList<Product>>  getQuickLiftDriverData(@Query("index")int index,@Query("pagesize")int pageNumber);


    @GET("api/other")
    Call<ArrayList<Product>>  getRayCenterData(@Query("index")int index,@Query("pagesize")int pageNumber);

    @GET("api/resturant")
    Call<ArrayList<Product>>  getResturantData(@Query("index")int index,@Query("pagesize")int pageNumber);
//

    @POST("Token")
    Call<LoginResponse> UserLogin(@Body RequestBody body);

    @POST("api/account2/register")
    Call<RegistrationResponse> userRegist(@Body Map<String,String> map);

    //upload data
    @POST("api/library")
    Call<AddResponse> addLibaray(@Body Map<String,Object> map);

    @POST("api/clinic")
    Call<AddResponse>  AddClinicData(@Body Map<String,Object> map);

    @POST("api/cafe")
    Call<AddResponse>  AddCafeData(@Body Map<String,Object> map);


    @POST("api/clothesShop")
    Call<AddResponse>  AddClothesShopData(@Body Map<String,Object> map);


    @POST("api/electronic")
    Call<AddResponse>  AddElectronicData(@Body Map<String,Object> map);


    @POST("api/gallery")
    Call<AddResponse>  AddGalleryData(@Body Map<String,Object> map);


    @POST("api/hall")
    Call<AddResponse>  AddHallData(@Body Map<String,Object> map);

    @POST("api/hospital")
    Call<AddResponse>  AddHospitalData(@Body Map<String,Object> map);


    @POST("api/journeyDriver")
    Call<AddResponse>  AddJourneyDriverData(@Body Map<String,Object> map);

    @POST("api/labCenter")
    Call<AddResponse>  AddLabCenterData(@Body Map<String,Object> map);


    @POST("api/market")
    Call<AddResponse>  AddMarketData(@Body Map<String,Object> map);

    @POST("api/pharmacy")
    Call<AddResponse>  AddPharmacyData(@Body Map<String,Object> map);

    @POST("api/quickLiftDriver")
    Call<AddResponse>  AddQuickLiftDriverData(@Body Map<String,Object> map);


    @POST("api/other")
    Call<AddResponse>  AddRayCenterData(@Body Map<String,Object> map);

    @POST("api/resturant")
    Call<AddResponse>  AddResturantData(@Body Map<String,Object> map);


    //Specialist
    @GET("api/specialist")
    Call<ArrayList<Specialist>>  getSpecialist();


    //upload Image
    @Multipart
    @POST("/api/cafe/AddCafeImage/6")
    Call<UploadImageResponse>  UploadImage(@Part MultipartBody.Part file, @Part("file") RequestBody name);

    //get profile Data
    @GET("api/profile")
    Call<Profile>  getProfile();


    //get search Result


    @GET("api/clinic/search")
    Call<ArrayList<Product>>  getClinicData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/cafe/search")
    Call<ArrayList<Product>>  getCafeData(@Query("text")String searchkey, @Query("index")int pageNumber);


    @GET("api/clothesShop/search")
    Call<ArrayList<Product>>  getClothesShopData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/electronic/search")
    Call<ArrayList<Product>>  getElectronicData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/gallery/search")
    Call<ArrayList<Product>>  getGalleryData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/hall/search")
    Call<ArrayList<Product>>  getHallData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/hospital/search")
    Call<ArrayList<Product>>  getHospitalData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/journeyDriver/search")
    Call<ArrayList<Product>>  getJourneyDriverData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/labCenter/search")
    Call<ArrayList<Product>>  getLabCenterData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/library/search")
    Call<ArrayList<Product>>  getLibraryData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/market/search")
    Call<ArrayList<Product>>  getMarketData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/pharmacy/search")
    Call<ArrayList<Product>>  getPharmacyData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/quickLiftDriver/search")
    Call<ArrayList<Product>>  getQuickLiftDriverData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/other/search")
    Call<ArrayList<Product>>  getRayCenterData(@Query("text")String searchkey,@Query("index")int pageNumber);

    @GET("api/resturant/search")
    Call<ArrayList<Product>>  getResturantData(@Query("text")String searchkey,@Query("index")int pageNumber);


    @GET("api/account2/getuser")
    Call<UserInfoResponse>  getUserInfo();




}
