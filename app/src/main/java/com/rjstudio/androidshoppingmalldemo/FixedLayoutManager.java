package com.rjstudio.androidshoppingmalldemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by r0man on 2017/7/31.
 */

public class FixedLayoutManager extends GridLayoutManager {
    public FixedLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FixedLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FixedLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try
        {
            super.onLayoutChildren(recycler, state);
        }
        catch (Exception e)
        {

        }
    }
}
