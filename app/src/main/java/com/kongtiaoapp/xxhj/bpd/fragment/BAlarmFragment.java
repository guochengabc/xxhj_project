package com.kongtiaoapp.xxhj.bpd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AlarmActivity;
import com.kongtiaoapp.xxhj.activites.AlarmDetailActivity;
import com.kongtiaoapp.xxhj.adapter.AlarmListAdapter;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.AlarmListBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BAlarmP;
import com.kongtiaoapp.xxhj.mvp.view.BAlarmV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.  变配电  报警管理
 */
public class BAlarmFragment extends BaseFragment<BAlarmP,BAlarmV> implements BAlarmV {

    private int currentPage = 1;
    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private CustomViewPager viewPager;
    private List<AlarmListBean.ResobjBean> listAll = new ArrayList<AlarmListBean.ResobjBean>();
    private AlarmListAdapter adapter;
    private boolean isClick=true;
    public BAlarmFragment() {
        // Required empty public constructor
    }

    public BAlarmFragment(CustomViewPager viewPager) {
        this.viewPager = viewPager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (viewPager != null) {
            viewPager.setObjectForPosition(rootView, 0);
        }
        return rootView;
    }

    @Override
    public BAlarmP getPresenter() {
        return new BAlarmP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_alarm, null);
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new AlarmListAdapter(mActivity, listAll);
        listView.setAdapter(adapter);
        getDataForServies();
    }

    private void getDataForServies() {
        //presenter.onResume(mActivity, currentPage + "");
       presenter.onResume(mActivity,1+"");
    }

    @Override
    public void setText(Object data) {

    }

    @OnClick({R.id.txt_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_more:
                if (isClick){
                    startActivity(new Intent(mActivity, AlarmActivity.class).putExtra("method", HttpMethod.LISTPOWERALARMINFO));
                }
                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        AlarmListBean.ResobjBean bean = (AlarmListBean.ResobjBean) adapter.getItem(position);
        startActivity(new Intent(mActivity, AlarmDetailActivity.class).putExtra("alarmId", bean.getAlarmId()).putExtra("method","bpd"));
    }


    @Override
    public void setList(Object data) {
        txt_nodata.setVisibility(View.GONE);
        AlarmListBean bean = (AlarmListBean) data;
        List<AlarmListBean.ResobjBean> list = bean.getResobj();
        listAll.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i < 3) {
                listAll.add(list.get(i));
            }
        }
        adapter.setList(listAll);

    }

    @Override
    public void listNull() {
        isClick=false;
        txt_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
