package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.login.LoginResponse;
import com.vegeta.my.dealer.model.login.UserInfoResponse;

public interface UserInfoInterface {
    void LoginUser(UserInfoResponse response);
    void error(String errorMsg);
}
