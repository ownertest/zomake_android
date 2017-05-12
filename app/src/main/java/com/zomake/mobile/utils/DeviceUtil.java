package com.zomake.mobile.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.jaydenxiao.common.R;

/**
 * Created by wojiushiwn on 2017/3/27.
 * desc:
 */

public class DeviceUtil {

    public static synchronized String getDeviceId(Context context) {
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static synchronized String getMACAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo info = null;
            try {
                // here maybe throw exception in android framework
                info = wifiManager.getConnectionInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (info != null) {
                return info.getMacAddress();
            }
        }
        return null;
    }

    /**
     * 获取国家码
     */
    public static String[] getCountryZipCode(Context context) {
        String CountryID = "";
        String CountryZipCode[] = new String[]{"86","CN"};
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CountryID = manager.getSimCountryIso().toUpperCase();
        String[] rl = context.getResources().getStringArray(R.array.CountryCodes);
        for (String aRl : rl) {
            String[] g = aRl.split(",");
            if (g[1].trim().equals(CountryID.trim())) {
                CountryZipCode = g;
                break;
            }
        }
        return CountryZipCode;
    }
}
