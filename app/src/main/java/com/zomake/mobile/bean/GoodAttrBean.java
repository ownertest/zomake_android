package com.zomake.mobile.bean;

import java.util.List;

/**
 * Created by wojiushiwn on 2017/3/16.
 * desc:
 */

public class GoodAttrBean {

    private int itemType;

    private String attr;

    private String attrValue;

    private List<AttrDetail> typeDetailList;

    public GoodAttrBean() {
    }

    public GoodAttrBean(int itemType) {
        this.itemType = itemType;
        if (itemType == 1) {
            attrValue = "数量";
        }
    }


    public GoodAttrBean(int itemType, String attr, String attrValue, List<AttrDetail> typeDetailList) {
        this.itemType = itemType;
        this.attr = attr;
        this.attrValue = attrValue;
        this.typeDetailList = typeDetailList;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public List<AttrDetail> getTypeDetailList() {
        return typeDetailList;
    }

    public void setTypeDetailList(List<AttrDetail> typeDetailList) {
        this.typeDetailList = typeDetailList;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public static class AttrDetail {
        String value;
        boolean isEnable;

        public AttrDetail(String value) {
            this.value = value;
            isEnable = true;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isEnable() {
            return isEnable;
        }

        public void setEnable(boolean enable) {
            isEnable = enable;
        }
    }
}
