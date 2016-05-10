package com.pengkv.may.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by pro on 2016/4/30.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String contextString = this.toString();
        Log.v("FragmentName", contextString.substring(0, contextString.lastIndexOf("{")));
    }
}
