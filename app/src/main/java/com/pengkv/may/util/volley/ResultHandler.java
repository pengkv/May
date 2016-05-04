package com.pengkv.may.util.volley;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.bean.ListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by pro on 2016/5/2.
 */
public class ResultHandler {
    private Context mContext;
    private String jsonUrl;
    public int mTag;
    public Class mTargetClass;

    public ResultHandler(Context mContext) {
        this.mContext = mContext;
    }


    public void onSuccess(String response) {
        try {
            onSuccess(new JSONObject(response));
        } catch (JSONException e) {
        }
    }
    public void onSuccess(JSONObject response){

        Object obj = null;

        if (mTargetClass == null) {
            obj = response;
        } else if (mTargetClass == Boolean.class) {
            obj = response.optBoolean("IsSuccess");
        } else if (mTargetClass == Long.class) {
            obj = response.optLong("Data");
        } else if (mTargetClass == Double.class) {
            obj = response.optDouble("Data");
        } else if (mTargetClass == Integer.class) {
            obj = response.optInt("Data");
        } else if (mTargetClass == String.class) {
            obj = response.optString("Data");
        } else if (mTargetClass == Date.class) {
            try {
                obj = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA).parse(response.optString("Data"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
//            JSONObject jsonObject = response.optJSONObject("Data");
            JSONObject jsonObject = response;

            if (jsonObject != null) {
                // 先取Total，如果有total为ListModel,没有则为EntityModel
                int total = jsonObject.optInt("total", -1);

                if (total == -1) {
                    // 按实体解析

                    obj = JSON.parseObject(jsonObject.toString(), mTargetClass);
                } else {
                    // 按列表实体解析

                    ListModel m = new ListModel();

                    JSONArray jsonArray = jsonObject.optJSONArray("tngou");

                    if (jsonArray != null) {
                        List list = JSON.parseArray(jsonArray.toString(), mTargetClass);

                        m.setTotal(total);
                        m.setList(list);
                    }

                    obj = m;
                }
            }
        }

        if (mContext instanceof IRequestHander){
            IRequestHander iRequestHander=(IRequestHander)mContext;
            iRequestHander.updateUI(obj,mTag);
        }

    }

    public void onError(VolleyError error){
        if (mContext instanceof IRequestHander){
            IRequestHander iRequestHander=(IRequestHander)mContext;
            iRequestHander.updateUI(null,mTag);
        }
    }
}
