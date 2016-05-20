package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;

import com.pengkv.may.R;
import com.pengkv.may.interfaces.RequestService;
import com.pengkv.may.model.bean.PhoneBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/20.
 */
public class RetrofitActivity extends BaseActivity {

    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        query();

    }

    private void query() {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        RequestService service = retrofit.create(RequestService.class);
        Call<PhoneBean> call = service.getResult(API_KEY, "18868944725");

        //3.发送请求
        call.enqueue(new Callback<PhoneBean>() {
            @Override
            public void onResponse(Call<PhoneBean> call, Response<PhoneBean> response) {
                //4.处理结果
                if (response.isSuccess()) {
                    PhoneBean result = response.body();
                    if (result != null) {
                        PhoneBean.RetDataEntity entity = result.getRetData();
                        Log.v("-->", entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneBean> call, Throwable t) {

            }
        });
    }
}
