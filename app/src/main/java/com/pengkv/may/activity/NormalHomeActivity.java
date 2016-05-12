package com.pengkv.may.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.pengkv.may.R;
import com.pengkv.may.fragment.AFragment;
import com.pengkv.may.fragment.BFragment;
import com.pengkv.may.fragment.CFragment;

import java.util.List;

/**
 * Created by pro on 2016/5/3.
 */
public class NormalHomeActivity extends BaseActivity {

    private AFragment aFragment;
    private BFragment bFragment;
    private CFragment cFragment;
    private RadioGroup mRadioGroup;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narmal_home);

        mRadioGroup = $(R.id.rg_tab);

        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.fl_fragment, new AFragment()).commit();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//设置点击事件
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = mManager.beginTransaction();
                hideFragments(transaction);
                switch (checkedId) {
                    case R.id.rb_a:
                        if (aFragment == null) {
                            aFragment = new AFragment();
                            transaction.add(R.id.fl_fragment, aFragment);
                        } else {
                            transaction.show(aFragment);
                        }
                        break;
                    case R.id.rb_b:
                        if (bFragment == null) {
                            bFragment = new BFragment();
                            transaction.add(R.id.fl_fragment, bFragment);
                        } else {
                            transaction.show(bFragment);
                        }
                        break;
                    case R.id.rb_c:
                        if (cFragment == null) {
                            cFragment = new CFragment();
                            transaction.add(R.id.fl_fragment, cFragment);
                        } else {
                            transaction.show(cFragment);
                        }
                        break;
                }
                transaction.commit();
            }
        });

    }


    /**
     * 隐藏所有的fragment
     */
    protected void hideFragments(FragmentTransaction transaction) {
        List<Fragment> list = getSupportFragmentManager().getFragments();
        if (null != list) {
            for (Fragment fragment : list) {
                if (null != fragment)
                    transaction.hide(fragment);
            }
        }
    }

}
