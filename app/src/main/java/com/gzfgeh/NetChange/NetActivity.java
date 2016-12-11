package com.gzfgeh.NetChange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetActivity extends AppCompatActivity {

    @Bind(R.id.test_text)
    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);


    }
}
