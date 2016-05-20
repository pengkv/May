package com.pengkv.may.interfaces;

import com.pengkv.may.model.bean.PhoneBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface RequestService {

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneBean> getResult(@Header("apikey") String apikey, @Query("phone") String phone);

}
