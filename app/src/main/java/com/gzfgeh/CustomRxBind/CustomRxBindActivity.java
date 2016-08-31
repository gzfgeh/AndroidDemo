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
    private TextView tv_name; //学生姓名
    private TextView tv_age; //学生年龄
    private Button btn_setting; //设置
    private Student student; //学生对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        initView();
        initData();
        initListener();
    }


    /**
     * 描述:初始化控件
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/10 10:17
     */
    private void initView() {
        tv_name = (TextView) findViewById(R.id.activity_main_stu_name);
        tv_age = (TextView) findViewById(R.id.activity_main_stu_age);
        btn_setting = (Button) findViewById(R.id.activity_main_stu_setting);
    }

    /**
     * 描述:初始化数据
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/10 10:19
     */
    private void initData() {
        //创建一个学生对象
        student = new Student("俊文", 22);
        //根据学生对象赋值到控件上
        tv_name.setText(student.getName());
        tv_age.setText(String.valueOf(student.getAge()));
    }

    /**
     * 描述:初始化监听
     * 作者:卜俊文
     * 邮箱:344176791@qq.com
     * 创建时间: 2016/8/10 10:22
     */
    private void initListener() {
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击设置,更改Student对象的名字。
                student.setName("卜俊文");
                tv_name.setText(student.getName());
            }
        });
    }
}
