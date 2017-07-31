package com.rjstudio.androidshoppingmalldemo.utils;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjstudio.androidshoppingmalldemo.bean.ShoppingCart;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r0man on 2017/7/31.
 */

public class CartProvider {

    private SparseArray<ShoppingCart>datas = null;
    private final Context mContext;

    private static final String CART_JSON = "cart_json";
    private List<ShoppingCart> carts;
    //Hash Map


    public CartProvider(Context context) {
        this.datas = new SparseArray<ShoppingCart>();

        this.mContext = context;
        //从本地内容加载到内存中
        listToSpares();
    }

    public void put(ShoppingCart cart )
    {
        ShoppingCart temp = datas.get((int)cart.getId());
        if (temp != null)
        {
            temp.setCount(cart.getCount()+1);
        }
        else
        {
            temp = cart;
        }
        datas.put((int) cart.getId(),temp);
        commit();
    }

    public void update(ShoppingCart cart)
    {
        datas.put((int) cart.getId(),cart);
        commit();
    }

    public void delete(ShoppingCart cart)
    {
        datas.delete((int) cart.getId());
        commit();

    }

    public List<ShoppingCart> getAll()
    {
        //去本地读取数据
        return  getDataFromLocal();
    }

    public void listToSpares()
    {
        List<ShoppingCart> carts = getDataFromLocal();
        if (carts != null && carts.size() > 0)
        {
            for (ShoppingCart cart : carts)
            {
                datas.put((int) cart.getId(),cart);
            }
        }
    }
    public void commit()
    {
        //用于保存数据到本地
        List<ShoppingCart> carts = sparesToList();
        //TODO : 这里不懂...
        //获取数据 -> list -> JSON ->保存到本地
        Gson gson = new Gson();
        String toJsonContent = null;
        for (ShoppingCart shoppingcart : carts)
        {
            toJsonContent += gson.toJson(shoppingcart);
        }
        Log.d("COMMIT", "JSON: "+toJsonContent);
        PreferencesUtils.putString(mContext,CART_JSON,toJsonContent);

    }

    private List<ShoppingCart> sparesToList()
    {
        int size = datas.size();
        List<ShoppingCart> list = new ArrayList<>(size);
        for (int i = 0 ; i < size ; i ++)
        {
            list.add(datas.valueAt(i));
        }
        return list;
    }

    public List<ShoppingCart> getDataFromLocal()
    {
        String json = PreferencesUtils.getString(mContext,CART_JSON);
        List<ShoppingCart> carts = null;
        if (json != null)
        {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ShoppingCart>>(){}.getType();
            carts = gson.fromJson(json,type);
            return carts;
        }
        return carts;
    }
}
