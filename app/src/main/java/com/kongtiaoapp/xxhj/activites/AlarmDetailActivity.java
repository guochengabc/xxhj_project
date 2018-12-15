package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.AlarmDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AlarmDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlarmDetailViewq;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 报警详情
 */
public class AlarmDetailActivity extends BaseActivity<AlarmDetailPresenter, AlarmDetailViewq> implements AlarmDetailViewq {
    @BindView(R.id.txt_projectName)
    TextView txt_projectName;//项目名
    @BindView(R.id.txt_deviceType)
    TextView txt_deviceType;//设备类型
    @BindView(R.id.txt_alarmStatus)
    TextView txt_alarmStatus;//报警程度
    @BindView(R.id.txt_breakDs)
    TextView txt_breakDs;//报警描述
    @BindView(R.id.txt_breakTime)
    TextView txt_breakTime;//出现故障时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_alarm_detail;
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
        String alarmId = intent.getStringExtra("alarmId");
        if (alarmId != null) {
            presenter.onResume(this, alarmId);
        }
    }

    @Override
    protected AlarmDetailPresenter getPresenter() {
        return new AlarmDetailPresenter();
    }

    @Override
    public void setText(Object data) {
        AlarmDetailBean.ResobjBean bean = (AlarmDetailBean.ResobjBean) data;
        txt_projectName.setText(bean.getProjectName());
        txt_deviceType.setText(bean.getDeviceName());
        txt_alarmStatus.setText(bean.getAlarmTypeName());
        txt_breakDs.setText(bean.getKeyWords());
        txt_breakTime.setText(bean.getTime());
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }

    }
}
