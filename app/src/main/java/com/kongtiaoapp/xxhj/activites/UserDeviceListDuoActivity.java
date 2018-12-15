package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.UserDeviceListDuoAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.UserDeviceListDuoPresenter;
import com.kongtiaoapp.xxhj.mvp.view.UserDeviceListDuoView;
import com.kongtiaoapp.xxhj.mvp.view.UserListView;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 多选设备列表
 */
public class UserDeviceListDuoActivity extends BaseActivity<UserDeviceListDuoPresenter, UserListView> implements UserDeviceListDuoView {

    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private List<DeviceInfo.DeviceInfoList> mList = new ArrayList();
    private ArrayList<DeviceInfo.DeviceInfoList> mSelectList = new ArrayList();
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
    private UserDeviceListDuoAdapter adapter;


    @Override
    protected int initContentView() {
        //每次获取数据都先清空，然后再进行赋值
        mSelectList.clear();
        mSelectList.addAll((ArrayList<DeviceInfo.DeviceInfoList>) getIntent().getSerializableExtra("list"));
        return R.layout.activity_user_device_list;
    }

    @Override
    protected void initView() {
        //        springView.setHeader(new RotationHeader(mContext));
        //        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        currentPage = 1;
                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            DeviceInfo.DeviceInfoList item = (DeviceInfo.DeviceInfoList) adapter.getItem(position);
            mList.get(position).setSelected(!mList.get(position).isSelected());
            adapter.notifyDataSetChanged();
        });
        tvSubmit.setOnClickListener(v -> {
            mSelectList.clear();
            for (DeviceInfo.DeviceInfoList item : mList) {
                if (item.isSelected()) {
                    mSelectList.add(item);
                }
            }
            Intent mIntent = new Intent();
            mIntent.putExtra("list", (Serializable) mSelectList);
            // 设置结果，并进行传送
            setResult(RESULT_OK, mIntent);
            finish();
        });
    }

    @Override
    protected void initData() {
        adapter = new UserDeviceListDuoAdapter(mContext, mList);
        gridView.setAdapter(adapter);
        getDataForServers();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected UserDeviceListDuoPresenter getPresenter() {
        return new UserDeviceListDuoPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(this, getIntent().getStringExtra("projectType"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        if (mSelectList != null) {
            mSelectList.clear();
            mSelectList = null;
        }
    }

    @Override
    public void setLsit(Object response) {
        DeviceInfo dict = (DeviceInfo) response;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(dict.getResobj());
        for (int i = 0; i < mSelectList.size(); i++) {
            for (int j = 0; j < mList.size(); j++) {
                if (mSelectList.get(i).getDeviceId().equals(mList.get(j).getDeviceId())) {
                    mList.get(j).setSelected(true);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object data) {

    }
}
