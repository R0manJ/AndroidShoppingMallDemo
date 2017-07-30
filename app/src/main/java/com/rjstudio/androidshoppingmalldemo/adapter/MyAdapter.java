package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.HomeCampaign;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

/**
 * Created by r0man on 2017/7/27.
 */

/*
* 要实现封装Adapter
* 1.数据使用泛型
* 2.数据绑定通过抽象方法实现
* 3.ViewHolder中的View成员变量转而通过view数组来实现
* 4.基类里面提供常用的方法
* */
public class MyAdapter extends RecyclerView.Adapter {

    private int LEFT_VALUE = 1;
    private int RIGHT_VALUE = 0;
    //Data
    private List<HomeCampaign> mList;

    //Context
    private Context mContext;
    private MyItemViewHolder myViewHolder;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter(Context context,List<HomeCampaign> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyItemViewHolder myHolder = (MyItemViewHolder)holder;
        //Why ? The args must be 'RecyclerView.ViewHolder'? Not be 'MyItemViewHolder';
        myHolder.tv_title.setText(mList.get(position).getTitle());

        //设置图片
        Picasso.with(mContext).load(mList.get(position).getCpOne().getImgUrl()).into(myHolder.iv_product);
        Picasso.with(mContext).load(mList.get(position).getCpTwo().getImgUrl()).into(myHolder.iv_product2);
        Picasso.with(mContext).load(mList.get(position).getCpThree().getImgUrl()).into(myHolder.iv_product3);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Question: The layout_width is not match parent.
        //Answer:View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_item, parent,false);//解决宽度不能铺满

        if (viewType == LEFT_VALUE)
        {
            myViewHolder = new MyItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.right_card_layout,parent,false));
        }
        else if (viewType == RIGHT_VALUE)
        {
            myViewHolder = new MyItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.left_card_layout,parent,false));
        }
        //TODO : What the mean of the LayoutInflater second constructor?
        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
        {
            return LEFT_VALUE;
        }
        else
        {
            return RIGHT_VALUE;
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view,int position,String value);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView iv_product;
        private ImageView iv_product2;
        private ImageView iv_product3;
        private TextView tv_title;

        private View itemView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            // Up of itemView
            this.itemView = itemView;
            initView();
        }

        private void initView() {
            tv_title = (TextView)itemView.findViewById(R.id.tv_card_title);
            iv_product = (ImageView) itemView.findViewById(R.id.iv_first_product_image);
            iv_product2 = (ImageView) itemView.findViewById(R.id.iv_second_product_image);
            iv_product3 = (ImageView)itemView.findViewById(R.id.iv_third_product_image);


            iv_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                    {
                       // mOnItemClickListener.onItemClick(v,getLayoutPosition(),testList.get(getLayoutPosition())+"image");
                    }
                }
            });

            iv_product2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                    {
                        //mOnItemClickListener.onItemClick(v,getLayoutPosition(),testList.get(getLayoutPosition())+"image2");
                    }
                }
            });

            iv_product3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null)
                    {
                       // mOnItemClickListener.onItemClick(v,getLayoutPosition(),testList.get(getLayoutPosition())+"image3");
                    }
                }
            });

        }

    }
    public DividerItemDecoration getDividerItemDecoration()
    {
        return new DividerItemDecoration(mContext,LinearLayoutManager.HORIZONTAL);
    }

    public class DividerItemDecoration extends RecyclerView.ItemDecoration
    {
        private int[] ATTRS = new int[]{android.R.attr.listDivider};
        private int mOrientation;
        private int VERTICAL_LIST = LinearLayoutManager.HORIZONTAL;
        private Drawable mDivider;

        public DividerItemDecoration(Context context,int orientation)
        {
            TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();

        }
        @Override
        public void onDraw(Canvas c, RecyclerView parent) {
             if (mOrientation == VERTICAL_LIST)
             {
                 drawHorizontalDecoration(c,parent);
             }
        }

        private void drawHorizontalDecoration(Canvas c , RecyclerView parent) {
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() -parent.getPaddingBottom();
            int childCount = parent.getChildCount();

            for (int i = 0; i< childCount;i++)
            {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left,top,right,bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }



        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),20);
        }
    }
}
