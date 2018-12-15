package com.kongtiaoapp.xxhj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.OverAllEvaluateAdapter;
import com.kongtiaoapp.xxhj.bean.RunningGuigeBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningGuidePresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningGuideView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunningGuideFragment extends BaseFragment<RunningGuidePresenter, RunningGuideView> implements RunningGuideView {
    @BindView(R.id.lv_oae)
    MyListView lv_oae;
    private CustomViewPager vp;
    private List<RunningGuigeBean.ResobjBean> list = new ArrayList<>();
    private OverAllEvaluateAdapter adapter;
    private boolean isFirst=true;

    public RunningGuideFragment() {
    }

    public RunningGuideFragment(CustomViewPager vp) {
        this.vp = vp;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (vp != null) {
            vp.setObjectForPosition(rootView, 2);
        }
        return rootView;
    }

    @Override
    public RunningGuidePresenter getPresenter() {
        return new RunningGuidePresenter();
    }


    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_runningguide, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        adapter = new OverAllEvaluateAdapter(mActivity, list);
        lv_oae.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isFirst = !isFirst;
            presenter.onResume(mActivity);
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void getList(Object data) {
        adapter.setList((List<RunningGuigeBean.ResobjBean>) data);
    }

    @Override
    public void list_null() {
        ToastUtils.showToast(mActivity, getString(R.string.no_data));
    }
}
