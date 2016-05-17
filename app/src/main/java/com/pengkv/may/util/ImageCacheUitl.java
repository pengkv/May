package com.pengkv.may.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/5/16.
 * http://blog.csdn.net/baidu_23086307/article/details/51272729
 * 图片获取工具，三级缓存
 */
public class ImageCacheUitl {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    private Context context;
    private LruCache<String, Bitmap> cache;
    private File cacheDir;
    private Handler handler;
    private ExecutorService executorService;

    public ImageCacheUitl(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        cacheDir = context.getCacheDir();//获得cache文件夹
        executorService = Executors.newFixedThreadPool(5);//维护几个网络线程下载图片
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);//获得运行环境的内存大小的1/8
        cache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight(); //返回当前一行所占的字节数*高度，就是图片的大小
            }
        };
    }

    //通过url获取图片
    public Bitmap getBitmapFromUrl(String url, int position) {
        Bitmap bitmap = cache.get(url);
        if (bitmap != null) {
            Log.v("-->", "从内存中获得图片" + url);
            return bitmap;
        }

        bitmap = getBitmapFromFile(url);
        if (bitmap != null) {
            Log.v("-->", "从文件中获得图片" + url);
            return bitmap;
        }

        Log.i("-->", "从网络中获得图片" + url);
        getBitmapFromNet(url, position);
        return null;
    }

    //从文件中获取图片
    private Bitmap getBitmapFromFile(String url) {
        try {
            String[] strings = url.split("/");
            String bitmapFileName = strings[strings.length - 1];
            File file = new File(cacheDir, bitmapFileName);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap == null)
                return null;
            cache.put(url, bitmap);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //从网络中获取图片
    private void getBitmapFromNet(String url, int position) {
        executorService.execute(new RunnableTask(url, position));
    }

    //图片下载线程
    private class RunnableTask implements Runnable {
        private String imageUrl;
        private int position;
        private HttpURLConnection httpURLConnection;

        public RunnableTask(String imageUrl, int position) {
            this.imageUrl = imageUrl;
            this.position = position;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageUrl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    msg.arg1 = position;
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);
                    cache.put(imageUrl, bitmap);
                    writeToLoc(imageUrl, bitmap);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            }
            handler.obtainMessage(FAIL).sendToTarget();
        }
    }

    //图片存储到本地
    private void writeToLoc(String imageUrl, Bitmap bitmap) {
        try {
            String[] strings = imageUrl.split("/");
            String bitmapFileName = strings[strings.length - 1];
            Log.v("-->", "bitmapFileName" + bitmapFileName);
            File file = new File(cacheDir, bitmapFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
