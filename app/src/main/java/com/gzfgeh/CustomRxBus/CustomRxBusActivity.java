package com.gzfgeh.CustomRxBus;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.CustomRxBus.annotation.Accept;
import com.gzfgeh.CustomRxBus.annotation.AcceptType;
import com.gzfgeh.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/4/27 15:57.
 */
public class CustomRxBusActivity extends BaseActivity {
    @Bind(R.id.bus)
    Button bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        ButterKnife.bind(this);
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.get().post("456", "123");
            }
        });
    }

    @Accept(value = {@AcceptType(tag = "456", clazz = String.class)})
    public void onPostAccept(String tag, String event){
         Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }



}
