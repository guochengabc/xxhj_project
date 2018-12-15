package com.kongtiaoapp.xxhj.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfo_GroupAdapter;
import com.kongtiaoapp.xxhj.bean.DeviceInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Device_ProjectPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Device_ProjectView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备信息
 */
public class Device_ProjectFragment extends BaseFragment<Device_ProjectPresenter,Device_ProjectView> implements Device_ProjectView {
    @BindView(R.id.elv_deviceinfo)
    MyExpandableListView elv_deviceinfo;
    private List<DeviceInfoBean.ResobjBean> list_reso = new ArrayList<>();
    private DeviceInfo_GroupAdapter adapter;
    // 这个数组是用来存储一级item的点击次数的，根据点击次数设置一级标签的选中、为选中状态
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;
    private boolean dialog = true;
    public CustomViewPager vp;


    public Device_ProjectFragment (CustomViewPager vp) {
        this.vp = vp;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        vp.setObjectForPosition(view, 1);
        return view;
    }

    @Override
    public Device_ProjectPresenter getPresenter() {
        return new Device_ProjectPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_device__project, null);
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new DeviceInfo_GroupAdapter(mActivity, list_reso);
        elv_deviceinfo.setGroupIndicator(null);
        elv_deviceinfo.setAdapter(adapter);
        // 设置一级item点击的监听器
        elv_deviceinfo.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            group_checked[groupPosition] = group_checked[groupPosition] + 1;
            adapter.setCheck(group_checked[groupPosition],groupPosition);
            // 刷新界面
            ((DeviceInfo_GroupAdapter) adapter).notifyDataSetChanged();
            return false;
        });

        // 设置二级item点击的监听器
        elv_deviceinfo.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // 将被点击的一丶二级标签的位置记录下来
            child_groupId = groupPosition;
            child_childId = childPosition;
            // 刷新界面
            ((DeviceInfo_GroupAdapter) adapter).notifyDataSetChanged();
            return false;
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getDataForService();
        }
    }

    private void getDataForService() {
        presenter.onResume(mActivity,dialog);
    }

    @Override
    public void setList(Object data) {
        DeviceInfoBean bean= (DeviceInfoBean) data;
        list_reso.clear();
        list_reso = bean.getResobj();
        adapter.setList(list_reso);
    }

    @Override
    public void setText(Object data) {
        dialog = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
