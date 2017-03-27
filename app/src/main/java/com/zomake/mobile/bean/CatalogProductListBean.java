package com.zomake.mobile.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wojiushiwn on 2017/3/23.
 * desc:
 */

public class CatalogProductListBean implements Serializable {


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

    public static class DataEntity implements Serializable {

        private int totalPage;
        private int count;
        private String banner;
        private List<ProductArrEntity> productArr;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public List<ProductArrEntity> getProductArr() {
            return productArr;
        }

        public void setProductArr(List<ProductArrEntity> productArr) {
            this.productArr = productArr;
        }

        public static class ProductArrEntity {

            private AttachmentEntityXXX attachment;
            private String catalog_id;
            private String cover;
            private long create_date;
            private int hidden;
            private String id;
            private String img;
            private String inspiration;
            private String location;
            private String name;
            private int pack;
            private String pid;
            private String print_img;
            private int quantity;
            private int royalty;
            private String royalty_cny;
            private String shop_id;
            private int status;
            private String tag;
            private int timeStamp;
            private String uid;
            private long update_date;
            private String summary;
            private String design;

            public AttachmentEntityXXX getAttachment() {
                return attachment;
            }

            public void setAttachment(AttachmentEntityXXX attachment) {
                this.attachment = attachment;
            }

            public String getCatalog_id() {
                return catalog_id;
            }

            public void setCatalog_id(String catalog_id) {
                this.catalog_id = catalog_id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public int getHidden() {
                return hidden;
            }

            public void setHidden(int hidden) {
                this.hidden = hidden;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getInspiration() {
                return inspiration;
            }

            public void setInspiration(String inspiration) {
                this.inspiration = inspiration;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPack() {
                return pack;
            }

            public void setPack(int pack) {
                this.pack = pack;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPrint_img() {
                return print_img;
            }

            public void setPrint_img(String print_img) {
                this.print_img = print_img;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getRoyalty() {
                return royalty;
            }

            public void setRoyalty(int royalty) {
                this.royalty = royalty;
            }

            public String getRoyalty_cny() {
                return royalty_cny;
            }

            public void setRoyalty_cny(String royalty_cny) {
                this.royalty_cny = royalty_cny;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public int getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(int timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public long getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(long update_date) {
                this.update_date = update_date;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getDesign() {
                return design;
            }

            public void setDesign(String design) {
                this.design = design;
            }

            public static class AttachmentEntityXXX implements Serializable {
                private LikeInfoEntity likeInfo;
                private TemplateEntity template;
                private RoyaltyMapEntity royaltyMap;
                private int likeCount;
                private BasePriceMapEntityX basePriceMap;
                private List<?> mateArr;
                private List<String> imgArray;
                private List<?> thumbnailArray;

                public LikeInfoEntity getLikeInfo() {
                    return likeInfo;
                }

                public void setLikeInfo(LikeInfoEntity likeInfo) {
                    this.likeInfo = likeInfo;
                }

                public TemplateEntity getTemplate() {
                    return template;
                }

                public void setTemplate(TemplateEntity template) {
                    this.template = template;
                }

                public RoyaltyMapEntity getRoyaltyMap() {
                    return royaltyMap;
                }

                public void setRoyaltyMap(RoyaltyMapEntity royaltyMap) {
                    this.royaltyMap = royaltyMap;
                }

                public int getLikeCount() {
                    return likeCount;
                }

                public void setLikeCount(int likeCount) {
                    this.likeCount = likeCount;
                }

                public BasePriceMapEntityX getBasePriceMap() {
                    return basePriceMap;
                }

                public void setBasePriceMap(BasePriceMapEntityX basePriceMap) {
                    this.basePriceMap = basePriceMap;
                }

                public List<?> getMateArr() {
                    return mateArr;
                }

                public void setMateArr(List<?> mateArr) {
                    this.mateArr = mateArr;
                }

                public List<String> getImgArray() {
                    return imgArray;
                }

                public void setImgArray(List<String> imgArray) {
                    this.imgArray = imgArray;
                }

                public List<?> getThumbnailArray() {
                    return thumbnailArray;
                }

                public void setThumbnailArray(List<?> thumbnailArray) {
                    this.thumbnailArray = thumbnailArray;
                }

                public static class LikeInfoEntity {
                }

                public static class TemplateEntity {

                    private AttachmentEntity attachment;
                    private BasePriceMapEntity basePriceMap;
                    private String brand;
                    private String catalog_id;
                    private String cn_desc;
                    private String en_desc;
                    private String en_resume;
                    private String id;
                    private String img;
                    private String jp_desc;
                    private String jp_resume;
                    private String model_file;
                    private String model_id;
                    private int model_type;
                    private String name;
                    private int pack;
                    private String pack_no;
                    private int point;
                    private int produce_day;
                    private String produce_no;
                    private String product_no;
                    private String resume;
                    private double rmb_base_price;
                    private int status;
                    private String tag;
                    private String trans_id;
                    private String tw_desc;
                    private String tw_resume;
                    private String unit;
                    private double usd_base_price;
                    private List<PropertyListEntity> propertyList;
                    private List<TransDetailListEntity> transDetailList;

                    public AttachmentEntity getAttachment() {
                        return attachment;
                    }

                    public void setAttachment(AttachmentEntity attachment) {
                        this.attachment = attachment;
                    }

                    public BasePriceMapEntity getBasePriceMap() {
                        return basePriceMap;
                    }

                    public void setBasePriceMap(BasePriceMapEntity basePriceMap) {
                        this.basePriceMap = basePriceMap;
                    }

                    public String getBrand() {
                        return brand;
                    }

                    public void setBrand(String brand) {
                        this.brand = brand;
                    }

                    public String getCatalog_id() {
                        return catalog_id;
                    }

                    public void setCatalog_id(String catalog_id) {
                        this.catalog_id = catalog_id;
                    }

                    public String getCn_desc() {
                        return cn_desc;
                    }

                    public void setCn_desc(String cn_desc) {
                        this.cn_desc = cn_desc;
                    }

                    public String getEn_desc() {
                        return en_desc;
                    }

                    public void setEn_desc(String en_desc) {
                        this.en_desc = en_desc;
                    }

                    public String getEn_resume() {
                        return en_resume;
                    }

                    public void setEn_resume(String en_resume) {
                        this.en_resume = en_resume;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getJp_desc() {
                        return jp_desc;
                    }

                    public void setJp_desc(String jp_desc) {
                        this.jp_desc = jp_desc;
                    }

                    public String getJp_resume() {
                        return jp_resume;
                    }

                    public void setJp_resume(String jp_resume) {
                        this.jp_resume = jp_resume;
                    }

                    public String getModel_file() {
                        return model_file;
                    }

                    public void setModel_file(String model_file) {
                        this.model_file = model_file;
                    }

                    public String getModel_id() {
                        return model_id;
                    }

                    public void setModel_id(String model_id) {
                        this.model_id = model_id;
                    }

                    public int getModel_type() {
                        return model_type;
                    }

                    public void setModel_type(int model_type) {
                        this.model_type = model_type;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getPack() {
                        return pack;
                    }

                    public void setPack(int pack) {
                        this.pack = pack;
                    }

                    public String getPack_no() {
                        return pack_no;
                    }

                    public void setPack_no(String pack_no) {
                        this.pack_no = pack_no;
                    }

                    public int getPoint() {
                        return point;
                    }

                    public void setPoint(int point) {
                        this.point = point;
                    }

                    public int getProduce_day() {
                        return produce_day;
                    }

                    public void setProduce_day(int produce_day) {
                        this.produce_day = produce_day;
                    }

                    public String getProduce_no() {
                        return produce_no;
                    }

                    public void setProduce_no(String produce_no) {
                        this.produce_no = produce_no;
                    }

                    public String getProduct_no() {
                        return product_no;
                    }

                    public void setProduct_no(String product_no) {
                        this.product_no = product_no;
                    }

                    public String getResume() {
                        return resume;
                    }

                    public void setResume(String resume) {
                        this.resume = resume;
                    }

                    public double getRmb_base_price() {
                        return rmb_base_price;
                    }

                    public void setRmb_base_price(double rmb_base_price) {
                        this.rmb_base_price = rmb_base_price;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public String getTag() {
                        return tag;
                    }

                    public void setTag(String tag) {
                        this.tag = tag;
                    }

                    public String getTrans_id() {
                        return trans_id;
                    }

                    public void setTrans_id(String trans_id) {
                        this.trans_id = trans_id;
                    }

                    public String getTw_desc() {
                        return tw_desc;
                    }

                    public void setTw_desc(String tw_desc) {
                        this.tw_desc = tw_desc;
                    }

                    public String getTw_resume() {
                        return tw_resume;
                    }

                    public void setTw_resume(String tw_resume) {
                        this.tw_resume = tw_resume;
                    }

                    public String getUnit() {
                        return unit;
                    }

                    public void setUnit(String unit) {
                        this.unit = unit;
                    }

                    public double getUsd_base_price() {
                        return usd_base_price;
                    }

                    public void setUsd_base_price(double usd_base_price) {
                        this.usd_base_price = usd_base_price;
                    }

                    public List<PropertyListEntity> getPropertyList() {
                        return propertyList;
                    }

                    public void setPropertyList(List<PropertyListEntity> propertyList) {
                        this.propertyList = propertyList;
                    }

                    public List<TransDetailListEntity> getTransDetailList() {
                        return transDetailList;
                    }

                    public void setTransDetailList(List<TransDetailListEntity> transDetailList) {
                        this.transDetailList = transDetailList;
                    }

                    public static class AttachmentEntity {
                        private List<String> nameArray;

                        public List<String> getNameArray() {
                            return nameArray;
                        }

                        public void setNameArray(List<String> nameArray) {
                            this.nameArray = nameArray;
                        }
                    }

                    public static class BasePriceMapEntity {

                        private double AUD;
                        private double HKD;
                        private double TWD;
                        private double KRW;
                        private double SGD;
                        private double JPY;
                        private double EUR;
                        private double GBP;
                        private double RMB;
                        private double USD;
                        private double CAD;
                        private double MYR;

                        public double getAUD() {
                            return AUD;
                        }

                        public void setAUD(double AUD) {
                            this.AUD = AUD;
                        }

                        public double getHKD() {
                            return HKD;
                        }

                        public void setHKD(double HKD) {
                            this.HKD = HKD;
                        }

                        public double getTWD() {
                            return TWD;
                        }

                        public void setTWD(double TWD) {
                            this.TWD = TWD;
                        }

                        public double getKRW() {
                            return KRW;
                        }

                        public void setKRW(double KRW) {
                            this.KRW = KRW;
                        }

                        public double getSGD() {
                            return SGD;
                        }

                        public void setSGD(double SGD) {
                            this.SGD = SGD;
                        }

                        public double getJPY() {
                            return JPY;
                        }

                        public void setJPY(double JPY) {
                            this.JPY = JPY;
                        }

                        public double getEUR() {
                            return EUR;
                        }

                        public void setEUR(double EUR) {
                            this.EUR = EUR;
                        }

                        public double getGBP() {
                            return GBP;
                        }

                        public void setGBP(double GBP) {
                            this.GBP = GBP;
                        }

                        public double getRMB() {
                            return RMB;
                        }

                        public void setRMB(double RMB) {
                            this.RMB = RMB;
                        }

                        public double getUSD() {
                            return USD;
                        }

                        public void setUSD(double USD) {
                            this.USD = USD;
                        }

                        public double getCAD() {
                            return CAD;
                        }

                        public void setCAD(double CAD) {
                            this.CAD = CAD;
                        }

                        public double getMYR() {
                            return MYR;
                        }

                        public void setMYR(double MYR) {
                            this.MYR = MYR;
                        }
                    }

                    public static class PropertyListEntity {

                        private AttachmentEntityX attachment;
                        private String capacity;
                        private String color;
                        private long create_date;
                        private String id;
                        private String material;
                        private String model;
                        private String pid;
                        private int quantity;
                        private String ram;
                        private double rmb_price;
                        private String size;
                        private String sizec;
                        private String style;
                        private String type;
                        private long update_date;
                        private double usd_price;

                        public AttachmentEntityX getAttachment() {
                            return attachment;
                        }

                        public void setAttachment(AttachmentEntityX attachment) {
                            this.attachment = attachment;
                        }

                        public String getCapacity() {
                            return capacity;
                        }

                        public void setCapacity(String capacity) {
                            this.capacity = capacity;
                        }

                        public String getColor() {
                            return color;
                        }

                        public void setColor(String color) {
                            this.color = color;
                        }

                        public long getCreate_date() {
                            return create_date;
                        }

                        public void setCreate_date(long create_date) {
                            this.create_date = create_date;
                        }

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getMaterial() {
                            return material;
                        }

                        public void setMaterial(String material) {
                            this.material = material;
                        }

                        public String getModel() {
                            return model;
                        }

                        public void setModel(String model) {
                            this.model = model;
                        }

                        public String getPid() {
                            return pid;
                        }

                        public void setPid(String pid) {
                            this.pid = pid;
                        }

                        public int getQuantity() {
                            return quantity;
                        }

                        public void setQuantity(int quantity) {
                            this.quantity = quantity;
                        }

                        public String getRam() {
                            return ram;
                        }

                        public void setRam(String ram) {
                            this.ram = ram;
                        }

                        public double getRmb_price() {
                            return rmb_price;
                        }

                        public void setRmb_price(double rmb_price) {
                            this.rmb_price = rmb_price;
                        }

                        public String getSize() {
                            return size;
                        }

                        public void setSize(String size) {
                            this.size = size;
                        }

                        public String getSizec() {
                            return sizec;
                        }

                        public void setSizec(String sizec) {
                            this.sizec = sizec;
                        }

                        public String getStyle() {
                            return style;
                        }

                        public void setStyle(String style) {
                            this.style = style;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public long getUpdate_date() {
                            return update_date;
                        }

                        public void setUpdate_date(long update_date) {
                            this.update_date = update_date;
                        }

                        public double getUsd_price() {
                            return usd_price;
                        }

                        public void setUsd_price(double usd_price) {
                            this.usd_price = usd_price;
                        }

                        public static class AttachmentEntityX {
                        }
                    }

                    public static class TransDetailListEntity {

                        private String country;
                        private String province;
                        private String city;
                        private String trans_id;
                        private int step;
                        private IdEntity _id;
                        private String id;
                        private int expense;
                        private CreateDateEntity create_date;
                        private int trans_day;
                        private UpdateDateEntity update_date;
                        private String cny;
                        private AttachmentEntityXX attachment;

                        public String getCountry() {
                            return country;
                        }

                        public void setCountry(String country) {
                            this.country = country;
                        }

                        public String getProvince() {
                            return province;
                        }

                        public void setProvince(String province) {
                            this.province = province;
                        }

                        public String getCity() {
                            return city;
                        }

                        public void setCity(String city) {
                            this.city = city;
                        }

                        public String getTrans_id() {
                            return trans_id;
                        }

                        public void setTrans_id(String trans_id) {
                            this.trans_id = trans_id;
                        }

                        public int getStep() {
                            return step;
                        }

                        public void setStep(int step) {
                            this.step = step;
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

                        public int getExpense() {
                            return expense;
                        }

                        public void setExpense(int expense) {
                            this.expense = expense;
                        }

                        public CreateDateEntity getCreate_date() {
                            return create_date;
                        }

                        public void setCreate_date(CreateDateEntity create_date) {
                            this.create_date = create_date;
                        }

                        public int getTrans_day() {
                            return trans_day;
                        }

                        public void setTrans_day(int trans_day) {
                            this.trans_day = trans_day;
                        }

                        public UpdateDateEntity getUpdate_date() {
                            return update_date;
                        }

                        public void setUpdate_date(UpdateDateEntity update_date) {
                            this.update_date = update_date;
                        }

                        public String getCny() {
                            return cny;
                        }

                        public void setCny(String cny) {
                            this.cny = cny;
                        }

                        public AttachmentEntityXX getAttachment() {
                            return attachment;
                        }

                        public void setAttachment(AttachmentEntityXX attachment) {
                            this.attachment = attachment;
                        }

                        public static class IdEntity {
                            /**
                             * $oid : 5825a8f7e553c80001308df1
                             */

                            private String $oid;

                            public String get$oid() {
                                return $oid;
                            }

                            public void set$oid(String $oid) {
                                this.$oid = $oid;
                            }
                        }

                        public static class CreateDateEntity {
                            /**
                             * $date : 1478863095355
                             */

                            private long $date;

                            public long get$date() {
                                return $date;
                            }

                            public void set$date(long $date) {
                                this.$date = $date;
                            }
                        }

                        public static class UpdateDateEntity {
                        }

                        public static class AttachmentEntityXX {
                        }
                    }
                }

                public static class RoyaltyMapEntity {
                    /**
                     * AUD : 3
                     * HKD : 16
                     * TWD : 66
                     * KRW : 2316
                     * SGD : 3
                     * JPY : 246
                     * EUR : 2
                     * GBP : 2
                     * RMB : 10
                     * USD : 2
                     * CAD : 3
                     * MYR : 9
                     */

                    private int AUD;
                    private int HKD;
                    private int TWD;
                    private int KRW;
                    private int SGD;
                    private int JPY;
                    private int EUR;
                    private int GBP;
                    private int RMB;
                    private int USD;
                    private int CAD;
                    private int MYR;

                    public int getAUD() {
                        return AUD;
                    }

                    public void setAUD(int AUD) {
                        this.AUD = AUD;
                    }

                    public int getHKD() {
                        return HKD;
                    }

                    public void setHKD(int HKD) {
                        this.HKD = HKD;
                    }

                    public int getTWD() {
                        return TWD;
                    }

                    public void setTWD(int TWD) {
                        this.TWD = TWD;
                    }

                    public int getKRW() {
                        return KRW;
                    }

                    public void setKRW(int KRW) {
                        this.KRW = KRW;
                    }

                    public int getSGD() {
                        return SGD;
                    }

                    public void setSGD(int SGD) {
                        this.SGD = SGD;
                    }

                    public int getJPY() {
                        return JPY;
                    }

                    public void setJPY(int JPY) {
                        this.JPY = JPY;
                    }

                    public int getEUR() {
                        return EUR;
                    }

                    public void setEUR(int EUR) {
                        this.EUR = EUR;
                    }

                    public int getGBP() {
                        return GBP;
                    }

                    public void setGBP(int GBP) {
                        this.GBP = GBP;
                    }

                    public int getRMB() {
                        return RMB;
                    }

                    public void setRMB(int RMB) {
                        this.RMB = RMB;
                    }

                    public int getUSD() {
                        return USD;
                    }

                    public void setUSD(int USD) {
                        this.USD = USD;
                    }

                    public int getCAD() {
                        return CAD;
                    }

                    public void setCAD(int CAD) {
                        this.CAD = CAD;
                    }

                    public int getMYR() {
                        return MYR;
                    }

                    public void setMYR(int MYR) {
                        this.MYR = MYR;
                    }
                }

                public static class BasePriceMapEntityX {
                    /**
                     * AUD : 12
                     * HKD : 63
                     * TWD : 262
                     * KRW : 9261
                     * SGD : 12
                     * JPY : 982
                     * EUR : 8
                     * GBP : 6
                     * RMB : 35
                     * USD : 8
                     * CAD : 12
                     * MYR : 35
                     */

                    private int AUD;
                    private int HKD;
                    private int TWD;
                    private int KRW;
                    private int SGD;
                    private int JPY;
                    private int EUR;
                    private int GBP;
                    private int RMB;
                    private int USD;
                    private int CAD;
                    private int MYR;

                    public int getAUD() {
                        return AUD;
                    }

                    public void setAUD(int AUD) {
                        this.AUD = AUD;
                    }

                    public int getHKD() {
                        return HKD;
                    }

                    public void setHKD(int HKD) {
                        this.HKD = HKD;
                    }

                    public int getTWD() {
                        return TWD;
                    }

                    public void setTWD(int TWD) {
                        this.TWD = TWD;
                    }

                    public int getKRW() {
                        return KRW;
                    }

                    public void setKRW(int KRW) {
                        this.KRW = KRW;
                    }

                    public int getSGD() {
                        return SGD;
                    }

                    public void setSGD(int SGD) {
                        this.SGD = SGD;
                    }

                    public int getJPY() {
                        return JPY;
                    }

                    public void setJPY(int JPY) {
                        this.JPY = JPY;
                    }

                    public int getEUR() {
                        return EUR;
                    }

                    public void setEUR(int EUR) {
                        this.EUR = EUR;
                    }

                    public int getGBP() {
                        return GBP;
                    }

                    public void setGBP(int GBP) {
                        this.GBP = GBP;
                    }

                    public int getRMB() {
                        return RMB;
                    }

                    public void setRMB(int RMB) {
                        this.RMB = RMB;
                    }

                    public int getUSD() {
                        return USD;
                    }

                    public void setUSD(int USD) {
                        this.USD = USD;
                    }

                    public int getCAD() {
                        return CAD;
                    }

                    public void setCAD(int CAD) {
                        this.CAD = CAD;
                    }

                    public int getMYR() {
                        return MYR;
                    }

                    public void setMYR(int MYR) {
                        this.MYR = MYR;
                    }
                }
            }
        }
    }
}
