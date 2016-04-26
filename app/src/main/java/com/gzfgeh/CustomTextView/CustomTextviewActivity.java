package com.gzfgeh.CustomTextView;

import android.app.Activity;
import android.os.Bundle;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/4/26 15:38.
 */
public class CustomTextviewActivity extends Activity {

    @Bind(R.id.text1)
    MyTextView text1;
    @Bind(R.id.text2)
    MyTextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_text_view);
        ButterKnife.bind(this);

        text1.setText("很好非/常好");
        text2.setText("非常好高兴很");
    }
}
