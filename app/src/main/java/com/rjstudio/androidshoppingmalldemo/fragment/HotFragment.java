package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.HotWaresAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.Page;
import com.rjstudio.androidshoppingmalldemo.bean.Ware;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.rjstudio.androidshoppingmalldemo.http.SpotsCallback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * Created by r0man on 2017/7/26.
 */

public class HotFragment extends Fragment {

    private int curPage = 1;
    private int pageSize = 10;
    private OKHttpHelper httpHelper;
    private RecyclerView recyclerView;
    private List<Ware> pageDatas;
    private Page<Ware> curPageData;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREN = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;
    private MaterialRefreshLayout mRefreshLayout;
    private HotWaresAdapter hotWaresAdapter;
    private int totalPages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.hot_layout,null);
        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.rl_hot);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_showHotProductItem);
        initRefreshLayout();
        getData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setData(List<Ware> data)
    {

        switch (state)
        {
            case STATE_NORMAL:
                hotWaresAdapter = new HotWaresAdapter(getContext(),data);
                recyclerView.setAdapter(hotWaresAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case STATE_REFREN:
                hotWaresAdapter.refreshData(data);
                recyclerView.scrollToPosition(0);
                mRefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                recyclerView.scrollToPosition(hotWaresAdapter.addData(data));
//
//                int recordPosition = hotWaresAdapter.getLastPosition();
//                hotWaresAdapter.addData(recordPosition,data);
//                recyclerView.scrollToPosition(recordPosition);
                mRefreshLayout.finishRefreshLoadMore();
                Log.d("B", "setData: finished.");
                break;
        }

    }


    private void getData()
    {
        String url = Contants.API.WARES_HOT+"?curPage="+ curPage +"&pageSize="+ pageSize;
        Log.d("XXXX", "getData: "+url);
        httpHelper = OKHttpHelper.getInstance();
        httpHelper.get(url, new SpotsCallback<Page<Ware>>(getContext()) {
            @Override
            public void onFailure(Request request, Exception e) {
                Log.d("xxx", "onFailure: ");
            }

            @Override
            public void onSuccess(Response response, Page<Ware> waresPage) {
                //curPageData = waresPage;
                setData(waresPage.getList());
                for (Ware w : waresPage.getList())
                {
                    Log.d("Name",w.getName());
                }
                totalPages = waresPage.getTotalCount();

            }

            @Override
            public void onError(Response response, int code, Exception e) {
                Log.d("xxx", "onError: ");

            }
        });
    }

    private void initRefreshLayout()
    {
        mRefreshLayout.setLoadMore(true);
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                if (curPage <= totalPages)
                {
                    Log.d("A", "onRefresh: "+totalPages);
                    refreshData();
                }
                else
                {
                    Toast.makeText(getContext(), "已经是最后一页喽 >,< ", Toast.LENGTH_SHORT).show();
                    materialRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (curPage < totalPages-1)
                {
                    loadMoreData();
                }
                else
                {
                    Toast.makeText(getContext(), "已经是最后一页喽 >,< ", Toast.LENGTH_SHORT).show();
                    materialRefreshLayout.finishRefreshLoadMore();
                }
            }
        });


    }

    private void refreshData()
    {
        curPage ++;
        state = STATE_REFREN;
        getData();

    }

    private void loadMoreData()
    {
        curPage ++;
        state = STATE_MORE;
        getData();
    }
}
