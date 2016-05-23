package com.gzfgeh.CustomRxBind;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzfgeh.R;
import com.gzfgeh.ShareUtils;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/23 15:28.
 */
public class CustomRxBindActivity extends Activity {
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.btn)
    Button btn;

    private RxProperty<String> spContent = RxProperty.create();
    private SharedPreferences mSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbind);
        ButterKnife.bind(this);

        mSP = this.getSharedPreferences("my_sharedpreference", MODE_PRIVATE);
        mSP.registerOnSharedPreferenceChangeListener(spListener);
        mSP.edit().putString("key", "origin");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSP.edit().putString("key", "change");
            }
        });

        RxView.of(text).bind(spContent, (v, content)-> {v.setText(content);});
    }

    private SharedPreferences.OnSharedPreferenceChangeListener spListener = (sp, key) -> {
        spContent.set(sp.getString(key, ""));
    };

    @Override
    public void finish() {
        super.finish();
        mSP.unregisterOnSharedPreferenceChangeListener(spListener);
    }
}
