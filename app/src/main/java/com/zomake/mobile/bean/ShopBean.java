package com.zomake.mobile.bean;

import java.util.List;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class ShopBean {

    /**
     * formatter : https://shop-cdn.zomake.com/{file}-amblumPic
     * data : {"attachment":{"certInfo":{"img":["201606222961161933_Fs5oStC13E3AGshifnZTlCPSgPTy.jpg","201606222961161959_FsSgYN7wqa0BtQGqVu2MFPpSZYRU.jpg","201606222961162001_Fok-YHaM264bNv7_1aXOyklDrI45.jpg","201606222961162003_Fg11_19QyVu5ieBrMoexvRH5K93c.jpg","201606222961162228_FpE2Wtscp69GaQfu3egY3ufT-lE9.jpg"],"updateDate":"2016-09-11 21:24:53","desc_info":"自由商业插画师，绘画风格多样化。","origin_id":210,"type":1,"url":"http://weibo.com/1821919157/profile?topnav=1&wvr=6&is_all=1","update_date":1473600293935,"shop_id":"57d559353916c325f8ead422","_id":{"$oid":"57d556533916c325f8e9931a"},"id":"57d556533916c325f8e9931a","create_date":1466555168000,"status":1,"createDate":"2016-06-22 08:26:08"},"liked":false},"banner":"201606222961104526_FuJxION1kggV8zGL0Oojar5-AbV7.jpg","delete_flag":0,"desc_info":"随遇而安！！！（人体可遮掩，人性可隐藏，艺术却可表现一切的情欲！）","domain":"sunnie","grade":1,"id":"57d559353916c325f8ead422","location":"CN","logo":"201606222961105012_FhPfk8HeJuVk5TvxkfdM1QnsHAuW.jpg","name":"O.K.O","product_count":26,"record_id":"199995633","status":0,"summary":"{\"result\":[\"）\"]}","tag":"手绘创意,艺人明星,原创设计,宠物萌物,随意涂鸦,商业插画","type":1,"uid":"57d5595d3916c325f8eae97a","update_permission":0}
     * imgDomain : https://shop-cdn.zomake.com/
     */

    private String formatter;
    private DataEntity data;
    private String imgDomain;

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public static class DataEntity {
        /**
         * attachment : {"certInfo":{"img":["201606222961161933_Fs5oStC13E3AGshifnZTlCPSgPTy.jpg","201606222961161959_FsSgYN7wqa0BtQGqVu2MFPpSZYRU.jpg","201606222961162001_Fok-YHaM264bNv7_1aXOyklDrI45.jpg","201606222961162003_Fg11_19QyVu5ieBrMoexvRH5K93c.jpg","201606222961162228_FpE2Wtscp69GaQfu3egY3ufT-lE9.jpg"],"updateDate":"2016-09-11 21:24:53","desc_info":"自由商业插画师，绘画风格多样化。","origin_id":210,"type":1,"url":"http://weibo.com/1821919157/profile?topnav=1&wvr=6&is_all=1","update_date":1473600293935,"shop_id":"57d559353916c325f8ead422","_id":{"$oid":"57d556533916c325f8e9931a"},"id":"57d556533916c325f8e9931a","create_date":1466555168000,"status":1,"createDate":"2016-06-22 08:26:08"},"liked":false}
         * banner : 201606222961104526_FuJxION1kggV8zGL0Oojar5-AbV7.jpg
         * delete_flag : 0
         * desc_info : 随遇而安！！！（人体可遮掩，人性可隐藏，艺术却可表现一切的情欲！）
         * domain : sunnie
         * grade : 1
         * id : 57d559353916c325f8ead422
         * location : CN
         * logo : 201606222961105012_FhPfk8HeJuVk5TvxkfdM1QnsHAuW.jpg
         * name : O.K.O
         * product_count : 26
         * record_id : 199995633
         * status : 0
         * summary : {"result":["）"]}
         * tag : 手绘创意,艺人明星,原创设计,宠物萌物,随意涂鸦,商业插画
         * type : 1
         * uid : 57d5595d3916c325f8eae97a
         * update_permission : 0
         */

        private AttachmentEntity attachment;
        private String banner;
        private int delete_flag;
        private String desc_info;
        private String domain;
        private int grade;
        private String id;
        private String location;
        private String logo;
        private String name;
        private int product_count;
        private String record_id;
        private int status;
        private String summary;
        private String tag;
        private int type;
        private String uid;
        private int update_permission;

        public AttachmentEntity getAttachment() {
            return attachment;
        }

        public void setAttachment(AttachmentEntity attachment) {
            this.attachment = attachment;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public int getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(int delete_flag) {
            this.delete_flag = delete_flag;
        }

        public String getDesc_info() {
            return desc_info;
        }

        public void setDesc_info(String desc_info) {
            this.desc_info = desc_info;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
        }

        public String getRecord_id() {
            return record_id;
        }

        public void setRecord_id(String record_id) {
            this.record_id = record_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getUpdate_permission() {
            return update_permission;
        }

        public void setUpdate_permission(int update_permission) {
            this.update_permission = update_permission;
        }

        public static class AttachmentEntity {
            /**
             * certInfo : {"img":["201606222961161933_Fs5oStC13E3AGshifnZTlCPSgPTy.jpg","201606222961161959_FsSgYN7wqa0BtQGqVu2MFPpSZYRU.jpg","201606222961162001_Fok-YHaM264bNv7_1aXOyklDrI45.jpg","201606222961162003_Fg11_19QyVu5ieBrMoexvRH5K93c.jpg","201606222961162228_FpE2Wtscp69GaQfu3egY3ufT-lE9.jpg"],"updateDate":"2016-09-11 21:24:53","desc_info":"自由商业插画师，绘画风格多样化。","origin_id":210,"type":1,"url":"http://weibo.com/1821919157/profile?topnav=1&wvr=6&is_all=1","update_date":1473600293935,"shop_id":"57d559353916c325f8ead422","_id":{"$oid":"57d556533916c325f8e9931a"},"id":"57d556533916c325f8e9931a","create_date":1466555168000,"status":1,"createDate":"2016-06-22 08:26:08"}
             * liked : false
             */

            private CertInfoEntity certInfo;
            private boolean liked;

            public CertInfoEntity getCertInfo() {
                return certInfo;
            }

            public void setCertInfo(CertInfoEntity certInfo) {
                this.certInfo = certInfo;
            }

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public static class CertInfoEntity {
                /**
                 * img : ["201606222961161933_Fs5oStC13E3AGshifnZTlCPSgPTy.jpg","201606222961161959_FsSgYN7wqa0BtQGqVu2MFPpSZYRU.jpg","201606222961162001_Fok-YHaM264bNv7_1aXOyklDrI45.jpg","201606222961162003_Fg11_19QyVu5ieBrMoexvRH5K93c.jpg","201606222961162228_FpE2Wtscp69GaQfu3egY3ufT-lE9.jpg"]
                 * updateDate : 2016-09-11 21:24:53
                 * desc_info : 自由商业插画师，绘画风格多样化。
                 * origin_id : 210
                 * type : 1
                 * url : http://weibo.com/1821919157/profile?topnav=1&wvr=6&is_all=1
                 * update_date : 1473600293935
                 * shop_id : 57d559353916c325f8ead422
                 * _id : {"$oid":"57d556533916c325f8e9931a"}
                 * id : 57d556533916c325f8e9931a
                 * create_date : 1466555168000
                 * status : 1
                 * createDate : 2016-06-22 08:26:08
                 */

                private String updateDate;
                private String desc_info;
                private int origin_id;
                private int type;
                private String url;
                private long update_date;
                private String shop_id;
                private IdEntity _id;
                private String id;
                private long create_date;
                private int status;
                private String createDate;
                private List<String> img;

                public String getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(String updateDate) {
                    this.updateDate = updateDate;
                }

                public String getDesc_info() {
                    return desc_info;
                }

                public void setDesc_info(String desc_info) {
                    this.desc_info = desc_info;
                }

                public int getOrigin_id() {
                    return origin_id;
                }

                public void setOrigin_id(int origin_id) {
                    this.origin_id = origin_id;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public long getUpdate_date() {
                    return update_date;
                }

                public void setUpdate_date(long update_date) {
                    this.update_date = update_date;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }

                public IdEntity get_id() {
                    return _id;
                }

                public void set_id(IdEntity _id) {
                    this._id = _id;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public long getCreate_date() {
                    return create_date;
                }

                public void setCreate_date(long create_date) {
                    this.create_date = create_date;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public List<String> getImg() {
                    return img;
                }

                public void setImg(List<String> img) {
                    this.img = img;
                }

                public static class IdEntity {
                    /**
                     * $oid : 57d556533916c325f8e9931a
                     */

                    private String $oid;

                    public String get$oid() {
                        return $oid;
                    }

                    public void set$oid(String $oid) {
                        this.$oid = $oid;
                    }
                }
            }
        }
    }
}
