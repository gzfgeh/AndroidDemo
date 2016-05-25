package com.gzfgeh.EaseRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.gzfgeh.R;
import com.gzfgeh.Recycler.BaseViewHolder;
import com.gzfgeh.Recycler.CustomRecyclerAdapter;

/**
 * Description:
 * Created by guzhenfu on 16/5/25.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class CustomAdapter extends CustomRecyclerAdapter<String>
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    public CustomAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_contact_title, item);
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        String showValue = String.valueOf(getItem(position).charAt(0));
        textView.setText(showValue);
    }
}