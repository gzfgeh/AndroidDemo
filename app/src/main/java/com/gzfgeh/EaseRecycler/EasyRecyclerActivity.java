package com.gzfgeh.EaseRecycler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersTouchListener;
import com.gzfgeh.LogUtils;
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
    @Bind(R.id.sticky_header)
    View stickyHeaderLayout;

    private View falseHeaderView;
    private LinearLayoutManager manager;
    CustomAdapter adapter;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_recycler);
        ButterKnife.bind(this);

        stickyHeaderLayout.setVisibility(View.INVISIBLE);
        adapter = new CustomAdapter(this, R.layout.item_contacts);

        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshListener(this);
        adapter.setMore(R.layout.view_more, 10, this);
        initAdapter();

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                LogUtils.i("---dy:" + dy);
                if (dy >= 0 && falseHeaderView.getTop() <= 0){
                    stickyHeaderLayout.setVisibility(View.VISIBLE);
                }
                if (dy < 0 && falseHeaderView.getTop() > 0){
                    stickyHeaderLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        //StickyRecyclerHeadersDecoration decoration = new StickyRecyclerHeadersDecoration(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                for (int i = 0; i < 30; i++) {
                    data.add(i + "--789");
                }
                data.clear();
                adapter.addAll(data);
                //recyclerView.addItemDecoration(decoration);
            }
        }, 1000);

//        StickyRecyclerHeadersTouchListener touchListener = new StickyRecyclerHeadersTouchListener
//                (recyclerView.getRecyclerView(), decoration);
//
//        touchListener.setOnHeaderClickListener(new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
//            @Override
//            public void onHeaderClick(View header, int position, long headerId) {
//                Toast.makeText(EasyRecyclerActivity.this, "Header position: " + position + ", id: " + headerId,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.addOnItemTouchListener(touchListener);

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

        adapter.addHeader(new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                falseHeaderView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.stick_header, null);
                return falseHeaderView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        recyclerView.setAdapterWithProgress(adapter);
    }
}
