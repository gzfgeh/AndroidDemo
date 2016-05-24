package com.gzfgeh.EaseRecycler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gzfgeh.CustomRecycler.RecyclerViewAdapter;
import com.gzfgeh.R;
import com.gzfgeh.Recycler.EasyRecyclerView;
import com.gzfgeh.Recycler.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/24 09:57.
 */
public class EasyRecyclerActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    RecyclerArrayAdapter<String> adapter;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_recycler);
        ButterKnife.bind(this);

        for (int i=0; i<100; i++){
            data.add(i+"--789");
        }
        adapter = new RecyclerArrayAdapter<String>(this, R.layout.item_contacts){

            @Override
            protected void convert(com.gzfgeh.Recycler.BaseViewHolder helper, String item) {
                helper.setText(R.id.item_contact_title, item);
            }
        };

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data.clear();
                for (int i=0; i<100; i++){
                    data.add(i+"--789");
                }
                adapter.addAll(data);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++){
                    data.add(i+"--789");
                }
                adapter.addAll(data);
            }
        }, 1000);
    }
}
