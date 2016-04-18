package com.gzfgeh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Description:
 * Created by guzhenfu on 2016/4/18 14:16.
 */
public class CustomTagLayout extends ViewGroup {
    private Context context;
    private int tagMargin, width;
    private OnTagClickListener mClickListener;
    private List<String> data = new ArrayList<>();

    public CustomTagLayout(Context context) {
        this(context, null);
    }

    public CustomTagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TagLayout, defStyleAttr, defStyleAttr);
        tagMargin = (int) array.getDimension(R.styleable.TagLayout_tagMargin, Utils.dp2Px(5));
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        if (w <= 0){
            return;
        }
        width = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTags();
    }

    private void drawTags() {
        removeAllViews();
        final int index = 0;
        float total = getPaddingLeft() + getPaddingRight();     //初始大小
        for (final String s : data){
            View view = LayoutInflater.from(context).inflate(R.layout.tagview_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_tag_item_contain);
            textView.setText(s);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null)
                        mClickListener.onTagClick(s, index);
                }
            });

            MarginLayoutParams params = (MarginLayoutParams) textView.getLayoutParams();
            float tagWidth = textView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (width < total + tagWidth + tagMargin){

            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setOnTagClickListener(OnTagClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface OnTagClickListener {
        void onTagClick(String tag, int position);
    }


}
