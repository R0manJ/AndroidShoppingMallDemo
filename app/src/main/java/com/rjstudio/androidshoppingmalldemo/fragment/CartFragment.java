package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjstudio.androidshoppingmalldemo.R;

/**
 * Created by r0man on 2017/7/26.
 */

/**
 * 1.数据存储在本地(SharePrefrence)
 * 2.提供put,update,delete,getAll()方法
 * 3.put数据时,如购物车存在相同产品,则需要将数量加1即可
 */
/

public class CartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_layout,null);
        initView(view);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void initView(View view)
    {

    }

}
