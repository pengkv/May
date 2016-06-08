package com.pengkv.may.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.pengkv.may.R;

/**
 * Created by Administrator on 2016/6/6.
 * 三张倾斜图片
 */
public class TiltListView extends LinearLayout {


    public TiltListView(Context context) {
        super(context);
    }

    public TiltListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TiltListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_tilt, null);
    }


}
