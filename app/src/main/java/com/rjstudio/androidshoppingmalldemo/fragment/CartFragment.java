package com.rjstudio.androidshoppingmalldemo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.rjstudio.androidshoppingmalldemo.MainActivity;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.CartAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.ShoppingCart;
import com.rjstudio.androidshoppingmalldemo.utils.CartProvider;
import com.rjstudio.androidshoppingmalldemo.widget.MyToolBar;

import java.util.List;


/**
 * Created by r0man on 2017/7/26.
 */

/**
 * 1.数据存储在本地(SharePrefrence)
 * 2.提供put,update,delete,getAll()方法
 * 3.put数据时,如购物车存在相同产品,则需要将数量加1即可
 */

public class CartFragment extends Fragment implements View.OnClickListener {

    private CheckBox cb_selectAll;
    private RecyclerView rv_showCartWare;
    private TextView tv_totalPrice;
    private CartProvider cartProvider;
    private Button bu_buy;
    private CartAdapter cartAdapter;
    private int STATUS_EDIT = 1;
    private int STATUS_NORMAL = 2;
    private int STATUS = STATUS_NORMAL;
    private MyToolBar cartToolBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_layout,null);

        cartProvider = new CartProvider(getContext());
        initView(view);
        return view;

    }

    private void initView(View view)
    {
        rv_showCartWare = (RecyclerView) view.findViewById(R.id.rv_showCartWares);
        cb_selectAll = (CheckBox)view.findViewById(R.id.cb_all);
        tv_totalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        bu_buy = (Button) view.findViewById(R.id.bu_Buy);
        bu_buy.setOnClickListener(this);
        showData();

    }

    private void showData()
    {
        List<ShoppingCart> carts = cartProvider.getAll();
        //getAll() === null;

//        Log.d(TAG, "showData: "+ cartProvider.getAll().size());
        cartAdapter = new CartAdapter(getContext(),carts,cb_selectAll,tv_totalPrice);



        rv_showCartWare.setAdapter(cartAdapter);
        rv_showCartWare.setLayoutManager(new LinearLayoutManager(getContext()));
            //缺少分割线


//        cb_selectAll.setChecked(true);

    }

    // 界面切换时,数据更新
    public void refreshData()
    {
        cartAdapter.clearData();
        List<ShoppingCart> carts = cartProvider.getAll();
        cartAdapter.addData(carts);
    }


    //TODO : 修改工具栏的样式
    //拿到MainActivity的内容
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity)
        {
            MainActivity activity = (MainActivity) context;
            cartToolBar = (MyToolBar) activity.findViewById(R.id.toolbar);
            cartToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerEditButton();
                    Toast.makeText(getContext(), "XxX", Toast.LENGTH_SHORT).show();
                }
            });

            //TODO : 工具栏的修改
           // mToolbar.hidewSearchView();
            //mToolbar.setTitle();
            //mToolbar.getRightButton().setText();
        }
    }

    //编辑购物车功能
    //点击编辑按钮 -> 按钮显示为"完成" -> 设置界面界面状态值 -> 购买按钮变为删除
    private void listenerEditButton()
    {
        if (STATUS == STATUS_NORMAL)
        {
            STATUS = STATUS_EDIT;
            bu_buy.setText(getString(R.string.delect));
            cartToolBar.setRightButtonIcon(getResources().getDrawable(R.mipmap.ic_launcher));
            cartAdapter.editStatus();

        }
        else
        {
            STATUS = STATUS_NORMAL;
            bu_buy.setText(getString(R.string.buy));
            cartToolBar.setRightButtonIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bu_Buy:
                if (STATUS == STATUS_EDIT)
                {
                    cartAdapter.delect();
//                    cartAdapter.delect();
                }
                else
                {
                    //TODO : 购买的逻辑
                }
                break;
        }
    }
}
