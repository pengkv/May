package com.pengkv.may.interfaces;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/5/13.
 * RecyclerView滑到底部时的监听事件
 */
public class OnScrollBottomListener extends RecyclerView.OnScrollListener {

    private int lastVisibleItem;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private onBottomListener onBottomListener;

    public OnScrollBottomListener(RecyclerView.Adapter mAdapter, LinearLayoutManager mLayoutManager, OnScrollBottomListener.onBottomListener onBottomListener) {
        this.mAdapter = mAdapter;
        this.mLayoutManager = mLayoutManager;
        this.onBottomListener = onBottomListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
            this.onBottomListener.onBottomListener();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
    }

    public interface onBottomListener {
        void onBottomListener();
    }
}
