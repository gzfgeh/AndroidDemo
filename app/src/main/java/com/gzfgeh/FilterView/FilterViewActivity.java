package com.gzfgeh.FilterView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * Description:
 * Created by guzhenfu on 2016/6/7 15:32.
 */
public class FilterViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
    }
}
