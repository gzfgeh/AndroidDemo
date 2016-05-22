package com.gzfgeh.CustomDrawable;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.LogUtils;
import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 16/5/22.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> datas = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("view-----:" + position);
                if (listener != null)
                    listener.setOnItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_contact_title);
        }
    }

    interface OnItemClickListener{
        void setOnItemClickListener(int position);
    }
}
