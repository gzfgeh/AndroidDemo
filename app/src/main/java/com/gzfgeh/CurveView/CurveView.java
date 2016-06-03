package com.gzfgeh.CurveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Description:
 * Created by guzhenfu on 2016/6/3 14:08.
 */
public class CurveView extends LinearLayout {
    private Paint mPaint;
    private float remain;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain==0){
            remain = (int)(w-gap)%(2*radius+gap);
        }
        circleNum = (int) ((w-gap)/(2*radius+gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<circleNum;i++){
            float x = gap+radius+remain/2+((gap+radius*2)*i);
            canvas.drawCircle(x,0,radius,mPaint);
            //canvas.drawCircle(x,getHeight(),radius,mPaint);
        }
    }
}
