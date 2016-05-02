package com.gzfgeh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzfgeh.CustomBottomTab.BottomTabLayoutActivity;
import com.gzfgeh.CustomBtn.CustomBtnActivity;
import com.gzfgeh.CustomChart.CustomChartActivity;
import com.gzfgeh.CustomFlowLayout.CustomFlowLayoutActivity;
import com.gzfgeh.CustomRxBus.CustomRxBusActivity;
import com.gzfgeh.CustomRxBus.annotation.Accept;
import com.gzfgeh.CustomTag.CustomTagLayoutActivity;
import com.gzfgeh.CustomTextView.CustomTextviewActivity;
import com.gzfgeh.CustomViewGroup.CustomViewGroupActivity;

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
        }
    }

    @Accept
    public void onPostAccept(Object tag, String event) {
        Toast.makeText(this, event + "main", Toast.LENGTH_SHORT).show();
    }
}
