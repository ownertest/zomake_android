package com.zomake.mobile.bean;

import java.util.List;

/**
 * Created by wojiushiwn on 2017/3/24.
 * desc:
 */

public class ChinaCityBean {


    /**
     * cityArr : [{"city":"北京市","districtArr":[{"district":"东城区","id":1},{"district":"西城区","id":2},{"district":"崇文区","id":3},{"district":"宣武区","id":4},{"district":"朝阳区","id":5},{"district":"丰台区","id":6},{"district":"石景山区","id":7},{"district":"海淀区","id":8},{"district":"门头沟区","id":9},{"district":"房山区","id":10},{"district":"通州区","id":11},{"district":"顺义区","id":12},{"district":"昌平区","id":13},{"district":"大兴区","id":14},{"district":"怀柔区","id":15},{"district":"平谷区","id":16},{"district":"密云县","id":17},{"district":"延庆县","id":18}]}]
     * province : 北京
     */

    private String province;
    private List<CityArrEntity> cityArr;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<CityArrEntity> getCityArr() {
        return cityArr;
    }

    public void setCityArr(List<CityArrEntity> cityArr) {
        this.cityArr = cityArr;
    }

    public static class CityArrEntity {
        /**
         * city : 北京市
         * districtArr : [{"district":"东城区","id":1},{"district":"西城区","id":2},{"district":"崇文区","id":3},{"district":"宣武区","id":4},{"district":"朝阳区","id":5},{"district":"丰台区","id":6},{"district":"石景山区","id":7},{"district":"海淀区","id":8},{"district":"门头沟区","id":9},{"district":"房山区","id":10},{"district":"通州区","id":11},{"district":"顺义区","id":12},{"district":"昌平区","id":13},{"district":"大兴区","id":14},{"district":"怀柔区","id":15},{"district":"平谷区","id":16},{"district":"密云县","id":17},{"district":"延庆县","id":18}]
         */

        private String city;
        private List<DistrictArrEntity> districtArr;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<DistrictArrEntity> getDistrictArr() {
            return districtArr;
        }

        public void setDistrictArr(List<DistrictArrEntity> districtArr) {
            this.districtArr = districtArr;
        }

        public static class DistrictArrEntity {
            /**
             * district : 东城区
             * id : 1
             */

            private String district;
            private int id;

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
