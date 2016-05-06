package com.gzfgeh.CustomChart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
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
public class CustomChartActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.chart)
    LineChartView chart;
    @Bind(R.id.go)
    Button go;

    private LineChartData data;
    private int numValues = 500;
    private LineChartData previewData;
    private Viewport tempViewport;

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
        chart.setZoomType(ZoomType.HORIZONTAL);
        tempViewport = new Viewport(chart.getMaximumViewport());
        showCurrentViewChart(0, tempViewport.width() / 8);

        go.setOnClickListener(this);
    }

    private void generateDefaultData() {
        List<PointValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            if (i > 100)
                values.add(new PointValue(i, (float) Math.random() * 100f + 50f));
            else
                values.add(new PointValue(i, (float) Math.random() * 100f));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setHasPoints(false);// too many values so don't draw points.

        List<Line> lines = new ArrayList<>();
        lines.add(line);

        data = new LineChartData(lines);
        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis().setHasLines(true));

        previewData = new LineChartData(data);
        previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);
    }

    private void showCurrentViewChart(float left, float right) {
        tempViewport.set(left, tempViewport.top, right, tempViewport.bottom);
        chart.setCurrentViewport(tempViewport);
    }


    @Override
    public void onClick(View v) {
        showCurrentViewChart(100, chart.getCurrentViewport().width() + 100);
    }
}
