package com.rjstudio.androidshoppingmalldemo.http;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import dmax.dialog.SpotsDialog;

/**
 * Created by r0man on 2017/7/29.
 */

public class SpotsCallback<T> extends BaseCallback<T> {
    private Context mContext;
    private SpotsDialog spotsDialog;

    @Override
    public void onRequestBefore(Request request) {
        showDialog();
    }


    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();
    }

    @Override
    public void onSuccess(Response response, T t) {
        dismissDialog();
    }

    @Override
    public void onError(Response response, int code, Exception e) {
        dismissDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

    public SpotsCallback(Context context) {
        super();
        this.mContext = context;
        initSpotsDialog();
    }

    private void initSpotsDialog() {
        //loading
        spotsDialog = new SpotsDialog(mContext,"玩命加载中...");
    }

    private void showDialog()
    {
        spotsDialog.show();
    }

    private void dismissDialog()
    {
        spotsDialog.dismiss();
        spotsDialog.hide();
        //TODO: 跟hide()方法有什么区别?
    }

    public void setDialogMessage(int stringId)
    {
        spotsDialog.setMessage(mContext.getText(stringId));
    }
}
