package com.rjstudio.androidshoppingmalldemo.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by r0man on 2017/7/29.
 */

public class OKHttpHelper {
    //TODO : 为什么是单例模式?
    private static OkHttpClient okHttpClient;
    private Gson gson;

    private Handler handler;

    private OKHttpHelper()
    {
        okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(10,TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(10,TimeUnit.SECONDS);
        gson = new Gson();
        //TODO: Looper.getMainLooper是什么意思?
        handler = new Handler(Looper.getMainLooper());

    };

    public static OKHttpHelper getInstance()
    {
        return new OKHttpHelper();
    }

    //GET
    public void get(String url,BaseCallback callback)
    {
        Request request = buildRequest(url,null,HttpMethodType.GET);
        doRequest(request,callback);
    }

    public void post(String url, Map<String,String> params,BaseCallback baseCallback)
    {
        Request request = buildRequest(url,params,HttpMethodType.POST);
        doRequest(request,baseCallback);
    }

    //Request Method
    public void doRequest(Request request, final BaseCallback callback)
    {
        callback.onRequestBefore(request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(request,e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
//                response.body().string();
//                gson.fromJson(response.body().string(),callback.t);
                //toString 是把流转成字符串
                //String是把对象转成字符串
                callback.onResponse(response);
                if (response.isSuccessful())
                {
//                    callback.onSuccess(response,null);

                    String ressultStr = response.body().string();
                    if (callback.mType == String.class)
                    {
//                        callback.onSuccess(response,ressultStr);
                        callbackSuccess(callback,response,ressultStr);
                    }
                    else
                    {
//                        防止解析错误
                        try {
                            Object object = gson.fromJson(ressultStr,callback.mType);
//                            callback.onSuccess(response,object);
                            callbackSuccess(callback,response,object);
                        }
                        catch (JsonParseException e)
                        {
                            callbackError(callback,response,e);
                        }

                    }
                }
                else
                {
                    callback.onError(response,response.code(),null);
                }

            }
        });
    }

    private Request buildRequest(String url,Map<String,String> params,HttpMethodType methodType)
    {
        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (methodType == HttpMethodType.GET)
        {
            builder.get();
        }
        else if (methodType == HttpMethodType.POST)
        {
            RequestBody body = buildFormatData(params);
            builder.post(body);
        }
        return builder.build();
    }

    private RequestBody buildFormatData(Map<String,String> params)
    {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (params != null)
        {
            for (Map.Entry<String,String> entry : params.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        return builder.build();
    }

    private void callbackSuccess(final BaseCallback baseCallback, final Response response, final Object object)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallback.onSuccess(response,object);
            }
        });
    }
    private void callbackError(final BaseCallback baseCallback, final Response response, final Exception e)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallback.onError(response,response.code(),e);
            }
        });
    }
    enum  HttpMethodType{
        GET,
        POST
    }
}
