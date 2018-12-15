package com.kongtiaoapp.xxhj.activites;

import android.view.View;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoParamAdapter;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.DvInfoDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.DvInfoDetailView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 设备信息详情页面
 */
public class DeviceInfoDetailActivity extends BaseActivity<DvInfoDetailPresenter, DvInfoDetailView> implements DvInfoDetailView {

    @ViewInject(R.id.listview)
    private ListView mListview;//列表

    private String dictCode;
    private String type;
    private String deviceId;
    private List<DeviceParam.DeviceParamList> deviceParamLists = new ArrayList<>();
    private DeviceInfoParamAdapter adapter;

    @Event(value = {R.id.iv_back})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /*接收传过来的值*/
    @Override
    protected int initContentView() {
        dictCode = getIntent().getStringExtra("dictCode");
        type = getIntent().getStringExtra("type");
        deviceId = getIntent().getStringExtra("deviceId");
        return R.layout.activity_add_device_info;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
    }

    //设置监听事件
    @Override
    protected void initListener() {

    }

    /*初始化数据*/
    @Override
    protected void initData() {
        adapter = new DeviceInfoParamAdapter(deviceParamLists, mContext);
        mListview.setAdapter(adapter);
        presenter.onResume(this, deviceId);
    }

    @Override
    protected DvInfoDetailPresenter getPresenter() {
        return new DvInfoDetailPresenter();
    }

    /*回传过来的数据*/
    @Override
    public void setList(Object data) {
        DeviceParam dict = (DeviceParam) data;
        deviceParamLists.clear();
        deviceParamLists.addAll(dict.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (deviceParamLists != null) {
            deviceParamLists.clear();
            deviceParamLists = null;
        }
    }
}
