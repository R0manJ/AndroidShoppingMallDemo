package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

/**
 * Created by r0man on 2017/7/27.
 */

public class MyAdapter extends RecyclerView.Adapter {

    //Data
    private List<String> testList;
    private String[] datas = {"1","2","3","4"};

    //Context
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;
        testList = Arrays.asList(datas);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder myHolder = (MyItemViewHolder)holder;
        //Why ? The args must be 'RecyclerView.ViewHolder'? Not be 'MyItemViewHolder';
        myHolder.title.setText(testList.get(position));
        myHolder.title2.setText(testList.get(position));
        myHolder.title3.setText(testList.get(position));

    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyItemViewHolder myViewHolder = new MyItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.right_card_layout,null));
        return myViewHolder;
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView iv_product;
        private ImageView iv_product2;
        private ImageView iv_product3;

        private TextView cardTitle;
        private TextView title;
        private TextView title2;
        private TextView title3;

        private TextView subtitle;
        private TextView subtitle2;
        private TextView subtitle3;

        private View itemView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            // Up of itemView
            this.itemView = itemView;
            initView();
        }

        private void initView() {
            iv_product = (ImageView) itemView.findViewById(R.id.iv_first_product_image);
            iv_product2 = (ImageView) itemView.findViewById(R.id.iv_second_product_image);
            iv_product2 = (ImageView)itemView.findViewById(R.id.iv_third_product_image);

            cardTitle = (TextView) itemView.findViewById(R.id.tv_card_title);
            title = (TextView) itemView.findViewById(R.id.tv_first_product_title);
            title2 = (TextView) itemView.findViewById(R.id.tv_second_product_title);
            title3  = (TextView) itemView.findViewById(R.id.tv_third_product_title);

            subtitle = (TextView) itemView.findViewById(R.id.tv_first_product_subtitle);
            subtitle2 = (TextView) itemView.findViewById(R.id.tv_second_product_subtitle);
            subtitle3 = (TextView) itemView.findViewById(R.id.tv_third_product_subtitle);

        }

    }
}
