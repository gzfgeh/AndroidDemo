package com.gzfgeh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gzfgeh.CurveView.CurveViewActivity;
import com.gzfgeh.CustomBottomTab.BottomTabLayoutActivity;
import com.gzfgeh.CustomBtn.CustomBtnActivity;
import com.gzfgeh.CustomChart.ChartViewActivity;
import com.gzfgeh.CustomChart.CustomChartActivity;
import com.gzfgeh.CustomDagger.Dagger2Activity;
import com.gzfgeh.CustomDrawable.CustomDrawableActivity;
import com.gzfgeh.CustomFlowLayout.CustomFlowLayoutActivity;
import com.gzfgeh.CustomHotFix.HotFixActivity;
import com.gzfgeh.CustomPopupWindow.PopupWindowActivity;
import com.gzfgeh.CustomRecycler.CustomRecyclerActivity;
import com.gzfgeh.CustomRetrifit.RetrofitActivity;
import com.gzfgeh.CustomRxBind.CustomRxBindActivity;
import com.gzfgeh.CustomRxBus.CustomRxBusActivity;
import com.gzfgeh.CustomRxBus.annotation.Accept;
import com.gzfgeh.CustomScrollView.ScrollViewActivity;
import com.gzfgeh.CustomStickRecycler.StickRecyclerActivity;
import com.gzfgeh.CustomTag.CustomTagLayoutActivity;
import com.gzfgeh.CustomTextView.CustomTextviewActivity;
import com.gzfgeh.CustomViewGroup.CustomViewGroupActivity;
import com.gzfgeh.EaseRecycler.EasyRecyclerActivity;
import com.gzfgeh.FilterView.FilterViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.custom_viewgroup)
    Button customViewgroup;
    @Bind(R.id.custom_viewgroup_flow)
    Button customViewgroupFlow;
    @Bind(R.id.custom_viewgroup_tag)
    Button customViewgroupTag;
    @Bind(R.id.custom_text_view)
    Button customTextView;
    @Bind(R.id.custom_rx_bus)
    Button customRxBus;
    @Bind(R.id.custom_chart_view)
    Button customChartView;
    @Bind(R.id.custom_btn)
    Button customBtn;
    @Bind(R.id.bottom_tablayout)
    Button bottomTablayout;
    @Bind(R.id.chart)
    Button chart;
    @Bind(R.id.dragger2)
    Button dragger2;
    @Bind(R.id.recycler)
    Button recycler;
    @Bind(R.id.scroll_view)
    Button scrollView;
    @Bind(R.id.layout_three)
    LinearLayout layoutThree;
    @Bind(R.id.retrofit)
    Button retrofit;
    @Bind(R.id.custom_drawable)
    Button drawable;
    @Bind(R.id.stick_recycler)
    Button stickRecycler;
    @Bind(R.id.rxbind)
    Button rxBind;
    @Bind(R.id.custom_recycler)
    Button recyclerBtn;
    @Bind(R.id.popup_window)
    Button popupWindow;
    @Bind(R.id.curve_view)
    Button curveView;
    @Bind(R.id.filter_view)
    Button filterView;
    @Bind(R.id.hot_fix)
    Button hotFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);

        customViewgroup.setOnClickListener(this);
        customViewgroupFlow.setOnClickListener(this);
        customViewgroupTag.setOnClickListener(this);
        customTextView.setOnClickListener(this);
        customRxBus.setOnClickListener(this);
        customChartView.setOnClickListener(this);
        customBtn.setOnClickListener(this);
        bottomTablayout.setOnClickListener(this);
        chart.setOnClickListener(this);
        dragger2.setOnClickListener(this);
        recycler.setOnClickListener(this);
        scrollView.setOnClickListener(this);
        retrofit.setOnClickListener(this);
        drawable.setOnClickListener(this);
        stickRecycler.setOnClickListener(this);
        rxBind.setOnClickListener(this);
        recyclerBtn.setOnClickListener(this);
        popupWindow.setOnClickListener(this);
        curveView.setOnClickListener(this);
        filterView.setOnClickListener(this);
        hotFix.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_viewgroup:
                startActivity(new Intent(this, CustomViewGroupActivity.class));
                break;

            case R.id.custom_viewgroup_flow:
                startActivity(new Intent(this, CustomFlowLayoutActivity.class));
                break;

            case R.id.custom_viewgroup_tag:
                startActivity(new Intent(this, CustomTagLayoutActivity.class));
                break;

            case R.id.custom_text_view:
                startActivity(new Intent(this, CustomTextviewActivity.class));
                break;

            case R.id.custom_rx_bus:
                startActivity(new Intent(this, CustomRxBusActivity.class));
                break;

            case R.id.custom_chart_view:
                startActivity(new Intent(this, CustomChartActivity.class));
                break;

            case R.id.custom_btn:
                startActivity(new Intent(this, CustomBtnActivity.class));
                break;

            case R.id.bottom_tablayout:
                startActivity(new Intent(this, BottomTabLayoutActivity.class));
                break;

            case R.id.chart:
                startActivity(new Intent(this, ChartViewActivity.class));
                break;

            case R.id.dragger2:
                startActivity(new Intent(this, Dagger2Activity.class));
                break;

            case R.id.recycler:
                startActivity(new Intent(this, CustomRecyclerActivity.class));
                break;

            case R.id.scroll_view:
                startActivity(new Intent(this, ScrollViewActivity.class));
                break;

            case R.id.retrofit:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;

            case R.id.custom_drawable:
                startActivity(new Intent(this, CustomDrawableActivity.class));
                break;

            case R.id.stick_recycler:
                startActivity(new Intent(this, StickRecyclerActivity.class));
                break;

            case R.id.rxbind:
                startActivity(new Intent(this, CustomRxBindActivity.class));
                break;

            case R.id.custom_recycler:
                startActivity(new Intent(this, EasyRecyclerActivity.class));
                break;

            case R.id.popup_window:
                startActivity(new Intent(this, PopupWindowActivity.class));
                break;

            case R.id.curve_view:
                startActivity(new Intent(this, CurveViewActivity.class));
                break;

            case R.id.filter_view:
                startActivity(new Intent(this, FilterViewActivity.class));
                break;

            case R.id.hot_fix:
                startActivity(new Intent(this, HotFixActivity.class));
                break;
        }
    }

    @Accept
    public void onPostAccept(Object tag, String event) {
        Toast.makeText(this, event + "main", Toast.LENGTH_SHORT).show();
    }
}
