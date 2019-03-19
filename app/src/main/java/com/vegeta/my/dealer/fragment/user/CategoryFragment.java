package com.vegeta.my.dealer.fragment.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vegeta.my.dealer.R;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.adapter.category.CategoryAdapter;
import com.vegeta.my.dealer.model.category.Category;
import com.vegeta.my.dealer.view.CategoryClick;

import java.util.ArrayList;

public class CategoryFragment extends FragmentParent implements
        CategoryClick {


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
        category=new Category(10,"خدمات اخري", R.drawable.otherservices);
        categories.add(category);
        category=new Category(11,"خدمات تعليميه", R.drawable.library);
        categories.add(category);
        category=new Category(12,"قاعات", R.drawable.qa3at);
        categories.add(category);
    }
    private void setRecycleContent() {
        CategoryAdapter categoryAdapter=new CategoryAdapter(this.getContext(),categories);
        categoryAdapter.onClick(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

    }


    @Override
    public void openCategoryItmes(int id) {
        //todo open categoryItem
        FragmentParent productsFragment=null;
        if(id==9){
             productsFragment=new DriverFragment();

        }else {
            productsFragment = new ProductsFragment();
            NavigationActivity.CurrentProcuct=id;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("category", id);
        productsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace( R.id.frame
                ,productsFragment ,"product_fragment")
                .addToBackStack("home").commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate( R.layout.fragment_category, container, false );

        setRetainInstance(true);

        findviews();
        setData();
        setRecycleContent();
        context=this.getActivity();

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if( i == KeyEvent.KEYCODE_BACK )
                {
                    context.finish();
                }
                return false;
            }
        });
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
        recyclerView=view.findViewById(R.id.category_fragment_recycler);

    }
}
