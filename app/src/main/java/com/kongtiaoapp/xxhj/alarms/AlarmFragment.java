package com.kongtiaoapp.xxhj.alarms;

import android.content.Intent;
import android.os.Bundle;
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
import com.kongtiaoapp.xxhj.mvp.presenter.AlarmPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlarmView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by G_XXHJ on 2018/5/21.  报警管理
 */

public class AlarmFragment extends BaseFragment<AlarmPresenter, AlarmView> implements AlarmView {
    private int currentPage = 1;
    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private List<AlarmListBean.ResobjBean> listAll = new ArrayList<AlarmListBean.ResobjBean>();
    private AlarmListAdapter adapter;
    private boolean isClick=true;

    private CustomViewPager vp;
    private boolean isFirst=true;

    public AlarmFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (vp != null) {
            vp.setObjectForPosition(rootView, 1);
        }
        return rootView;
    }


    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_alarm, null);
    }


    protected void initListener() {
        adapter = new AlarmListAdapter(mActivity, listAll);
        listView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        initListener();

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (isFirst){
                isFirst = false;
                getDataForServies();
            }
        }
    }

    private void getDataForServies() {
        List<String> list=new ArrayList<>();
        list.add(currentPage+"");
        list.add(HttpMethod.LISTALARMINFO);
        presenter.onResume(mActivity, list);
    }

    @Override
    public AlarmPresenter getPresenter() {
        return new AlarmPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @OnClick({R.id.txt_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_more:
                if (isClick){
                    startActivity(new Intent(mActivity, AlarmActivity.class).putExtra("method", HttpMethod.LISTALARMINFO));
                }
                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        AlarmListBean.ResobjBean bean = (AlarmListBean.ResobjBean) adapter.getItem(position);
        startActivity(new Intent(mActivity, AlarmDetailActivity.class).putExtra("alarmId", bean.getAlarmId()));
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
