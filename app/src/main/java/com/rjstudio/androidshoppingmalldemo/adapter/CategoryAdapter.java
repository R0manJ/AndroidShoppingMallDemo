package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.BaseAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.BaseViewHolder;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryList;

import java.util.List;

/**
 * Created by r0man on 2017/7/31.
 */

public class CategoryAdapter extends BaseAdapter<CategoryList,BaseViewHolder>  {
    private List<CategoryList> mList;
    private OnItemClickListener mListener;
    private int position;

    public interface OnItemClickListener {
       abstract void onClick(View v, int categoryId,CategoryList categoryList);
    }

    public CategoryAdapter(List<CategoryList> datas, Context context) {
        super(datas, context, R.layout.category_simple_textview);
        this.mList = datas;

    }



    @Override
    public void bindData(final BaseViewHolder holder, CategoryList categoryList) {
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
