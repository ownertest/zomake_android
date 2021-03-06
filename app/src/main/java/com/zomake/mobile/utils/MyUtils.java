/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.zomake.mobile.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;

import com.jaydenxiao.common.baseapp.BaseApp;
import com.jaydenxiao.common.exception.ExceptionEngine;
import com.zomake.mobile.R;
import com.zomake.mobile.app.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

/**
 * @author 咖枯
 * @version 1.0 2016/5/31
 */
public class MyUtils {

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWith();

        if (tabWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private static int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }

    public static int getScreenWith() {
        return BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return BaseApplication.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static int dip2px(float dipValue) {
        final float scale = BaseApplication.getAppResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static boolean existsField(Class clz, String fieldName) {
        try {
            return clz.getDeclaredField(fieldName) != null;
        } catch (Exception ignored) {
        }
        return clz != Object.class && existsField(clz.getSuperclass(), fieldName);
    }

    public static Object getValueByFieldName(Object obj, String fieldName) {
        try {
            if (obj.getClass().getDeclaredField(fieldName) == null) return new Object();

            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public static String getFromRaw(Context context, int rawRes) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().openRawResource(rawRes));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                result.append(line);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                    inputReader = null;
                }
                if (bufReader != null) {
                    bufReader.close();
                    bufReader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
