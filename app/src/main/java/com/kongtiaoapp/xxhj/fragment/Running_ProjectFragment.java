package com.kongtiaoapp.xxhj.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.Add_AUTO_DeviceRunningActivity;
import com.kongtiaoapp.xxhj.adapter.RunningInfo_GroupAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;
import com.kongtiaoapp.xxhj.bean.Running_SpredBean;
import com.kongtiaoapp.xxhj.hvac.Running_ProjectActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Running_ProjectPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Running_ProjectView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Running_ProjectFragment extends BaseFragment<Running_ProjectPresenter, Running_ProjectView> implements Running_ProjectView {
    @BindView(R.id.elv_runninginfo)
    MyExpandableListView elv_runninginfo;
    private CustomViewPager viewPager;
    private boolean dialog = true;
    private RunningInfo_GroupAdapter adapter;
    List<RunDevice_StateBean.ResobjBean> list_reso = new ArrayList<>();
    // 这个数组是用来存储一级item的点击次数的，根据点击次数设置一级标签的选中、为选中状态
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;
    private boolean isFirstResume = true,isClick=false;
    private String hvac="";
    private ClickTextView txt_more;

    public Running_ProjectFragment() {
    }

    public Running_ProjectFragment(CustomViewPager viewPager) {
        this.viewPager = viewPager;
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        if (viewPager != null) {
            viewPager.setObjectForPosition(view, 0);
        }
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public Running_ProjectPresenter getPresenter() {
        return new Running_ProjectPresenter();
    }

    @Override
    public void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            hvac = arguments.getString("hvac");
            if (hvac==null){
                hvac="";
            }
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_footer, null);
            txt_more = ((ClickTextView) view.findViewById(R.id.txt_more));
            elv_runninginfo.addFooterView(view);
            txt_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isClick){
                        startActivity(new Intent(mActivity, Running_ProjectActivity.class));
                    }
                }
            });
        }
        adapter = new RunningInfo_GroupAdapter(mActivity, list_reso);
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
                    startActivity(new Intent(mActivity, Add_AUTO_DeviceRunningActivity.class).
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
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_running__project, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            getDataForService();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstResume == false) {
                getDataForService();
            }
        }
    }

    private void getDataForService() {
        presenter.onResume(mActivity, dialog);
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
        isClick=true;
        RunDevice_StateBean bean = (RunDevice_StateBean) data;
        list_reso.clear();
        list_reso = bean.getResobj();
        List<RunDevice_StateBean.ResobjBean> list_resoAll = new ArrayList<>();
        if (hvac.equals("true")) {
            list_resoAll.add(list_reso.get(0));
            adapter.setList(list_resoAll);
        } else {
            adapter.setList(list_reso);
        }

        if (adapter.getGroupCount() > 0) {//父条目大于0个默认  展开
            elv_runninginfo.expandGroup(0);
        }


    }

    @Override
    public void setText(Object data) {
        dialog = false;
        String info= (String) data;
        if (info.equals("no_data")){
            txt_more.setText("暂无数据");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
