package com.pengkv.may.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SpalshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }
}
