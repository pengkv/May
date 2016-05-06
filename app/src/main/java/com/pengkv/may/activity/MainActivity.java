package com.pengkv.may.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.databinding.ActivityHomeBinding;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.bean.ImageDetailBean;
import com.pengkv.may.model.param.GetImageDetailParam;
import com.pengkv.may.model.param.GetImageListParam;

public class MainActivity extends BaseActivity implements IRequestHander, View.OnClickListener {


    ActivityHomeBinding binding;
    ImageDetailBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        fetchData(TAG_B);

    }


    @Override
    public void fetchData(int tag) {
        if (tag == TAG_A) {
            GetImageListParam param = new GetImageListParam("1", "10", "1");
            ApiClient.getImageList(this, param, tag);
        } else if (tag == TAG_B) {
            GetImageDetailParam param = new GetImageDetailParam("711");
            ApiClient.getImageDetail(this, param, tag);
        }

    }

    @Override
    public void updateUI(Object response, int tag) {
        Log.v("-->", response.toString());
        mBean = (ImageDetailBean) response;
        binding.setImage(mBean);
    }

    @Override
    public void onClick(View v) {
        fetchData(TAG_B);
//        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }


    public class MyHandlers {
        public void onClickFriend(View view) {
            Log.v("-->", "onClickFriend");
        }

        public void onClickEnemy(View view) {
            Log.v("-->", "onClickEnemy");
        }
    }
}
