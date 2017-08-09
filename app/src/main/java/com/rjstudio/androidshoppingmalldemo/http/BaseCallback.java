package com.rjstudio.androidshoppingmalldemo.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by r0man on 2017/7/29.
 */

public abstract class BaseCallback<T> {
    public Type mType;
    public abstract void onRequestBefore(Request request);
    public abstract void onFailure(Request request, Exception e);
    public abstract void onSuccess(Response response,T t);
    public abstract void onError(Response response , int code,Exception e);
    public abstract void onResponse(Response response);

    //TODO : 把泛型转成任意的类型?
    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");

        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }
    public BaseCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }


}
