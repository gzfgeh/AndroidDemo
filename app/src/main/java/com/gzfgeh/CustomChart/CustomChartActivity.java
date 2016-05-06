package com.gzfgeh.CustomChart;

import android.os.Bundle;
import android.os.Handler;
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
public class CustomChartActivity extends BaseActivity implements View.OnClickListener, LineChartView.LoadMoreListener {
    @Bind(R.id.chart)
    LineChartView chart;
    @Bind(R.id.go)
    Button go;

    private LineChartData data;
    private int numValues = 500;
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
        chart.setListener(this);
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
    }

    private void showCurrentViewChart(float left, float right) {
        tempViewport.set(left, tempViewport.top, right, tempViewport.bottom);
        chart.setCurrentViewport(tempViewport);
    }


    @Override
    public void onClick(View v) {
        showCurrentViewChart(100, chart.getCurrentViewport().width() + 100);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<PointValue> moreData = new ArrayList<>();
                moreData.addAll(data.getLines().get(0).getValues());
                //generateDefaultData();

                List<PointValue> values = new ArrayList<>();
                for (int i = 500; i < numValues + 500; ++i) {
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
                moreData.addAll(data.getLines().get(0).getValues());
                data.getLines().get(0).setValues(moreData);
                float left = chart.getCurrentViewport().left;
                float right = chart.getCurrentViewport().right;
                chart.setLineChartData(data);
                showCurrentViewChart(left, right);
            }
        }, 1000);

    }
}
