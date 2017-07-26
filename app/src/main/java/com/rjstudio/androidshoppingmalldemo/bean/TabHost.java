package com.rjstudio.androidshoppingmalldemo.bean;


/**
 * Created by r0man on 2017/7/26.
 */

public class TabHost {
    private Class mfragment;
    private int mDrawableId;
    private int mTitleTextId;

    public TabHost(Class mfragment, int mTitleTextId) {
        this(mfragment,-1,mTitleTextId);
    }

    public TabHost(Class mfragment, int mDrawableId, int mTitleTextId) {
        this.mfragment = mfragment;
        this.mDrawableId = mDrawableId;
        this.mTitleTextId = mTitleTextId;
    }


    public Class getMfragment() {
        return mfragment;
    }

    public void setMfragment(Class mfragment) {
        this.mfragment = mfragment;
    }

    public int getmDrawableId() {
        return mDrawableId;
    }

    public void setmDrawableId(int mDrawableId) {
        this.mDrawableId = mDrawableId;
    }

    public int getmTitleTextId() {
        return mTitleTextId;
    }

    public void setmTitleTextId(int mTitleTextId) {
        this.mTitleTextId = mTitleTextId;
    }
}
