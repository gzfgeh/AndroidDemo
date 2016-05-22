package com.gzfgeh.CustomRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.CustomRecycler.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.gzfgeh.R;

import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/5/12 17:37.
 */
public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
                    implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{
    private List<Model> mLists;

    public RecyclerViewAdapter(List<Model> mLists) {
        this.mLists = mLists;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(getItem(position).getUsername());
    }

    public Model getItem(int position){
        return mLists.get(position);
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }


    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSortLetters().charAt(0);
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
        String showValue = String.valueOf(getItem(position).getSortLetters().charAt(0));
        textView.setText(showValue);
    }

    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mLists.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_contact_title);
        }
    }
}
