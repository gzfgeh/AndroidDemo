package com.gzfgeh.CustomDagger;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gzfgeh.BaseActivity;
import com.gzfgeh.R;
import com.gzfgeh.Recycler.CustomRecyclerAdapter;
import com.gzfgeh.SwipeRefresh.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/9 17:49.
 */
public class Dagger2Activity extends BaseActivity implements CustomSwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh)
    CustomSwipeRefreshLayout swipeRefreshLayout;

//    @Inject
//    HomeAdapter adapter;

    @Inject
    Dagger2ActivityPresenter presenter;

    CustomRecyclerAdapter<String> adapter;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);


        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new CustomRecyclerAdapter<String>(this, R.layout.item_contacts){

            @Override
            protected void convert(com.gzfgeh.Recycler.BaseViewHolder helper, String item) {
                helper.setText(R.id.item_contact_title, item);
            }
        };

        for (int i = 0; i < 100; i++) {
            data.add(i + "--789");
        }
        adapter.addAll(data);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

//        DaggerUserComponent.builder()
//                .homeModle(new HomeModle(this))
//                .dagger2ActivityModule(new Dagger2ActivityModule(this))
//                .userModule(new UserModule())
//                .build()
//                .inject(this);
//
//
//        presenter.showText();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }

    public void setTextView(String username){
        //text.setText(username);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                for (int i = 0; i < 100; i++) {
                    data.add(i + "--789");
                }
                adapter.addAll(data);
                swipeRefreshLayout.refreshComplete();
            }
        }, 1000);
    }
}
