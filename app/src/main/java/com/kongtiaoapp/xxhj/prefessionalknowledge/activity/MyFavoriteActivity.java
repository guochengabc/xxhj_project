package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.os.Bundle;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.MyFavoritePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的收藏页面
 * Created by Luoye on 2016-6-15.
 */
public class MyFavoriteActivity extends BaseActivity<MyFavoritePresenter,BaseView> implements BaseView {

    @Override
    protected int initContentView() {
        return R.layout.activity_my_favorite;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected MyFavoritePresenter getPresenter() {
        return new MyFavoritePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
