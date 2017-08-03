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

    private OnItemClickListener onItemClickListener;

//    public HotWaresAdapter(Context context, List<Page > list) {
//        this.mContext = context;
//        this.mList = list;
//    }


    public HotWaresAdapter(Context context, List<Ware> datas) {
        //List<Ware>
        super(context,R.layout.hot_item_layout, datas);
        this.mList = datas;
        this.cartProvider = new CartProvider(context);
    }

    @Override
    void convert(BaseViewHolder holder, final Ware ware) {

        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(ware.getImgUrl());
        holder.findTextView(R.id.tv_wareName).setText(ware.getName());
        holder.findTextView(R.id.tv_warePrice).setText(ware.getPrice() + " $ ");
        holder.findButton(R.id.bu_Buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartProvider.put(convertData(ware));
//                Log.d("Button",ware.getName());
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
