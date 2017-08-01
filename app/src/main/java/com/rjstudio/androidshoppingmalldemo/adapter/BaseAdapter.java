package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by r0man on 2017/8/1.
 */

public abstract class BaseAdapter<T,H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<T> mList;
    private final int mLayoutId;
    private final Context mContext;
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public BaseAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mLayoutId = layoutId;
        mList = datas;
        onItemClickListener = null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T itemClass = mList.get(position);
        convert(holder , itemClass);
    }

    //用于转化数据,参数传入一个ViewHolder,再传入一个具体类
    abstract void convert(BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO : 检查这个方法有什么作用?
        View view = LayoutInflater.from(mContext).inflate(mLayoutId,null);
        BaseViewHolder viewHolder = new BaseViewHolder(view,onItemClickListener);
        return viewHolder;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public int addData(List<T> addDatas)
    {
        mList.addAll(addDatas);
        //TODO : 这里可以优化
        notifyItemRangeChanged(0,mList.size());
        return mList.size();
    }

    public void refreshData(List<T> targetDatas)
    {
        if (targetDatas.size() > 0 && targetDatas.size() > mList.size())
        {
            mList.addAll(targetDatas);
            notifyItemRangeChanged(0,mList.size());
        }
        else if (targetDatas.size() > 0 && targetDatas.size() > mList.size())
        {
            clearData();
            mList.addAll(targetDatas);
            notifyItemRangeChanged(0,mList.size());
        }
        else
        {
            clearData();
        }
    }
    public void clearData()
    {
        int recordPreviousDataSize = mList.size();
        mList.clear();
        notifyItemRangeChanged(0,recordPreviousDataSize);
    }
}
