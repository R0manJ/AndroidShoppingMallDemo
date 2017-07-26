package com.rjstudio.androidshoppingmalldemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.bean.TabHost;
import com.rjstudio.androidshoppingmalldemo.fragment.CartFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.CategoryFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.HomeFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.HotFragment;
import com.rjstudio.androidshoppingmalldemo.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TabHost> mTabHosts;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView()
    {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        FragmentTabHost fragmentTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTabHost.setup(this,fragmentManager,R.id.fg_content);

        TabHost tabHost_Home = new TabHost(HomeFragment.class,R.string.home);
        TabHost tabHost_Hot = new TabHost(HotFragment.class,R.string.hot);
        TabHost tabHost_Category = new TabHost(CategoryFragment.class,R.string.category);
        TabHost tabHost_Cart = new TabHost(CartFragment.class,R.string.cart);
        TabHost tabHost_Mine = new TabHost(MineFragment.class,R.string.me);

        mTabHosts = new ArrayList<>();
        mTabHosts.add(tabHost_Home);
        mTabHosts.add(tabHost_Hot);
        mTabHosts.add(tabHost_Category);
        mTabHosts.add(tabHost_Cart);
        mTabHosts.add(tabHost_Mine);

        for (TabHost tabHost : mTabHosts)
        {
            TAG = "MainActivity";
           // Log.d(TAG, "initView: "+getString(tabHost.getmTitleTextId()));
            android.widget.TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(getString(tabHost.getmTitleTextId()));
            tabSpec.setIndicator(buildIndicator(tabHost));
            fragmentTabHost.addTab(tabSpec,tabHost.getMfragment(),null);
        }
        //mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //fragmentTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

    }

    private View buildIndicator(TabHost tabHost) {
        View view = LayoutInflater.from(this).inflate(R.layout.indicator_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_indicator_icon);
        TextView textView = (TextView) view.findViewById(R.id.tv_indicator_title);
        //TODO :Set indicator imageIcon
        textView.setText(getString(tabHost.getmTitleTextId()));
        return view;
    }
}
