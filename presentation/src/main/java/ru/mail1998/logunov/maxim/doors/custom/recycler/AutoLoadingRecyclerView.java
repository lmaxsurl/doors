package ru.mail1998.logunov.maxim.doors.custom.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class AutoLoadingRecyclerView extends RecyclerView {

    private PublishSubject<OffsetAndLimit> scrolledItemsSubject = PublishSubject.create();
    private int limit;

    public AutoLoadingRecyclerView(Context context) {
        super(context);
        init();
    }

    public AutoLoadingRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoLoadingRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        startScrollingChannel();
    }

    private void startScrollingChannel() {
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int position = getLastVisibleItemPosition();
                int limit = getLimit();
                int updatePosition = getAdapter().getItemCount() - 1 - (limit / 2);
                if (position >= updatePosition) {
                    int offset = getAdapter().getItemCount();
                    scrolledItemsSubject.onNext(new OffsetAndLimit(offset, limit));
                }
            }
        });
    }

    private int getLastVisibleItemPosition() {
        Class recyclerViewLMClass = getLayoutManager().getClass();
        if (recyclerViewLMClass == LinearLayoutManager.class
                || LinearLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
            return linearLayoutManager.findLastVisibleItemPosition();
        } else if (recyclerViewLMClass == StaggeredGridLayoutManager.class
                || StaggeredGridLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] into = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            List<Integer> intoList = new ArrayList<>();
            for (int i : into) {
                intoList.add(i);
            }
            return Collections.max(intoList);
        }
        throw new AutoLoadingRecyclerViewException("Unknown LayoutManager class: " + recyclerViewLMClass.toString());
    }

    public int getLimit() {
        if (limit <= 0) {
            throw new AutoLoadingRecyclerViewException("limit must be initialised! And limit must be more than zero!");
        }
        return limit;
    }

    /**
     * required method
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Observable<OffsetAndLimit> observeScrolledData() {
        return scrolledItemsSubject;
    }
}
