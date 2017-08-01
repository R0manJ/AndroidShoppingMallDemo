package com.rjstudio.androidshoppingmalldemo.bean;

import java.io.Serializable;

/**
 * Created by r0man on 2017/7/31.
 */

public class ShoppingCart extends Ware implements Serializable {

    private int count;
    private boolean isChecked = true;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
