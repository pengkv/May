package com.pengkv.may.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pengkv.may.R;

/**
 * Created by pro on 2016/4/30.
 */
public class BaseFragment extends Fragment {

    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String contextString = this.toString();
        Log.v("FragmentName", contextString.substring(0, contextString.lastIndexOf("{")));
    }

    //初始化主页标题栏
    public void initToolbar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            TextView mLeftTV = (TextView) toolbar.findViewById(R.id.tv_bar_left);
            TextView mTitleTV = (TextView) toolbar.findViewById(R.id.tv_bar_title);
            mLeftTV.setVisibility(View.INVISIBLE);
            mTitleTV.setText(title);
        }
    }

    public <T> T $(int viewID) {
        return (T) view.findViewById(viewID);
    }
}
