package com.pengkv.may.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.config.ApiClient;
import com.pengkv.may.interfaces.IRequestHander;
import com.pengkv.may.model.param.GetImageDetailParam;
import com.pengkv.may.model.param.GetImageListParam;

public class MainActivity extends BaseActivity implements IRequestHander,View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void fetchData(int tag) {
        if (tag==TAG_A){
            GetImageListParam param = new GetImageListParam("1","10","1");
            ApiClient.getImageList(this, param, tag);
        }else if (tag==TAG_B){
            GetImageDetailParam param = new GetImageDetailParam("710");
            ApiClient.getImageDetail(this, param, tag);
        }

    }

    @Override
    public void updateUI(Object response, int tag) {
        Log.v("-->",response.toString());

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }

}
