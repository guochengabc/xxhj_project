package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.UserDeviceListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.UserDeviceListPresenter;
import com.kongtiaoapp.xxhj.mvp.view.UserDeviceListView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 单选 设备列表
 */
public class UserDeviceListActivity extends BaseActivity<UserDeviceListPresenter, UserDeviceListView> implements UserDeviceListView {

    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private List<DeviceInfo.DeviceInfoList> mList = new ArrayList();
    private int currentPage = 1;

    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private UserDeviceListAdapter adapter;


    @Override
    protected int initContentView() {
        return R.layout.activity_user_device_list;
    }

    @Override
    protected void initView() {
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            DeviceInfo.DeviceInfoList item = (DeviceInfo.DeviceInfoList) adapter.getItem(position);
            Intent mIntent = new Intent();
            mIntent.putExtra("DeviceId", item.getDeviceId());
            mIntent.putExtra("Type", item.getDeviceType());
            mIntent.putExtra("Name", item.getName());
            // 设置结果，并进行传送
            setResult(RESULT_OK, mIntent);
            finish();
        });
    }

    @Override
    protected void initData() {
        tv_submit.setVisibility(View.GONE);
        adapter = new UserDeviceListAdapter(mContext, mList);
        gridView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    protected UserDeviceListPresenter getPresenter() {
        return new UserDeviceListPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(this, String.valueOf(currentPage));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                break;
            default:
                break;
        }
    }

    @Override
    public void setList(Object response) {
        DeviceInfo dict = (DeviceInfo) response;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(dict.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
