package com.zomake.mobile.ui.fragment;

import com.jaydenxiao.common.base.BaseFragment;
import com.zomake.mobile.R;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;


/**
 * Created by Ryan on 17/1/18.
 */

public class CustomFragment extends BaseFragment<EmptyPresenter> {
    @Override
    protected int getLayoutResource() {
        return R.layout.base_fragment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
