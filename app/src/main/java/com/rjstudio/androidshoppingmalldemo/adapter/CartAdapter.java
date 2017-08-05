package com.rjstudio.androidshoppingmalldemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.bean.ShoppingCart;
import com.rjstudio.androidshoppingmalldemo.utils.CartProvider;
import com.rjstudio.androidshoppingmalldemo.widget.CnButton;

import java.util.Iterator;
import java.util.List;

/**
 * Created by r0man on 2017/8/1.
 */

public class CartAdapter extends BaseAdapter<ShoppingCart,BaseViewHolder> {

    private List<ShoppingCart> mList;
    private Context mContext;
    private CartProvider cartProvider;
    private TextView tv_totalPrice;
    private CheckBox cb_all;
    private float totalPrice = 0;


    public CartAdapter(Context context, List<ShoppingCart> datas, final CheckBox checkBox , TextView textView)
    {
        super(context,R.layout.cart_item_layout,datas);
        this.cb_all = checkBox;
        this.tv_totalPrice = textView;
        this.mList = datas;
        this.mContext = context;
        this.cartProvider = new CartProvider(mContext);
        showTotalPrice();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Not_AllItem(checkBox.isChecked());
            }
        });
    }

    public CartAdapter( Context context, int LayoutId,List<ShoppingCart> datas) {
        super(context,LayoutId,datas);
        this.mList = datas;
        this.mContext = context;
    }


    @Override
    void convert(BaseViewHolder holder, final ShoppingCart cart) {
        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(cart.getImgUrl());
        holder.findTextView(R.id.tv_wareName).setText(cart.getName());
        holder.findTextView(R.id.tv_warePrice).setText(cart.getPrice() + " $ ");

        holder.findCheckBox(R.id.cb_select).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedListenCheckBox(cart,isChecked);
                listenCheckBoxStatus();
            }
        });

        //TODO : 结算按钮没有实现

        holder.findCnButton(R.id.cnb_button).setValue(cart.getCount());
        holder.findCnButton(R.id.cnb_button).setOnButtonClickListener(new CnButton.OnButtonClickListener() {
            @Override
            public void sub(View view, int value) {
                cart.setCount(value);
                cartProvider.update(cart);
                showTotalPrice();
            }

            @Override
            public void add(View view, int value) {
                cart.setCount(value);
                cartProvider.update(cart);
                showTotalPrice();
            }
        });

        CheckBox checkBox = holder.findCheckBox(R.id.cb_select);
        checkBox.setChecked(cart.isChecked());



    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    //显示总价格,方案是在Adapter里面传进一个checkbox和总价textview
    //convert -> CnButton -> 监听数量控件的监听 ->CartProvider.updata
    private void showTotalPrice()
    {
        float itemTotalPrice = 0;
        float totalPrice = 0;
        //循环遍历cartProvider里面的数据->将数量和单价相乘->设置Textview的数据
        //总价TotalPrice要设置初值
        for (ShoppingCart shoppingCart : mList)
        {
            if (shoppingCart.isChecked())
            {
                itemTotalPrice = shoppingCart.getPrice() * shoppingCart.getCount();
            }
            totalPrice += itemTotalPrice;
        }
        tv_totalPrice.setText(totalPrice+"");

    }

    //设置复选框监听 -> 获取CheckBox -> 状态变化 ->写入cartProvider数据 -> 更新总价格
    public void checkedListenCheckBox(ShoppingCart cart,boolean isCheck)
    {
        cart.setChecked(isCheck);
        cartProvider.update(cart);
        showTotalPrice();
    }

    //更改全选按钮的状态
    //拿到cartProvider的长度 -> 遍历循环是否每个子项的选中状态 -> 如果相同的话,则设置全选
    private void listenCheckBoxStatus()
    {
        List<ShoppingCart> itemLists = cartProvider.getAll();
        int itemAmounts = itemLists.size();
        int count = 0;
        for (ShoppingCart cart : itemLists)
        {
            if (cart.isChecked()) count ++;
        }
        cb_all.setChecked(count == itemAmounts);
    }

    //设置全选按钮
    private void check_Not_AllItem(boolean isChecked)
    {
        int i = 0;
        for (ShoppingCart cart : mList)
        {
            cart.setChecked(isChecked);
//            cartProvider.update(cart);
            //设置一个notifyChange就可以让条目发生改变
            notifyItemChanged(i);
            i ++;
        }
        showTotalPrice();

    }

    //编辑按钮 -> 把所有的条目的checkbox都设置为空 -> 选中 -> 删除List -> 删除cartProvider
    public void editStatus()
    {
        for (ShoppingCart cart : mList)
        {
            cart.setChecked(false);
        }
        cb_all.setChecked(false);
        notifyItemRangeChanged(0, mList.size());
    }

    public void delect()
    {
//      这里会报错,迭代器有问题
//        for (ShoppingCart cart : mList)
//        {
//            if (cart.isChecked())
//            {
//                int position = mList.indexOf(cart);
//                mList.remove(cart);
//                cartProvider.delete(cart);
//                notifyItemRemoved(position);
//            }
//        }

//        TODO : 整理笔记  -> 迭代器的知识点 -> Iterator的使用
        for (Iterator<ShoppingCart> iterator = mList.iterator() ; iterator.hasNext();)
        {
            ShoppingCart cart = iterator.next();
            if (cart.isChecked())
            {
                int position = mList.indexOf(cart);
                iterator.remove();
                cartProvider.delete(cart);
                notifyItemRemoved(position);
            }
        }
        showTotalPrice();
    }



}
