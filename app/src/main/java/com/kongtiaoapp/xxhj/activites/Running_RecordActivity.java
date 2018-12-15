package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.fragment.Export_RecorderFragment;
import com.kongtiaoapp.xxhj.hvac.fragment.RunningRecord_DutyFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Running_RecordPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Running_RecordView;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Running_RecordActivity extends BaseActivity<Running_RecordPresenter,Running_RecordView>implements Running_RecordView {
    @BindView(R.id.tl_recorder)
    MyTablayout tl_recorder;
    @BindView(R.id.dv_recorder)
    DefinationViewpager dv_recorder;

    @Override
    protected Running_RecordPresenter getPresenter() {
        return new Running_RecordPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_running__report;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(new Export_RecorderFragment());
        list_fragment.add(new RunningRecord_DutyFragment());
        String title[] = {"导出记录", "运行记录"};
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragment);
        dv_recorder.setAdapter(adapter);
        dv_recorder.setNoScroll(false);
        tl_recorder.setupWithViewPager(dv_recorder);
        tl_recorder.addTablayoutChanges(this, list_fragment, title, dv_recorder);//设置监听

    }

    @OnClick({R.id.iv_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tl_recorder.removeOnLayoutChangeListener(null);
    }

    @Override
    public void setText(Object data) {

    }
}
