package com.rjstudio.androidshoppingmalldemo.bean;

/**
 * Created by r0man on 2017/7/27.
 */

public class HomeDataBean {
    private String image1;
    private String image2;
    private String image3;

    private String title1;
    private String title2;
    private String title3;

    private String subtitle1;
    private String subtitle2;
    private String subtitle3;

    public HomeDataBean(String image1, String image2, String image3, String title1, String title2, String title3, String subtitle1, String subtitle2, String subtitle3) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.subtitle3 = subtitle3;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public String getSubtitle3() {
        return subtitle3;
    }

    public void setSubtitle3(String subtitle3) {
        this.subtitle3 = subtitle3;
    }
}
