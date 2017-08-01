package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryWares;
import com.rjstudio.androidshoppingmalldemo.bean.ShoppingCart;
import com.rjstudio.androidshoppingmalldemo.bean.Ware;
import com.rjstudio.androidshoppingmalldemo.utils.CartProvider;

import java.util.List;

/**
 * Created by r0man on 2017/7/30.
 * 这个适配器用于第二个界面--热门商品展示
 */

public class HotWaresAdapter extends SimpleAdatper<Ware>  {

    private Context mContext;
    private List<Ware> mList;
    private CartProvider cartProvider;

    private CartProvider mCartProvider;
    private OnItemClickListener onItemClickListener;

//    public HotWaresAdapter(Context context, List<Page > list) {
//        this.mContext = context;
//        this.mList = list;
//    }


    public HotWaresAdapter(Context context, List<Ware> datas) {
        super(context,R.layout.hot_item_layout, null);
        this.mList = datas;
        this.cartProvider = new CartProvider(context);
    }

    @Override
    void convert(BaseViewHolder holder, Ware ware) {
        //null
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(mList.get(position).getImgUrl());
        holder.findTextView(R.id.tv_wareName).setText(mList.get(position).getName());
        holder.findTextView(R.id.tv_warePrice).setText(mList.get(position).getPrice()+ " $");
        //TODO : ViewHolder中的点击事件中传递的抽象方法意义在哪里?
        holder.findButton(R.id.bu_Buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartProvider.put(convertData(mList.get(position)));
                Log.d("Buy","Add success.");
            }
        });
    }




    @Override
    public int getItemCount() {
        return mList.size();
    }



    public ShoppingCart convertData(Ware item){

        ShoppingCart cart = new ShoppingCart();

        cart.setId(item.getId());
     //   cart.setDescription(item.getDescription());
        cart.setImgUrl(item.getImgUrl());
        cart.setName(item.getName());
        cart.setPrice(item.getPrice());

        return cart;
    }
}
