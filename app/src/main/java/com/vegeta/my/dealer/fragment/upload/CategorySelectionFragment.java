package com.vegeta.my.dealer.fragment.upload;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.Utils.Maps.Validation;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.adapter.category.AddCategoryAdapter;
import com.vegeta.my.dealer.api.NetworkUtil;
import com.vegeta.my.dealer.fragment.user.DriverFragment;
import com.vegeta.my.dealer.fragment.user.FragmentParent;
import com.vegeta.my.dealer.fragment.user.ProductsFragment;
import com.vegeta.my.dealer.model.AdsModels.ApiAdvertiseModel;
import com.vegeta.my.dealer.model.category.Category;
import com.vegeta.my.dealer.model.product.ProductAddBody;
import com.vegeta.my.dealer.view.CategoryClick;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.Context.MODE_PRIVATE;
import static com.vegeta.my.dealer.Utils.Maps.Validation.buildDialog;

public class CategorySelectionFragment extends FragmentParent implements
        CategoryClick {


    ProductAddBody body;



    RecyclerView recyclerView;
    ArrayList<Category> categories;

    Activity context;
    private void setData() {
        categories=new ArrayList<>();
        Category category=new Category(1,"ماركت", R.drawable.market);
        categories.add(category);
        category=new Category(2,"كافيهات", R.drawable.caffee);
        categories.add(category);
        category=new Category(3,"مطاعم", R.drawable.restaurant);
        categories.add(category);
        category=new Category(4,"محلات ملابس", R.drawable.twsila);
        categories.add(category);
        category=new Category(5,"خدمات طبيه", R.drawable.clinic);
        categories.add(category);
        category=new Category(6,"مستشفيات", R.drawable.hospital);
        categories.add(category);
        category=new Category(7,"معارض", R.drawable.m3ard);
        categories.add(category);
        category=new Category(8,"موبايلات", R.drawable.mobiles);
        categories.add(category);
        category=new Category(9,"مشاوير", R.drawable.m4awir);
        categories.add(category);
         category=new Category(11,"خدمات تعليميه", R.drawable.library);
        categories.add(category);
        category=new Category(12,"قاعات", R.drawable.qa3at);
        categories.add(category);
        category=new Category(10,"خدمات اخري", R.drawable.otherservices);
        categories.add(category);

    }

    private void setRecycleContent() {
        AddCategoryAdapter categoryAdapter=new AddCategoryAdapter(this.getContext(),categories);
        categoryAdapter.onClick(this);
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getContext(),3);
        LinearLayoutManager gridLayoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

    }
    @Override
    public void openCategoryItmes(int id) {
        body.setId(id);
        if (body.getId() != 9) {
            ServiceProviderFragment uploadDataFragment = new ServiceProviderFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("body", body);
            uploadDataFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame
                    , uploadDataFragment)
                    .addToBackStack(null).commit();

        } else {
            ServiceCarFragment uploadDataFragment = new ServiceCarFragment();
            getFragmentManager().beginTransaction().replace(R.id.frame
                    , uploadDataFragment)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_category_selection, container, false);
        body=new ProductAddBody();
        setRetainInstance(true);





        findviews();
        setData();
        setRecycleContent();
        context=this.getActivity();

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        NavigationActivity.CurrentProcuct=-1;
        try {
            makeNavigation("الديلر");
            NavigationActivity.ToolbarColor.setBackground(getResources().getDrawable(R.drawable.bg));

        }catch (Exception e){

        }


    }

    private void findviews() {
        recyclerView=view.findViewById(R.id.category_Search_list);

    }
}
