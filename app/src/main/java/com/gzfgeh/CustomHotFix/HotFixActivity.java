package com.gzfgeh.CustomHotFix;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzfgeh.APP;
import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/6/11 21:13.
 */
public class HotFixActivity extends BaseActivity {
    private static final String APATCH_PATH = "/out.apatch";
    @Bind(R.id.hot_fix_text)
    Button hotFixText;
    @Bind(R.id.load_fix)
    Button loadFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        ButterKnife.bind(this);

        hotFixText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BugClass bugClass = new BugClass();
                Toast.makeText(HotFixActivity.this, bugClass.bug(), Toast.LENGTH_SHORT).show();
            }
        });

        loadFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
                try {
                    APP.getPatchManagerInstance().addPatch(patchFileString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
