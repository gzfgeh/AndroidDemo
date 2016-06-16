package com.gzfgeh.CurveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Description:
 * Created by guzhenfu on 2016/6/3 14:08.
 */
public class CurveView extends LinearLayout {
    private Path path;
    private Paint mPaint;
    private float startX;
    private float remain;
    /**
     * 长度
     */
    private float length = 24;
    /**
     * 数量
     */
    private int num;
    private int color = Color.WHITE;

    private Paint bottomPaint;
    private int width, height;

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setDither(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        path = new Path();

        bottomPaint = new Paint();
        bottomPaint.setColor(Color.BLUE);
        bottomPaint.setAntiAlias(true);
        bottomPaint.setStyle(Paint.Style.FILL);
        bottomPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain==0){
            remain = w % length;
        }
        num = (int) (w / length);
        startX = (w % length) / 2;
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(startX, 0);
        float y = (float) Math.sqrt(length * length / 12);
        for (int i=1; i <= num; i++){
            path.lineTo(startX + length/2 + length* (i - 1), y);
            path.lineTo(startX + length * i, 0);
        }
        canvas.drawPath(path, mPaint);
        canvas.drawLine(0, height, width, height, bottomPaint);
    }
}
