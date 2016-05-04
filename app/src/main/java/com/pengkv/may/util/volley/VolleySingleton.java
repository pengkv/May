package com.pengkv.may.util.volley;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.pengkv.may.config.MayApplication;

/**
 * Created by pro on 2016/5/2.
 */
public class VolleySingleton {
    public static final String TAG = "VolleySingleton";

    private static VolleySingleton INSTANCE;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(MayApplication.getAppContext());
    }

    public static synchronized VolleySingleton getInstance(){
        if (INSTANCE==null)
            INSTANCE=new VolleySingleton();
        return INSTANCE;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    //添加请求到队列(自定义Tag)
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

     //添加请求到队列(默认Tag)
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

   //取消所有请求
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    //取消所有请求
    public void cancelPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

}
