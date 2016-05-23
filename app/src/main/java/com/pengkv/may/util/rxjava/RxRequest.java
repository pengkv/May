package com.pengkv.may.util.rxjava;

import com.pengkv.may.interfaces.RequestService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/5/23.
 */
public class RxRequest<T> {


    private static final String BASE_URL = "http://www.tngou.net/tnfs/api/";
    public Subscription subscription;


    public static RequestService getRequestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加回调适配器广场
                .baseUrl(BASE_URL)//主机地址
                .build();

        return retrofit.create(RequestService.class);
    }

//
//    public void getSingleImage(Subscriber<RequestResult<Subject>> subscriber, String id) {
//        //3.订阅观察者
//        getRequestService().getSingleImage("171")
//                .map(new ResultErrorHandler<List<Subject>>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((Action1<? super List<Subject>>) subscriber);
//    }


    public class ResultErrorHandler<T> implements Func1<RequestResult<T>, T> {

        @Override
        public T call(RequestResult<T> httpResult) {
            if (httpResult.getResultCode() != 0) {
                try {
                    throw new Exception(httpResult.getResultMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return httpResult.getData();
        }

    }


}
