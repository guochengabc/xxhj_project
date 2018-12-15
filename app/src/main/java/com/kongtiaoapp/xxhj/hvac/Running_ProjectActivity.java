package com.kongtiaoapp.xxhj.hvac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.Add_AUTO_DeviceRunningActivity;
import com.kongtiaoapp.xxhj.adapter.RunningInfo_GroupAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;
import com.kongtiaoapp.xxhj.bean.Running_SpredBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Running_ProjectPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Running_ProjectView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Running_ProjectActivity extends BaseActivity<Running_ProjectPresenter, Running_ProjectView> implements Running_ProjectView {
    @BindView(R.id.elv_runninginfo)
    ExpandableListView elv_runninginfo;
    private boolean dialog = true;
    private RunningInfo_GroupAdapter adapter;
    List<RunDevice_StateBean.ResobjBean> list_reso = new ArrayList<>();
    // 这个数组是用来存储一级item的点击次数的，根据点击次数设置一级标签的选中、为选中状态
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(Running_SpredBean eventbusBean) {
        if (eventbusBean != null) {
            if (eventbusBean.getRunningIsSpred().equals("0")) {//收缩
                for (int i = 1; i < adapter.getGroupCount(); i++) {
                    elv_runninginfo.collapseGroup(i);
                }
            } else if (eventbusBean.getRunningIsSpred().equals("1")) {//展开
                for (int i = 1; i < adapter.getGroupCount(); i++) {
                    elv_runninginfo.expandGroup(i);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_running__project;
    }

    @Override
    protected void initView() {
        getDataForService();
    }

    @Override
    protected void initListener() {
        adapter = new RunningInfo_GroupAdapter(this, list_reso);
        elv_runninginfo.setGroupIndicator(null);
        elv_runninginfo.setAdapter(adapter);
        // 设置一级item点击的监听器
        elv_runninginfo.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if (groupPosition == 0) {//代表第0个位置不可点击
                    return true;
                }
                group_checked[groupPosition] = group_checked[groupPosition] + 1;
                adapter.setCheck(group_checked[groupPosition], groupPosition);
                // 刷新界面
                ((RunningInfo_GroupAdapter) adapter).notifyDataSetChanged();
                return false;
            }
        });

        // 设置二级item点击的监听器
        elv_runninginfo.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // 将被点击的一丶二级标签的位置记录下来
                child_groupId = groupPosition;
                child_childId = childPosition;
                RunDevice_StateBean.ResobjBean.ColDataBean colDataBean = list_reso.get(child_groupId).getColData().get(child_childId);
                if (child_childId != 0) {//下面的这一句不能删除
                    startActivity(new Intent(Running_ProjectActivity.this, Add_AUTO_DeviceRunningActivity.class).
                            putExtra("type", colDataBean.getType())
                            .putExtra(ConstantValue.AUTO, true).putExtra("deviceId", colDataBean.getDeviceId()).putExtra("deviceName",
                                    colDataBean.getK0()));
                    //  startActivity(new Intent(mActivity, SEquitmentStatusActivity.class));
                }

                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected Running_ProjectPresenter getPresenter() {
        return new Running_ProjectPresenter();
    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        finish();
    }

    private void getDataForService() {
        presenter.onResume(this, dialog);
      /* String runinfo= AssetsUtils.readText(mActivity,"hvacs.json");
        RunDevice_StateBean bean = JSON.parseObject(runinfo, RunDevice_StateBean.class);
        list_reso.clear();
        list_reso = bean.getResobj();
        adapter.setList(list_reso);
        if (adapter.getGroupCount() > 0) {//父条目大于0个默认展开
            elv_runninginfo.expandGroup(0);
        }*/
    }

    @Override
    public void setList(Object data) {
        RunDevice_StateBean bean = (RunDevice_StateBean) data;
        list_reso.clear();
        list_reso = bean.getResobj();
        adapter.setList(list_reso);

        if (adapter.getGroupCount() > 0) {//父条目大于0个默认  展开
            elv_runninginfo.expandGroup(0);
        }


    }

    @Override
    public void setText(Object data) {
        dialog = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
