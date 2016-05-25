package com.gzfgeh.EaseRecycler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;
import com.gzfgeh.Recycler.CustomRecyclerAdapter;
import com.gzfgeh.Recycler.CustomRecyclerView;
import com.gzfgeh.SwipeRefresh.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/24 09:57.
 */
public class EasyRecyclerActivity extends BaseActivity implements CustomSwipeRefreshLayout.OnRefreshListener, CustomRecyclerAdapter.OnLoadMoreListener {

    @Bind(R.id.recyclerView)
    CustomRecyclerView recyclerView;

    CustomAdapter adapter;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_recycler);
        ButterKnife.bind(this);

        adapter = new CustomAdapter(this, R.layout.item_contacts);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, this);
        initAdapter();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                for (int i = 0; i < 100; i++) {
                    data.add(i + "--789");
                }
                adapter.addAll(data);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.showError();
//                adapter.clear();
//                for (int i=0; i<100; i++){
//                    data.add(i+"--789");
//                }
//                adapter.addAll(data);
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

    private void initAdapter(){
        adapter.addHeader(new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recylcer_header, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
            }
        });

//        adapter.addFooter(new CustomRecyclerAdapter.ItemView() {
//            @Override
//            public View onCreateView(ViewGroup parent) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.item_contacts, null);
//                return view;
//            }
//
//            @Override
//            public void onBindView(View headerView) {
//
//            }
//        });

        recyclerView.setAdapterWithProgress(adapter);
    }
}
