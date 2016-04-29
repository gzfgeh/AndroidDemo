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

/**
 * Description:
 * Created by guzhenfu on 2016/4/29 10:01.
 */
public class CustomChartActivity extends BaseActivity {

    @Bind(R.id.chart_view)
    CustomChartView chartView;

    private String[] x = {"08", "09", "10", "11", "12", "13", "14", "15"};
    private String[] y = {"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        ButterKnife.bind(this);
        chartView.setAxis(x, y);
        chartView.setData(getData());
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
