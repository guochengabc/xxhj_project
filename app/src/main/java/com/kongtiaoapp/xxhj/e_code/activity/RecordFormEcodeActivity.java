package com.kongtiaoapp.xxhj.e_code.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.e_code.fragment.AddDeviceRunning_EcodeFragment;
import com.kongtiaoapp.xxhj.e_code.fragment.InspectionFragment;
import com.kongtiaoapp.xxhj.e_code.fragment.NameplateInformationFragment;
import com.kongtiaoapp.xxhj.e_code.fragment.WeiBaoRecordFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordFormEcodeActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_weiBao)
    TextView tv_weiBao;//添加维保
    @BindView(R.id.mtl_recordForm)
    MyTablayout mtl_recordForm;//滑动卡
    @BindView(R.id.dvp_recordForm)
    DefinationViewpager dvp_recordForm;//盛装fragment的容器
    private String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_record_form_ecode;
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
        if (intent != null) {
            DeviceNameE_CodeBean deviceNameE_codeBean = (DeviceNameE_CodeBean) intent.getSerializableExtra("device");
            deviceId = deviceNameE_codeBean.getDeviceId();
            tv_title.setText(deviceNameE_codeBean.getName());
            String title[] = {"设备信息", "巡视记录", "运行记录","维保记录"};
            List<Fragment> list_fragemnt = new ArrayList<>();
            NameplateInformationFragment nameplateInformationFragment = new NameplateInformationFragment();
            InspectionFragment inspectionFragment = new InspectionFragment();
            AddDeviceRunning_EcodeFragment fragmentRunning = new AddDeviceRunning_EcodeFragment();
            WeiBaoRecordFragment weiBaoRecordFragment=new WeiBaoRecordFragment();
            list_fragemnt.add(nameplateInformationFragment);
            list_fragemnt.add(inspectionFragment);
            list_fragemnt.add(fragmentRunning);
            list_fragemnt.add(weiBaoRecordFragment);
            MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
            dvp_recordForm.setAdapter(adapter);
            dvp_recordForm.setNoScroll(false);
            mtl_recordForm.setupWithViewPager(dvp_recordForm);
            mtl_recordForm.addTablayoutChanges(this, list_fragemnt, title, dvp_recordForm,tv_weiBao);//设置监听
            dvp_recordForm.setOffscreenPageLimit(4);
            Bundle bundle = new Bundle();
            bundle.putSerializable("device", deviceNameE_codeBean);
            nameplateInformationFragment.setArguments(bundle);
            fragmentRunning.setArguments(bundle);
            inspectionFragment.setArguments(bundle);
            weiBaoRecordFragment.setArguments(bundle);

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

    @OnClick({R.id.iv_back,R.id.tv_weiBao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_weiBao:
                if (deviceId==null){
                    return;
                }
                startActivity(new Intent(this,AddWeiBaoActivity.class).putExtra("deviceId",deviceId));
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
