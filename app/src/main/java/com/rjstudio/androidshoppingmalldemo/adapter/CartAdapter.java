package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.ShoppingCart;

import java.util.List;

/**
 * Created by r0man on 2017/8/1.
 */

public class CartAdapter extends BaseAdapter<ShoppingCart,BaseViewHolder> {

    private List<ShoppingCart> mList;
    private Context mContext;



    public CartAdapter( Context context,List<ShoppingCart> datas)
    {
        super(context,R.layout.cart_layout,datas);
        this.mList = datas;
        this.mContext = context;
    }

    public CartAdapter( Context context, int LayoutId,List<ShoppingCart> datas) {
        super(context,LayoutId,datas);
        this.mList = datas;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        //ShoppingCart extends wares


    }


    @Override
    void convert(BaseViewHolder holder, ShoppingCart cart) {
        holder.findView(R.id.cb_select);
        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(cart.getImgUrl());
        holder.findTextView(R.id.tv_card_title).setText(cart.getName());
        holder.findTextView(R.id.tv_warePrice).setText(cart.getPrice()+ " $");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }


}