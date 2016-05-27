package com.pengkv.may.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Liam on 2014/12/24.
 * 系统工具类
 */
public class SystemUtil {

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Activity ctx, View v) {
        try {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (v == null) {
                imm.hideSoftInputFromWindow(ctx.getCurrentFocus().getWindowToken(), 0);
            } else {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Activity ctx) {
        hideKeyboard(ctx, null);
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(final Activity ctx, final EditText targetEditText) {
        new Handler().post(
                new Runnable() {
                    public void run() {
                        InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(ctx.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInputFromWindow(targetEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                        targetEditText.requestFocus();
                    }
                });
    }

    /**
     * 获取屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;  // 屏幕宽度（像素）
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeight(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;  // 屏幕高度（像素）
    }

    /**
     * 获取屏幕密度(简洁)
     */
    public static int getDisplayDensity() {
        return (int) Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}
