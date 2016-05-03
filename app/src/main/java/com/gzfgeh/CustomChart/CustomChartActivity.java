package com.gzfgeh.CustomChart;

import android.os.Bundle;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CustomChartActivity extends BaseActivity {
    @Bind(R.id.chart)
    LineChartView chart;

    private LineChartData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        ButterKnife.bind(this);

        generateDefaultData();
        chart.setLineChartData(data);
//        chart.setZoomEnabled(false);
        chart.setScrollEnabled(true);
        previewX();
    }

    private void generateDefaultData() {
        int numValues = 5000;

        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, (float) Math.random() * 100f));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN);
        line.setHasPoints(false);// too many values so don't draw points.

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        data = new LineChartData(lines);
        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis().setHasLines(true));

    }

    private void previewX() {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        //float dx = tempViewport.width() / 4;
        tempViewport.insetX(tempViewport.width()-5);

        chart.setCurrentViewport(tempViewport);
        chart.setZoomType(ZoomType.HORIZONTAL);
    }

    public List<Map<String, String>> getData() {
        //9 2000
        //10 3000
        List<Map<String, String>> list = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("time", "09");
        map1.put("price", "2000");
        list.add(map1);
        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("time", "10");
        map2.put("price", "3000");
        list.add(map2);
        HashMap<String, String> map3 = new HashMap<String, String>();
        map3.put("time", "11");
        map3.put("price", "5000");
        list.add(map3);
        HashMap<String, String> map4 = new HashMap<String, String>();
        map4.put("time", "12");
        map4.put("price", "8000");
        list.add(map4);
        HashMap<String, String> map5 = new HashMap<String, String>();
        map5.put("time", "14");
        map5.put("price", "6000");
        list.add(map5);
        return list;

    }
}
