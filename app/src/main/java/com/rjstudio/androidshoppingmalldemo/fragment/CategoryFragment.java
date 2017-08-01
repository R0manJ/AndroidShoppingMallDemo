package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.FixedLayoutManager;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.BaseAdapter;
import com.rjstudio.androidshoppingmalldemo.adapter.BaseViewHolder;
import com.rjstudio.androidshoppingmalldemo.adapter.CategoryAdapter;
import com.rjstudio.androidshoppingmalldemo.adapter.CategoryWareAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.Banner;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryList;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryWare;
import com.rjstudio.androidshoppingmalldemo.bean.CategoryWares;
import com.rjstudio.androidshoppingmalldemo.http.BaseCallback;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * Created by r0man on 2017/7/26.
 */

public class CategoryFragment extends Fragment {

    private View view;

    private OKHttpHelper okHttpHelper = OKHttpHelper.getInstance();
    private RecyclerView rv_categoryItem;
    private SliderLayout sl_categoryAdv;
    private int curPage =0;
    private int pageSize = 10;
    private int categoryId = 1;
    private RecyclerView rv_categoryWare;
    private BaseAdapter<CategoryWare,BaseViewHolder> baseAdapter;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_LOAD_MORE = 1;
    private static final int STATE_REFRESH = 2;
    private int STATE = STATE_NORMAL;
    private List<CategoryWare> mList;
    private int totalPages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.category_layout,null);
        //view = inflater.inflate(R.layout.category_layout,null);
        initRefreshLayout();
        requestListData();
        requestAdvData();
        requestCategoryWares();
        return view;
    }

    private void initRefreshLayout()
    {
        if (view != null)
        {
            MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.rl_category);
            materialRefreshLayout.setLoadMore(true);
            materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {

                @Override
                public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                    super.onRefreshLoadMore(materialRefreshLayout);
                    STATE = STATE_LOAD_MORE;
                    loadMorePage();
                    materialRefreshLayout.finishRefreshLoadMore();
                }

                @Override
                public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                    STATE = STATE_REFRESH;
                    refreshPageToNextPage();
                    materialRefreshLayout.finishRefresh();


                }
            });

            rv_categoryItem = (RecyclerView) view.findViewById(R.id.rv_primaryCategoryItem);

            sl_categoryAdv = (SliderLayout) view.findViewById(R.id.sl_categoryImageAdv);

            rv_categoryWare = (RecyclerView) view.findViewById(R.id.rv_showCategoryWares);
        }
    }

    private void loadMorePage() {
        curPage ++;
        if (curPage <= totalPages)
        {
            requestCategoryWares();
        }
        else
        {
            Toast.makeText(getContext(), "已经没有了哦 >,<", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshPageToNextPage() {
        curPage ++;
        if (curPage <= totalPages)
        {
            requestCategoryWares();
        }
        else
        {
            Toast.makeText(getContext(), "已经没有了哦 >,<", Toast.LENGTH_SHORT).show();

        }
    }


    private void requestListData()
    {
        okHttpHelper.get(Contants.API.CATEGORY_LIST, new BaseCallback<List<CategoryList>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, List<CategoryList> categoryLists) {
                showListData(categoryLists);

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }
        });
    }
    private void showListData(List<CategoryList> data)
    {
        CategoryAdapter categoryAdapter = new CategoryAdapter(data,getContext());
        categoryAdapter.setOnClickItemListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int categoryId, CategoryList categoryList) {
                Log.d("Test",categoryId+"---"+categoryList.getName());
                setCategoryId(categoryId);
                requestCategoryWares();
                //setCategoryId(categoryId);

            }
        });
        rv_categoryItem.setAdapter(categoryAdapter);
        rv_categoryItem.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void requestAdvData()
    {
        okHttpHelper.get(Contants.API.BANNER + "?type=1", new BaseCallback<List<Banner>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                showAdvData(banners);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }
        });
    }
    private void showAdvData(List<Banner> datas)
    {
        DefaultSliderView defaultSliderView = null;
        for (Banner b : datas)
        {
            defaultSliderView = new DefaultSliderView(getContext());
            Log.d("xxx", "showAdvData: "+b.getName());
            defaultSliderView.image(b.getimgUrl());
            sl_categoryAdv.addSlider(defaultSliderView);
        }
        sl_categoryAdv.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sl_categoryAdv.setDuration(3000);


    }

    public void requestCategoryWares()
    {
        String url = Contants.API.CATEGORY_WARE;
        //这里需要三个参数,分别是curPage.pageSize.categoryId
        url = url +"?curPage="+curPage+"&pageSize="+ pageSize +"&categoryId="+ categoryId;

        Log.d("request", "requestCategoryWares: "+url);
        okHttpHelper.get(url, new BaseCallback<CategoryWares<CategoryWare>>() {

            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, CategoryWares<CategoryWare> categoryWareCategoryWares) {
                mList = categoryWareCategoryWares.getList();
                totalPages = (int) categoryWareCategoryWares.getTotalCount();
                Log.d("On", "totalPages: "+totalPages+"---"+"curPage"+curPage+"dataList:"+mList.size());
                if (mList != null && mList.size() > 0)
                {
                    showCategoryWare(mList);

                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }
        });
    }

    private void showCategoryWare(List<CategoryWare> categoryWares) {
        switch (STATE)
        {
            case STATE_NORMAL:
                baseAdapter = new BaseAdapter<CategoryWare,BaseViewHolder>(categoryWares,getContext(), R.layout.category_ware_item_layout) {
                    @Override
                    public void bindData(BaseViewHolder holder, CategoryWare categoryWare) {

                        holder.findSimpleDraweeView(R.id.sv_productImage).setImageURI(categoryWare.getImgUrl());
                        holder.findTextView(R.id.tv_wareTitle).setText(categoryWare.getName());
                        holder.findTextView(R.id.tv_warePrice).setText(categoryWare.getPrice()+" $");
                    }
                };
                //CategoryWareAdapter categoryWareAdapter = new CategoryWareAdapter(getContext(),c)
                rv_categoryWare.setAdapter(baseAdapter);
                //设置每行显示两个项目
                rv_categoryWare.setLayoutManager(new FixedLayoutManager(getContext(),2));
                break;

            case STATE_LOAD_MORE:

                    // java.lang.IndexOutOfBoundsException: Inconsistency detected.
                    // Invalid view holder adapter positionViewHolder{8d2c306 position=3 id=-1, oldPos=-1, pLpos:-1 no parent}
                    //解决方案 是重写一个LayoutManager
                    baseAdapter.addData(categoryWares);

                    Toast.makeText(getContext(), "已经没有了哦 >,<", Toast.LENGTH_SHORT).show();


                break;
            case STATE_REFRESH:

                    baseAdapter.refreshData(categoryWares);

                break;
        }


    }

    public void setCategoryId(int id)
    {
        this.categoryId = id;
        this.curPage = 1;
        baseAdapter.clearAllData();
    }
}
