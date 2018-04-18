package com.airletnet.mobile.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class PullableRecyclerView extends WrapRecyclerView implements Pullable {
    public static final String TAG = PullableRecyclerView.class.getSimpleName();
    private View emptyView;
    public int mFirstVisiblePosition = -1;
    public int firstViewTop=0;
    public int mLastVisiblePosition = -1;
    private OnScrollUpListener mOnScrollUpListener;
    private int mTempLastVisiblePosition = -1;
    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public PullableRecyclerView(Context context) {
        this(context, null, 0);
    }

    public PullableRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullableRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (null != mOnScrollUpListener) {
//                    Log.i(TAG, "LastVisibleItemPosition:" + getLastVisibleItemPosition());
//                    Log.i(TAG, "position:" + mTempLastVisiblePosition);
//                    Log.i(TAG, "childCount:" + getChildCount());
                    if (getAdapter().getItemCount() - 1 == getLastVisibleItemPosition()) {
                        return;
                    }
                    if (mTempLastVisiblePosition < getLastVisibleItemPosition()
                            || mTempLastVisiblePosition - getLastVisibleItemPosition() >= getChildCount()) {
                        mTempLastVisiblePosition = getLastVisibleItemPosition();
                        mOnScrollUpListener.onScrollUp(mTempLastVisiblePosition);
                    }
                }
            }
        });
    }
    private void checkIfEmpty() {
        if (emptyView != null) {
            final boolean emptyViewVisible = getAdapter() == null ||
                    getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? INVISIBLE : VISIBLE);
        }
    }
    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }

    //设置没有内容时，提示用户的空布局
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
    @Override
    public boolean canPullDown() {
        LayoutManager lm = getLayoutManager();
        if(lm ==null)
            return true;
        mFirstVisiblePosition = getFirstVisibleItemPosition();
        View view = lm.findViewByPosition(mFirstVisiblePosition);
        int count = getAdapter().getItemCount();
        if (0 == count) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (null != view && view.getTop() == firstViewTop && mFirstVisiblePosition == 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    public void setFirstViewTop(int firstViewTop){
        this.firstViewTop =firstViewTop;
    }

    @Override
    public boolean canPullUp() {
        LayoutManager lm = getLayoutManager();
        if(lm ==null)
            return true;
        mFirstVisiblePosition = getFirstVisibleItemPosition();
        mLastVisiblePosition = getLastVisibleItemPosition();
        int count = getAdapter().getItemCount();
        if (0 == count) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (mLastVisiblePosition == (count - 1)) {
            // 滑到底部了
            if (lm.findViewByPosition(count - 1).getBottom() <= getMeasuredHeight()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取顶部可见项的位置
     *
     * @return
     */
    public int getFirstVisibleItemPosition() {
        LayoutManager lm = getLayoutManager();
        int firstVisibleItemPosition = 0;
        if (lm instanceof GridLayoutManager) {
            firstVisibleItemPosition = ((GridLayoutManager) lm).findFirstVisibleItemPosition();
        } else if (lm instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
        } else if (lm instanceof StaggeredGridLayoutManager) {
            int positions[] = new int[1];
            ((StaggeredGridLayoutManager) lm).findFirstVisibleItemPositions(positions);
            firstVisibleItemPosition = positions[0];
        }
        return firstVisibleItemPosition;
    }

    /**
     * 获取底部可见项的位置
     *
     * @return
     */
    public int getLastVisibleItemPosition() {
        LayoutManager lm = getLayoutManager();
        int lastVisibleItemPosition = 0;
        if (lm instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) lm).findLastVisibleItemPosition();
        } else if (lm instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
        } else if (lm instanceof StaggeredGridLayoutManager) {
            int positions[] = new int[1];
            ((StaggeredGridLayoutManager) lm).findLastVisibleItemPositions(positions);
            lastVisibleItemPosition = positions[0];
        }
        return lastVisibleItemPosition;
    }

    public int getFirstVisiblePosition() {
        return mFirstVisiblePosition;
    }

    public int getLastVisiblePosition() {
        return mLastVisiblePosition;
    }

    public void setOnScrollUpListener(OnScrollUpListener listener) {
        mOnScrollUpListener = listener;
    }

    /**
     * 向上滚动监听
     */
    public interface OnScrollUpListener {
        void onScrollUp(int position);
    }
}
