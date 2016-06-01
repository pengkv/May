package com.pengkv.may.interfaces;

import com.pengkv.may.model.bean.SingleImageBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface RequestService {

    //单独使用retrofit请求
    @GET("show")
    Call<SingleImageBean> getImage(@Query("id") String id);

    //rxjava+retrofit请求
    @GET("show")
    Observable<Object> getSingleImage(@Query("id") String id);

}
