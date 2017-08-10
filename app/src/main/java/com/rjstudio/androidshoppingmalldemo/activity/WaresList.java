package com.rjstudio.androidshoppingmalldemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.google.gson.reflect.TypeToken;
import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.HotWaresAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.Page;
import com.rjstudio.androidshoppingmalldemo.bean.Ware;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.rjstudio.androidshoppingmalldemo.utils.PageUtil;
import com.rjstudio.androidshoppingmalldemo.widget.MyToolBar;

import java.util.List;

/**
 * Created by r0man on 2017/8/5.
 */

//继承三个点击事件 -> 页面请求有加载的点击事件 &

public class WaresList extends AppCompatActivity implements PageUtil.OnpageListener<Ware>,TabLayout.OnTabSelectedListener{
    //工具栏
    //商品排序变量
    //指示栏

    //工具栏指示值 -> 默认 & 价格 & 销量
    private final int DEFAULT_TAG = 0;
    private final int PRICE_TAG = 1;
    private final int SALES_TAG = 2;
    private TextView tv_sum;
    private MaterialRefreshLayout materialRefreshLayout;
    private RecyclerView recyclerView;
    //排序这部分工作,又后台完成
    private int orderBy = 0;
    private long campaignId = 1;
    private PageUtil pageUtil;
    private HotWaresAdapter hotWaresAdapter;


    private int GRID_LAYOUT = 1;
    private int LINE_LAYOUT = 2;
    private int STATUS = LINE_LAYOUT;
    private MyToolBar myToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wareslist_activity);
//        campaignId=getIntent().getLongExtra(Contants.COMPAINGAIN_ID,0);
        initView();
        requestData();

    }

    private void initView()
    {
        //工具栏
        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
        myToolBar.setRightButtonIcon(getDrawable(R.mipmap.ic_launcher_round));
        myToolBar.showRightButton();
        myToolBar.setTitle("分类");
        myToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (STATUS == GRID_LAYOUT )
                {
                    STATUS = LINE_LAYOUT;
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myToolBar.setRightButtonIcon(getDrawable(R.mipmap.ic_launcher));

                }
                else if (STATUS == LINE_LAYOUT)
                {
                    STATUS = GRID_LAYOUT;
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    myToolBar.setRightButtonIcon(getDrawable(R.mipmap.ic_launcher_round));

                }
            }
        });

        //TabLayout
        //给指示的工具栏设置标签与文字 -> text & tag
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tb_indicate);
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("DEFAULT");
        tab.setTag(DEFAULT_TAG);
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText("PRICE");
        tab.setTag(PRICE_TAG);
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText("SALE");
        tab.setTag(SALES_TAG);
        tabLayout.addTab(tab);
        tabLayout.setOnTabSelectedListener(this);

        //SumText
        tv_sum = (TextView) findViewById(R.id.tv_showSum);

        //MaterialRefreshLayout
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.rl_waresList);
        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.rv_showWareList);

    }

    //请求数据 -> 获取数据 -> 设置数据
    private void requestData()
    {
        pageUtil = PageUtil.newBuilder()
                .setUrl(Contants.API.WARES_CAMPAIN_LIST)
                .setRreshLayout(materialRefreshLayout)
                .setIsLoadMore(true)
                .setOnPageListener(this)
                .putParams("campaignId",campaignId)
                .putParams("orderBy",orderBy)
                .build(this,new TypeToken<Page<Ware>>(){}.getType());

        pageUtil.request();
    }

// RefreshLayout -> Override load method -> load data -> set recyclerView 's adapter

    @Override
    public void load(List<Ware> datas, int totalPage, int totalCount) {
        hotWaresAdapter = new HotWaresAdapter(this,datas);

        Log.d("Load",datas.size()+"--");
        recyclerView.setAdapter(hotWaresAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void refresh(List<Ware> datas, int totalPage, int totalCount) {
        hotWaresAdapter.refreshData(datas);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void loadMore(List<Ware> datas, int totalPage, int totalCount) {
        hotWaresAdapter.addData(datas);
    }

//    TabLayout

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        orderBy = (int) tab.getTag();
        pageUtil.putParam("orderBy",orderBy);
        pageUtil.request();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

