package com.pengkv.may.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


/**
 * Created by Administrator on 2016/5/11.
 * 判断网络状态工具类
 */
public class NetworkUtil {
    public static int NetCode = 1;

    /**
     * 判断网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null)
                    return info.isConnected();
            }
        }
        return false;
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null)
                    return info.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isWifiAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    return info.isAvailable();
                }
            }
        }
        return false;
    }


    /**
     * 检查当前环境网络是否可用，不可用跳转至开启网络界面
     */
    public static void validateNetWork(final Context context) {
        if (!isNetworkConnected(context)) {
            new AlertDialog.Builder(context)
                    .setMessage("网络不可用，是否现在设置网络?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((Activity) context).startActivityForResult(new Intent(Settings.ACTION_SETTINGS), NetCode);
                        }
                    }).show();
        }
    }

}
