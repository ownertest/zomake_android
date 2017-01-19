package com.zomake.mobile.bean;

import java.util.List;

/**
 * Created by Ryan on 17/1/19.
 */

public class AdvertImageBean {
    /**
     * list : [{"image":"img/201701161748272267.jpg","url":""},{"image":"img/201701161748279765.jpg","url":""},{"image":"img/201701161748272041.jpg","url":""},{"image":"img/201701161748289871.jpg","url":""}]
     * cid : 57d5564f3916c325f8e9924a
     */

    public String cid;
    public List<ListBean> list;


    public static class ListBean {
        /**
         * image : img/201701161748272267.jpg
         * url :
         */

        public String image;
        public String url;


    }
}
