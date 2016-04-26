package com.gzfgeh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.gzfgeh.CustomFlowLayout.CustomFlowLayoutActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customViewgroup.setOnClickListener(this);
        customViewgroupFlow.setOnClickListener(this);
        customViewgroupTag.setOnClickListener(this);
        customTextView.setOnClickListener(this);
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
        }
    }
}
