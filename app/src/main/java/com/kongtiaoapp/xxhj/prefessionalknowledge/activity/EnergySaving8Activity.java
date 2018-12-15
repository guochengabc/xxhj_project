package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.PagerSlidingAdapter;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8InformationFragment;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8SpecialFragment;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8TalkingFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergySaving8Presenter;
import com.kongtiaoapp.xxhj.mvp.view.EnergySaving8View;
import com.kongtiaoapp.xxhj.ui.view.PagerSlidingTab;
import com.kongtiaoapp.xxhj.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节能吧详情页面
 * Created by Luoye on 2016-6-16.
 */
public class EnergySaving8Activity extends BaseActivity<EnergySaving8Presenter, EnergySaving8View> implements EnergySaving8View {
    @BindView(R.id.pstab)
    PagerSlidingTab pstab;
    @BindView(R.id.viewPager)//节能吧轮滑
    ViewPager viewPager;
    @BindView(R.id.tv_search)//搜索
    TextView tvSearch;

    private Fragment[] fragments;
    private String[] strings;
    private int index = 0;//记录当前页面的角标
    private Energy8InformationFragment energy8InformationFragment;
    private Energy8SpecialFragment energy8SpecialFragment;
    private Energy8TalkingFragment energy8TalkingFragment;

    @Override
    protected int initContentView() {
        return R.layout.activity_energy8;
    }

    @Override
    protected void initView() {
        pstab.setTextColor(getResources().getColor(R.color.font_nine));
        pstab.setIndicatorColor(getResources().getColor(R.color.main_color));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        index = position;
                        tvSearch.setVisibility(View.GONE);
                        tvSearch.setText("搜索");

                        break;
                    case 1:
                        index = position;
                        tvSearch.setVisibility(View.GONE);
                        break;
                    case 2:
                        index = position;
                        tvSearch.setVisibility(View.VISIBLE);
                        tvSearch.setText("发帖");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        energy8InformationFragment = new Energy8InformationFragment();//节能吧_精选
        energy8SpecialFragment = new Energy8SpecialFragment();//节能吧_专栏
        energy8TalkingFragment = new Energy8TalkingFragment();//节能吧_讨论
        fragments = new Fragment[]{energy8InformationFragment, energy8SpecialFragment, energy8TalkingFragment};
        strings = CommonUtil.getStringArray(R.array.tab_energy8);
        viewPager.setAdapter(new PagerSlidingAdapter(FM, strings, fragments));
        pstab.setViewPager(viewPager);
    }

    @Override
    protected EnergySaving8Presenter getPresenter() {
        return new EnergySaving8Presenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        switch (index) {
            case 0://搜索

                break;
            case 2://发帖
                startActivity(new Intent(mContext, PublishPostActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void setList(Object data) {

    }

    @Override
    public void setText(Object text) {

    }
}
