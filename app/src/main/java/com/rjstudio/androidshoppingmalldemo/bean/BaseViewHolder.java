package com.rjstudio.androidshoppingmalldemo.bean;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by r0man on 2017/7/30.
 */

/*
* 要实现封装Adapter
* 1.数据使用泛型
* 2.数据绑定通过抽象方法实现
* 3.ViewHolder中的View成员变量转而通过view数组来实现
* 4.基类里面提供常用的方法
* */


public class BaseViewHolder extends RecyclerView.ViewHolder {

//    protected LayoutInflater mLayoutInflater;
//    protected Context mContext;
//    protected int LayoutId;
    protected View view;
    private View mItemView;

    private SparseArray<View> sparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        sparseArray = new SparseArray<View>();
        this.mItemView = itemView;
//        this.mContext = context;
//        this.mLayoutInflater = LayoutInflater.from(context);

    }

    protected  <T extends View> T findView(int id)
    {
        view = sparseArray.get(id);
        if (view == null)
        {
            view = mItemView.findViewById(id);
            sparseArray.put(id,view);

        }
        return (T)view;
    }

    /*
    * 查找控件
    * */
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
}
