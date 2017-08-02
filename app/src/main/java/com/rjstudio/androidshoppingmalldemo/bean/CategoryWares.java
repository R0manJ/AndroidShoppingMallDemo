package com.rjstudio.androidshoppingmalldemo.bean;

import java.util.List;

/**
 * Created by r0man on 2017/7/31.
 * DATA from : Contants.API.CATEGORY_WARE
 * JSON DATA : copyright":"本API接口只允许菜鸟窝(http://www.cniao5.com)用户使用,其他机构或者个人使用均为侵权行为",
                "totalCount":23,"currentPage":1,"totalPage":0,"pageSize":10,"orders":[],"list":
 */

public class CategoryWares<T> {

    private String copytright;
    private long totalCount;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List orders;
    private List<T> list;

    public CategoryWares(String copytright, long totalCount, int currentPage, int totalPage, int pageSize, List orders, List<T> list) {
        this.copytright = copytright;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.orders = orders;
        this.list = list;
    }

    public String getCopytright() {
        return copytright;
    }

    public void setCopytright(String copytright) {
        this.copytright = copytright;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
