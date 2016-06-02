package com.gzfgeh.CustomPopupWindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.gzfgeh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/6/2 19:51.
 */
public class PopupWindowActivity extends Activity {
    @Bind(R.id.up_pos)
    Button upPos;
    @Bind(R.id.center_pos)
    Button centerPos;
    @Bind(R.id.any_pos)
    Button anyPos;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        ButterKnife.bind(this);

        upPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        centerPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        anyPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }else {
                    View contentView = LayoutInflater.from(v.getContext()).inflate(R.layout.pop_up_content, null);
                    popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    View rootView = LayoutInflater.from(v.getContext()).inflate(R.layout.activity_pop_up, null);
                    popupWindow.showAtLocation(rootView, Gravity.CENTER, 200, 400);
                }
            }
        });
    }
}
