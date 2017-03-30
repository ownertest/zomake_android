package com.zomake.mobile.widget.CounterView;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.commonutils.ToastUitl;
import com.zomake.mobile.R;

/**
 * Created by wojiushiwn on 2017/3/19.
 * desc:
 */

public class CounterView extends LinearLayout implements View.OnClickListener, TextWatcher {
    /**
     * 最大的数量
     **/
    public static final int MAX_VALUE = 100;

    /**
     * 最小的数量
     **/
    public static final int MIN_VALUE = 1;

    private int countValue = 1;//数量

    private TextView tvAdd, tvMinu;

    private EditText etCount;

    private IChangeCountCallback callback;

    private int maxValue = MAX_VALUE;


    public void setCallback(IChangeCountCallback c) {
        this.callback = c;
    }

    private Context mContext;

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    /**
     * 功能描述：设置最大数量
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/3 18:33
     * 参数：
     */
    public void setMaxValue(int max) {
        this.maxValue = max;
    }


    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.my_counter_size);

        int maxValue = a.getInt(R.styleable.my_counter_size_count_max, 100);

        setMaxValue(maxValue);

        LayoutInflater.from(mContext).inflate(R.layout.model_count_view, this);

        tvMinu = (TextView) findViewById(R.id.iv_count_minus);
        tvMinu.setOnClickListener(this);

        tvAdd = (TextView) findViewById(R.id.iv_count_add);
        tvAdd.setOnClickListener(this);

        etCount = (EditText) findViewById(R.id.et_count);
        etCount.addTextChangedListener(this);
        a.recycle();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_count_add:
                addAction();
                break;
            case R.id.iv_count_minus:
                minuAction();
                break;


        }
    }

    /**
     * 添加操
     */
    private void addAction() {
        countValue++;
        btnChangeWord();
    }


    /**
     * 删除操作
     */
    private void minuAction() {
        countValue--;
        btnChangeWord();
    }

    /**
     * 功能描述：
     * 作者： hg_liuzl@qq.com
     * 时间：2016/12/12 10:29
     * 参数：boolean 是否需要重新赋值
     */
    private void changeWord(boolean needUpdate) {
        if (needUpdate) {
            etCount.removeTextChangedListener(this);
            if (!TextUtils.isEmpty(etCount.getText().toString().trim())) {  //不为空的时候才需要赋值
                etCount.setText(String.valueOf(countValue));
            }
            etCount.addTextChangedListener(this);
        }
        etCount.setSelection(etCount.getText().toString().trim().length());
        callback.change(countValue);
    }

    private void btnChangeWord() {
        tvMinu.setEnabled(countValue > MIN_VALUE);
        tvAdd.setEnabled(countValue < maxValue);
        etCount.setText(String.valueOf(countValue));
        etCount.setSelection(etCount.getText().toString().trim().length());
        callback.change(countValue);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        boolean needUpdate = false;
        if (!TextUtils.isEmpty(s)) {
            countValue = Integer.valueOf(s.toString());
            if (countValue <= MIN_VALUE) {
                countValue = MIN_VALUE;
                tvMinu.setEnabled(false);
                tvAdd.setEnabled(true);
                needUpdate = true;
                ToastUitl.showShort(String.format("最少添加%s个数量", MIN_VALUE));
            } else if (countValue >= maxValue) {
                countValue = maxValue;
                tvMinu.setEnabled(true);
                tvAdd.setEnabled(false);
                needUpdate = true;
                ToastUitl.showShort(String.format("最多只能添加%s个数量", maxValue));

            } else {
                tvMinu.setEnabled(true);
                tvAdd.setEnabled(true);
            }
        } else {  //如果编辑框被清空了，直接填1
            countValue = 1;
            tvMinu.setEnabled(false);
            tvAdd.setEnabled(true);
            needUpdate = true;
            ToastUitl.showShort(String.format("最少添加%s个数量", MIN_VALUE));

        }
        changeWord(needUpdate);
    }
}