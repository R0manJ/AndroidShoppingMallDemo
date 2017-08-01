package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.Ware;

import java.util.List;

/**
 * Created by r0man on 2017/8/1.
 */

public class CategoryWareAdapter extends BaseAdapter<Ware,BaseViewHolder> {
    public CategoryWareAdapter(Context context, List<Ware> datas) {
        super(context, R.layout.cart_item_layout, datas);
    }

    @Override
    void convert(BaseViewHolder holder, Ware ware) {
        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(ware.getImgUrl());
        holder.findTextView(R.id.tv_wareName).setText(ware.getName());
        holder.findTextView(R.id.tv_warePrice).setText(ware.getPrice() + " $");
    }
}
