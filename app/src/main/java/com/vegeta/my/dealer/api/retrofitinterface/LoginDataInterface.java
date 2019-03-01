package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.login.LoginResponse;

/**
 * Created by Eraky on 1/19/2019.
 */

public interface LoginDataInterface {
    void LoginUser(LoginResponse response);
    void error(String errorMsg);

}
