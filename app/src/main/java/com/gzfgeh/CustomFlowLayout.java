package com.gzfgeh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by guzhenfu on 2016/4/18 11:35.
 */
public class CustomFlowLayout extends ViewGroup {

    public CustomFlowLayout(Context context) {
        this(context, null);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0, height = 0, lineWidth = 0, lineHeight = 0;
        int cnt = getChildCount();
        for(int i=0; i<cnt; i++){
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int childWidth = view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = view.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (lineWidth + childWidth > sizeWidth){
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            }else{
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == cnt - 1){
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }


    private List<List<View>> allViews = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        allViews.clear();
        mLineHeight.clear();

        int width = getWidth();
        int lineWidth = 0, lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int cnt = getChildCount();
        for (int i=0; i<cnt; i++){
            View view = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();
            if (childWidth + params.leftMargin + params.rightMargin + lineWidth > width){
                mLineHeight.add(lineHeight);
                allViews.add(lineViews);
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }

            lineWidth += childWidth + params.leftMargin + params.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + params.topMargin + params.bottomMargin);
            lineViews.add(view);
        }

        mLineHeight.add(lineHeight);
        allViews.add(lineViews);

        int left = 0, top = 0;
        int lineNum = allViews.size();
        for (int i=0; i<lineNum; i++){
            lineViews = allViews.get(i);
            lineHeight = mLineHeight.get(i);

            for(int j=0; j<lineViews.size(); j++){
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE){
                    continue;
                }
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int childLeft = left + params.leftMargin;
                int childTop = top + params.topMargin;
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = childTop + child.getMeasuredHeight();

                child.layout(childLeft, childTop, childRight, childBottom);
                left += child.getMeasuredWidth() + params.rightMargin + params.leftMargin;
            }

            left = 0;
            top += lineHeight;
        }
    }

}
