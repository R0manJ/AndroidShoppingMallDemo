package com.rjstudio.androidshoppingmalldemo.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by r0man on 2017/8/10.
 */

public class CountTimerView extends CountDownTimer {
    public static final int TIME_COUNT = 61000;
    private TextView btn;
    private String endStrRid;

    public CountTimerView(long millisInFutre,long countDownInterval,TextView btn ,String endStrRid)
    {
        super(millisInFutre,countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;

    }

    public CountTimerView(TextView btn)
    {
        super(TIME_COUNT,1000);
        this.btn = btn;
        this.endStrRid = "重新获取验证码";
    }
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);
        btn.setText(millisUntilFinished/1000 +"秒后可重新发送");

    }

    @Override
    public void onFinish() {
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }
}
