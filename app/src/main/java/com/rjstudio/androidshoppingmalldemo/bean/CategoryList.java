package com.rjstudio.androidshoppingmalldemo.bean;

import java.io.Serializable;

/**
 * Created by r0man on 2017/7/31.
 */

public class CategoryList implements Serializable{
    private int id;
    private String name;
    private int sort ;

    public CategoryList(int id, String name, int sort) {
        this.id = id;
        this.name = name;
        this.sort = sort;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
