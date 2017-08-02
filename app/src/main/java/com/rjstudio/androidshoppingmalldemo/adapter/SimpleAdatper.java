package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by r0man on 2017/8/1.
 */

public abstract class SimpleAdatper<T> extends BaseAdapter<T, BaseViewHolder> {

    private Context mContext;
    private final int mLayoutId;
    private final List<T> mList;

    public SimpleAdatper(Context context, int layoutId, List<T> datas)
    {
        super(context,layoutId,datas);
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mList = datas;
    }






}
