package com.zomake.mobile.bean;

import java.util.List;

/**
 * Created by wojiushiwn on 2017/3/24.
 * desc:
 */

public class CatalogFilterBean {

    private int index;

    private String sectionTitle;

    private List<ValueBean> valueBeanList;

    public CatalogFilterBean(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public CatalogFilterBean(String sectionTitle, List<ValueBean> values) {
        this.sectionTitle = sectionTitle;
        this.valueBeanList = values;
    }

    public CatalogFilterBean(int index, String sectionTitle, List<ValueBean> valueBeanList) {
        this.index = index;
        this.sectionTitle = sectionTitle;
        this.valueBeanList = valueBeanList;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public List<ValueBean> getValues() {
        return valueBeanList;
    }

    public void setValueBeanList(List<ValueBean> valueBeanList) {
        this.valueBeanList = valueBeanList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static class ValueBean {
        private String value;
        private String parameter;

        public ValueBean() {
        }

        public ValueBean(String value, String parameter) {
            this.value = value;
            this.parameter = parameter;
        }

        public String getValue() {
            return value;
        }

        public String getParameter() {
            return parameter;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }
    }
}
