package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.pengkv.may.R;


/**
 * Created by Administrator on 2016/6/3.
 * 加载动画
 */
public class LoadingWindow implements View.OnClickListener {
    public View view;
    public WindowManager windowManager;
    private static Context context;
    private boolean isCanCancel;

    private volatile static LoadingWindow INSTANCE;

    private LoadingWindow() {
    }

    public static synchronized LoadingWindow getInstance(Context context) {
        LoadingWindow.context = context;
        if (INSTANCE == null) {
            synchronized (LoadingWindow.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoadingWindow();
                }
            }
        }
        return INSTANCE;
    }

    public void show() {
        initView();
    }

    public void show(boolean isCanCancel) {
        this.isCanCancel = isCanCancel;
        initView();
    }


    public void initView() {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);

        view = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
        view.setOnClickListener(this);

//        int toolbarHeight = SystemUtil.getStatusHeight(context);
//        FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        fParams.setMargins(0, toolbarHeight, 0, 0);
//        fParams.gravity = Gravity.CENTER;
//        view.setLayoutParams(fParams);

        windowManager.addView(view, params);
    }


    public void dismiss() {
        if (view != null)
            windowManager.removeView(view);
    }


    @Override
    public void onClick(View v) {
        if (isCanCancel) {
            dismiss();
        }
    }
}
