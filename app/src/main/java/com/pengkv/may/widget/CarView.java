package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.pengkv.may.R;
import com.pengkv.may.util.SystemUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/6/3.
 */
public class CarView extends View {

    Paint paint, speedAreaPaint;
    Paint textPaint;
    int width, height;//View的长宽
    int length;//View长宽中小的那个长度
    int minLength = 200;//View的最小长度
    private RectF speedRectF, speedRectFInner; //速度范围的2个扇形外切矩形

    int speed = 1;

    public void start() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (speed < 210) {
                    speed++;
                    postInvalidate();
                } else {
                    this.cancel();
                }
            }
        }, 0, 100);
    }

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setColor(0xFF000000);
        textPaint = new Paint();
        textPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.white, null));


        //设置抗锯齿
        speedAreaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        speedAreaPaint.setAntiAlias(true);
        //设置画笔样式
        speedAreaPaint.setStyle(Paint.Style.FILL);
        speedAreaPaint.setColor(0x7E3F51B5);

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

        int padding = 20;
        canvas.translate(width / 2, height / 2);
        canvas.drawCircle(0, 0, length / 2 - padding, paint);

        int innerPadding = 35;
        paint.setStrokeWidth(5);
        canvas.drawCircle(0, 0, length / 2 - innerPadding, paint);

        canvas.drawCircle(0, 0, length / 6, paint);


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

        int innerOffset = length / 3 + 3;
        speedRectF = new RectF(-length / 2 + innerPadding, -length / 2 + innerPadding, length / 2 - innerPadding, length / 2 - innerPadding);
        speedRectFInner = new RectF(-length / 2 + innerOffset, -length / 2 + innerOffset, length / 2 - innerOffset, length / 2 - innerOffset);
        drawSpeedArea(canvas);

        drawCenter(canvas);

        //单位
        textPaint.setTextSize(20 * SystemUtil.getDisplayDensity());
        float tw = textPaint.measureText("km/h");
        int baseX = (int) (-tw / 2);
        int baseY = (int) (width / 8 - 20 + Math.abs(textPaint.descent() + textPaint.ascent()) / 4);
        canvas.drawText("km/h", baseX, baseY, textPaint);


    }

    private void drawCenter(Canvas canvas) {
        //速度
        textPaint.setTextSize(30 * SystemUtil.getDisplayDensity());
        float tw = textPaint.measureText(String.valueOf(speed));
        int baseX = (int) (-tw / 2);
        int baseY = (int) (Math.abs(textPaint.descent() + textPaint.ascent()) / 4);
        canvas.drawText(String.valueOf(speed), baseX, baseY, textPaint);
    }


    /**
     * 绘制速度区域扇形
     */
    private void drawSpeedArea(Canvas canvas) {
        int degree;
        if (speed < 210) {
            degree = speed * 36 / 30;
        } else {
            degree = 210 * 36 / 30;
        }

        canvas.drawArc(speedRectF, 144, degree, true, speedAreaPaint);

        //不显示中间的内圈的扇形区域
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFFBBBBBB);
        canvas.drawArc(speedRectFInner, 144, degree, true, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF000000);
    }

}
