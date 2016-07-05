package com.pengkv.may.config;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by pro on 2016/4/30.
 */
public class MayApplication extends Application {

    public static Context mContext;
    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        setAppContext(getApplicationContext());

        CONTEXT = this;

        // just for open the log in this demo project.
        FileDownloadLog.NEED_LOG = true;

        /**
         * just for cache Application's Context, and ':filedownloader' progress will NOT be launched
         * by below code, so please do not worry about performance.
         * @see FileDownloader#init(Context)
         */
        FileDownloader.init(getApplicationContext(),
                new FileDownloadHelper.OkHttpClientCustomMaker() { // is not has to provide.
                    @Override
                    public OkHttpClient customMake() {
                        // just for OkHttpClient customize.
                        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        // you can set the connection timeout.
                        builder.connectTimeout(15_000, TimeUnit.MILLISECONDS);
                        // you can set the HTTP proxy.
                        builder.proxy(Proxy.NO_PROXY);
                        // etc.
                        return builder.build();
                    }
                });

    }

    public static Context getAppContext() {
        return mContext;
    }

    public void setAppContext(Context mContext) {
        this.mContext = mContext;
    }
}
