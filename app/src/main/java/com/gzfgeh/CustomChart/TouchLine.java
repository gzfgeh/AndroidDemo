package com.gzfgeh.CustomChart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
    private float x, y;

    public TouchLine(ChartComputator chartComputator, LineChartData data) {
        this.chartComputator = chartComputator;
        this.data = data;
        paint = new Paint();
        paint.setColor(Color.RED);
        lines = new float[8];
    }

    public void drawLine(Canvas canvas){
        lines[0] = 0;
        lines[1] = y;
        lines[2] = chartComputator.getChartWidth();
        lines[3] = y;

        lines[4] = x;
        lines[5] = 0;
        lines[6] = x;
        lines[7] = chartComputator.getChartHeight();

        canvas.drawLines(lines, paint);
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
