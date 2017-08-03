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

import static android.content.ContentValues.TAG;

/**
 * Created by r0man on 2017/7/31.
 */

public class CartProvider {

    private SparseArray<ShoppingCart>datas = null;
    private final Context mContext;

    private static final String CART_JSON = "cart_json";
    //Hash Map


    public CartProvider(Context context) {

        this.mContext = context;
        //从本地内容加载到内存中
        this.datas = new SparseArray<>(10);
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
            //设置购买的数量
            temp.setCount(1);
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

    public int readDataSize()
    {
        return 0;
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

    public List<ShoppingCart> sparseToList()
    {
        int size = datas.size();
        List<ShoppingCart> list = new ArrayList<>(size);
        for (int i = 0 ; i < size ; i ++ )
        {
            list.add(datas.valueAt(i));
        }
        return list;
    }
    public void commit()
    {

        List<ShoppingCart> carts = sparseToList();
//        Gson gson = new Gson();
//        String toJsonContent = "";
//        for (ShoppingCart shoppingcart : carts)
//        {
//            toJsonContent += gson.toJson(shoppingcart);
//        }
////        Log.d("COMMIT", "JSON: "+toJsonContent);
//        PreferencesUtils.putString(mContext,CART_JSON,toJsonContent);
        PreferencesUtils.putString(mContext,CART_JSON,JSONUtil.toJSON(carts));



    }


    public List<ShoppingCart> getDataFromLocal()
    {
        String json = PreferencesUtils.getString(mContext,CART_JSON);
        Log.d(TAG, "getDataFromLocal: "+ json);



        List<ShoppingCart> carts = null;
        if (json != null)
        {
            carts = JSONUtil.fromJson(json,new TypeToken<List<ShoppingCart>>(){}.getType());
        }
//        Log.d("List",carts.size() + "");
        return carts;
    }
}
