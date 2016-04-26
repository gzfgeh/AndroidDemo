package com.gzfgeh.CustomViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description: 自定义ViewGroup onMeasure onLayout onDraw
 *              传入4个ChildView 分别依次显示在左上角，右上角，左下角，右下角
 * Created by guzhenfu on 2016/4/18 10:08.
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 只需要Margin支持
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 根据xml配置计算ChildView的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = 0, height = 0;
        int cnt = getChildCount();
        int cWidth = 0, cHeight = 0;
        MarginLayoutParams params = null;
        int lHeight = 0, rHeight = 0;       //左边两个ChildView高度 和 右边两个ChildView高度
        int tWidth = 0, bWidth = 0;         //顶部两个ChildView宽度 和 底部两个ChildView宽度

        for(int i=0; i<cnt; i++){
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            params = (MarginLayoutParams) childView.getLayoutParams();

            //上面两个View
            if (i == 0 || i == 1){
                tWidth += cWidth + params.leftMargin + params.rightMargin;
            }

            if (i == 2 || i == 3){
                bWidth += cWidth + params.leftMargin + params.rightMargin;
            }

            if (i == 0 || i == 2){
                lHeight += cHeight + params.topMargin + params.bottomMargin;
            }

            if (i == 1 || i == 3){
                rHeight += cHeight + params.topMargin + params.bottomMargin;
            }
        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width
                , (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cnt = getChildCount();
        int cWidth = 0, cHeight = 0;
        MarginLayoutParams params = null;

        for (int i=0; i< cnt; i++){
            View view = getChildAt(i);
            cWidth = view.getMeasuredWidth();
            cHeight = view.getMeasuredHeight();
            params = (MarginLayoutParams) view.getLayoutParams();
            int left = 0, top = 0, right = 0, bottom = 0;
            switch (i){
                case 0:
                    left = params.leftMargin;
                    top = params.topMargin;
                    break;

                case 1:
                    left = getWidth() - cWidth - params.rightMargin;
                    top = params.topMargin;
                    break;

                case 2:
                    left = params.leftMargin;
                    top = getHeight() - cHeight - params.bottomMargin;
                    break;

                case 3:
                    left = getWidth() - cWidth - params.rightMargin;
                    top = getHeight() - cHeight - params.bottomMargin;
                    break;
            }

            right = left + cWidth;
            bottom = top + cHeight;
            view.layout(left, top, right, bottom);
        }
    }
}
