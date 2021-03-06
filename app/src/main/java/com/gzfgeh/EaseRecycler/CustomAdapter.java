package com.gzfgeh.EaseRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
public class CustomAdapter extends CustomRecyclerAdapter<String>{

    public CustomAdapter(Context context, int resId) {
        super(context, resId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_contact_title, item);
    }

//    @Override
//    public long getHeaderId(int position) {
//        if (position >= getHeaderCount() && position < getCount())
//            return 2;
//        else
//            return 2;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.stick_header, parent, false);
//        return new RecyclerView.ViewHolder(view){};
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Button btn = (Button) holder.itemView.findViewById(R.id.stick_button);
//        btn.setText(holder.itemView.getContext().getString(R.string.app_name));
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "ddddd-----", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
