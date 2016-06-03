package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.pengkv.may.R;

/**
 * Created by Administrator on 2016/6/3.
 */
public class CarView extends View {

    Paint paint;
    Paint textPaint;
    int width, height;//View的长宽
    int length;//View长宽中小的那个长度
    int minLength = 200;//View的最小长度

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setColor(ResourcesCompat.getColor(getResources(), R.color.oragnge_stand, null));
        textPaint = new Paint();
        textPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = measureSpec(widthMeasureSpec);
        height = measureSpec(heightMeasureSpec);
        length = Math.min(width, height);
        setMeasuredDimension(width, height); //设置View的大小
    }

    //测量长度
    private int measureSpec(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int length = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST) {
            length = Math.min(length, minLength);
        }
        return length;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        canvas.translate(width / 2, height / 2);
        canvas.drawCircle(0, 0, length / 2 - 20, paint);

        paint.setStrokeWidth(5);
        canvas.drawCircle(0, 0, length / 2 - 35, paint);

        canvas.drawCircle(0, 0, length / 5, paint);


        int offset = 35;
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0, -width / 2 + offset, 0, -width / 2 + offset + 30, paint);
                canvas.drawText("100", 0, -width / 2 + offset + 50, paint);
            } else {
                canvas.drawLine(0, -width / 2 + offset, 0, -width / 2 + offset + 15, paint);
            }
            canvas.rotate(360 / 60);
        }


    }
}
