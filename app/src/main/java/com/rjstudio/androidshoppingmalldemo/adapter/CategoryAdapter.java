package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.view.View;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryList;

import java.util.List;

/**
 * Created by r0man on 2017/7/31.
 * 这个适配器用于显示商品页面的菜单列表
 */

public class CategoryAdapter extends SimpleAdatper<CategoryList> {
    private List<CategoryList> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
       abstract void onClick(View v, int categoryId,CategoryList categoryList);
    }

    public CategoryAdapter(List<CategoryList> datas, Context context) {
        super(context, R.layout.category_simple_textview,datas);
        this.mList = datas;

    }


    @Override
    void convert(final BaseViewHolder holder, CategoryList categoryList) {
        holder.findTextView(R.id.tv_Item).setText(categoryList.getName());
        holder.findTextView(R.id.tv_Item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                {
                    mListener.onClick(v,mList.get(holder.getLayoutPosition()).getId(),mList.get(holder.getLayoutPosition()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return  mList.size();
    }

    public void setOnClickItemListener(OnItemClickListener onItemClickListener)
    {
        this.mListener = onItemClickListener;
    }


}
