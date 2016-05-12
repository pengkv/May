package com.pengkv.may.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.pengkv.may.R;
import com.pengkv.may.adapter.MainFragmentAdapter;
import com.pengkv.may.fragment.AFragment;
import com.pengkv.may.fragment.BFragment;
import com.pengkv.may.fragment.CFragment;
import com.pengkv.may.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 2016/5/3.
 */
public class MiddleHomeActivity extends BaseActivity {

    private TabViewPager mViewPager;
    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private RadioGroup mRadioGroup;
    private MainFragmentAdapter mAdapter;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_home);

        mViewPager = $(R.id.vp_tab);
        mRadioGroup = $(R.id.rg_tab);

        aFragment = new AFragment();
        bFragment = new BFragment();
        cFragment = new CFragment();

        mList.add(aFragment);
        mList.add(bFragment);
        mList.add(cFragment);

        mAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mAdapter);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//设置点击事件
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_a:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_b:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_c:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });

    }


}
