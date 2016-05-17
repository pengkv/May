package com.pengkv.may.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pengkv.may.R;
import com.pengkv.may.util.ImageCacheUitl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CacheActivity extends BaseActivity implements View.OnClickListener {

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

        imageCacheUitl = new ImageCacheUitl(CacheActivity.this, handler);


//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new RunTask());
//        }
//        if (!executorService.isShutdown()) {
//            executorService.shutdown();
//        }
//
//
//        try {
//            List<Future<String>> results = new ArrayList<>();
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            for (int i = 0; i < 7; i++) {
//                results.add(executorService.submit(new CallTask()));
//            }
//            for (Future<String> future : results) {
//                Log.v("-->future", future.get());
//            }
//            Log.v("-->", "Main complete");
//
//            if (!executorService.isShutdown()) {
//                executorService.shutdown();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }


        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            FutureTask<String> futureTask = new FutureTask<>(
                    new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            Log.v("-->call", Thread.currentThread().getName() + "execute!!!");
                            return Thread.currentThread().getName() + "complete";
                        }
                    });
            // 提交futureTask
            executorService.submit(futureTask);
            Log.v("-->futureTask", futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        String s = "http://i0.sinaimg.cn/travel/2015/0507/U9385P704DT20150507151553.jpg";
        Bitmap bitmap = imageCacheUitl.getBitmapFromUrl(s, 0);
        imageView.setImageBitmap(bitmap);
    }


    //一般线程
    public class RunTask implements Runnable {
        @Override
        public void run() {
            Log.v("-->run", Thread.currentThread().getName() + "execute!!!");
        }
    }

    //回调线程
    public class CallTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Log.v("-->call", Thread.currentThread().getName() + "execute!!!");
            return Thread.currentThread().getName() + "complete";
        }
    }


}
