package com.pengkv.may.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.pengkv.may.R;

/**
 * Created by pro on 2016/4/30.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);//状态栏变透明
        }
        initToolbar();
    }

    //初始化标题栏
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        TextView mLeftTV = (TextView) toolbar.findViewById(R.id.tv_bar_left);
        TextView mTitleTV = (TextView) toolbar.findViewById(R.id.tv_bar_title);
        TextView mRightTV = (TextView) toolbar.findViewById(R.id.tv_bar_right);


    }

    //简化view映射方法
    public <T> T $(int viewID) {
        return (T) findViewById(viewID);
    }
}
