package com.pengkv.may.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.pengkv.may.R;
import com.pengkv.may.config.EventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by pro on 2016/4/30.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);//状态栏变透明
        }

        //获取当前页面的名字
        String contextString = this.toString();
        Log.v("ActivityName", contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@")));

        EventBus.getDefault().register(this);
    }

    //初始化标题栏
    public void initToolbar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView mLeftTV = (TextView) toolbar.findViewById(R.id.tv_bar_left);
            TextView mTitleTV = (TextView) toolbar.findViewById(R.id.tv_bar_title);
            mLeftTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mTitleTV.setText(title);
        }
    }

    //初始化标题栏(带右侧菜单键)
    public void initToolbar(Toolbar toolbar, String title, String menuStr) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView mLeftTV = (TextView) toolbar.findViewById(R.id.tv_bar_left);
            TextView mTitleTV = (TextView) toolbar.findViewById(R.id.tv_bar_title);
            TextView mRightTV = (TextView) toolbar.findViewById(R.id.tv_bar_right);

            mLeftTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            mTitleTV.setText(title);

            mRightTV.setVisibility(View.VISIBLE);
            mRightTV.setText(menuStr);
        }
    }

    //简化view映射方法
    public <T> T $(int viewID) {
        return (T) findViewById(viewID);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMainEventBus(EventType eventType) {
    }
}
