package com.gzfgeh.CustomRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.gzfgeh.LogUtils;
import com.gzfgeh.R;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/6/8 10:05.
 */
public class NewRecyclerAdapter extends BaseRecyclerAdapter<Model> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacts, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int position, Model data) {
        if (viewHolder instanceof MyViewHolder) {
            ((MyViewHolder)viewHolder).mName.setText(data.getUsername());
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public MyViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_contact_title);
        }
    }

    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mDatas.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public Model getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public long getHeaderId(int position) {
        int i = getItem(position).getSortLetters().charAt(0);
        LogUtils.i("i------" + i);
        return i;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.head_text);
        String showValue = String.valueOf(getItem(position).getSortLetters().charAt(0));
        textView.setText(showValue);
    }


}
