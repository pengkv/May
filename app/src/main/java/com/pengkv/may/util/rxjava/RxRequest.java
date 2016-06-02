package com.pengkv.may.util.rxjava;

import android.content.Context;

import com.pengkv.may.interfaces.RequestService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/23.
 */
public class RxRequest {
    private static final String BASE_URL = "http://www.tngou.net/tnfs/api/";
    public static Context context;

    private volatile static RxRequest INSTANCE;
    RequestService requestService;

    private RxRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加回调适配器广场
                .baseUrl(BASE_URL)//主机地址
                .build();

        requestService = retrofit.create(RequestService.class);
    }

    private static synchronized RxRequest getInstance() {
        if (INSTANCE == null) {
            synchronized (RxRequest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxRequest();
                }
            }
        }
        return INSTANCE;
    }


    private static RequestService getRequestService() {
        return getInstance().requestService;
    }


    public static void getSingleImage(Context context, String id) {
        RxRequest.context = context;
        handle(getRequestService().getSingleImage(id));
    }

    //统一处理请求
    public static void handle(Observable<Object> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //RxJava的请求结果
    static Observer<Object> observer = new Observer<Object>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object o) {
            if (context instanceof IResultHandler)
                ((IResultHandler) context).upData(o);
        }
    };


}
