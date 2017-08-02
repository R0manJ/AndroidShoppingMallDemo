package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryWare;
import com.rjstudio.androidshoppingmalldemo.bean.Ware;

import java.util.List;

/**
 * Created by r0man on 2017/8/1.
 * 这个适配器用于显示商品分类界面的商品信息.
 *
 */

public class CategoryWareAdapter extends SimpleAdatper<CategoryWare> {
    public CategoryWareAdapter(Context context, List<CategoryWare> datas) {
        super(context, R.layout.category_ware_item_layout, datas);
    }

    @Override
    void convert(BaseViewHolder holder, CategoryWare categoryWare) {
        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(categoryWare.getImgUrl());
        holder.findTextView(R.id.tv_wareName).setText(categoryWare.getName());
        holder.findTextView(R.id.tv_warePrice).setText(categoryWare.getPrice() + " $");
    }
}
