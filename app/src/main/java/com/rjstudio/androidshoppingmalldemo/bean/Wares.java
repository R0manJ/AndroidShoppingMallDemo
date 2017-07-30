package com.rjstudio.androidshoppingmalldemo.bean;

/**
 * Created by r0man on 2017/7/30.
 */

public class Wares
{
    private long id;
    private int categoryId;
    private String name;
    private String imgUrl;
    private Float price;
    private long sale;

    public Wares(long id, int categoryId, String name, String imgUrl, Float price, long sale) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.sale = sale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }
}
