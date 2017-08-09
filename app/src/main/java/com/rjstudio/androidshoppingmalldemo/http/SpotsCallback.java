package com.rjstudio.androidshoppingmalldemo.http;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import dmax.dialog.SpotsDialog;

/**
 * Created by r0man on 2017/7/29.
 */

public abstract class SpotsCallback<T> extends BaseCallback<T> {
    private Context mContext;
    private SpotsDialog spotsDialog;

    public SpotsCallback(Context context) {
        this.mContext = context;
        initSpotsDialog();
    }
    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }


    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();
    }

    private void initSpotsDialog() {
        //loading
        spotsDialog = new SpotsDialog(mContext,"玩命加载中...");
    }

    public void showDialog()
    {
        spotsDialog.show();
    }

    public void dismissDialog()
    {
        spotsDialog.dismiss();
//        spotsDialog.hide();
        //TODO: 跟hide()方法有什么区别?
    }

    public void setDialogMessage(int stringId)
    {
        spotsDialog.setMessage(mContext.getText(stringId));
    }
    public abstract void onError(Response response,int code,Exception e);
}
