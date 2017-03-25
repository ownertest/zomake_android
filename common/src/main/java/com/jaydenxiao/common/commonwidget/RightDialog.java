package com.jaydenxiao.common.commonwidget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jaydenxiao.common.commonutils.DisplayUtil;


/**
 * description:弹窗浮动加载进度条
 * Created by xsf
 * on 2016.07.17:22
 */
public class RightDialog extends Dialog {

    private View mContentView;

    public RightDialog(@NonNull Context context) {
        super(context);
    }

    public RightDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    public RightDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setContentView(@NonNull View view) {
        this.mContentView = view;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        super.setContentView(view);
    }

    public void showDialogForLoading() {
        if (!isShowing())
            show();
    }

    @Override
    public void show() {
        super.show();
        int width = DisplayUtil.getScreenWidth(getContext()) / 3;
        Window window = getWindow();
        if (window != null && window.getDecorView() != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) window.getDecorView().getLayoutParams();
            layoutParams.height = DisplayUtil.getScreenHeight(getContext());
            layoutParams.width = DisplayUtil.getScreenHeight(getContext());
            window.getDecorView().setLayoutParams(layoutParams);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = DisplayUtil.getScreenHeight(getContext());
                attr.width = DisplayUtil.getScreenHeight(getContext());
                attr.gravity = Gravity.RIGHT;
            }
            window.setAttributes(attr);

        }
//            window.getDecorView().setPadding(0, 0, 0, 0);
////            window.getDecorView().setMinimumHeight(100000);

//        }
    }
}
