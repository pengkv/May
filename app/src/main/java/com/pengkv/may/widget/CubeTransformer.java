package com.pengkv.may.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/5/11.
 * ViewPager的翻页效果
 */
public class CubeTransformer implements ViewPager.PageTransformer {

    private static final float ROT_MAX = 20.0f;
    private float mRot;

    public void transformPage(View view, float position) {
        if (position < -1) { // [-Infinity,-1)
            view.setRotation(0);

        } else if (position <= 1) // a页滑动至b页 ； a页从 0.0 ~ -1 ；b页从1 ~ 0.0
        {
            if (position < 0) {
                mRot = (ROT_MAX * position);
                view.setPivotX(view.getMeasuredWidth() * 0.5f);
                view.setPivotY(view.getMeasuredHeight());
                view.setRotation(mRot);
            } else {
                mRot = (ROT_MAX * position);
                view.setPivotX(view.getMeasuredWidth() * 0.5f);
                view.setPivotY(view.getMeasuredHeight());
                view.setRotation(mRot);
            }
        } else {
            view.setRotation(0);
        }
    }
}