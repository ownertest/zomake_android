package com.zomake.mobile.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonwidget.FontTextView;
import com.jaydenxiao.common.irecyclerview.IRecyclerView;
import com.jaydenxiao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.jaydenxiao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.zomake.mobile.R;
import com.zomake.mobile.event.UserChangeEvent;
import com.zomake.mobile.ui.Presenter.EmptyPresenter;
import com.zomake.mobile.ui.activity.AddressManageActivity;
import com.zomake.mobile.ui.activity.CarActivity;
import com.zomake.mobile.ui.activity.CatalogProductListActivity;
import com.zomake.mobile.ui.activity.InboxActivity;
import com.zomake.mobile.ui.activity.LoginRegisterActivity;
import com.zomake.mobile.ui.activity.OrderActivity;
import com.zomake.mobile.ui.activity.ProductDetailActivity;
import com.zomake.mobile.ui.activity.SettingActivity;
import com.zomake.mobile.ui.activity.WalletActivity;
import com.zomake.mobile.utils.UserInfoManager;
import com.zomake.mobile.widget.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends BaseFragment<EmptyPresenter> implements View.OnClickListener {

    @BindView(R.id.user_center_recycler)
    IRecyclerView recyclerView;
    @BindView(R.id.order)
    LinearLayout ll_order;
    @BindView(R.id.car)
    LinearLayout ll_car;
    @BindView(R.id.user_center_set)
    FontTextView ft_set;
    @BindView(R.id.user_center_msg)
    FontTextView mUserCenterMsg;
    @BindView(R.id.iv_user_head)
    ImageView mIvUserHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_phone)
    TextView mTvUserPhone;
    @BindView(R.id.icon_order)
    FontTextView mIconOrder;
    @BindView(R.id.tv_order)
    TextView mTvOrder;
    @BindView(R.id.icon_car)
    FontTextView mIconCar;
    @BindView(R.id.tv_car)
    TextView mTvCar;
    @BindView(R.id.btn_login)
    TextView mBtnLogin;

    private String[] iconIds = {"\uEA3B", "\ue794", "\ue7ac", "\ueac9", "\ue6df", "\ue6a4"};
    private String[] iconTexts = {"钱包", "收货地址", "优惠", "收件箱", "收藏", "反馈"};
    private CommonRecycleViewAdapter<String> adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.my_fragment;
    }

    @Override
    public void initPresenter() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        initFunctionGrid();
        ll_order.setOnClickListener(this);
        ll_car.setOnClickListener(this);
        ft_set.setOnClickListener(this);

    }

    public void setUserInfoView() {
        if (!UserInfoManager.getInstance().isLogin()) {
            mTvUserName.setVisibility(View.GONE);
            mTvUserPhone.setVisibility(View.GONE);
            mBtnLogin.setVisibility(View.VISIBLE);
        } else {
            mTvUserName.setVisibility(View.VISIBLE);
            mTvUserPhone.setVisibility(View.VISIBLE);
            mBtnLogin.setVisibility(View.GONE);
            String url = !UserInfoManager.getInstance().isLogin() || TextUtils.isEmpty(UserInfoManager.getInstance().getCurUserInfo().getHead()) ? "" :
                    ImageLoaderUtils.DEFAULT_URL_HOST + UserInfoManager.getInstance().getCurUserInfo().getHead();
            ImageLoaderUtils.display(getActivity(),
                    mIvUserHead, url, R.drawable.usercenter_avatar, R.drawable.usercenter_avatar);
            mTvUserName.setText(UserInfoManager.getInstance().getCurUserInfo().getNickname());
            mTvUserPhone.setText(UserInfoManager.getInstance().getCurUserInfo().getPhone());
        }
    }

    private void initFunctionGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CommonRecycleViewAdapter<String>(getContext(), R.layout.user_center_item_layout) {
            @Override
            public void convert(ViewHolderHelper helper, String text) {
                helper.setText(R.id.name, text);
                helper.setText(R.id.ftv_icon, iconIds[helper.getAdapterPosition() - 2]);
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.e("123", position + "");
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), WalletActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getActivity(), AddressManageActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
//                    Intent intent = new Intent(getActivity(), CarActivity.class);
                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(getActivity(), InboxActivity.class);
                    startActivity(intent);
                } else if (position == 6) {
//                    Intent intent = new Intent(getActivity(), FavoriteActivity.class);
                    Intent intent = new Intent(getActivity(), CatalogProductListActivity.class);
                    intent.putExtra("catalogId", "5836c796d939c40001dc842c");
                    intent.putExtra("catalogName", "手机壳");
                    intent.putExtra("parentName", "数码");
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        adapter.addAll(Arrays.asList(iconTexts));
        recyclerView.addItemDecoration(new SpacesItemDecoration(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.order) {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.car) {
            Intent intent = new Intent(getActivity(), CarActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.user_center_set) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_login)
    public void login() {
        LoginRegisterActivity.startActivity(getActivity());
    }

    @Subscribe
    public void onEventMainThread(final UserChangeEvent userChangeEvent) {
        setUserInfoView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
