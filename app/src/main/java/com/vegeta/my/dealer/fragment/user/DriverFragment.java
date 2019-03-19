package com.vegeta.my.dealer.fragment.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.adapter.product.DriverAdapter;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.retrofitinterface.DriverInterface;
import com.vegeta.my.dealer.model.product.ProductDriver;
import com.vegeta.my.dealer.presenter.DriverPresenter;
import com.vegeta.my.dealer.view.DriverClick;

import java.util.ArrayList;

public class DriverFragment extends FragmentParent implements DriverInterface,DriverClick {
    RecyclerView recyclerView;
    ArrayList<ProductDriver> products;
    NetworkConnection networkConnection;
    DriverPresenter productPresenter;
    SkeletonScreen skeletonScreen;
    DriverAdapter productsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_driver, container, false);
        setRetainInstance(true);

        NavigationActivity.ToolbarColor.setBackgroundColor(Color.RED);

        networkConnection = new NetworkConnection(this.getContext());


        findViews();

        setData();
        if (networkConnection.isNetworkAvailable(this.getContext())) {

        }else {
            error(getString(R.string.no_internet_msg));
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            makeNavigation("مشاوير");
        }
        catch (Exception e){

        }
    }
    private void findViews() {
        recyclerView = view.findViewById(R.id.driver_fragment_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        skeletonScreen = Skeleton.bind(recyclerView).load(R.layout.item_skeleton_news).show();
    }

    private void setData() {
        if (networkConnection.isNetworkAvailable(this.getContext())) {
            productPresenter = new DriverPresenter(this.getContext(), this);
            productPresenter.getProductData();

        }else {
            error(getString(R.string.no_internet_msg));
        }
    }


    @Override
    public void getDriverData(ArrayList<ProductDriver> products) {

        this.products=products;
        if(this.products==null||this.products.size()==0)
        {
            Toast.makeText(this.getContext(), "عفوا لا توجد بيانات الان", Toast.LENGTH_SHORT).show();
        }
        else{
            skeletonScreen.hide();
            setRecycleContent();
        }

    }

    private void setRecycleContent() {
        productsAdapter = new DriverAdapter(this.getContext(), products,this);
        recyclerView.setAdapter(productsAdapter);
    }



    @Override
    public void error(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void openChat(ProductDriver productDriver) {
        ChatFragment chatFragment=new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",productDriver.id);
        bundle.putString("name",productDriver.journeyDriver.name);
        bundle.putString("productUser",productDriver.journeyDriver.id);
        chatFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace( R.id.frame
                ,chatFragment ,"product_fragment")
                .addToBackStack("home").commit();
    }
}
