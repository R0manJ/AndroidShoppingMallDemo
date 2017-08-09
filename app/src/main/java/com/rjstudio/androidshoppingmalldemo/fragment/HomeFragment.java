package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.HomeCampaignAdapter;
import com.rjstudio.androidshoppingmalldemo.bean.Banner;
import com.rjstudio.androidshoppingmalldemo.bean.Campaign;
import com.rjstudio.androidshoppingmalldemo.bean.HomeCampaign;
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
    private OKHttpHelper okHttpHelper;
    private List<HomeCampaign> mComaigns;
    private RecyclerView recyclerView;

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
            recyclerView = (RecyclerView) homeView.findViewById(R.id.rv_showMainItem);
            initImageSlider();
            setSliderLayoutData();
            requestCompaginData();
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
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Fade);
            sliderLayout.setDuration(2000);
        }
    }

    private void setComaignData(List<HomeCampaign> homeCampaigns)
    {


        HomeCampaignAdapter homeCampaignAdapter = new HomeCampaignAdapter(getContext(),homeCampaigns);
        recyclerView.addItemDecoration(homeCampaignAdapter.getDividerItemDecoration());
        recyclerView.setAdapter(homeCampaignAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeCampaignAdapter.setOnItemClickListener(new HomeCampaignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Campaign campaign) {
                Toast.makeText(getContext(), campaign.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setSliderLayoutData()
    {
        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
        okHttpHelper = OKHttpHelper.getInstance();

        okHttpHelper.get(url,new SpotsCallback<List<Banner>>(getContext()){


            @Override
            public void onFailure(Request request, Exception e) {
                //super.onFailure(request, e);
            }

            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                mBanner = banners;
                setImageData(mBanner);
            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }

        });
    }

    private void requestCompaginData()
    {
        okHttpHelper.get(Contants.API.CAMPAIGN_HOME, new BaseCallback<List<HomeCampaign>>() {
            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {
//                Log.d("Init fail", "onFailure: ");
            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                mComaigns = homeCampaigns;
                for (HomeCampaign homeCampaign:mComaigns)
                {
//                    Log.d("HomeCampaign", "onSuccess: "+homeCampaign.getTitle());
                }
                setComaignData(mComaigns);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
//                Log.d("Init fail", "onError: ");

            }

            @Override
            public void onResponse(Response response) {

            }
        });
    }


}
