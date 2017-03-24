package com.jaydenxiao.common.commonwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;


public class NormalTitleBar extends RelativeLayout implements View.OnClickListener {

    private View bottomLine;
    private ImageView ivRight;
    private FontTextView ivBack, tvTitle, tvRight, tvSecLeft;
    private RelativeLayout rlCommonTitle;
    private Context context;

    public NormalTitleBar(Context context) {
        super(context, null);
        this.context = context;
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.bar_normal, this);
        ivBack = (FontTextView) findViewById(R.id.tv_back);
        tvTitle = (FontTextView) findViewById(R.id.tv_title);
        tvSecLeft = (FontTextView) findViewById(R.id.tv_left);
        tvRight = (FontTextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.image_right);
        bottomLine = findViewById(R.id.bottom_line);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);
        //setHeaderHeight();
        ivBack.setOnClickListener(this);
    }

    public void setHeaderHeight() {
        rlCommonTitle.setPadding(0, DisplayUtil.getStatusBarHeight(context), 0, 0);
        rlCommonTitle.requestLayout();
    }

    /**
     * 管理返回按钮
     */
    public void setBackVisibility(boolean visible) {
        if (visible) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable
     */
    public void setTvLeftVisible(boolean visiable) {
        if (visiable) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        ivBack.setText(tvLeftText);
    }

    /**
     * 设置标题栏第二个左侧字符串
     *
     * @param visible
     */
    public void setSecTvLeftVisible(boolean visible) {
        if (visible) {
            tvSecLeft.setVisibility(View.VISIBLE);
        } else {
            tvSecLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setSecTvLeft(String tvLeftText) {
        tvSecLeft.setText(tvLeftText);
        tvSecLeft.setVisibility(VISIBLE);
    }

    public FontTextView getTvSecLeft() {
        return tvSecLeft;
    }

    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public void setTitleText(String string) {
        tvTitle.setText(string);
    }

    public void setTitleText(int string) {
        tvTitle.setText(string);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightImagSrc(int id) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        ivBack.setCompoundDrawables(getResources().getDrawable(id), null, null, null);
    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        ivBack.setText(str);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightTitle(String text) {
        tvRight.setText(text);
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        if (listener != null)
            ivBack.setOnClickListener(listener);
    }

    public void setOnRightImagListener(OnClickListener listener) {
        if (listener != null)
            ivRight.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        if (listener != null)
            tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(color);
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

    public void setVisibleBottomLine(boolean visible) {
        bottomLine.setVisibility(visible ? VISIBLE : GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_back && context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

}
