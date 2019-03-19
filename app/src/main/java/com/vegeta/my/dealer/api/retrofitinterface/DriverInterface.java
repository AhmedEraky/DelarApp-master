package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductDriver;

import java.util.ArrayList;

public interface DriverInterface {
    void getDriverData(ArrayList<ProductDriver> products);
    void error(String error);
}
