package com.pengkv.may.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/8.
 * 圆角图片
 */
public class RadiusImageView extends ImageView {

    Paint mMaskPaint = new Paint();
    Path mMaskPath;
    int mCornerRadius = 10;

    public RadiusImageView(Context context) {
        this(context, null);
    }

    public RadiusImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMaskPaint.setAntiAlias(true);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }


    private void generateMaskPath(int width, int height) {
        mMaskPath = new Path();
        mMaskPath.addRoundRect(new RectF(0.0F, 0.0F, width, height), mCornerRadius, mCornerRadius, Path.Direction.CW);
        mMaskPath.setFillType(Path.FillType.INVERSE_WINDING);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if ((w != oldw) || (h != oldh))
            generateMaskPath(w, h);

    }

    protected void onDraw(Canvas canvas) {
        // 保存当前layer的透明橡树到离屏缓冲区。并新创建一个透明度爲255的新layer
        int saveCount = canvas.saveLayerAlpha(0.0F, 0.0F, canvas.getWidth(), canvas.getHeight(),
                255, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        super.onDraw(canvas);
        if (mMaskPath != null) {
            canvas.drawPath(mMaskPath, mMaskPaint);
        }
        canvas.restoreToCount(saveCount);
    }
}
