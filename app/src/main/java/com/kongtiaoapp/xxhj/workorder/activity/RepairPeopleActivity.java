package com.kongtiaoapp.xxhj.workorder.activity;

import android.os.Bundle;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.RepairPeoplePresneter;

import butterknife.ButterKnife;

/**
 * 工程师列表
 */
public class RepairPeopleActivity extends BaseActivity<RepairPeoplePresneter, BaseView> implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_repair_people;
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
    protected RepairPeoplePresneter getPresenter() {
        return new RepairPeoplePresneter();
    }

    @Override
    public void setText(Object data) {

    }
}
