package com.gzfgeh.Recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gzfgeh.R;
import com.gzfgeh.SwipeRefresh.CustomSwipeRefreshLayout;


public class CustomRecyclerView extends FrameLayout {
    public static final String TAG = "EasyRecyclerView";
    public static boolean DEBUG = false;
    protected RecyclerView mRecycler;
    protected ViewGroup mProgressView;
    protected ViewGroup mLoginView;
    private View mEmptyView;
    private View mErrorView;
    private int mProgressId;
    private int mEmptyId;
    private int mErrorId;
    private int mLoginId;

    protected boolean mClipToPadding;
    protected int mPadding;
    protected int mPaddingTop;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mScrollbarStyle;
    private CustomRecyclerAdapter.ItemView emptyItemView, errorItemView;


    protected RecyclerView.OnScrollListener mInternalOnScrollListener;
    protected RecyclerView.OnScrollListener mExternalOnScrollListener;

//    protected SwipeRefreshLayout mPtrLayout;
//    protected SwipeRefreshLayout.OnRefreshListener mRefreshListener;

    protected CustomSwipeRefreshLayout mPtrLayout;
    protected CustomSwipeRefreshLayout.OnRefreshListener mRefreshListener;

    public RecyclerView getRecyclerView() {
        return mRecycler;
    }

    public CustomRecyclerView(Context context) {
        super(context);
        initView();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        initView();
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.superrecyclerview);
        try {
            mClipToPadding = a.getBoolean(R.styleable.superrecyclerview_recyclerClipToPadding, false);
            mPadding = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPadding, -1.0f);
            mPaddingTop = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingTop, 0.0f);
            mPaddingBottom = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingBottom, 0.0f);
            mPaddingLeft = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingLeft, 0.0f);
            mPaddingRight = (int) a.getDimension(R.styleable.superrecyclerview_recyclerPaddingRight, 0.0f);
            mScrollbarStyle = a.getInteger(R.styleable.superrecyclerview_scrollbarStyle, -1);

            mEmptyId = a.getResourceId(R.styleable.superrecyclerview_layout_empty, 0);
            mProgressId = a.getResourceId(R.styleable.superrecyclerview_layout_progress, 0);
            mErrorId = a.getResourceId(R.styleable.superrecyclerview_layout_error, 0);
            mLoginId = a.getResourceId(R.styleable.superrecyclerview_layout_login, 0);
        } finally {
            a.recycle();
        }
    }

    private void initView() {
        if (isInEditMode()) {
            return;
        }
        //生成主View
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_progress_recyclerview, this);
        mPtrLayout = (CustomSwipeRefreshLayout) v.findViewById(R.id.ptr_layout);
        mPtrLayout.setEnabled(false);

        mProgressView = (ViewGroup) v.findViewById(R.id.progress);
        if (mProgressId!=0)
            LayoutInflater.from(getContext()).inflate(mProgressId,mProgressView);
        if (mEmptyId != 0)
            mEmptyView = LayoutInflater.from(getContext()).inflate(mEmptyId, mRecycler, false);
        if (mErrorId != 0)
            mErrorView = LayoutInflater.from(getContext()).inflate(mErrorId, mRecycler, false);
        mLoginView = (ViewGroup) v.findViewById(R.id.login);
        if (mLoginId != 0)
            LayoutInflater.from(getContext()).inflate(mLoginId, mLoginView);
        initRecyclerView(v);

        if (mEmptyId != 0) {
            emptyItemView = new CustomRecyclerAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    return mEmptyView;
                }

                @Override
                public void onBindView(View headerView) {}
            };
        }

        if (mErrorId != 0) {
            errorItemView = new CustomRecyclerAdapter.ItemView() {
                @Override
                public View onCreateView(ViewGroup parent) {
                    return mErrorView;
                }

                @Override
                public void onBindView(View headerView) {

                }
            };
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mPtrLayout.dispatchTouchEvent(ev);
    }

    public void setRecyclerPadding(int left,int top,int right,int bottom){
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
        mRecycler.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
    }

    public void setEmptyView(View emptyView){
        mEmptyView = emptyView;
        emptyItemView = new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mEmptyView;
            }

            @Override
            public void onBindView(View headerView) {}
        };
    }
    public void setProgressView(View progressView){
        mProgressView.removeAllViews();
        mProgressView.addView(progressView);
    }

    public void setLoginView(View loginView){
        mLoginView.removeAllViews();
        mLoginView.addView(loginView);
    }
    public void setErrorView(View errorView){
        mErrorView = errorView;
        errorItemView = new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mErrorView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        };
    }
    public void setEmptyView(int emptyView){
        mEmptyView = LayoutInflater.from(getContext()).inflate(emptyView, mRecycler, false);
        emptyItemView = new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mEmptyView;
            }

            @Override
            public void onBindView(View headerView) {}
        };
    }
    public void setProgressView(int progressView){
        mProgressView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(progressView, mProgressView);
    }

    public void setLoginView(int loginView){
        mLoginView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(loginView, mLoginView);
    }

    public void setErrorView(int errorView){
        mErrorView = LayoutInflater.from(getContext()).inflate(errorView, mRecycler, false);
        errorItemView = new CustomRecyclerAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mErrorView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        };
    }

    public void scrollToPosition(int position){
        getRecyclerView().scrollToPosition(position);
    }

    /**
     * Implement this method to customize the AbsListView
     */
    protected void initRecyclerView(View view) {
        mRecycler = (RecyclerView) view.findViewById(android.R.id.list);

        if (mRecycler != null) {
            mRecycler.setHasFixedSize(true);
            mRecycler.setClipToPadding(mClipToPadding);
            mInternalOnScrollListener = new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (mExternalOnScrollListener != null)
                        mExternalOnScrollListener.onScrolled(recyclerView, dx, dy);

                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    //滚到的时候有动画，不滚动没动画
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING){
                        if (getAdapter() instanceof CustomRecyclerAdapter){
                            ((CustomRecyclerAdapter) getAdapter()).isLoadAnimation(true);
                        }
                    }else{
                        if (getAdapter() instanceof CustomRecyclerAdapter){
                            ((CustomRecyclerAdapter) getAdapter()).isLoadAnimation(false);
                        }
                    }

                    if (mExternalOnScrollListener != null)
                        mExternalOnScrollListener.onScrollStateChanged(recyclerView, newState);

                }
            };
            mRecycler.addOnScrollListener(mInternalOnScrollListener);

            if (mPadding != -1.0f) {
                mRecycler.setPadding(mPadding, mPadding, mPadding, mPadding);
            } else {
                mRecycler.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            }
            if (mScrollbarStyle != -1) {
                mRecycler.setScrollBarStyle(mScrollbarStyle);
            }
        }
        showRecycler();
    }


    /**
     * Set the layout manager to the recycler
     *
     * @param manager
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecycler.setLayoutManager(manager);
    }


    private static class EasyDataObserver extends AdapterDataObserver {
        private CustomRecyclerView recyclerView;
        private boolean isInitialized = false;
        private boolean hasProgress = false;
        private boolean isLoadEmpty = false;

        public EasyDataObserver(CustomRecyclerView recyclerView,boolean hasProgress) {
            this.recyclerView = recyclerView;
            this.hasProgress = hasProgress;
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            update();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            update();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            update();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            update();
        }

        @Override
        public void onChanged() {
            super.onChanged();
            update();
        }

        //自动更改Container的样式
        private void update() {
            log("update");
            if (recyclerView.getAdapter() instanceof CustomRecyclerAdapter) {
                if (((CustomRecyclerAdapter) recyclerView.getAdapter()).getCount() == 0){
                    log("no data:"+((hasProgress&&!isInitialized)?"show progress":"show empty"));
                    if (hasProgress&&!isInitialized)
                        recyclerView.showProgress();
                    else {
                        if (isLoadEmpty)
                            recyclerView.showEmpty();
                        isLoadEmpty = true;
                    }
                } else{
                    log("has data");
                    recyclerView.showRecycler();
                }
            } else {
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    log("no data:"+((hasProgress&&!isInitialized)?"show progress":"show empty"));
                    if (hasProgress&&!isInitialized)recyclerView.showProgress();
                    else recyclerView.showEmpty();
                } else{
                    log("has data");
                    recyclerView.showRecycler();
                }
            }
            isInitialized = true;//设置Adapter时会有一次onChange。忽略此次。
        }
    }

    /**
     * 设置适配器，关闭所有副view。展示recyclerView
     * 适配器有更新，自动关闭所有副view。根据条数判断是否展示EmptyView
     *
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecycler.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new EasyDataObserver(this,false));
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置适配器，关闭所有副view。展示进度条View
     * 适配器有更新，自动关闭所有副view。根据条数判断是否展示EmptyView
     *
     * @param adapter
     */
    public void setAdapterWithProgress(RecyclerView.Adapter adapter) {
        mRecycler.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new EasyDataObserver(this,true));
        adapter.notifyDataSetChanged();
    }

    /**
     * Remove the adapter from the recycler
     */
    public void clear() {
        mRecycler.setAdapter(null);
    }


    private void hideAll(){
        if (mLoginView != null)
            mLoginView.setVisibility(GONE);
        if (mProgressView != null)
            mProgressView.setVisibility(View.GONE);
        mPtrLayout.setRefreshing(false);
        mRecycler.setVisibility(VISIBLE);
        if (getAdapter() instanceof CustomRecyclerAdapter) {
            CustomRecyclerAdapter adapter = (CustomRecyclerAdapter) getAdapter();
            if (emptyItemView != null)
                adapter.removeHeader(emptyItemView);
            if (errorItemView != null)
                adapter.removeHeader(errorItemView);
        }
    }


    public void showError() {
        log("showError");
        hideAll();
        if (getAdapter() instanceof CustomRecyclerAdapter){
            CustomRecyclerAdapter adapter = (CustomRecyclerAdapter) getAdapter();
            if (adapter.getCount() == 0){
                adapter.addHeader(errorItemView);
            }
        }
    }

    public void showEmpty() {
        log("showEmpty");
        hideAll();
        if (getAdapter() instanceof CustomRecyclerAdapter){
            CustomRecyclerAdapter adapter = (CustomRecyclerAdapter) getAdapter();
            if (adapter.getCount() == 0){
                adapter.addHeader(emptyItemView);
            }
        }

    }


    public void showProgress() {
        log("showProgress");
        hideAll();
        if (mProgressView.getChildCount()>0){
            if (getAdapter() instanceof CustomRecyclerAdapter){
                if (((CustomRecyclerAdapter) getAdapter()).getCount() == 0){
                    mRecycler.setVisibility(GONE);
                }
            }
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

    public void showLogin() {
        hideAll();
        if (mLoginView.getChildCount()>0){
            if (getAdapter() instanceof CustomRecyclerAdapter){
                if (((CustomRecyclerAdapter) getAdapter()).getCount() == 0){
                    mRecycler.setVisibility(GONE);
                }
            }
            mLoginView.setVisibility(View.VISIBLE);
        }
    }


    public void showRecycler() {
        log("showRecycler");
        hideAll();
        if (getAdapter() instanceof CustomRecyclerAdapter) {
            CustomRecyclerAdapter adapter = (CustomRecyclerAdapter) getAdapter();
            if (emptyItemView != null)
                adapter.removeHeader(emptyItemView);
            if (errorItemView != null)
                adapter.removeHeader(errorItemView);
        }
    }


    /**
     * Set the listener when refresh is triggered and enable the SwipeRefreshLayout
     *
     * @param listener
     */
    public void setRefreshListener(CustomSwipeRefreshLayout.OnRefreshListener listener) {
        mPtrLayout.setEnabled(true);
        mPtrLayout.setOnRefreshListener(listener);
        this.mRefreshListener = listener;
    }

    public void setRefreshing(final boolean isRefreshing){
        mPtrLayout.post(new Runnable() {
            @Override
            public void run() {
                mPtrLayout.setRefreshing(isRefreshing);
            }
        });
    }

    public void setRefreshing(final boolean isRefreshing, final boolean isCallbackListener){
        mPtrLayout.post(new Runnable() {
            @Override
            public void run() {
                mPtrLayout.setRefreshing(isRefreshing);
                if (isRefreshing&&isCallbackListener&&mRefreshListener!=null){
                    mRefreshListener.onRefresh();
                }
            }
        });
    }

    /**
     * Set the scroll listener for the recycler
     *
     * @param listener
     */
    public void setOnScrollListener(RecyclerView.OnScrollListener listener) {
        mExternalOnScrollListener = listener;
    }

    /**
     * Add the onItemTouchListener for the recycler
     *
     * @param listener
     */
    public void addOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        mRecycler.addOnItemTouchListener(listener);
    }

    /**
     * Remove the onItemTouchListener for the recycler
     *
     * @param listener
     */
    public void removeOnItemTouchListener(RecyclerView.OnItemTouchListener listener) {
        mRecycler.removeOnItemTouchListener(listener);
    }

    /**
     * @return the recycler adapter
     */
    public RecyclerView.Adapter getAdapter() {
        return mRecycler.getAdapter();
    }


    public void setOnTouchListener(OnTouchListener listener) {
        mRecycler.setOnTouchListener(listener);
    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecycler.setItemAnimator(animator);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecycler.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int index) {
        mRecycler.addItemDecoration(itemDecoration, index);
    }

    public void removeItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecycler.removeItemDecoration(itemDecoration);
    }


    /**
     * @return inflated error view or null
     */
    public View getErrorView() {
        return mErrorView;
    }

    /**
     * @return inflated progress view or null
     */
    public View getProgressView() {
        if (mProgressView.getChildCount()>0)
            return mProgressView.getChildAt(0);
        return null;
    }

    public View getLoginView() {
        if (mLoginView.getChildCount()>0)
            return mLoginView.getChildAt(0);
        return null;
    }


    /**
     * @return inflated empty view or null
     */
    public View getEmptyView() {
        return mEmptyView;
    }

    private static void log(String content){
        if (DEBUG){
            Log.i(TAG,content);
        }
    }

}
