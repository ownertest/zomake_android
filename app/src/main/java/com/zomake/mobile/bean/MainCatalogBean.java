package com.zomake.mobile.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ryan on 17/1/18.
 */

public class MainCatalogBean implements Serializable {

    public String formatter;
    public String imgDomain;
    public List<DataBean> data;

    public static class DataBean implements Serializable{
        public AttachmentBeanXX attachment;
        public String banner;
        public String en_name;
        public String icon;
        public String id;
        public String jp_name;
        public int level;
        public String name;
        public int orderline;
        public String parent_id;
        public int product_count;
        public String tw_name;
        public static class AttachmentBeanXX implements Serializable{
            public int count;
            public List<ChildrenBean> children;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean implements Serializable{
                public AttachmentBean attachment;
                public String banner;
                public String en_name;
                public String icon;
                public String id;
                public String jp_name;
                public int level;
                public String name;
                public int orderline;
                public ParentBean parent;
                public String parent_id;
                public int product_count;
                public String tw_name;


                public static class AttachmentBean implements Serializable{
                }

                public static class ParentBean implements Serializable{

                    public AttachmentBeanX attachment;
                    public String banner;
                    public String en_name;
                    public String icon;
                    public String id;
                    public String jp_name;
                    public int level;
                    public String name;
                    public int orderline;
                    public String parent_id;
                    public int product_count;
                    public String tw_name;


                    public static class AttachmentBeanX implements Serializable{
                    }
                }
            }
        }
    }
}
