package com.kongtiaoapp.xxhj.workorder.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.fragment.MaintanenceFragment;
import com.kongtiaoapp.xxhj.fragment.PatrolFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.WorkOrderRepairPresenter;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderRepairView;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkOrderRepairActivity extends BaseActivity<WorkOrderRepairPresenter, WorkOrderRepairView> implements WorkOrderRepairView {
    @BindView(R.id.myTablayout)
    MyTablayout myTablayout;//滑动卡
    @BindView(R.id.myViewpager)
    DefinationViewpager myViewpager;//盛装fragment的容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_work_order_repair;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String title[] = {"巡视", "维保"};
        List<Fragment> list_fragemnt = new ArrayList<>();
        list_fragemnt.add(new PatrolFragment());
        list_fragemnt.add(new MaintanenceFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        myViewpager.setAdapter(adapter);
        myViewpager.setNoScroll(false);
        myTablayout.setupWithViewPager(myViewpager);
        myTablayout.addTablayoutChanges(this, list_fragemnt, title, myViewpager);//设置监听
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
               finish();
               break;
        }
    }

    @Override
    protected WorkOrderRepairPresenter getPresenter() {
        return new WorkOrderRepairPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
