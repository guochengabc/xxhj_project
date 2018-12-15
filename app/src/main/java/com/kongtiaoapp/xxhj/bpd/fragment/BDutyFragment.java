package com.kongtiaoapp.xxhj.bpd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DutyInfoBean;
import com.kongtiaoapp.xxhj.bean.EventDutyBean;
import com.kongtiaoapp.xxhj.duty.activity.ZhiBanSubmitActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BDutyP;
import com.kongtiaoapp.xxhj.mvp.view.BDutyV;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HistoryActivity;
import com.kongtiaoapp.xxhj.ui.address.AssetsUtils;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.  变配电 值班管理
 */
public class BDutyFragment extends BaseFragment<BDutyP, BDutyV> implements BDutyV {

    @BindView(R.id.line_duty)
    LinearLayout line_duty;
    @BindView(R.id.txt_project_duty_person_value)
    TextView txt_project_duty_person_value;
    @BindView(R.id.txt_project_connect_tel_value)
    TextView txt_project_connect_tel_value;
    private boolean isFirst = true;
    private CustomViewPager vp;

    public BDutyFragment() {
        // Required empty public constructor
    }

    public BDutyFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (vp != null) {
            vp.setObjectForPosition(rootView, 1);
        }
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(EventDutyBean eventbusBean) {
        if (eventbusBean != null) {
            if (eventbusBean.getUserName() == null || eventbusBean.getUserName().equals("")) {
                txt_project_duty_person_value.setText("暂无");
            } else {
                txt_project_duty_person_value.setText(eventbusBean.getUserName());
            }
            if (eventbusBean.getUserPhone() == null || eventbusBean.getUserPhone().equals("")) {
                txt_project_connect_tel_value.setText("暂无");
            } else {
                txt_project_connect_tel_value.setText(eventbusBean.getUserPhone());
            }
        }
    }

    @Override
    public BDutyP getPresenter() {
        return new BDutyP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_duty__project, null);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirst) {
                isFirst = false;
                getDataForService();
            }
        }
    }

    private void getDataForService() {
        //presenter.onResume(mActivity);
        String duty = AssetsUtils.readText(mActivity, "b_duty.json");
        setList(gson.fromJson(duty, DutyInfoBean.class));
    }

    @OnClick({R.id.line_duty})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_duty:
                if (App.sp.isLeader().equals("BB")) {///ZhiBanLeaderActivity之前是跳转到值班列表    没有分页  现在用HistoryActivity替代
                    startActivity(new Intent(mActivity, HistoryActivity.class));
                } else {
                    startActivity(new Intent(mActivity, ZhiBanSubmitActivity.class));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setList(Object data) {
        DutyInfoBean bean = (DutyInfoBean) data;
        txt_project_duty_person_value.setText(bean.getResobj().getUserName());
        txt_project_connect_tel_value.setText(bean.getResobj().getPhone());
    }

    @Override
    public void list_null() {
        txt_project_duty_person_value.setText("暂无");
        txt_project_connect_tel_value.setText("暂无");
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
