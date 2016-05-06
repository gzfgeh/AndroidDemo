package com.gzfgeh.CustomChart;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Description:
 * Created by guzhenfu on 2016/4/29 10:01.
 */
public class CustomChartActivity extends BaseActivity {
    @Bind(R.id.chart)
    LineChartView chart;

    private LineChartData data;
    private int numValues = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        ButterKnife.bind(this);

        generateDefaultData();

        chart.setLineChartData(data);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setZoomEnabled(true);
        chart.setScrollEnabled(true);
        showCurrentViewChart(chart);

    }

    private void generateDefaultData() {
        List<PointValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            if(i > 100)
                values.add(new PointValue(i, (float) Math.random() * 100f + 50f));
            else
                values.add(new PointValue(i, (float) Math.random() * 100f));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setHasPoints(true);// too many values so don't draw points.

        List<Line> lines = new ArrayList<>();
        lines.add(line);

        data = new LineChartData(lines);
        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis().setHasLines(true));
    }

    private void showCurrentViewChart(LineChartView chart) {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dx = tempViewport.width() / 8;
        tempViewport.set(0, tempViewport.top, dx, tempViewport.bottom);
        chart.setCurrentViewport(tempViewport);
        chart.setZoomType(ZoomType.HORIZONTAL);
    }



}
