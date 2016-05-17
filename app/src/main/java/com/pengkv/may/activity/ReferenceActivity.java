package com.pengkv.may.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pengkv.may.R;
import com.pengkv.may.util.ImageCacheUitl;

/**
 * Created by Administrator on 2016/5/16.
 */
public class ReferenceActivity extends BaseActivity implements View.OnClickListener {


    //    private Runnable runnable;
    private Handler handler;
    ImageView imageView;
    ImageCacheUitl imageCacheUitl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        imageView = $(R.id.iv_img);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ImageCacheUitl.SUCCESS:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        if (null != bitmap && null != imageView) {
                            imageView.setImageBitmap(bitmap);
                        }
                        Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_LONG).show();
                        break;
                    case ImageCacheUitl.FAIL:
                        Toast.makeText(getApplicationContext(), "下载错误", Toast.LENGTH_LONG).show();
                    default:
                        break;
                }
            }
        };

        imageCacheUitl = new ImageCacheUitl(ReferenceActivity.this, handler);
    }

    @Override
    public void onClick(View v) {
        String s = "http://i0.sinaimg.cn/travel/2015/0507/U9385P704DT20150507151553.jpg";
//        String s = "baidu.jpg";
        Bitmap bitmap = imageCacheUitl.getBitmapFromUrl(s, 0);
        imageView.setImageBitmap(bitmap);
    }
}
