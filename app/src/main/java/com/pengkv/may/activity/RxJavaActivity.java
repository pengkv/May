package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;

import com.pengkv.may.interfaces.RequestService;
import com.pengkv.may.model.bean.SingleImageBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/23.
 */
public class RxJavaActivity extends BaseActivity {

    private static final String BASE_URL = "http://www.tngou.net/tnfs/api/";
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加回调适配器广场
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        RequestService service = retrofit.create(RequestService.class);

        //3.订阅观察者
        subscription = service.getSingleImage("171")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    Observer<SingleImageBean> observer = new Observer<SingleImageBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(SingleImageBean imageBean) {
            Log.v("-->", imageBean.toString());
        }
    };
}
