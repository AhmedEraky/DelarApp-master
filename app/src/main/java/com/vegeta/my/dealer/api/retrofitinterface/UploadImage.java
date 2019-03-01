package com.vegeta.my.dealer.api.retrofitinterface;

import com.vegeta.my.dealer.model.product.Specialist;
import com.vegeta.my.dealer.model.product.UploadImageResponse;

import java.util.ArrayList;

public interface UploadImage {
    void UploadImageData(UploadImageResponse uploadImageResponse);
    void error(String error);
}
