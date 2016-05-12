package com.pengkv.may.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.databinding.ActivityImageDetailBinding;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.bean.ImageDetailBean;
import com.pengkv.may.model.param.GetImageDetailParam;

public class ImageDetailActivity extends BaseActivity implements IRequestHander {

    private ActivityImageDetailBinding binding;
    private ImageDetailBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_detail);
        binding.setHandler(new Handlers());

        initToolbar(binding.bar.toolbar, "图片详情");

        fetchData(TAG_A);

    }


    @Override
    public void fetchData(int tag) {
        GetImageDetailParam param = new GetImageDetailParam("711");
        ApiClient.getImageDetail(this, param, tag);
    }

    @Override
    public void updateUI(Object response, int tag) {
        mBean = (ImageDetailBean) response;
        binding.setImage(mBean);
    }


    public class Handlers {

        public void onClickFriend(View view) {
            Log.v("-->", "onClickFriend");
            mBean.setTitle("变身");
        }

        public void onClickEnemy(View view) {
            Log.v("-->", "onClickEnemy");
        }
    }

}
