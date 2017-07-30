package com.rjstudio.androidshoppingmalldemo.bean;

import java.io.Serializable;

/**
 * Created by r0man on 2017/7/30.
 */

public class HomeCampaign implements Serializable{
    private long id;
    private String title;
    private Campaign cpOne;
    private Campaign cpTwo;
    private Campaign cpThree;

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

    public Campaign getCpOne() {
        return cpOne;
    }

    public void setCpOne(Campaign cpOne) {
        this.cpOne = cpOne;
    }

    public Campaign getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(Campaign cpTwo) {
        this.cpTwo = cpTwo;
    }

    public Campaign getCpThree() {
        return cpThree;
    }

    public void setCpThree(Campaign cpThree) {
        this.cpThree = cpThree;
    }

    public HomeCampaign(long id, String title, Campaign cpOne, Campaign cpTwo, Campaign cpThree) {

        this.id = id;
        this.title = title;
        this.cpOne = cpOne;
        this.cpTwo = cpTwo;
        this.cpThree = cpThree;
    }
}
