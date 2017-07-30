package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.Page;
import com.rjstudio.androidshoppingmalldemo.bean.Wares;

import java.util.List;

/**
 * Created by r0man on 2017/7/30.
 */

public class HotWaresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Wares> waresList;



//    public HotWaresAdapter(Context context, List<Page > list) {
//        this.mContext = context;
//        this.mList = list;
//    }

    public HotWaresAdapter(Context mContext,List<Wares> list) {
        this.mContext = mContext;
        this.waresList = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotWaresViewHolder myHolder = (HotWaresViewHolder)holder;
        //List<Wares> waresList = mList.get(position).getList();

        String img_url = waresList.get(position).getImgUrl();
        String ware_name = waresList.get(position).getName();
        float price = waresList.get(position).getPrice();

        myHolder.sv_productImage.setImageURI(img_url);
        myHolder.tv_productTitle.setText(ware_name);
        myHolder.tv_productSubtitle.setText(price+"$");
    }

    @Override
    public int getItemCount() {
        return waresList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wares_layout,null);
        HotWaresViewHolder viewHolder = new HotWaresViewHolder(view);
        return viewHolder;
    }

    public void addData(List<Wares> datas)
    {
        waresList.addAll(datas);
        notifyItemRangeChanged(0,waresList.size());
    }
    public void clearData()
    {
        waresList.clear();
        notifyItemRangeChanged(0,waresList.size());
    }

    public void addData(int position,List<Wares> datas)
    {
        if (datas != null && datas.size() > 0)
        {
            waresList.addAll(datas);
            notifyItemRangeChanged(position,waresList.size());
        }
    }

    public int getLastPosition()
    {
        return waresList.size();
    }
    class HotWaresViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView sv_productImage;
        private TextView tv_productTitle;
        private TextView tv_productSubtitle;
        private Button bu_addToCart;

        public HotWaresViewHolder(View itemView) {
            super(itemView);

            sv_productImage = (SimpleDraweeView) itemView.findViewById(R.id.sv_productImage);
            tv_productTitle = (TextView) itemView.findViewById(R.id.tv_wareTitle);
            tv_productSubtitle = (TextView) itemView.findViewById(R.id.tv_wareSubtitle);
            bu_addToCart = (Button) itemView.findViewById(R.id.bu_wareBuy);

        }

    }
}
