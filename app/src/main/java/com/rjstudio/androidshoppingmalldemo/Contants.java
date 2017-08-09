package com.rjstudio.androidshoppingmalldemo;

/**
 * Created by r0man on 2017/7/30.
 */

public class Contants {

    public static final String  COMPAINGAIN_ID="compaigin_id";

    public static class API{
        public static final String BASE_URL = "http://112.124.22.238:8081/course_api";
//http://112.124.22.238:8081/course_api/campaign/recommend
        public static final String CAMPAIGN_HOME = BASE_URL+"/campaign/recommend";
        public static final String WARES_HOT = BASE_URL+"/wares/hot";
//        http://112.124.22.238:8081/course_api/banner/query?type=1
        public static final String BANNER = BASE_URL+"/banner/query";
        public static final String CATEGORY_LIST = BASE_URL + "/category/list";

        //http://112.124.22.238:8081/course_api/wares/list?curPage=1&pageSize=10&categoryId=5
        public static final String CATEGORY_WARE = BASE_URL +"/wares/list";
        public static final String WARES_CAMPAIN_LIST=BASE_URL +"/wares/campaign/list";

    }
}
