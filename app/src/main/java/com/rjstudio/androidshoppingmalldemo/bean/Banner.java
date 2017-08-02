package com.rjstudio.androidshoppingmalldemo.bean;

import com.rjstudio.androidshoppingmalldemo.Contants;

/**
 * Created by r0man on 2017/7/29.
 * Data from Contants.API.Banner
 * JSON_DATA: {"id":1,"name":"音箱狂欢","imgUrl":"http://7mno4h.com2.z0.glb.qiniucdn.com/5608f3b5Nc8d90151.jpg","type":1}
 */

public class Banner {

    private int id;
    private String name;
    private String imgUrl;
    private int type;

    public Banner(int id, String name, String imgUrl, int type) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getimgUrl() {
        return imgUrl;
    }

    public void setimgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
