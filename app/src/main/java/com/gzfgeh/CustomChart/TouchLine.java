package com.gzfgeh.CustomChart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.gzfgeh.LogUtils;

import lecho.lib.hellocharts.computator.ChartComputator;
import lecho.lib.hellocharts.model.LineChartData;

/**
 * Description:
 * Created by guzhenfu on 2016/5/5 11:45.
 */
public class TouchLine{
    private ChartComputator chartComputator;
    private LineChartData data;
    private Paint paint;
    private float[] lines;
    private float x, y, valueX, valueY;

    public TouchLine(ChartComputator chartComputator, LineChartData data) {
        this.chartComputator = chartComputator;
        this.data = data;
        paint = new Paint();
        paint.setColor(Color.RED);
        lines = new float[8];
    }

    public void drawLine(Canvas canvas){
        lines[0] =chartComputator.getContentRectMinusAllMargins().left;
        lines[1] = y;
        lines[2] = chartComputator.getChartWidth();
        lines[3] = y;

        lines[4] = x;
        lines[5] = 0;
        lines[6] = x;
        lines[7] = chartComputator.computeRawY(0);
        LogUtils.i("lines[0] :" + lines[0] + "-----lines[1] :" + lines[1]
                        + "-----lines[2] :" + lines[2] + "-----lines[3] :" + lines[3]
                        + "-----lines[4] :" + lines[4] + "-----lines[5] :" + lines[5]
                        + "-----lines[6] :" + lines[6] + "-----lines[7] :" + lines[7]);
        canvas.drawLines(lines, paint);

        paint.setTextSize(20f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(String.valueOf(valueX), x - getStringWidth(String.valueOf(valueX))/2, chartComputator.getChartHeight(), paint);
        canvas.drawText(String.valueOf(valueY), 0, y + getStringHeight(String.valueOf(valueY))/3, paint);
    }

    private int getStringWidth(String str) {
        return (int) paint.measureText(str);
    }

    private int getStringHeight(String str) {
        Paint.FontMetrics fr = paint.getFontMetrics();
        return (int) Math.ceil(fr.descent - fr.ascent)-2;  //ceil() 函数向上舍入为最接近的整数。
    }

    public void setPos(float valueX, float valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.x = chartComputator.computeRawX(valueX);
        this.y = chartComputator.computeRawY(valueY);
    }

    public LineChartData getData() {
        return data;
    }
}
