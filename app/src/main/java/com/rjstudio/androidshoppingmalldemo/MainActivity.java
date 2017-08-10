package com.rjstudio.androidshoppingmalldemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rjstudio.androidshoppingmalldemo.bean.TabHost;
import com.rjstudio.androidshoppingmalldemo.fragment.CartFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.CategoryFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.HomeFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.HotFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.MineFragment;
import com.rjstudio.androidshoppingmalldemo.widget.MyToolBar;

import java.util.ArrayList;
import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends AppCompatActivity {

    private List<TabHost> mTabHosts;
    private String TAG;
    private CartFragment cartFragment;
    private MyToolBar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();

    }

    private void initView() {


        FragmentTabHost fragmentTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTabHost.setup(this, fragmentManager, R.id.fg_content);

        TabHost tabHost_Home = new TabHost(HomeFragment.class, R.string.home);
        TabHost tabHost_Hot = new TabHost(HotFragment.class, R.string.hot);
        TabHost tabHost_Category = new TabHost(CategoryFragment.class, R.string.category);
        TabHost tabHost_Cart = new TabHost(CartFragment.class, R.string.cart);
        TabHost tabHost_Mine = new TabHost(MineFragment.class, R.string.me);

        mTabHosts = new ArrayList<>();
        mTabHosts.add(tabHost_Home);
        mTabHosts.add(tabHost_Hot);
        mTabHosts.add(tabHost_Category);
        mTabHosts.add(tabHost_Cart);
        mTabHosts.add(tabHost_Mine);


        fragmentTabHost.setOnTabChangedListener(new android.widget.TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.d("MainActivity", tabId);
                //当切换到Cart界面的时候刷新数据
                if (tabId == getString(R.string.cart)) {

                    Log.d("MainActivity-", tabId);

                    refreshCartFragmentData();
                    changeToolbarInCartPage();
                } else {
                    changeToOtherPage(tabId);
                }
            }
        });

        for (TabHost tabHost : mTabHosts) {
            TAG = "MainActivity";
            // Log.d(TAG, "initView: "+getString(tabHost.getmTitleTextId()));
            android.widget.TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(getString(tabHost.getmTitleTextId()));
            tabSpec.setIndicator(buildIndicator(tabHost));
            fragmentTabHost.addTab(tabSpec, tabHost.getMfragment(), null);
        }
        //mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

    }

    private View buildIndicator(TabHost tabHost) {
        View view = LayoutInflater.from(this).inflate(R.layout.indicator_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_indicator_icon);
        TextView textView = (TextView) view.findViewById(R.id.tv_indicator_title);
        //TODO :Set indicator imageIcon
        textView.setText(getString(tabHost.getmTitleTextId()));
        return view;
    }

    //当界面跳到购物车界面时,操作一下函数
    public void refreshCartFragmentData() {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.cart));

        //changeToolbar();
        if (fragment != null) {
            if (cartFragment == null) {

                cartFragment = (CartFragment) fragment;
                cartFragment.refreshData();
            } else {
                cartFragment.refreshData();
            }
        }


    }


    //当页面切换到购物车界面,工具栏的变化
    public void changeToolbarInCartPage() {
        //标题
        myToolBar.setTitle(getText(R.string.cart));
        //隐藏左按钮
        myToolBar.hideLeftButton();
        //显示右边按钮 -- 编辑按钮
//        myToolBar.hideRightButton();
        myToolBar.showRightButton();
        //隐藏搜索栏
        myToolBar.hideSearchBar();

    }

    //跳转到其他页面
    public void changeToOtherPage(final String tabId) {

        myToolBar.setTitle(tabId);
        //  myToolBar.hideTitle();
        if (tabId.equals("Home")) {
            myToolBar.hideSearchBar();
            myToolBar.showTitle();
            myToolBar.hideRightButton();
        } else {
            myToolBar.showRightButton();
        }
        // toolBar.showLeftButton();
    }

    //初始化工具栏
    public void initToolbar() {
        myToolBar = (MyToolBar) findViewById(R.id.toolbar);
        myToolBar.setTitle(getText(R.string.home));
        myToolBar.hideSearchBar();

    }
}