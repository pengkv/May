package com.pengkv.may.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/6/21.
 * 左侧和底部菜单栏
 */
public class HomeDragLayout extends RelativeLayout {

    private ViewDragHelper mDragHelper;

    private View mLeftView;//左侧菜单栏
    private View mBottomView;//底部信息栏

    private int maxWidth = 0;//水平位移最大距离
    private int maxHeight = 0;//上下位移最大距离
    private int minHeight = 0;//上下位移最小距离

    private boolean isLeftHide;//左侧菜单栏是否隐藏


    public HomeDragLayout(Context context) {
        this(context, null);
    }

    public HomeDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            //控制拖动的View
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mLeftView || (child == mBottomView && isLeftHide);
            }

            //设置水平滑动范围
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == mLeftView) {
                    return Math.min(left, maxWidth);
                } else {
                    return super.clampViewPositionHorizontal(child, left, dx);
                }
            }

            //设置垂直滑动范围
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (child == mBottomView) {

                    if (minHeight <= top && top <= maxHeight)
                        return top;
                    else if (top < minHeight)
                        return minHeight;
                    else
                        return maxHeight;

                } else {
                    return super.clampViewPositionVertical(child, top, dy);
                }
            }

            //手势释放处理
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == mLeftView) {
                    if (xvel > 0) {
                        mDragHelper.settleCapturedViewAt(0, 0);
                        isLeftHide = false;
                    } else {
                        mDragHelper.settleCapturedViewAt(-mLeftView.getWidth() + 1, 0);
                        isLeftHide = true;
                    }
                } else if (releasedChild == mBottomView) {
                    if (yvel > 0) {
                        mDragHelper.settleCapturedViewAt(0, maxHeight);
                    } else {
                        mDragHelper.settleCapturedViewAt(0, minHeight);
                    }
                }
                invalidate();
            }

            //边界拖动回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mDragHelper.captureChildView(mLeftView, pointerId);
            }
        });
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    //布局加载完毕处理
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBottomView = getChildAt(1);
        mLeftView = getChildAt(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //设置上下滑动距离
        int height = getHeight();
        int bottomHeight = mBottomView.getHeight();
        maxHeight = height - 200;
        minHeight = height - bottomHeight;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true))
            invalidate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }
}
