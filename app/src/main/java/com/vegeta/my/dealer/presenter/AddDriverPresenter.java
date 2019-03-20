package com.vegeta.my.dealer.presenter;
import android.content.Context;
import com.vegeta.my.dealer.api.Client;
import com.vegeta.my.dealer.api.Service;
import com.vegeta.my.dealer.fragment.upload.UploadCarFragment;
import com.vegeta.my.dealer.fragment.upload.UploadDataFragment;
import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.product.ProductDriver;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDriverPresenter {
    Context context;
    UploadCarFragment uploadDataFragment;

    public AddDriverPresenter(Context context, UploadCarFragment uploadDataFragment) {
        this.context = context;
        this.uploadDataFragment = uploadDataFragment;
    }

    public void registUser(ProductDriver body) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0);
        map.put("SourceAdress", body.sourceAdress);
        map.put("launchTime", body.launchTime);
        map.put("DestinationAddress", body.destinationAddress);
        map.put("SourceLocation", body.sourceLocation);
        map.put("DestinationLocation", body.destinationLocation);
        map.put("journeydate", new Date());
        map.put("NumOfSeats", body.numOfSeats);
        map.put("JourneyDriver", body.journeyDriver);
        Service service = Client.getClient().create(Service.class);
        Call<AddResponse> call;
        call = service.AddJourneyDriverData(map);
        call.enqueue(new Callback<AddResponse>() {
            @Override
            public void onResponse(Call<AddResponse> call, Response<AddResponse> response) {
                uploadDataFragment.AddNewProduct(response.body());
            }
            @Override
            public void onFailure(Call<AddResponse> call, Throwable t) {
                uploadDataFragment.error("عفوا هناك خطاء");
            }
        });
    }
}
