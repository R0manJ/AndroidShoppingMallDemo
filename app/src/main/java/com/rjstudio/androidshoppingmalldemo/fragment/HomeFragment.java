package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.MyAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.Banner;
import com.rjstudio.androidshoppingmalldemo.http.BaseCallback;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.rjstudio.androidshoppingmalldemo.http.SpotsCallback;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by r0man on 2017/7/26.
 */

public class HomeFragment extends Fragment {

    private LayoutInflater layoutInflater;
    private View homeView;
    private Gson gson;
    private List<Banner> mBanner;
    private SliderLayout sliderLayout;
    private TextSliderView textSliderView;

    private int RRESH_UI = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == RRESH_UI)
            {
                setImageData(mBanner);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = LayoutInflater.from(getContext());
        homeView = layoutInflater.inflate(R.layout.home_layout,null);
        initView();
//        requestImage();
        return homeView;
    }

    private void initView()
    {
        if (homeView != null)
        {
            RecyclerView recyclerView = (RecyclerView) homeView.findViewById(R.id.rv_showMainItem);
            MyAdapter myAdapter = new MyAdapter(getContext());
            recyclerView.addItemDecoration(myAdapter.getDividerItemDecoration());
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, String value) {
                    Toast.makeText(getContext(), "Clicking "+value, Toast.LENGTH_SHORT).show();
                }
            });
            initImageSlider();
            setSliderLayoutData();
        }

    }

    private void initImageSlider()
    {
        sliderLayout = (SliderLayout) homeView.findViewById(R.id.sl_image_advertisement);

    }

    private void setImageData(List<Banner> mBanner)
    {
        if (mBanner != null)
        {
            for (Banner banner : mBanner)
            {
                textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getimgUrl());
                textSliderView.description(banner.getName());
                sliderLayout.addSlider(textSliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipPage);
        }
    }

    private void setSliderLayoutData()
    {
        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
        OKHttpHelper okHttpHelper = OKHttpHelper.getInstance();

        okHttpHelper.get(url,new SpotsCallback<List<Banner>>(getContext()){


            @Override
            public void onFailure(Request request, Exception e) {
                super.onFailure(request, e);
            }

            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                mBanner = banners;
                setImageData(mBanner);
            }


            @Override
            public void onError(Response response, int code, Exception e) {
                super.onError(response, code, e);
            }


        });

    }


    /*

    public void requestImage()
    {
//        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        try {
//            //Response response = client.newCall(request).execute();
//            //TODO : 这个方法是同步的方法,不能放在主线程里面
////            String JSON_DATA = response.body().string();
////            Log.d("JSON_DATA", "requestImage: "+JSON_DATA);
//
//
//            //enqueue是一个异步的方法
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Request request, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Response response) throws IOException {
//                    String JSON_DATA = response.body().string();
//                    Log.d("JSON_DATA", "requestImage: "+JSON_DATA);
//
//                }
//            });
//
//        }
//        catch (Exception e)
//        {
//            String TAG = "Okhttp";
//            Log.d(TAG, "Response failed...");
//        }
        //POST方法比Get方法多一个表单请求
        String url_post = "http://112.124.22.238:8081/course_api/banner/query";
        //?表示传递的参数
        RequestBody body = new FormEncodingBuilder().add("type","1").build();
        Request request_post = new Request.Builder()
                .url(url_post)
                .post(body)
                .build();
        client.newCall(request_post).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String JSON_DATA = response.body().string();
                Log.d("JSON_DATA", "requestImage: "+JSON_DATA);
                gson = new Gson();
                Type type = new TypeToken<List<Banner>>(){}.getType();
                //TypeToken has protected access in "..."
                mBanner = gson.fromJson(JSON_DATA,type);
//                setImageData(mBanner);
                //Only the original thread that created a view hierarchy can touch its views.
                handler.sendEmptyMessage(2);
            }
        });
//        setImageData(mBanner);
    }

     */

}
