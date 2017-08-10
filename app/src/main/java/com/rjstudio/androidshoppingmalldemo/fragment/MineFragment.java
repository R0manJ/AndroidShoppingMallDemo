package com.rjstudio.androidshoppingmalldemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.activity.RegisterActivity;

/**
 * Created by r0man on 2017/7/26.
 */

public class MineFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mine_layout,null);
        initView(view);
        return view;
    }

    private void initView(View rootView) {
        Button button = (Button) rootView.findViewById(R.id.bu_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
