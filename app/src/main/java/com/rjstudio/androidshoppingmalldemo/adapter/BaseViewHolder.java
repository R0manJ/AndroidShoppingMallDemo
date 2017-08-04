package com.rjstudio.androidshoppingmalldemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rjstudio.androidshoppingmalldemo.widget.CnButton;

/**
 * Created by r0man on 2017/8/1.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BaseAdapter.OnItemClickListener onItemClickListener;
    private SparseArray<View> views;

    public BaseViewHolder(View itemView)
    {
        super(itemView);
        this.views = new SparseArray<View>();
    }
    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener onItemClickListener)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        this.onItemClickListener = onItemClickListener;
        this.views = new SparseArray<>();
    }

    //Return view
    protected <T extends View> T findView(int id)
    {
        View view = views.get(id);
        if (view == null)
        {
            view = itemView.findViewById(id);
            views.put(id,view);
            return (T) view;
        }
        return (T)view;
    }

    public TextView findTextView(int id)
    {
        return findView(id);
    }

    public SimpleDraweeView findSimpleDraweeView(int id)
    {
        return findView(id);
    }
    public Button findButton(int id)
    {
        return findView(id);
    }

    public CheckBox findCheckBox (int id)
    {
        return (CheckBox) findView(id);
    }

    public CnButton findCnButton(int id)
    {
        return findView(id);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
        {
            onItemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}
