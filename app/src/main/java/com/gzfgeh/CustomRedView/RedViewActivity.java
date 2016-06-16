package com.gzfgeh.CustomRedView;

import android.os.Bundle;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/6/14 10:31.
 */
public class RedViewActivity extends BaseActivity {
    @Bind(R.id.hot_one)
    RedHotView hotOne;
//    @Bind(R.id.hot_two)
//    RedHotView hotTwo;
//    @Bind(R.id.hot_three)
//    RedHotView hotThree;
//    @Bind(R.id.hot_four)
//    RedHotView hotFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_view);
        ButterKnife.bind(this);

        hotOne.setText("");
//        hotTwo.setText("8");
//        hotThree.setText("89");
//        hotFour.setText("888");
    }
}
