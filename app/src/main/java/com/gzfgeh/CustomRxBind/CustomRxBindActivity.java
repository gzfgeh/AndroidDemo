package com.gzfgeh.CustomRxBind;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzfgeh.R;
import com.gzfgeh.ShareUtils;
import com.gzfgeh.databinding.ActivityBindBinding;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/23 15:28.
 */
public class CustomRxBindActivity extends Activity {
    private TextView tv_name; //学生姓名
    private TextView tv_age; //学生年龄
    private Button btn_setting; //设置
    private Student student; //学生对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bind);
        ActivityBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bind);
        Student student = new Student("my", 22);
        binding.setUser(student);

    }
}
