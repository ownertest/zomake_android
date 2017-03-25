package com.jaydenxiao.common.commonutils;


import java.util.Arrays;
import java.util.Iterator;

public class StringUtils {
    public static final String EMPTY = "";

    public static String join(final Object[] array, final String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, true);
    }

    public static String join(final Object[] array, final String separator, final boolean isAllowRepeat) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length, isAllowRepeat);
    }

    public static String join(final Object[] array, String separator, final int startIndex, final int endIndex, final boolean isAllowRepeat) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        final StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (!isAllowRepeat && i > startIndex
                    && array[i] != null && array[i - 1] != null &&
                    array[i].equals(array[i - 1]))
                break;
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

}