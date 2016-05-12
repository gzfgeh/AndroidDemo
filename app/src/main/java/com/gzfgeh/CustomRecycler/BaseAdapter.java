package com.gzfgeh.CustomRecycler;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/5/12 17:18.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<Model> models = new ArrayList<>();

    public BaseAdapter() {
        setHasStableIds(true);
    }

    public void addAll(Collection<Model> collection){
        if (collection != null){
            models.clear();
            models.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public Model getItem(int position){
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
