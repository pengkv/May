package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;

import com.pengkv.may.interfaces.RequestService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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


        //1.创建Observable对象
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

        //2.简洁代码创建Observable对象
        Observable<String> myObservable2 = Observable.just("Hello, world!");

        //3.单事件处理
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable2.subscribe(onNextAction);

        //3.单事件处理
        Observable.just("").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });

        //4.map操作符，把一个事件转化成另一个事件
        Observable.just("").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

            }
        });

        final String[] urls = new String[]{"1", "2", "3"};

        query("").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                for (String url : urls) {
                    Log.v("-->", url);
                }
            }
        });

        Observable.from(urls).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v("-->", s);
            }
        });

        //from接收一个集合作为输入，然后每次输出一个元素给subscriber
        query("").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Observable.from(urls).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.v("-->", s);
                    }
                });
            }
        });

        //flatMap接收一个Observable的输出作为输入，同时输出另外一个Observable
        query("").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(urls);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v("-->", s);
            }
        });

        //4.map操作符，把一个事件转化成另一个事件
        Observable.just("").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {

            }
        });

    }

    Observable<List<String>> query(String text) {
        return null;
    }

    Observer<Object> observer = new Observer<Object>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object o) {
            Log.v("-->", o.toString());
        }
    };


}
