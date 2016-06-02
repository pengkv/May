package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;

import com.pengkv.may.util.rxjava.IResultHandler;
import com.pengkv.may.util.rxjava.RxRequest;

/**
 * Created by Administrator on 2016/5/23.
 */
public class RxJavaActivity extends BaseActivity implements IResultHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void fetchData() {
        RxRequest.getSingleImage(this, "171");
    }

    @Override
    public void upData(Object o) {
        Log.v("-->", o.toString());
    }
}
