package com.rjstudio.androidshoppingmalldemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;
import com.rjstudio.androidshoppingmalldemo.Contants;
import com.rjstudio.androidshoppingmalldemo.R;
import com.rjstudio.androidshoppingmalldemo.http.OKHttpHelper;
import com.rjstudio.androidshoppingmalldemo.utils.CountTimerView;
import com.rjstudio.androidshoppingmalldemo.widget.MyToolBar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    private EditText et_id;
    private EditText et_pwd;
    private EditText et_phone;
    private String phone;
    private String pwd;
    private String code;
    private Button bu_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        context = this;

       initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phoneNumber);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_id = (EditText) findViewById(R.id.et_confirmID);
        bu_register = (Button) findViewById(R.id.bu_ok);
        MyToolBar myToolbar = (MyToolBar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Register");
        myToolbar.showRightButton();
        myToolbar.setRightButtonIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
        Button bu_confirm = (Button) findViewById(R.id.bu_confirm);
        bu_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCode();
            }
        });
        bu_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSMSSKD();
                reSendCode();
            }
        });
        myToolbar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SMS","Test");

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
        SMSEvenHanlder evenHanlder = new SMSEvenHanlder();
        SMSSDK.registerEventHandler(evenHanlder);
    }

    //自定义界面跳转验证
    private void afterVerificationCodeRequested(boolean smart)
    {
        phone = et_phone.getText().toString().trim();
        Log.d("SMS","phone"+ phone);
        pwd = et_pwd.getText().toString().trim();
        code = et_id.getText().toString().trim();

        if (code.startsWith("+"))
        {
            code = code.substring(1);
        }

    }
    //实现按钮中的倒计时
    //写一个定时器 -> CountDownTimer

    //Overrid a eventHandler class;
    class SMSEvenHanlder extends EventHandler{
        @Override
        public void afterEvent(final int event, final int result, Object o) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (result == SMSSDK.RESULT_COMPLETE)
                    {
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
                        {
                            Toast.makeText(context, "收到验证码，注册成功"+code, Toast.LENGTH_SHORT).show();
                            doReg();
                        }
                        else{
                            Log.d("SMS","ERROR");
                        }
                    }
                }
            });

        }
    }


    private void doReg()
    {
        Map<String,String> params = new HashMap<>(2);
        params.put("phone",phone);
        params.put("password",pwd);
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext())
        {
            Log.d("SMS",(String)iterator.next());
        }
//        okHttpHelper.post(Contants.API);

    }

    //完成注册，提交到mob验证
    private void finishReg()
    {
        String code = et_id.getText().toString().trim();
        if (code.equals(""))
        {
            Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        //countryCode
        SMSSDK.submitVerificationCode("86",phone,code);
    }

    //点击注册按钮的逻辑
    public void reSendCode()
    {
        phone = et_phone.getText().toString().trim();
        Log.d("SMS","phone"+ phone);
        pwd = et_pwd.getText().toString().trim();
        Log.d("SMS", "reSendCode: "+phone);
        SMSSDK.getVerificationCode("+86",phone);
        CountTimerView countTimerView = new CountTimerView(bu_register);
        countTimerView.start();

    }

    public void submitCode()
    {

        code = et_id.getText().toString().trim();
        //String codes = et_id.getText().toString().trim();
        //Toast.makeText(context, "Test"+codes, Toast.LENGTH_SHORT).show();
       // Log.d("SMS", "onClick: code"+codes);
        SMSSDK.submitVerificationCode("86","13128852949",code);
    }
}
