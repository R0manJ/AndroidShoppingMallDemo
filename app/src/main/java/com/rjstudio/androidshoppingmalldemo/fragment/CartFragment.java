package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.CartAdapter;

/**
 * Created by r0man on 2017/7/26.
 */

/**
 * 1.数据存储在本地(SharePrefrence)
 * 2.提供put,update,delete,getAll()方法
 * 3.put数据时,如购物车存在相同产品,则需要将数量加1即可
 */

public class CartFragment extends Fragment {

    private CheckBox cb_selectAll;
    private RecyclerView rv_showCartWare;
    private TextView tv_totalPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_layout,null);
        initView(view);
        return view;

    }

    private void initView(View view)
    {
        rv_showCartWare = (RecyclerView) view.findViewById(R.id.rv_showCategoryWares);

        cb_selectAll = (CheckBox)view.findViewById(R.id.cb_all);
        tv_totalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        Button bu_buy = (Button) view.findViewById(R.id.bu_wareBuy);

       // CartAdapter cartAdapter = new CartAdapter()
    }

}
