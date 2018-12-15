package com.kongtiaoapp.xxhj.prefessionalknowledge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.PublishActivity;
import com.kongtiaoapp.xxhj.adapter.PagerSlidingAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergySavingRingPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnergySavingRingView;
import com.kongtiaoapp.xxhj.ui.view.PagerSlidingTab;
import com.kongtiaoapp.xxhj.utils.CommonUtil;
import com.kongtiaoapp.xxhj.utils.HintDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节能圈界面的Framgent
 * Created by Luoye on 2016-6-8.
 */
public class EnergySavingRingFragment extends BaseFragment<EnergySavingRingPresenter,EnergySavingRingView> implements HintDialogFragment.DialogFragmentCallback,EnergySavingRingView{


    @BindView(R.id.slidingtab)
    PagerSlidingTab slidingtab; //顶部导航
    @BindView(R.id.iv_camera)
    ImageView ivCamera;     //相机按钮
    @BindView(R.id.viewpager)
    ViewPager viewpager;    //viewpager
    private EnergyMomentsFragment energyMomentsFragment;
    private EnergyCommunityFragment energyCommunityFragment;
    private Fragment[] fragments;
    private String[] strings;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_energysaving, null);
//        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

        energyMomentsFragment = new EnergyMomentsFragment();
        energyCommunityFragment = new EnergyCommunityFragment();
        fragments = new Fragment[]{energyMomentsFragment,energyCommunityFragment};
        strings = CommonUtil.getStringArray(R.array.tab_energy);
        slidingtab.setIndicatorColor(getResources().getColor(R.color.white));
        slidingtab.setTextColor(getResources().getColor(R.color.white));
        //填充viewPager
        viewpager.setAdapter(new PagerSlidingAdapter(getFragmentManager(),strings,fragments));
        //绑定slidingTab和ViewPager
        slidingtab.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        ivCamera.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        ivCamera.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public EnergySavingRingPresenter getPresenter() {
        return new EnergySavingRingPresenter();
    }

    @OnClick(R.id.iv_camera)
    public void onClick() {
      startActivity(new Intent(getActivity(),PublishActivity.class));

    }



    @Override
    public void doPositiveClick(int requestCode) {

    }

    @Override
    public void doNegativeClick(int requestCode) {

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
