package com.gzfgeh.CustomChart;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gzfgeh.R;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Description:
 * Created by guzhenfu on 2016/4/29 10:04.
 */
public class CustomChartView extends FrameLayout {
    private int mProgressId;
    private int mEmptyId;
    private int mErrorId;
    private int mNoMoreId;
    protected ViewGroup mNoMoreView;
    protected ViewGroup mProgressView;
    protected ViewGroup mEmptyView;
    protected ViewGroup mErrorView;
    private LineChartView chart;
    //protected SwipeRefreshLayout mPtrLayout;

    public CustomChartView(Context context) {
        this(context, null);
    }

    public CustomChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomChartView);
        mEmptyId = array.getResourceId(R.styleable.CustomChartView_layout_empty_id, 0);
        mProgressId = array.getResourceId(R.styleable.CustomChartView_layout_progress_id, 0);
        mErrorId = array.getResourceId(R.styleable.CustomChartView_layout_error_id, 0);
        mNoMoreId = array.getResourceId(R.styleable.CustomChartView_layout_nomore_id, 0);
        array.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_char_view, this);
//        mPtrLayout = (SwipeRefreshLayout) v.findViewById(com.jude.easyrecyclerview.R.id.ptr_layout);
//        mPtrLayout.setEnabled(false);
        chart = (LineChartView) v.findViewById(R.id.chart);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setZoomEnabled(true);
        chart.setScrollEnabled(true);
        chart.getTouchHandler().disallowParentInterceptTouchEvent();

        mProgressView = (ViewGroup) v.findViewById(R.id.progress);
        if (mProgressId!=0)LayoutInflater.from(getContext()).inflate(mProgressId,mProgressView);
        mEmptyView = (ViewGroup) v.findViewById(R.id.empty);
        if (mEmptyId!=0)LayoutInflater.from(getContext()).inflate(mEmptyId,mEmptyView);
        mErrorView = (ViewGroup) v.findViewById(R.id.error);
        if (mErrorId!=0)LayoutInflater.from(getContext()).inflate(mErrorId,mErrorView);
        mNoMoreView = (ViewGroup) v.findViewById(R.id.no_more);
        if (mNoMoreId != 0) LayoutInflater.from(context).inflate(mNoMoreId, mNoMoreView);
        hideAll();
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return mPtrLayout.dispatchTouchEvent(ev);
//    }

    public void setNoMoreView(int noMoreView){
        mNoMoreView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(noMoreView, mNoMoreView);
    }

    public void setEmptyView(int emptyView){
        mEmptyView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(emptyView, mEmptyView);
    }
    public void setProgressView(int progressView){
        mProgressView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(progressView, mProgressView);
    }
    public void setErrorView(int errorView){
        mErrorView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(errorView, mErrorView);
    }

    public void hideAll(){
        mEmptyView.setVisibility(View.GONE);
        mProgressView.setVisibility(View.GONE);
        mErrorView.setVisibility(GONE);
        mNoMoreView.setVisibility(GONE);
    }

    public void showNoMore() {
        if (mNoMoreView.getChildCount()>0){
            hideAll();
            mNoMoreView.setVisibility(View.VISIBLE);
        }
    }

    public void showError() {
        if (mErrorView.getChildCount()>0){
            hideAll();
            mErrorView.setVisibility(View.VISIBLE);
        }
    }

    public void showEmpty() {
        if (mEmptyView.getChildCount()>0){
            hideAll();
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    public void showProgress() {
        if (mProgressView.getChildCount()>0){
            hideAll();
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

    public Chart getChart() {
        return chart;
    }

}
