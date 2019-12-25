package com.kongtiaoapp.xxhj.bpd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BPD_DataEntryListAdapter;
import com.kongtiaoapp.xxhj.bean.BPD_DataEntryBean;
import com.kongtiaoapp.xxhj.bpd.activity.BDataEntryActivity;
import com.kongtiaoapp.xxhj.bpd.activity.BDataEntryListActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BDataEntryListP;
import com.kongtiaoapp.xxhj.mvp.view.BDataEntryListV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BDataEntryListFragment extends BaseFragment<BDataEntryListP, BDataEntryListV> implements BDataEntryListV {
    private int currentPage = 1;
    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private CustomViewPager viewPager;
    private List<BPD_DataEntryBean.ResobjBean.PowerBean> listAll = new ArrayList<BPD_DataEntryBean.ResobjBean.PowerBean>();
    private BPD_DataEntryListAdapter adapter;
    private boolean isClick = true;
    private boolean isFirst = true;
    private String projectId;

    public BDataEntryListFragment() {
        // Required empty public constructor
    }

    public BDataEntryListFragment(CustomViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        if (viewPager != null) {
            viewPager.setObjectForPosition(view, 2);
        }

        return view;
    }

    @Override
    public BDataEntryListP getPresenter() {
        return new BDataEntryListP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_alarm, null);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            projectId = bundle.getString("projectId");
        }
        adapter = new BPD_DataEntryListAdapter(mActivity, listAll);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume(mActivity,projectId);
    }

    @OnClick({R.id.txt_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_more:
                if (isClick) {
                    startActivity(new Intent(mActivity, BDataEntryListActivity.class).putExtra("projectId",projectId));
                }
                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        BPD_DataEntryBean.ResobjBean.PowerBean bean = (BPD_DataEntryBean.ResobjBean.PowerBean) adapter.getItem(position);
        startActivity(new Intent(mActivity, BDataEntryActivity.class).putExtra("bpd", bean));
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object data) {
        txt_nodata.setVisibility(View.GONE);
        BPD_DataEntryBean bean = (BPD_DataEntryBean) data;
        List<BPD_DataEntryBean.ResobjBean.PowerBean> list = bean.getResobj().getPower();
        listAll.clear();
        for (int i = 0; i < list.size(); i++) {
            if (i < 3) {
                listAll.add(list.get(i));
            }
        }
        adapter.setList(listAll);
    }

    @Override
    public void list_null() {
        listAll.clear();
        txt_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
