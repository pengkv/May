package com.pengkv.may.config;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.pengkv.may.model.bean.ImageDetailBean;
import com.pengkv.may.model.bean.SingleImageBean;
import com.pengkv.may.model.param.GetImageDetailParam;
import com.pengkv.may.model.param.GetImageListParam;
import com.pengkv.may.util.volley.VolleyUtil;

/**
 * Created by pro on 2016/4/30.
 */
public class ApiClient extends ApiBaseClient {


    public static void getImageList(Context context, GetImageListParam param,int tag){
        VolleyUtil.newInstance(context,tag).doPost(Basic.URL_LIST,param, SingleImageBean.class);
    }

    public static void getImageDetail(Context context, GetImageDetailParam param, int tag){
        VolleyUtil.newInstance(context,tag).doPost(Basic.URL_DETAIL,param, ImageDetailBean.class);
    }



    public static String getJsonStr(Object param) {
        String s = "";
        if (param != null) {
            s = JSON.toJSONString(param);
//            s = EncodingUtils.getString(s.getBytes(), "utf-8");
        }
        return s;
    }


}
