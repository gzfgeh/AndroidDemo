package com.gzfgeh;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/4/18 14:17.
 */
public class CustomTagLayoutActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
    @Bind(R.id.tag_container_layout)
    TagLayout tagContainerLayout;
    @Bind(R.id.et_add_tag)
    EditText etAddTag;
    @Bind(R.id.btn_add_tag)
    Button btnAddTag;

    private List<String> tags = new ArrayList<>();
    private int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_layout);
        ButterKnife.bind(this);

        tags.add("全部");
        tagContainerLayout.setTags(tags);
        btnAddTag.setOnClickListener(this);

        tagContainerLayout.setOnTagClickListener(new TagLayout.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                tagContainerLayout.remove(position);
            }
        });

        btnAddTag.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        if (TextUtils.equals(etAddTag.getText().toString().trim(), ""))
            return;
        LogUtils.i("start location---x---" + x + "-----y-----" + y);
        tagContainerLayout.addTag(etAddTag.getText().toString().trim());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = (int) event.getRawX();
        y = (int) event.getRawY();
        return false;
    }
}
