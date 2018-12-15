package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ImageScaleAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.ImageScalePresenter;
import com.kongtiaoapp.xxhj.ui.view.HackyViewPager;
import com.kongtiaoapp.xxhj.utils.DensityUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 大图展示页面
 */
public class ImageScaleActivity extends BaseActivity<ImageScalePresenter, BaseView> implements BaseView {

    @BindView(R.id.viewPager)
    HackyViewPager viewPager;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;

    public static ImageScaleActivity instance = null;
    private ArrayList<String> urlList;

    @Override
    protected int initContentView() {
        instance = this;
        return R.layout.activity_image_scale;
    }

    @Override
    protected void initView() {

        int currentItem = getIntent().getIntExtra("currentItem", 0);
        urlList = new ArrayList<String>();
        String[] urls = getIntent().getStringArrayExtra("urls");
        for (String url : urls) {
            urlList.add(url);
        }
        ImageScaleAdapter adapter = new ImageScaleAdapter(mContext, urlList);
        viewPager.setAdapter(adapter);
        //设置 默认选中的条目
        viewPager.setCurrentItem(currentItem);
        initViewPager();
        initDot();
        updateDot();
    }

    @Override
    protected void initListener() {
        Logger.e("hello");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ImageScalePresenter getPresenter() {
        return new ImageScalePresenter();
    }

    private void initViewPager() {


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                updateDot();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /**
     * 根据当前page来显示不同的文字和点
     */
    private void updateDot() {
        int currentPage = viewPager.getCurrentItem();
        // 更新点
        // 遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i = 0; i < llDot.getChildCount(); i++) {
            llDot.getChildAt(i).setEnabled(i == currentPage);
        }
    }

    /**
     * 动态添加点
     */
    private void initDot() {
        for (int i = 0; i < urlList.size(); i++) {
            View view = new View(mContext);
            LayoutParams params = new LayoutParams(DensityUtils.dp2px(mContext,
                    10), DensityUtils.dp2px(mContext, 2));
            params.leftMargin = DensityUtils.dp2px(mContext, 5);// 给除了第一个点之外的点都加上marginLeft
            view.setLayoutParams(params);// 设置宽高
            view.setBackgroundResource(R.drawable.selector_dot1);// 设置背景图片
            llDot.addView(view);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (urlList != null) {
            urlList.clear();
            urlList = null;
        }
    }

    @Override
    public void setText(Object data) {

    }
}
