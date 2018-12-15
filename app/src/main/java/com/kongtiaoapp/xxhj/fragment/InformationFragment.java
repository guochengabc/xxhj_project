package com.kongtiaoapp.xxhj.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.PagerSlidingAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.InformationPresenter;
import com.kongtiaoapp.xxhj.mvp.view.InformationView;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8InformationFragment;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8SpecialFragment;
import com.kongtiaoapp.xxhj.prefessionalknowledge.fragment.Energy8TalkingFragment;
import com.kongtiaoapp.xxhj.ui.view.PagerSlidingTab;
import com.kongtiaoapp.xxhj.utils.CommonUtil;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资讯界面的Framgent
 * Created by Administrator on 2016-6-8.
 */
public class InformationFragment extends BaseFragment<InformationPresenter,InformationView> implements InformationView {

    @BindView(R.id.pstab)
    PagerSlidingTab pstab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.iv_publish)
    ImageView ivPublish;

    private Fragment[] fragments;
    private String[] strings;
    private int index = 0;//记录当前页面的角标
    private Energy8InformationFragment energy8InformationFragment;
    private Energy8SpecialFragment energy8SpecialFragment;
    private Energy8TalkingFragment energy8TalkingFragment;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_information, null);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void initData() {
        initViewPager();
        energy8InformationFragment = new Energy8InformationFragment();//节能吧_精选
        energy8SpecialFragment = new Energy8SpecialFragment();//节能吧_专栏
        energy8TalkingFragment = new Energy8TalkingFragment();//节能吧_讨论
        fragments = new Fragment[]{energy8InformationFragment, energy8SpecialFragment, energy8TalkingFragment};
        strings = CommonUtil.getStringArray(R.array.tab_energy8);
        viewPager.setAdapter(new PagerSlidingAdapter(getFragmentManager(), strings, fragments));
        viewPager.setOffscreenPageLimit(3);
        pstab.setViewPager(viewPager);

    }

    private void initViewPager() {
        pstab.setTextColor(getResources().getColor(R.color.font_nine));
        pstab.setIndicatorColor(getResources().getColor(R.color.theme_color));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        index = position;
                        ivPublish.setVisibility(View.GONE);
                        break;
                    case 1:
                        index = position;
                        ivPublish.setVisibility(View.GONE);
                        break;
                    case 2:
                        index = position;
                        ivPublish.setVisibility(View.VISIBLE);
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
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public InformationPresenter getPresenter() {
        return new InformationPresenter();
    }

    @OnClick({R.id.iv_jienengba, R.id.iv_zhanhui, R.id.iv_shangcheng, R.id.iv_publish,R.id.iv_back})
    public void onClick(View view) {
    /*    switch (index) {
            case 2://发帖
                startActivity(new Intent(getContext(), PublishPostActivity.class));
                ToastUtils.showToast(mActivity,getString(R.string.developing));//提示开发中。。。
                break;
        }*/
        switch (view.getId()){
            case R.id.iv_back:
                mActivity.finish();
                break;
            case R.id.iv_publish:
              //  startActivity(new Intent(getContext(), PublishPostActivity.class));
                ToastUtils.showToast(mActivity,getString(R.string.developing));//提示开发中。。。
                break;
            default:

                break;
        }

        /*switch (view.getId()) {
            case R.id.iv_jienengba://节能吧
                startActivity(new Intent(App.application, EnergySaving8Activity.class));
                break;
            case R.id.iv_zhanhui://节能展会
                startActivity(new Intent(App.application, EnergyShowActivity.class));
                break;
            case R.id.iv_shangcheng://节能商城
                startActivity(new Intent(App.application, EnergyStoreActivity.class));
                break;
        }*/
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
