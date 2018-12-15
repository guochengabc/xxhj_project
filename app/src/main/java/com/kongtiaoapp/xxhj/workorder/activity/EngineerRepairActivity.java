package com.kongtiaoapp.xxhj.workorder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.workorder.fragment.EngineerRepairFragment;
import com.kongtiaoapp.xxhj.fragment.FlowInfoFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EngineerRepairActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    @BindView(R.id.tl_workRepair)
    MyTablayout tl_workRepair;
    @BindView(R.id.vp_workRepair)
    DefinationViewpager vp_workRepair;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            String dispatchId = intent.getStringExtra("dispatchId");
        }
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_engineerepairs;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String dispatchId = null;
        if (intent != null) {
            dispatchId = intent.getStringExtra("dispatchId");
        }
        String title[] = {"报修信息", "流程信息"};
        List<Fragment> list_fragemnt = new ArrayList<>();
        EngineerRepairFragment egFragment = new EngineerRepairFragment();
        FlowInfoFragment flFragment = new FlowInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("dispatchId", dispatchId);
        egFragment.setArguments(bundle);
        flFragment.setArguments(bundle);
        list_fragemnt.add(egFragment);
        list_fragemnt.add(flFragment);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vp_workRepair.setAdapter(adapter);
        vp_workRepair.setNoScroll(false);
        tl_workRepair.setupWithViewPager(vp_workRepair);
        tl_workRepair.addTablayoutChanges(this, list_fragemnt, title, vp_workRepair);//设置监听
    }

    @OnClick({R.id.iv_back})
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return new BasePresenterLpl() {
            @Override
            protected BaseModule getModel() {
                return new BaseModule() {
                    @Override
                    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

                    }
                };
            }
        };
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
