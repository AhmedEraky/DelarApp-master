package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Product;
import com.vegeta.my.dealer.model.product.ProductResponse;

import java.util.ArrayList;

/**
 * Created by Eraky on 1/18/2019.
 */

public interface ProductDataInterface {
    void getProductData(ArrayList<Product> products);
    void error(String error);
}
