package com.kongtiaoapp.xxhj.hvac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.RunningRecord_DutyAdapter;
import com.kongtiaoapp.xxhj.bean.RunningRecord_DutyBean;
import com.kongtiaoapp.xxhj.hvac.RunningRecordHVACActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningRecord_DutyPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecord_DutyView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RunningRecord_DutyFragment extends BaseFragment<RunningRecord_DutyPresenter, RunningRecord_DutyView> implements RunningRecord_DutyView {
    @BindView(R.id.listView)
    MyListView listView;

    private String start_time = "", end_time = "";
    private boolean isRefresh;
    private boolean isSearch = false;
    private int currentPage = 1;
    List<RunningRecord_DutyBean.ResobjBean> list, listAll;
    private RunningRecord_DutyAdapter adapter = null;
    private CustomViewPager vp;
    private String userId = "";
    private boolean isFirst=true;

    public RunningRecord_DutyFragment() {

    }

    public RunningRecord_DutyFragment(CustomViewPager vp) {
        this.vp = vp;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        if (vp != null) {
            vp.setObjectForPosition(view, 2);
        }
        return view;
    }

    @Override
    public RunningRecord_DutyPresenter getPresenter() {
        return new RunningRecord_DutyPresenter();
    }

    @Override
    public void initData() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_footer, null);
        ClickTextView txt_more = ((ClickTextView) view.findViewById(R.id.txt_more));
        listView.addFooterView(view);
        txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, RunningRecordHVACActivity.class));
            }
        });
        list = new ArrayList<>();
        listAll = new ArrayList<>();
        adapter = new RunningRecord_DutyAdapter(mActivity, listAll);
        listView.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (isFirst){
                isFirst=!isFirst;
                getDataForService();
            }
        }
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_running_record__duty, null);
    }


    private void getDataForService() {
        List<String> list_param = new ArrayList<>();
        list_param.add(String.valueOf(currentPage));
        list_param.add(list.size() + "");
        if (currentPage > 1) {
            if (list.size() >= 1) {
                list_param.add(list.get((list.size() - 1)).getRecordId());
            }
        }
        list_param.add(start_time);
        list_param.add(end_time);
        list_param.add(userId);
        presenter.onResume(mActivity, list_param);
    }

    @Override
    public void setList(Object data) {
        RunningRecord_DutyBean bean = (RunningRecord_DutyBean) data;
        list.clear();
        if (isRefresh) {
            listAll.clear();
        }
        list = bean.getResobj();
        for (int i = 0; i < list.size(); i++) {
            if (i < 3) {
                listAll.add(list.get(i));
            }
        }

        adapter.setList(listAll);
        adapter.notifyDataSetChanged();
        isSearch = false;
    }

    @Override
    public void list_null() {
        if (isSearch) {
            listAll.clear();
        }
        isSearch = false;
        currentPage = 1;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
