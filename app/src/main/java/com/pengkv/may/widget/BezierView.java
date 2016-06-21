package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.pengkv.may.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/6/20.
 * 弹性小球
 */
public class BezierView extends View {

    Paint paint;//画笔
    Path path;//路径

    int radius = 50;//圆的半径
    int time = 100;//计数时长

    int index;
    int offsetIndex;
    float viewX, viewY;//图形中心点坐标

    float width;//屏幕宽度
    float partWidth;//屏幕宽度的1/4
    int paddingLeft, paddingRight;//图形内边距
    float x1, y1, x2, y2, x3, y3, x4, y4;//圆形左上右下四个点

    float x12, y12, x23, y23, x34, y34, x41, y41;//圆形左上右下四个点之间的渐变点

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
        paint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        width = getWidth() - paddingLeft - paddingRight;
        partWidth = width / 4;

        path = new Path();
        path.moveTo(x1, y1);
        path.cubicTo(x1, y1, x12, y12, x2, y2);
        path.cubicTo(x2, y2, x23, y23, x3, y3);
        path.cubicTo(x3, y3, x34, y34, x4, y4);
        path.cubicTo(x4, y4, x41, y41, x1, y1);
        canvas.drawPath(path, paint);

        move();
    }


    public void move() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (index < time - 1) {
                    index++;
                    viewX = width / time * index + paddingLeft;
                    viewY = 400;

                    x1 = viewX - radius;
                    x2 = viewX;
                    x3 = viewX + radius;
                    x4 = viewX;

                    y1 = viewY;
                    y2 = viewY - radius;
                    y3 = viewY;
                    y4 = viewY + radius;

                    offsetIndex = index % (time / 4) + 1;

                    //根据图形移动到的区域进行曲线变化
                    float position = (viewX - paddingLeft) / partWidth;

                    //右边半圆
                    if (position >= 0 && position < 1) {
                        x3 = viewX + radius + radius / (time / 4) * offsetIndex;
                    } else if (position >= 1 && position < 2) {
                        x3 = viewX + radius + radius;
                    } else if (position >= 2 && position < 3) {
                        x3 = viewX + radius + radius - radius / (time / 4) * offsetIndex;
                    } else {
                        x3 = viewX + radius;
                    }
                    x23 = x34 = x3;
                    y12 = y23 = y2;

                    //左边半圆
                    if (position >= 1 && position < 2) {
                        x1 = viewX - radius - radius / (time / 4) * offsetIndex;
                    } else if (position >= 2 && position < 3) {
                        x1 = viewX - radius - radius;
                    } else if (position >= 3) {
                        x1 = viewX - radius - radius + radius / (time / 4) * offsetIndex;
                    } else {
                        x1 = viewX - radius;
                    }
                    x12 = x41 = x1;
                    y34 = y41 = y4;

                    postInvalidate();
                } else {
                    cancel();
                }
            }
        }, 0, 5000);
    }

}
