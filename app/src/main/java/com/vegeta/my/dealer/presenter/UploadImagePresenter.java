package com.vegeta.my.dealer.presenter;

import android.content.Context;
import android.widget.Toast;

import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.api.retrofitinterface.UploadImage;
import com.vegeta.my.dealer.model.product.UploadImageResponse;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImagePresenter {
    Context context;
    UploadImage uploadImage;

    public UploadImagePresenter(Context context, UploadImage uploadImage) {
        this.context = context;
        this.uploadImage = uploadImage;
    }

    public void getUploadImageResult(RequestBody fileName,MultipartBody.Part fileToUpload){

        Service service=Client.getClient().create( Service.class );
        Call<UploadImageResponse> call=service.UploadImage( fileToUpload,fileName );
        call.enqueue(new Callback<UploadImageResponse>() {
            @Override
            public void onResponse(Call<UploadImageResponse> call, Response<UploadImageResponse> response) {
                Toast.makeText(context, response.code()+" "+response.message(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, response.message()+" "+response.code()+" "+response.raw(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UploadImageResponse> call, Throwable t) {

                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();


            }
        });


    }
}
