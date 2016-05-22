package com.gzfgeh.CustomStickRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzfgeh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan on 2016-05-14.
 */
public class StickyHeaderAdapter extends RecyclerView.Adapter<StickyHeaderAdapter.MyViewHoder> {

    public  static final int ITEM_HEADER=0;
    public  static final int ITEM_STICKY_HEADER=1;
    public  static final int ITEM=2;
    private List<String> data=new ArrayList<>();
    private Context context;


    public StickyHeaderAdapter(Context context, List<String> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_HEADER){
           return new MyViewHoder(LayoutInflater.from(context).inflate(R.layout.recylcer_header,parent,false));
        }else if(viewType==ITEM_STICKY_HEADER){
            return  new MyViewHoder(LayoutInflater.from(context).inflate(R.layout.recylcer_sticky_header,parent,false));
        }else {
            return new MyViewHoder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        if(getItemViewType(position)==ITEM){
            holder.tv_content.setText(data.get(position-2));
        }

    }

    @Override
    public int getItemCount() {
        return data.size()+2;
    }




    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return ITEM_HEADER;
        }else if(position==1){
            return ITEM_STICKY_HEADER;
        }else {
            return ITEM;
        }
    }

    static  class MyViewHoder extends RecyclerView.ViewHolder{


        private TextView tv_content;
        public MyViewHoder(View itemView) {
            super(itemView);
            View view=itemView.findViewById(R.id.item_contact_title);
            if(view!=null){
                tv_content=(TextView)view;
            }
        }
    }
}
