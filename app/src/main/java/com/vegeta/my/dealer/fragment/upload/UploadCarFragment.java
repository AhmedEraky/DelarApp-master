package com.vegeta.my.dealer.fragment.upload;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.api.retrofitinterface.AddProductInterface;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.AddTrips.AddResponseModel;
import com.vegeta.my.dealer.model.AddTrips.ProductDriverRequest;
import com.vegeta.my.dealer.model.product.Location;
import com.vegeta.my.dealer.model.product.ProductAddBody;
import com.vegeta.my.dealer.model.product.ProductDriver;
import com.vegeta.my.dealer.presenter.AddDriverPresenter;
import com.vegeta.my.dealer.presenter.AddProductPresenter;

import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;
import static com.vegeta.my.dealer.Utils.Maps.DelarUtils.productDriverRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadCarFragment extends FragmentParent implements AddProductInterface {


    AddDriverPresenter addProductPresenter;
    ProductDriver body;
    Button accept,reject;

    NetworkConnection networkConnection;

    private CompositeSubscription mSubscriptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_upload_car, container, false);
        networkConnection = new NetworkConnection(this.getContext());
       // bundle=this.getArguments();
        /*if(bundle!=null) {
            body = bundle.getParcelable("body");
        }

        if (body != null && body.destinationLocation == null) {
            Location location = new Location();
            location.setLatitude(0);
            location.setLongitude(0);
            body.destinationLocation = location;
        }

        if (body != null && body.sourceLocation == null) {
            Location location = new Location();
            location.setLatitude(0);
            location.setLongitude(0);
            body.sourceLocation = location;
        }*/
        setRetainInstance(true);
        mSubscriptions   = new CompositeSubscription();
        findviews();
        setOnClick();

        new DelarUtils().getAds(this.getActivity(),view);

        DelarUtils.flagSearchHome=true;


        return view;
    }

    private void findviews() {

        accept=view.findViewById(R.id.service_provider3_accept);
        reject=view.findViewById(R.id.service_provider3_reject);




    }


    private void setOnClick() {

        accept.setOnClickListener(view1 -> {
            SharedPreferences mSharedPreferences;
            mSharedPreferences  =   Objects.requireNonNull(getActivity()).getSharedPreferences("settings",MODE_PRIVATE);

            mSubscriptions.add(NetworkUtil.getRetrofitByToken( mSharedPreferences.getString("token", ""))
                    .journeyDriver(productDriverRequest)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handlResponse, this::handleError));


            /*addProductPresenter=new AddDriverPresenter(this.getContext(),this);
            addProductPresenter.registUser(body);*/


        });

        reject.setOnClickListener(view1 -> {
            getActivity().onBackPressed();
        });



    }

    private void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void handlResponse(AddResponseModel addResponseModel) {
        open();
    }

    public void open(){
        getActivity().onBackPressed();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("شكرا لتعاونك معنا لتفعيل الخدمه تواصل معنا\n" +
                "admin@thedelar.com \n" +
                "01091767746");
        alertDialogBuilder.setPositiveButton("تواصل بالهاتف الان",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+"01091767746"));
                        startActivity(intent);
                    }
                });
        alertDialogBuilder.setNegativeButton("تواصل في وقت لاحق",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void AddNewProduct(AddResponse response) {
        open();

    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();

    }
}
