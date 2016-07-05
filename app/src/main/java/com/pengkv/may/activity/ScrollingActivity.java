package com.pengkv.may.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.pengkv.may.R;
import com.pengkv.may.util.UnitUtil;
import com.pengkv.may.widget.AxisScrollView;

public class ScrollingActivity extends BaseActivity implements AxisScrollView.OnScrollListener {
    TextView tv1, tv2;
    AxisScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        scrollView = (AxisScrollView) findViewById(R.id.scrollView);
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);

        scrollView.setOnScrollListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public void onScroll(int scrollY) {
        Log.v("-->", scrollY + "");
        if (scrollY > UnitUtil.dp2px(ScrollingActivity.this, 80)) {
            tv2.setVisibility(View.VISIBLE);
            tv2.setAlpha((scrollY - UnitUtil.dp2px(ScrollingActivity.this, 80)) / (float) UnitUtil.dp2px(ScrollingActivity.this, 60));
            Log.v("-->", "显示中");
        } else {
            tv2.setVisibility(View.GONE);
        }
    }
}
