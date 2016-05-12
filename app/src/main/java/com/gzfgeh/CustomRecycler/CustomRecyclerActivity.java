package com.gzfgeh.CustomRecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description:
 * Created by guzhenfu on 2016/5/12 16:48.
 */
public class CustomRecyclerActivity extends Activity {
    @Bind(R.id.contact_member)
    RecyclerView mRecyclerView;
    @Bind(R.id.contact_dialog)
    TextView mUserDialog;
    @Bind(R.id.contact_sidebar)
    SideBar mSideBar;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private List<Model> list = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSideBar.setTextView(mUserDialog);
        getData();
    }

    private void getData() {
        List<Model> mAllLists = new ArrayList<>();
        for (int i=0; i<24; i++){
            Model model = new Model();
            model.setId(i+"");
            mAllLists.add(model);
        }

        mAllLists.get(0).setUsername("程景瑞");
        mAllLists.get(1).setUsername("钱黛");
        mAllLists.get(2).setUsername("许勤颖");
        mAllLists.get(3).setUsername("孙顺元");
        mAllLists.get(4).setUsername("朱佳");
        mAllLists.get(5).setUsername("李茂");
        mAllLists.get(6).setUsername("周莺");
        mAllLists.get(7).setUsername("任倩栋");
        mAllLists.get(8).setUsername("严庆佳");
        mAllLists.get(9).setUsername("彭怡1");
        mAllLists.get(10).setUsername("方谦");
        mAllLists.get(11).setUsername("谢鸣瑾");
        mAllLists.get(12).setUsername("孔秋");
        mAllLists.get(13).setUsername("曹莺安");
        mAllLists.get(14).setUsername("酆有松");
        mAllLists.get(15).setUsername("姜莺岩");
        mAllLists.get(16).setUsername("谢之轮");
        mAllLists.get(17).setUsername("钱固茂");
        mAllLists.get(18).setUsername("陶信勤");
        mAllLists.get(19).setUsername("水天固");
        mAllLists.get(20).setUsername("柳莎婷");
        mAllLists.get(21).setUsername("冯茜");
        mAllLists.get(22).setUsername("吕言栋");
        mAllLists.get(23).setUsername("褚奇清");

        setUI(mAllLists);
    }

    private void setUI(List<Model> mAllLists) {

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                LogUtils.i("position:" + position);
                if (position != -1) {
                    mRecyclerView.scrollToPosition(position);
                    layoutManager.smoothScrollToPosition(mRecyclerView, null, position);
                    LogUtils.i("position:" + layoutManager.findFirstCompletelyVisibleItemPosition());
                }

            }
        });
        seperateLists(mAllLists);

        if (mAdapter == null) {
            mRecyclerView.setHasFixedSize(true);
            mAdapter = new RecyclerViewAdapter(list);
            layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setAdapter(mAdapter);
            final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
            mRecyclerView.addItemDecoration(headersDecor);

            //   setTouchHelper();
            mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    headersDecor.invalidateHeaders();
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void seperateLists(List<Model> mAllLists) {
        for (Model model : mAllLists){
            String pinyin = characterParser.getSelling(model.getUsername());
            String sortString = pinyin.substring(0,1).toUpperCase();
            if (sortString.matches("[A-Z]")){
                model.setSortLetters(sortString.toUpperCase());
            }else{
                model.setSortLetters("#");
            }
            list.add(model);
        }
        Collections.sort(list, pinyinComparator);
    }

}
