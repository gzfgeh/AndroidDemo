package com.gzfgeh.CustomDagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gzfgeh.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/9 17:49.
 */
public class Dagger2Activity extends Activity {

    @Bind(R.id.id_recyclerview)
    RecyclerView recyclerView;

    @Inject
    HomeAdapter adapter;
    @Bind(R.id.text)
    TextView text;

    @Inject
    Dagger2ActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);

        DaggerUserComponent.builder()
                .homeModle(new HomeModle(this))
                .dagger2ActivityModule(new Dagger2ActivityModule(this))
                .build()
                .inject(this);


        presenter.showText();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void setTextView(String username){
        text.setText(username);
    }
}