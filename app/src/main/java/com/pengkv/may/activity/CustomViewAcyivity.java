package com.pengkv.may.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.amap.api.maps2d.MapView;
import com.baidu.mapapi.SDKInitializer;
import com.pengkv.may.R;
import com.pengkv.may.util.SystemUtil;
import com.pengkv.may.util.UnitUtil;
import com.pengkv.may.widget.BezierView;
import com.pengkv.may.widget.CarView;
import com.pengkv.may.widget.ScrollBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/5/25.
 */
public class CustomViewAcyivity extends BaseActivity {

    private AnimationDrawable animationDrawable = null;
    private ImageView mImageView = null;


    Toolbar toolbar;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mWindowLayoutParams;
    TextView mTv;
    boolean isShowing;//是否正在显示
    int i = 1;

    MapView mMapView = null;


    public static String getDeivceId(Context mActivity) {
        TelephonyManager telephonyManager = (TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = telephonyManager.getDeviceId();
        final String androidId = Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String uuid = "";
        if (deviceId != null) {
            uuid = deviceId;
        } else {
            if (!"9774d56d682e549c".equals(androidId)) {
                uuid = androidId;
            } else {
                uuid = UUID.randomUUID().toString();
            }
        }
        Log.v("-->", deviceId + "/" + androidId + "/" + UUID.randomUUID().toString());
        Log.v("-->", deviceId);
        Log.v("-->", androidId);
        Log.v("-->", UUID.randomUUID().toString());
        return uuid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_custom_view);

//        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.view_gaode_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);

        getDeivceId(this);

        final BezierView bezierView = $(R.id.view_bezier);
        bezierView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezierView.move();
            }
        });


        final CarView carView = $(R.id.view_car);
        carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carView.start();
            }
        });


        //------------------------------滚动----------------------------------------------
        ViewFlipper flipper = $(R.id.view_flipper);
        Animation rInAnim = AnimationUtils.loadAnimation(this, R.anim.fliper_in);
        Animation rOutAnim = AnimationUtils.loadAnimation(this, R.anim.fliper_out);
        flipper.setInAnimation(rInAnim);
        flipper.setOutAnimation(rOutAnim);
        for (int i = 0; i < 3; i++) {          // 添加图片源
            TextView tv = new TextView(this);
            tv.setText(i + "版");
            tv.setGravity(Gravity.CENTER);

            switch (i) {
                case 0:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.oragnge_deeep, null));
                    break;
                case 1:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.oragnge_stand, null));
                    break;
                case 2:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.oragnge_light, null));
                    break;
            }
            flipper.addView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


        ViewFlipper flipper2 = $(R.id.view_flipper2);
        Animation rInAnim2 = AnimationUtils.loadAnimation(this, R.anim.fliper_in2);
        Animation rOutAnim2 = AnimationUtils.loadAnimation(this, R.anim.fliper_out2);
        flipper2.setInAnimation(rInAnim2);
        flipper2.setOutAnimation(rOutAnim2);
        for (int i = 0; i < 3; i++) {          // 添加图片源
            TextView tv = new TextView(this);
            tv.setText(i + "版");
            tv.setGravity(Gravity.CENTER);

            switch (i) {
                case 0:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green_deep, null));
                    break;
                case 1:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green_stand, null));
                    break;
                case 2:
                    tv.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.green_light, null));
                    break;
            }
            flipper2.addView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


        //------------------------------悬停----------------------------------------------
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("我是第" + (i + 1) + "个选择项");
        }

        toolbar = $(R.id.toolbar);
        ListView listView = $(R.id.view_list);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.item_text, list));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 8) {//滑动到目的项时，显示悬停布局
                    if (!isShowing)
                        show();
                } else {
                    if (isShowing)
                        hide();
                }
            }
        });


        animationDrawable = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_load, null);
        mImageView = (ImageView) findViewById(R.id.iv_img);
        mImageView.setImageDrawable(animationDrawable);
        animationDrawable.start();


        //------------------------------滚动----------------------------------------------
        String[] strings = new String[]{"打折促销，买一送一，送完为止，先到先得", "喜庆国庆欢乐大酬宾", "热烈欢迎领导光临", "老板和小姨子跑了，跳楼大甩卖"};
        final ScrollBanner scrollBanner = $(R.id.view_banner);
        scrollBanner.setList(Arrays.asList(strings));
        scrollBanner.startScroll();

        scrollBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollBanner.stopScroll();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    //显示悬停布局
    public void show() {
        isShowing = true;
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; //图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP;
        mWindowLayoutParams.y = toolbar.getHeight() - SystemUtil.getStatusHeight(this);//设置悬停布局显示的Y坐标
        mWindowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowLayoutParams.height = UnitUtil.dp2px(this, 50);//设置悬停布局显示的高度
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        //设置悬停布局，为了看起来是悬停效果，布局的内容要设置成与ItemView一致
        mTv = new TextView(this);
        mTv.setGravity(Gravity.CENTER);
        mTv.setBackgroundColor(getResources().getColor(R.color.white));
        mTv.setTextSize(UnitUtil.px2sp(this, UnitUtil.dp2px(this, 16)));
        mTv.setText("我是第10个选择项");

        //添加悬停布局
        mWindowManager.addView(mTv, mWindowLayoutParams);
    }

    //隐藏悬停布局
    public void hide() {
        if (mTv != null) {
            isShowing = false;
            mWindowManager.removeView(mTv);
            mTv = null;
        }
    }


}
