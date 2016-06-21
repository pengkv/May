package com.pengkv.may.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by pro on 2016/4/30.
 */
public class MayApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setAppContext(getApplicationContext());
    }

    public static Context getAppContext() {
        return mContext;
    }

    public void setAppContext(Context mContext) {
        this.mContext = mContext;
    }
}
