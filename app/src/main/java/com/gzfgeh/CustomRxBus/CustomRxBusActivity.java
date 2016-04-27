package com.gzfgeh.CustomRxBus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.CustomRxBus.accept.Accept;
import com.gzfgeh.R;
import com.wangjie.androidinject.annotation.present.AIAppCompatActivity;

import java.lang.reflect.Method;

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
                RxBus.getInstance().post(new PostEvent().setMsg("123"));
            }
        });
    }

    @Accept
    public void onPostAccept(Object tag, PostEvent event){
        Toast.makeText(this, event.getMsg(), Toast.LENGTH_SHORT).show();
    }



}
