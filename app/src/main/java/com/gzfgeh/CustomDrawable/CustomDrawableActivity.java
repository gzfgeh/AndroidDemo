package com.gzfgeh.CustomDrawable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.gzfgeh.R;
import com.gzfgeh.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/19 11:23.
 */
public class CustomDrawableActivity extends Activity {
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.popup)
    Button popup;

    private List<String> list = new ArrayList<>();
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.startpage);
        CircleImageDrawable drawable = new CircleImageDrawable(this,bitmap);
        image.setImageDrawable(drawable);


        for (int i=0; i<10; i++){
            list.add(i+"");
        }
        View content = LayoutInflater.from(this).inflate(R.layout.popup_window, null);
        RecyclerView recyclerView = (RecyclerView) content.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter();
        adapter.setDatas(list);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int position) {
                PopupList.hide();
                Toast.makeText(CustomDrawableActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });


        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupList.show(CustomDrawableActivity.this, popup, content
                        , Utils.getHeightInPx(CustomDrawableActivity.this)/5
                        , Utils.getWidthInPx(CustomDrawableActivity.this)/3);
            }
        });
    }

}
