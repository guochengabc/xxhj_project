package com.kongtiaoapp.xxhj.bpd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BWeekReportAdapter;
import com.kongtiaoapp.xxhj.bean.BWeekReportBean;
import com.kongtiaoapp.xxhj.bpd.activity.BWeekReportActivity;
import com.kongtiaoapp.xxhj.bpd.activity.BWeekReportDetailActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BWeek_MonthReportP;
import com.kongtiaoapp.xxhj.mvp.view.BWeek_MonthReportV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.  变配电  周报/月报
 */
public class BWeek_MonthReportFragment extends BaseFragment<BWeek_MonthReportP, BWeek_MonthReportV> implements BWeek_MonthReportV {
    @BindView(R.id.listView)
    MyListView listView;
    private CustomViewPager vp;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private List<BWeekReportBean.ResobjBean.MonthDataBean> listAll = new ArrayList<BWeekReportBean.ResobjBean.MonthDataBean>();
    private BWeekReportAdapter adapter;
    private boolean isClick = true, isFirst = true;

    public BWeek_MonthReportFragment() {
        // Required empty public constructor
    }

    public BWeek_MonthReportFragment(CustomViewPager vp) {
        this.vp = vp;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        if (vp != null) {
            vp.setObjectForPosition(view, 1);
        }
        return view;
    }

    @Override
    public BWeek_MonthReportP getPresenter() {
        return new BWeek_MonthReportP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_alarm, null);
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new BWeekReportAdapter(mActivity, listAll);
        listView.setAdapter(adapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirst) {
                isFirst = !isFirst;
                getDataForServies();
            }
        }
    }

    private void getDataForServies() {
        presenter.onResume(mActivity,1+"");
    }

    @OnClick({R.id.txt_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_more:
                if (isClick) {
                    startActivity(new Intent(mActivity, BWeekReportActivity.class));
                }
                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
//        AlarmListBean.ResobjBean bean = (AlarmListBean.ResobjBean) adapter.getItem(position);
//        startActivity(new Intent(mActivity, AlarmDetailActivity.class).putExtra("alarmId", bean.getAlarmId()));
        BWeekReportBean.ResobjBean.MonthDataBean bean= (BWeekReportBean.ResobjBean.MonthDataBean) adapter.getItem(position);
        startActivity(new Intent(mActivity, BWeekReportDetailActivity.class).putExtra("reportId", bean.getReportId()).putExtra("position",position));
    }


    @Override
    public void setList(Object data) {
        txt_nodata.setVisibility(View.GONE);
        BWeekReportBean bean = (BWeekReportBean) data;
        BWeekReportBean.ResobjBean resobj = bean.getResobj();
        List<BWeekReportBean.ResobjBean.MonthDataBean> list = resobj.getMonthData();
        listAll.clear();
        for (int i = 0; i <list.size(); i++) {
            if (i < 3) {
                listAll.add(list.get(i));
            }
        }
        adapter.setList(listAll);
    }

    @Override
    public void listNull() {
        isClick = false;
        txt_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setText(Object data) {

    }
}
