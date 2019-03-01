package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.AddResponse;
import com.vegeta.my.dealer.model.login.LoginResponse;

public interface AddProductInterface {
    void AddNewProduct(AddResponse response);
    void error(String errorMsg);
}

