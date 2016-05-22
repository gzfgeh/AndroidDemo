package com.gzfgeh.CustomStickRecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.gzfgeh.CustomDrawable.RecyclerAdapter;
import com.gzfgeh.R;
import com.gzfgeh.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 16/5/22.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class StickRecyclerActivity extends Activity {
    private List<String> list = new ArrayList<>();
    View stickyView;
    private int totalChange;

    //普通header的高度
    private int maxDist;

    AppBarLayout appbar;

    private int barHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_recycler);

        maxDist= Utils.dipToPx(this,200);
        stickyView=findViewById(R.id.sticky_header);

        for (int i=0; i<100; i++){
            list.add(i+"");
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StickyHeaderAdapter adapter = new StickyHeaderAdapter(this, list);
        recyclerView.setAdapter(adapter);

        appbar= (AppBarLayout) findViewById(R.id.appbar);
        appbar.post(new Runnable() {
            @Override
            public void run() {
                barHeight= appbar.getMeasuredHeight();
                stickyView.setTranslationY(maxDist+barHeight);
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalChange+=dy;
                int tranY=Math.max(0,maxDist-totalChange);
                //移动距离超过maxDist，就定在0处  ,barHeight是appbar的高度，必须加上
                stickyView.setTranslationY(tranY+barHeight);

            }
        });
    }
}
