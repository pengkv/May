package com.pengkv.may.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.databinding.ActivityMainBinding;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.bean.ImageDetailBean;
import com.pengkv.may.model.param.GetImageDetailParam;
import com.pengkv.may.model.param.GetImageListParam;

public class MainActivity extends BaseActivity implements IRequestHander {


    private ActivityMainBinding binding;
    private ImageDetailBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(new Handlers());

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


    public class Handlers {

        public void onClickFriend(View view) {
            Log.v("-->", "onClickFriend");
            mBean.setTitle("变身");
        }

        public void onClickEnemy(View view) {
            Log.v("-->", "onClickEnemy");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
