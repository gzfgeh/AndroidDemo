package com.gzfgeh.CustomChart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.gzfgeh.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by guzhenfu on 2016/4/29 10:04.
 */
public class CustomChartView extends View {
    private int width, height, maxPrice;
    private List<Map<String, String>> data;
    private int distance = 10;
    private float x = 0f, y = 0f;
    private String[] axisX, axisY;
    private float maxX, maxY;
    private Rect rect;
    private Paint paint;

    public CustomChartView(Context context) {
        this(context, null);
    }

    public CustomChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rect = new Rect();
        paint = new Paint();
    }

    public void setAxis(String[] x, String[] y){
        rect = new Rect();
        this.axisX = x;
        for (int i=0; i<x.length; i++){
            paint.getTextBounds(x[i], 0, x[i].length(), rect);
            if (rect.width() > maxX)
                maxX = rect.width();
        }

        this.axisY = y;
        for (int i=0; i<y.length; i++){
            paint.getTextBounds(y[i], 0, y[i].length(), rect);
            if (rect.width() > maxY)
                maxY = rect.width();
        }
        invalidate();
    }

    public void setData(List<Map<String, String>> list) {
        if (list != null) {
            this.data = list;
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                String strPrice = map.get("price");
                int intPrice = Integer.parseInt(strPrice);
                if (intPrice > maxPrice)
                    maxPrice = intPrice;
            }
        } else {
            this.data = new ArrayList<>();
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == 0)
            width = getMeasuredWidth();
        else
            width = w;

        if (h == 0)
            height = getMeasuredHeight();
        else
            height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect = new Rect(0, 0, width, height);
        paint.setColor(getResources().getColor(android.R.color.holo_green_dark));
        canvas.drawRect(rect, paint);

        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setTextSize(20);
        x = maxY + distance;
        y = height - maxX - distance;

        float[] xLine = {x, y, width, y,
                            x, 0, x, y};
        canvas.drawLines(xLine, paint);

        float gap = (width - maxX * axisX.length - x) / (axisX.length - 1) - 1;   //1是偏差
        LogUtils.i("gap:" + gap + "----width:" + width + "---rect.width():" + maxX);
        for (int i=0; i<axisX.length; i++){
            float left = i * gap + maxX * i + x;
            canvas.drawText(axisX[i], left, height, paint);
        }

        gap = (y - maxX * axisY.length) / (axisY.length - 1) - 1;
        for (int i=0; i<axisY.length; i++){
            float top = i * gap + maxX * i + x;
            canvas.drawText(axisY[i], 0, top, paint);
        }


        int timeHeight = 40;
        int priceHeight = height - timeHeight;
        for (int i=0; i<data.size(); i++){
            //横轴
//            map = data.get(i);
//            time = map.get("time");
//            float timeLeft = i * gap + rect.width() * i + x;
//            canvas.drawText(time, timeLeft, height, paint);



            //竖轴
//            String strPrice = map.get("price");
//            int intPrice = Integer.parseInt(strPrice);
//            int priceTop = (intPrice * priceHeight)/maxPrice;
//            priceTop += 20;
//            canvas.drawText(strPrice, timeLeft, priceTop, paint);
        }
    }
}
