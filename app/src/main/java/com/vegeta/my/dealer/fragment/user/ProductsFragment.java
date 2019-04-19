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
import com.vegeta.my.dealer.Utils.Maps.DelarUtils;
import com.vegeta.my.dealer.activity.NavigationActivity;
import com.vegeta.my.dealer.adapter.product.EndlessRecyclerViewScrollListener;
import com.vegeta.my.dealer.adapter.product.ProductsAdapter;
import com.vegeta.my.dealer.api.NetworkConnection;
import com.vegeta.my.dealer.api.retrofitinterface.ProductDataInterface;
import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.presenter.ProductPresenter;
import com.vegeta.my.dealer.presenter.ProductSearchPresenter;
import com.vegeta.my.dealer.view.ProductClick;

import java.util.ArrayList;

public class ProductsFragment extends FragmentParent implements
        ProductDataInterface, ProductClick {


    RecyclerView recyclerView;
    ArrayList<Product> products;
    NetworkConnection networkConnection;
    ProductPresenter productPresenter;
    ProductSearchPresenter productSearchPresenter;
    LinearLayoutManager linearLayoutManager;
    SkeletonScreen skeletonScreen;
    ProductsAdapter productsAdapter;
    int id;
    int searchID;
    String query;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_products, container, false);
        setRetainInstance(true);

        NavigationActivity.ToolbarColor.setBackgroundColor(Color.RED);

        networkConnection = new NetworkConnection(this.getContext());
        bundle=this.getArguments();

        if(bundle!=null) {
            id = bundle.getInt("category", -1);
            searchID = bundle.getInt("searchCategory", -1);
             query=bundle.getString("query");

            findViews();
            if(searchID==-1&&id!=-1){
                setData();
            }
            else {
                if (networkConnection.isNetworkAvailable(this.getContext())) {
                    productSearchPresenter = new ProductSearchPresenter(this.getContext(), this);
                    productSearchPresenter.getProductData(searchID,query);

                }else {
                    error(getString(R.string.no_internet_msg));
                }
            }
        }

        new DelarUtils().getAds(this.getActivity(),view);
        DelarUtils.flagSearchHome=true;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setActivityTitle();

    }

    private void findViews() {
        recyclerView = view.findViewById(R.id.product_fragment_recycler);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        skeletonScreen = Skeleton.bind(recyclerView).load(R.layout.item_skeleton_news).show();
        if(searchID==-1||query==null) {
            recyclerView.setOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    productPresenter.getProductData(id, page + 1);
                }
            });
        }
    }
    private void setActivityTitle() {
        try {


            if(id!=-1){
                switch (id) {
                    case 1:
                        makeNavigation("ماركت");
                        break;
                    case 2:
                        makeNavigation("كافيهات");

                        break;
                    case 3:
                        makeNavigation("مطاعم");
                        break;
                    case 4:
                        makeNavigation("محلات ملابس");
                        break;
                    case 5:
                        makeNavigation("خدمات طبيه");
                        break;
                    case 6:
                        makeNavigation("مستشفيات");
                        break;
                    case 7:
                        makeNavigation("معارض");
                        break;
                    case 8:
                        makeNavigation("موبيلات");
                        break;
                    case 9:
                        makeNavigation("مشاوير");
                        break;
                    case 10:
                        makeNavigation("خدمات اخري");
                        break;
                    case 11:
                        makeNavigation("خدمات تعليميه");
                        break;
                    case 12:
                        makeNavigation("قاعات");
                        break;
                }

            }else if(searchID!=-1){

                switch (searchID) {
                    case 1:
                        makeNavigation("ماركت");
                        break;
                    case 2:
                        makeNavigation("كافيهات");

                        break;
                    case 3:
                        makeNavigation("مطاعم");
                        break;
                    case 4:
                        makeNavigation("محلات ملابس");
                        break;
                    case 5:
                        makeNavigation("خدمات طبيه");
                        break;
                    case 6:
                        makeNavigation("مستشفيات");
                        break;
                    case 7:
                        makeNavigation("معارض");
                        break;
                    case 8:
                        makeNavigation("موبيلات");
                        break;
                    case 9:
                        makeNavigation("مشاوير");
                        break;
                    case 10:
                        makeNavigation("خدمات اخري");
                        break;
                    case 11:
                        makeNavigation("خدمات تعليميه");
                        break;
                    case 12:
                        makeNavigation("قاعات");
                        break;
                }
            }
        }catch (Exception e){

        }
    }

    private void setData() {
        if (networkConnection.isNetworkAvailable(this.getContext())) {
            productPresenter = new ProductPresenter(this.getContext(), this);
            productPresenter.getProductData(id,1);

        }else {
            error(getString(R.string.no_internet_msg));
        }
    }




    @Override
    public void getProductData(ArrayList<Product> products) {
        if(this.products==null) {
            this.products = products;
            if(this.products==null||this.products.size()==0)
            {
                Toast.makeText(getActivity(), "عفوا لا توجد بيانات الان", Toast.LENGTH_SHORT).show();
            }
            else{
                skeletonScreen.hide();
                setRecycleContent();
            }
        }
        else
        {
            for (Product product:products){
                this.products.add(product);
            }
            productsAdapter.notifyDataSetChanged();
        }

    }


    private void setRecycleContent() {
        if(productsAdapter==null) {
            productsAdapter = new ProductsAdapter(this.getContext(), products);
            productsAdapter.onClick(this);
            recyclerView.setAdapter(productsAdapter);
        }
    }


    @Override
    public void error(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();


    }



    @Override
    public void openCategoryItmes(Product product) {
        ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", product);
        bundle.putInt("id",id);
        productDetailsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add( R.id.frame
                ,productDetailsFragment )
                .addToBackStack( null ).commit();

    }

}
