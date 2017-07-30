package com.rjstudio.androidshoppingmalldemo.bean;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

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


public abstract class BaseAdapter<T ,H extends BaseViewHolder > extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private final int mLayoutId;

    public BaseAdapter(List<T> datas , Context context,int LayoutId) {
        this.mList = datas;
        this.mContext = context;
        this.mLayoutId = LayoutId;
        mLayoutInflater = LayoutInflater.from(context);

    }

    //绑定数据交给子类来完成


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = mList.get(position);
        bindData(holder ,t);
    }

    public abstract void bindData(BaseViewHolder holder,T t);
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = new BaseViewHolder(mLayoutInflater.inflate(mLayoutId,null));
        return baseViewHolder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 常用方法封装
     * 这里包括数据的加载与刷新
     * */
    public void refreshData(List<T> list)
    {
        mList.clear();
        mList.addAll(list);
        notifyItemRangeChanged(0,mList.size());
    }

    public int addData(List<T> list)
    {
        int recordLastPosition = mList.size();
        mList.addAll(list);
        notifyItemRangeChanged(recordLastPosition,mList.size());
        return recordLastPosition;
    }


}
