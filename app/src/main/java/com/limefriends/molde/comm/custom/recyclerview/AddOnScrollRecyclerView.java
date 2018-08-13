package com.limefriends.molde.comm.custom.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;

public class AddOnScrollRecyclerView extends RecyclerView {

    private boolean isLoading = false;
    private int visibleThreshold = 1;
    private boolean isGridLayout;
    private LayoutManager layoutManager;
    private OnLoadMoreListener onLoadMoreListener;
//    private AdapterView.OnItemClickListener onItemClickedListener;

    public interface OnLoadMoreListener {
        void loadMore();
    }

//    public interface OnItemClickedListener<T> {
//        void itemClicked(T item);
//    }

    public AddOnScrollRecyclerView(Context context) {
        super(context);
    }

    public AddOnScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        addOnScrollListener(scrollListener);

    }

    public void setLayoutManager(LayoutManager layout, boolean isGridLayout) {
        super.setLayoutManager(layout);
        this.layoutManager = layout;
        this.isGridLayout = isGridLayout;
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public void setVisibleThreshold(int threshold) {
        if (threshold >= 10) return;
        this.visibleThreshold = threshold;
    }

    public void setOnLoadMoreListener (OnLoadMoreListener listener) {
        this.onLoadMoreListener = listener;
    }

//    public void setOnItemClickedListener(AdapterView.OnItemClickListener listener) {
//        this.onItemClickedListener = listener;
//    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    private OnScrollListener scrollListener = new OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = 0;
            int totalItemCount = 0;
            int firstOrLastVisibleItem = 0;

            if (isGridLayout) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                // int spanCount = gridLayoutManager.getSpanCount();
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                firstOrLastVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                // Log.d("tot:fir:vis:vis2-G", totalItemCount + ":" + firstOrLastVisibleItem + ":" + visibleItemCount+":"+layoutManager.getChildCount());
            } else {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstOrLastVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                // Log.d("tot:fir:vis:vis2-L", totalItemCount + ":" + firstVisibleItem + ":" + visibleItemCount+":"+layoutManager.getChildCount());
            }

            if (totalItemCount == visibleItemCount) return;

            if (!isLoading &&
                    (totalItemCount - visibleItemCount) <= firstOrLastVisibleItem + visibleThreshold) {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.loadMore();
                }
                isLoading = true;
            }
        }
    };




}
