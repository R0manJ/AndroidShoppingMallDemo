package com.rjstudio.androidshoppingmalldemo.bean;

import java.io.Serializable;

/**
 * Created by r0man on 2017/7/30.
 */

public class Campaign implements Serializable{
    //TODO : 为什么要继承Serializable
    private long id;
    private String title;
    private String imgUrl;

    public Campaign(long id, String title, String imgUrl) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
