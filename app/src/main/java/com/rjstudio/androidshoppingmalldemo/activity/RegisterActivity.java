package com.rjstudio.androidshoppingmalldemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mob.MobSDK;
import com.rjstudio.androidshoppingmalldemo.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by r0man on 2017/8/10.
 */

public class RegisterActivity extends AppCompatActivity {

    private EventHandler eventHandler;
    private SMSSDK smssdk;
    private Context context;
    private boolean ready;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        context = this;

       initView();
    }

    private void initView() {
        Button bu_register = (Button) findViewById(R.id.bu_ok);
        bu_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSMSSKD();
                registerPageInit();
             //   registerSDK();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        smssdk.unregisterEventHandler(eventHandler);
    }

    public void registerSDK()
    {
        SMSSDK.setAskPermisionOnReadContact(true);
        final Handler handler = new Handler();
        final EventHandler eventHandler = new EventHandler(){
            public void afterEvent(int event,int result,Object data)
            {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };

        SMSSDK.registerEventHandler(eventHandler);
        ready = true;


    }

    public void registerPageInit()
    {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler()
        {
            public void afterEvent(int event,int result , Object data)
            {
                if (result == SMSSDK.RESULT_COMPLETE)
                {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String,Object>)data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                }
            }
        });
        registerPage.show(this);
    }

    public void initSMSSKD()
    {
        MobSDK.init(this,"2016c87ba0dc9","d988be57252c8d85459cbf376f4674b8");
    }
}
