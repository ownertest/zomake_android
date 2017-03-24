package com.zomake.mobile.bean;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wojiushiwn on 2017/3/22.
 * desc:
 */

public enum SizecEnum {
    S("S", 0), M("M", 1), L("L", 2), XL("XL", 3),
    XXL("XXL", 4), XXXL("XXXL", 5), XXXXL("XXXXL", 6);

    SizecEnum(String sizecName, int sizecIndex) {
        this.sizecName = sizecName;
        this.sizecIndex = sizecIndex;
    }

    public static int getIndex(String sizecName) {
        for (SizecEnum sizecEnum : SizecEnum.values()) {
            if (sizecName.equals(sizecEnum.getSizecName())) {
                return sizecEnum.getSizecIndex();
            }
        }
        return -1;
    }

    private String sizecName;
    private int sizecIndex;

    public String getSizecName() {
        return sizecName;
    }

    public int getSizecIndex() {
        return sizecIndex;
    }
}
