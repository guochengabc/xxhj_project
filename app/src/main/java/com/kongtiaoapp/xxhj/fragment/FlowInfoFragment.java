package com.kongtiaoapp.xxhj.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.FlowInfoAdapter;
import com.kongtiaoapp.xxhj.bean.FlowInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.FlowInfoPresneter;
import com.kongtiaoapp.xxhj.mvp.view.FlowInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 流程追踪
 */
public class FlowInfoFragment extends BaseFragment<FlowInfoPresneter, FlowInfoView> implements FlowInfoView {


    @BindView(R.id.lv_flowinfo)
    ListView lv_flowinfo;
    private String dispatchId = "";
    private List<FlowInfoBean.ResobjBean> list = new ArrayList<>();
    private FlowInfoAdapter adapter;
    private boolean isFirst = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        dispatchId = bundle.getString("dispatchId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new FlowInfoAdapter(list, mActivity);
        lv_flowinfo.setAdapter(adapter);
        presenter.onResume(mActivity, dispatchId);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            presenter.onResume(mActivity, dispatchId);
        } else {

        }
    }

    @Override
    public FlowInfoPresneter getPresenter() {
        return new FlowInfoPresneter();
    }

    @Override
    protected View initView() {
        View inflate = View.inflate(mActivity, R.layout.fragment_flowinfo, null);
        return inflate;

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object data) {
        list= (List<FlowInfoBean.ResobjBean>) data;
        adapter.setList(list);
    }
}
