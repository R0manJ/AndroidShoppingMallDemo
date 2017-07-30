package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjstudio.androidshoppingmalldemo.R;

/**
 * Created by r0man on 2017/7/26.
 */

public class CategoryFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.category_layout,null);
        //view = inflater.inflate(R.layout.category_layout,null);
        initRefreshLayout();
        return view;
    }

    private void initRefreshLayout()
    {
        if (view != null)
        {
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
            swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_light));
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                }
            });
        }
    }
}
