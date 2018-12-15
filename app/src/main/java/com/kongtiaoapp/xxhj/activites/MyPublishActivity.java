package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MyPublishPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyPublishView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-10-18.
 * 说明:我的发布
 */
public class MyPublishActivity extends BaseActivity<MyPublishPresenter,MyPublishView> implements MyPublishView {

    @Override
    protected int initContentView() {
        return R.layout.activity_my_publish;
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
    protected MyPublishPresenter getPresenter() {
        return new MyPublishPresenter();
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
