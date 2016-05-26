package com.pengkv.may.activity;

import android.os.Bundle;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.widget.ScrollBanner;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/5/25.
 */
public class CustomViewAcyivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);


        String[] strings = new String[]{"1.打折促销，买一送一", "2.喜庆国庆欢乐大酬宾", "3.热烈欢迎领导光临", "4.老板和小姨子跑了"};
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
}
