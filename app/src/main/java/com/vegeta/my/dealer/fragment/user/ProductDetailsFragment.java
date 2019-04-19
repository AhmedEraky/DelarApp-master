package com.vegeta.my.dealer.fragment.user;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.rv_gallery.GalleryRecyclerView;
import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.activity.LoginActivity;
import com.vegeta.my.dealer.activity.SplashActivity;
import com.vegeta.my.dealer.adapter.RecyclerAdapter;
import com.vegeta.my.dealer.adapter.product.CafeMenuAdapter;
import com.vegeta.my.dealer.adapter.product.SpechialistAdapter;
import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductDriver;
import com.vegeta.my.dealer.view.DriverClick;

import java.util.ArrayList;
import java.util.Locale;

public class ProductDetailsFragment extends FragmentParent {



    Product product;
    GalleryRecyclerView mRecyclerView;
    ArrayList<String> imgdata;
    RelativeLayout spechialistLayout;
    ImageView mainImage,locationImage,imgSpecialist,imgChatBtn,imgPhone,imgPhone2;
    CardView phone_1,phone_2,listDetail;
    TextView addressTxt,phone,phone2,txtImg,txtMoreInfo,clincSpechislidt,txtMenuTitle;
    Button btnChat;
    NestedScrollView nestedScrollView;
    int id;
    View layoutMoreInfo;
    RecyclerView specialistList,menuList;
    private LoginResponse userModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bundle=this.getArguments();
        if(bundle!=null) {
            product = bundle.getParcelable("product");
            id=bundle.getInt("id");
        }
        if(id==5||id==6)
            view= inflater.inflate(R.layout.fragment_product_health, container, false);
        else if(id==2)
        {
            view= inflater.inflate(R.layout.fragment_product_cafe, container, false);

        }
        else
            view= inflater.inflate(R.layout.fragment_product_normal, container, false);

        findViews();
        setData();

        setOnClick();
        setRecycleContent();

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;

        return view;
    }

    private void setOnClick() {
        locationImage.setOnClickListener(v->{
            String head="الموقع";
            String geoUriString="geo:"+product.getLocation().getLatitude()+","+product.getLocation().getLongitude()+"?q=("+head+")@"+product.getLocation().getLatitude()+","+product.getLocation().getLongitude();
            Uri geoUri = Uri.parse(geoUriString);
            Intent mapCall  = new Intent(Intent.ACTION_VIEW, geoUri);
            startActivity(mapCall);

        });

        if(id==6||id==5) {
            spechialistLayout.setOnClickListener(v -> {
                if (id==6 && specialistList.getVisibility() == View.VISIBLE) {
                    specialistList.setVisibility(View.GONE);
                } else if(id==6)
                    specialistList.setVisibility(View.VISIBLE);
                else if(id==5 && listDetail.getVisibility() == View.VISIBLE)
                    listDetail.setVisibility(View.GONE);
                else if(id==5)
                    listDetail.setVisibility(View.VISIBLE);

            });
        }

        imgChatBtn.setOnClickListener(v->{
            ChatFragment chatFragment=new ChatFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", product.getId());
            bundle.putString("name",product.getName());
            bundle.putString("productUser",product.getUsername());

            chatFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add( R.id.frame
                    ,chatFragment )
                    .addToBackStack( null ).commit();
        });

        imgPhone.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+product.getMobileNo()));
            startActivity(intent);
        });


        if(id==5||id==6) {
            btnChat.setOnClickListener(v -> {
                ChatFragment chatFragment = new ChatFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", product.getId());
                bundle.putString("name", product.getName());
                chatFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.frame
                        , chatFragment)
                        .addToBackStack(null).commit();
            });
        }
    }

    private void setRecycleContent() {
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(this.getContext(),imgdata);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(recyclerAdapter);

    }

    private void setData() {



        if (product.getPhoneNo()!=null)
        {
            if(!product.getPhoneNo().equals(""))
            {
                phone.setText(product.getPhoneNo());
            }
        }
        if(id==5||id==6) {
            if (product.getMobileNo()!=null)
            {
                if(!product.getMobileNo().equals(""))
                {
                    phone2.setText(product.getMobileNo());
                }
            }
            phone_1.setOnClickListener(view1 -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+product.getMobileNo()));
                startActivity(intent);
            });

        }
        phone_2.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+product.getPhoneNo()));
            startActivity(intent);
        });

        if(!product.getAddress().equals(""))
            addressTxt.setText(product.getAddress());


        //more Information part
        if(id!=5&&id!=6 &&product.getMoreInformation()!=null)
        {
            if( product.getMoreInformation().equals("")){
                layoutMoreInfo.setVisibility(View.GONE);
            }
            else
            {
                txtMoreInfo.setText(product.getMoreInformation());
            }
        }

        // Gallery Part
        if(product.getServiceProviderImages().size()>0)
            Picasso.with(this.getContext()).load(product.getServiceProviderImages().get(0).getImageUrl()).error(R.drawable.m4awir).into(mainImage);
        if(product.getServiceProviderImages().size()==0 )
            txtImg.setVisibility(View.GONE);
        imgdata=new ArrayList<>();
        for(int i=0;i<product.getServiceProviderImages().size();i++)
        {
            imgdata.add(product.getServiceProviderImages().get(i).getImageUrl());
        }

        //specialist part

        //if clinic
        if(id==5){
            if(product.getSpecialist()!=null)
                clincSpechislidt.setText(product.getSpecialist().getName());
            else
                clincSpechislidt.setText("لا نوجد بيانات");
        }
        //if hospital
        else if(id==6){
            SpechialistAdapter adapter=new SpechialistAdapter(this.getContext(),product.getHospitalSpecialists());
            LinearLayoutManager linearLayout=new LinearLayoutManager(this.getContext());
            specialistList.setLayoutManager(linearLayout);
            specialistList.setAdapter(adapter);
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        userModel = SplashActivity.userData;
        if (userModel == null||userModel.getUserName().equals("")||userModel.getAccessToken().equals("")){
            Toast.makeText(this.getContext(), "يجب ان تكون مسجل", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this.getContext(), LoginActivity.class));
        }
        try {
            makeNavigation(product.getName());
        }catch (Exception e){

        }

    }

    private void findViews() {
        if(id==5||id==6) {
            imgPhone2=view.findViewById(R.id.product_details_img_phone2);
            phone2=view.findViewById(R.id.product_details_txt_phone2);

            phone_1=view.findViewById(R.id.phone_1);
        }
        phone_2=view.findViewById(R.id.phone_2);

        imgPhone=view.findViewById(R.id.product_details_img_phone);
        mRecyclerView = view.findViewById(R.id.product_details_gallery);
        mainImage=view.findViewById(R.id.product_details_img_main);
        locationImage=view.findViewById(R.id.product_details_img_location);
        addressTxt=view.findViewById(R.id.product_details_txt_location);
        btnChat=view.findViewById(R.id.product_details_btn_chat);
        phone=view.findViewById(R.id.product_details_txt_phone);
        nestedScrollView=view.findViewById(R.id.product_details_scroll_view);
        imgChatBtn=view.findViewById(R.id.product_details_img_chat);
        txtImg=view.findViewById(R.id.product_details_txt_img);
        txtMoreInfo=view.findViewById(R.id.product_details_txt_more_info);
        layoutMoreInfo=view.findViewById(R.id.product_details_layout_more_info);
        specialistList=view.findViewById(R.id.product_details_list_specialist);
        spechialistLayout=view.findViewById(R.id.product_details_layout_specialist);
        clincSpechislidt=view.findViewById(R.id.product_details_txt_clinic_specialist);
        listDetail=view.findViewById(R.id.listDetail);
    }


}
