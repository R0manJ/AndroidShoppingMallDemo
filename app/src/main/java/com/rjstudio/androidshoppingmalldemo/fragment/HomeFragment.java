package com.rjstudio.androidshoppingmalldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.adapter.MyAdapter;

/**
 * Created by r0man on 2017/7/26.
 */

public class HomeFragment extends Fragment {

    private LayoutInflater layoutInflater;
    private View homeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = LayoutInflater.from(getContext());
        homeView = layoutInflater.inflate(R.layout.home_layout,null);
        initView();
        return homeView;
    }

    private void initView()
    {
        if (homeView != null)
        {
            initImageSlider();
            RecyclerView recyclerView = (RecyclerView) homeView.findViewById(R.id.rv_showMainItem);
            recyclerView.setAdapter(new MyAdapter(getContext()));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }

    }

    private void initImageSlider()
    {
        SliderLayout sliderLayout = (SliderLayout) homeView.findViewById(R.id.sl_image_advertisement);
        TextSliderView textSliderView = new TextSliderView(getContext());
        textSliderView.image(R.mipmap.ic_launcher);
        textSliderView.description("1");
        sliderLayout.addSlider(textSliderView);
        textSliderView = new TextSliderView(getContext());
        textSliderView.image(R.mipmap.ic_launcher);
        textSliderView.description("2");
        sliderLayout.addSlider(textSliderView);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipPage);
    }
}
