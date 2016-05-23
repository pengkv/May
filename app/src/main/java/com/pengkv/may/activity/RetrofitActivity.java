package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;

import com.pengkv.may.R;
import com.pengkv.may.interfaces.RequestService;
import com.pengkv.may.model.bean.SingleImageBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/20.
 * https://segmentfault.com/a/1190000004536439
 */
public class RetrofitActivity extends BaseActivity {

    private static final String BASE_URL = "http://www.tngou.net/tnfs/api/";

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
        Call<SingleImageBean> call = service.getImage("171");

        //3.发送请求
        call.enqueue(new Callback<SingleImageBean>() {
            @Override
            public void onResponse(Call<SingleImageBean> call, Response<SingleImageBean> response) {
                //4.处理结果
                if (response.isSuccess()) {
                    SingleImageBean result = response.body();
                    if (result != null) {
                        Log.v("-->", result.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleImageBean> call, Throwable t) {

            }
        });
    }
}
