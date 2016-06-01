package com.pengkv.may.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pengkv.may.R;
import com.pengkv.may.util.SystemUtil;
import com.pengkv.may.util.UnitUtil;
import com.pengkv.may.widget.ScrollBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

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
