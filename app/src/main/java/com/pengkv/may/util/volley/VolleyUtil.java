package com.pengkv.may.util.volley;

import android.app.Activity;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pengkv.may.activity.BaseActivity;
import com.pengkv.may.model.param.BaseParam;
import com.pengkv.may.util.NetworkUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 2016/4/30.
 */
public class VolleyUtil {

    public Context mContext;
    public ResultHandler mResultHandler;

    public static VolleyUtil newInstance(Context context) {
        VolleyUtil volleyUtil = newInstance(context, 0);
        return volleyUtil;
    }

    public static VolleyUtil newInstance(Context context, int tag) {
        VolleyUtil volleyUtil = new VolleyUtil();
        volleyUtil.mContext = context;
        volleyUtil.mResultHandler = new ResultHandler(context);
        volleyUtil.mResultHandler.mTag = tag;
        return volleyUtil;
    }

    public void doPost(final String jsonUrl, final BaseParam paramStr, final Class<?> desClass) {
        if (!NetworkUtil.isNetworkConnected(mContext))
            return;

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                mResultHandler.mTargetClass = desClass;
                mResultHandler.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResultHandler.onError(volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return Bean2Map(paramStr);
            }
        };

        if (mContext instanceof BaseActivity) {
            stringRequest.setTag(((Activity) mContext).getLocalClassName());
        }

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2500, 3, 1f));
        VolleySingleton.getInstance().addToRequestQueue(stringRequest);
    }

    public void doGet(final String jsonUrl, final Class<?> desClass) {
        if (!NetworkUtil.isNetworkConnected(mContext))
            return;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                mResultHandler.mTargetClass = desClass;
                mResultHandler.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResultHandler.onError(volleyError);
            }
        });

        if (mContext instanceof BaseActivity) {
            stringRequest.setTag(((Activity) mContext).getLocalClassName());
        }

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2500, 3, 1f));
        VolleySingleton.getInstance().addToRequestQueue(stringRequest);
    }


    public static <K, V> Map<K, V> Bean2Map(Object javaBean) {
        Map<K, V> map = new HashMap<>();
        try {
            Method[] methods = javaBean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    //Object value = method.invoke(javaBean, (Object[]) null);
                    String value = String.valueOf(method.invoke(javaBean, (Object[]) null));
                    map.put((K) field, (V) (null == value ? "" : value));
                }
            }
        } catch (Exception e) {
            throw new ClassCastException("注意: 参数转化错误！");
        }
        return map;
    }

}
