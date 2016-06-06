package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TiltView extends ImageView {

    int imageWidth;
    int imageHeight;
    int angle = 360 + 20;
    int triangleHeight;

    Paint paint;

    public TiltView(Context context) {
        this(context, null);
    }

    public TiltView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TiltView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        imageWidth = measureSpec(widthMeasureSpec);
        imageHeight = measureSpec(heightMeasureSpec);
        setMeasuredDimension(imageWidth, imageHeight); //设置View的大小
        triangleHeight = (int) (Math.abs(Math.tan(angle) * imageHeight));
    }

    //测量长度
    private int measureSpec(int measureSpec) {
        int minLength = 200;
        int mode = MeasureSpec.getMode(measureSpec);
        int length = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            length = Math.min(length, minLength);
        }
        return length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();


        Bitmap mBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888); //初始化Bitmap
        Canvas mCanvas = new Canvas(mBitmap);
        Bitmap mBackBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        mCanvas.drawBitmap(mBackBitmap, 0, 0, null);//设置画板背景


        Path path = new Path();
        path.moveTo(0, imageHeight);
        path.lineTo(imageWidth, imageHeight);
        path.lineTo(imageWidth, imageHeight - triangleHeight);
        path.lineTo(0, imageHeight);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mCanvas.drawPath(path, paint);

        canvas.drawBitmap(mBitmap, 0, 0, null);
        

//        Bitmap mBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888); //初始化Bitmap
//        Canvas mCanvas = new Canvas(mBitmap);
//        Bitmap mBackBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
//        mCanvas.drawBitmap(mBackBitmap, 0, 0, null);//设置画板背景
//
//
//        Path path = new Path();
//        path.moveTo(0, imageHeight);
//        path.lineTo(imageWidth, imageHeight);
//        path.lineTo(imageWidth, imageHeight - triangleHeight);
//        path.lineTo(0, imageHeight);
//
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
//        mCanvas.drawPath(path, paint);
//
//        canvas.drawBitmap(mBitmap, 0, 0, null);

    }

    //初始化画笔
    private void initPaint() {
        paint = new Paint();
        paint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);//圆角
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
    }

}
