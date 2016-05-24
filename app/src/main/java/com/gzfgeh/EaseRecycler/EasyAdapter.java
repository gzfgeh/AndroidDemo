package com.gzfgeh.EaseRecycler;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/5/24 15:05.
 */
public class EasyAdapter extends BaseQuickAdapter<String> {

    public EasyAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
}
