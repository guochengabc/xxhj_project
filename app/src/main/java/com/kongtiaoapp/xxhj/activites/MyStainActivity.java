package com.kongtiaoapp.xxhj.activites;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.MyStainPresenter;

/**
 * Created by Shinelon on 2016/6/16.
 */
public class MyStainActivity extends BaseActivity<MyStainPresenter,BaseView> implements BaseView{
    @Override
    protected int initContentView() {
        return R.layout.activity_mystain;
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
    protected MyStainPresenter getPresenter() {
        return new MyStainPresenter();
    }

    @Override
    public void setText(Object data) {

    }
}
