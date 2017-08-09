package com.rjstudio.androidshoppingmalldemo.utils;

/**
 * Created by r0man on 2017/8/5.
 * 分页工具类
 *
 * 1.抽取公共的方法
 * 2.变化的东西有调入调出
 * 3.提供简洁的API
 */


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.rjstudio.androidshoppingmalldemo.bean.Page;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.rjstudio.androidshoppingmalldemo.http.SpotsCallback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页请求需要的变量:
 * 1. URL
 * 2. 页面下标
 * 3. 页面显示条目
 *
 * PageUtil -> Builder -> [url - prams - RefreshLayout - PageListener] -> RequestHttpData
 */

public class PageUtil {
    private static Builder builder;
    private final OKHttpHelper httpHelper;
    //刷新菜单的参数
    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;

    //PageUtil -> newBuilder -> setParams -> new Pageutil -> OkHttp

    public static Builder newBuilder()
    {

        builder = new Builder();
        return builder;
    }

    private PageUtil(){
        httpHelper = OKHttpHelper.getInstance();
        //TODO : 初始化刷新页面
        initRefreshLayout();
    }

    public void request()
    {
        requestData();
    }

    //传入post请求的相关参数
    public void putParam(String key , Object value)
    {
        builder.params.put(key,value);
    }

    //初始化刷新布局
    private void initRefreshLayout()
    {
        builder.mRefreshLayout.setLoadMore(builder.isLoadMore);
        builder.mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                builder.mRefreshLayout.setLoadMore(builder.isLoadMore);
                refresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (builder.pageIndex < builder.totalPage)
                {
                    loadMore();
                }
                else
                {
                    Toast.makeText(builder.mContext, "无更多数据", Toast.LENGTH_LONG).show();
                    materialRefreshLayout.finishRefreshLoadMore();
                    materialRefreshLayout.setLoadMore(false);
                }
            }
        });
    }
//请求数据

    private void requestData()
    {
        String url = buildUrl();
        Log.d("The url = ",url);
        httpHelper.get(url,new RequestCallBack(builder.mContext));
    }
    /**
     * 显示数据
     */
    private <T> void showData(List<T> datas,int totalPage,int totalCount){



        if(datas ==null|| datas.size()<=0){
            Toast.makeText(builder.mContext,"加载不到数据",Toast.LENGTH_LONG).show();
            return;
        }

        if(STATE_NORMAL==state){

            if(builder.onPageListener !=null){
                builder.onPageListener.load(datas,totalPage,totalCount);
            }
        }

        else  if(STATE_REFREH==state)   {
            builder.mRefreshLayout.finishRefresh();
            if(builder.onPageListener !=null){
                builder.onPageListener.refresh(datas,totalPage,totalCount);
            }

        }
        else  if(STATE_MORE == state){

            builder.mRefreshLayout.finishRefreshLoadMore();
            if(builder.onPageListener !=null){
                builder.onPageListener.loadMore(datas,totalPage,totalCount);
            }

        }
    }
    /**
     * 刷新数据
     */
    private void refresh(){

        state=STATE_REFREH;
        builder.pageIndex =1;
        requestData();
    }

    private void loadMore(){

        state=STATE_MORE;
        builder.pageIndex =++builder.pageIndex;
        requestData();
    }

    /**
     * 构建URL
     * @return
     */
    private String buildUrl(){

        return builder.url +"?"+buildUrlParams();
    }

    private   String buildUrlParams() {


        HashMap<String, Object> map = builder.params;

        map.put("curPage",builder.pageIndex);
        map.put("pageSize",builder.pageSize);

        StringBuffer sb = new StringBuffer();
        //TODO : 如何遍历HashMap数组?

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0,s.length()-1);
        }
        return s;
    }

    public static class Builder{
        private String url;
        private HashMap<String,Object> params = new HashMap<String,Object>(5);
        private boolean isLoadMore;
        private MaterialRefreshLayout mRefreshLayout;
        private Type mType;
        private Context mContext;

        private int totalPage = 1 ;
        private int pageSize = 10;
        private int pageIndex = 1;

        private OnpageListener onPageListener;

        public Builder setIsLoadMore(boolean isLoadMore)
        {
            this.isLoadMore = isLoadMore;
            return builder;
        }

        public Builder setOnPageListener(OnpageListener onPageListener)
        {
            this.onPageListener = onPageListener;
            return builder;
        }

        public Builder  setRreshLayout(MaterialRefreshLayout materialRefreshLayout)
        {
            this.mRefreshLayout = materialRefreshLayout;
            return builder;
        }
        public Builder putParams(String key,Object value)
        {
            params.put(key,value);
            return builder;
        }

        public Builder setUrl(String url)
        {
            this.url = url;
            return builder;
        }
        public PageUtil build(Context context,Type type)
        {
            mType = type;
            mContext = context;
            valid();
            return new PageUtil();
        }

        //该方法用于检测是否都为有效值
        private void valid()
        {
            if (this.mContext == null)
            {
                throw new RuntimeException("Content can't be null");
            }
            if (this.url == null || "".equals(this.url))
            {
                throw new RuntimeException("Url can't be null");
            }
            if (this.mRefreshLayout == null)
            {
                throw new RuntimeException("MateriaRefreshLayout can't be null");
            }
            Log.d("The url = ",url);
        }
    }

    //这个类用于写网络请求的回掉类
    //因为返回的是一个Page类,所以泛型用Page代替
    //Page类有以下参数
//    privat String copyright;
//    private int totalCount;
//    private int currentPage;
//    private int totalPage;
//    private int pageSize;
//    private List<T> orders;
//    private List<T> list;
    class RequestCallBack<T> extends SpotsCallback<Page<T>>
    {
        //这个类是有加载条的
        public RequestCallBack(Context context) {
            super(context);
            super.mType = builder.mType;
        }

        @Override
        public void onFailure(Request request, Exception e) {
            super.onFailure(request, e);
            dismissDialog();
            if(STATE_REFREH == state)   {
                builder.mRefreshLayout.finishRefresh();
            }
            else  if(STATE_MORE == state){

                builder.mRefreshLayout.finishRefreshLoadMore();
            }

        }

        @Override
        public void onSuccess(Response response, Page<T> page) {

            builder.pageIndex = page.getCurrentPage();
            builder.pageSize = page.getPageSize();
            builder.totalPage = page.getTotalPage();
            Log.d("Success",page.getList().size()+"---"+page.getOrders().size());
            showData(page.getList(),page.getTotalPage(),page.getTotalCount());
        }

        @Override
        public void onError(Response response, int code, Exception e) {
            Toast.makeText(builder.mContext,"加载数据失败",Toast.LENGTH_LONG).show();

            if(STATE_REFREH==state)   {
                builder.mRefreshLayout.finishRefresh();
            }
            else  if(STATE_MORE == state){

                builder.mRefreshLayout.finishRefreshLoadMore();
            }
        }

    }

    //监听接口
    public interface OnpageListener<T>{
        void load(List<T> datas,int totalPage,int totalCount);
        void refresh(List<T> datas, int totalPage,int totalCount);
        void loadMore(List<T> datas, int totalPage,int totalCount);
    }



}
