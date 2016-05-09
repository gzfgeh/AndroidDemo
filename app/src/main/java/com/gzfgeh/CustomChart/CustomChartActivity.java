package com.gzfgeh.CustomChart;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.LogUtils;
import com.gzfgeh.NetWorkUtils;
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
public class CustomChartActivity extends BaseActivity implements LineChartView.LoadMoreListener {
    @Bind(R.id.chart)
    LineChartView chart;
    @Bind(R.id.right_progress)
    ProgressBar rightProgress;
    @Bind(R.id.left_progress)
    ProgressBar leftProgress;
    @Bind(R.id.empty)
    View empty;

    private LineChartData data;
    private int pageSize = 200, pageNum = 0, startPoine = 200;
    private Viewport tempViewport;
    private List<PointValue> moreData = new ArrayList<>();
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_chart_view);
        ButterKnife.bind(this);

        empty.setVisibility(View.GONE);
        generateDefaultData();
        moreData.addAll(data.getLines().get(0).getValues());

        chart.setLineChartData(data);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setZoomEnabled(true);
        chart.setScrollEnabled(true);
        chart.setZoomType(ZoomType.HORIZONTAL);
        tempViewport = new Viewport(chart.getMaximumViewport());
        chart.setZoomLevel(startPoine, data.getLines().get(0).getValues().get(0).getY(), 8.0f);
        chart.setListener(this);
        if (data.getLines().get(0).getValues().size() == 0){
            chart.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }

    private void generateDefaultData() {
        List<PointValue> values = new ArrayList<>();
        for (int i = startPoine + pageNum * pageSize; i < (pageNum + 1) * pageSize + startPoine; ++i) {
            if (i > startPoine + 100 + pageNum * pageSize)
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
        chart.setMaxZoom(chart.getMaxZoom() * 2);
    }

    @Override
    public void onLoadRightMore() {
        LogUtils.i("left:" + "---right:");
        float left = chart.getCurrentViewport().left;
        float right = chart.getCurrentViewport().right;

        if (NetWorkUtils.isNetworkAvailable()) {
            rightProgress.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rightProgress.setVisibility(View.GONE);

                    ++pageNum;
                    generateDefaultData();
                    if (data.getLines().get(0).getValues().size() == 0){
                        Toast.makeText(CustomChartActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                    moreData.addAll(data.getLines().get(0).getValues());
                    data.getLines().get(0).setValues(moreData);

                    chart.setLineChartData(data);
                    LogUtils.i("left:" + left + "---right:" + right
                            + "---tempLeft:" + tempViewport.left
                            + "---tempRight:" + tempViewport.right);
                    showCurrentViewChart(left, right);

                }
            }, 1000);
        }else{
            Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
            showCurrentViewChart(left, right);
        }

    }

    @Override
    public void onLoadLeftMore() {
        LogUtils.i("left:" + "---left:");
        float left = chart.getCurrentViewport().left;
        float right = chart.getCurrentViewport().right;

        if (NetWorkUtils.isNetworkAvailable()) {
            leftProgress.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    leftProgress.setVisibility(View.GONE);
                    if (first) {
                        List<PointValue> values = new ArrayList<>();
                        for (int i = 0; i < startPoine; ++i) {
                            if (i > startPoine - 100)
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

//                    ++pageNum;
//                    generateDefaultData();
                        if (data.getLines().get(0).getValues().size() == 0) {
                            Toast.makeText(CustomChartActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        }
                        List<PointValue> tempValue = new ArrayList<>();
                        tempValue.addAll(data.getLines().get(0).getValues());
                        tempValue.addAll(moreData);
//                    moreData.addAll(data.getLines().get(0).getValues());
                        data.getLines().get(0).setValues(tempValue);

                        chart.setLineChartData(data);
                        LogUtils.i("left:" + left + "---right:" + right
                                + "---tempLeft:" + tempViewport.left
                                + "---tempRight:" + tempViewport.right);
                        first = false;
                    }
                    showCurrentViewChart(left, right);

                }
            }, 2000);
        }else{
            Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
            showCurrentViewChart(left, right);
        }
    }
}
