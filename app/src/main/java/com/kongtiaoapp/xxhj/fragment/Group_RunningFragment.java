package com.kongtiaoapp.xxhj.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.Group_SurveyActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Group_RunningPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Group_RunningView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Group_RunningFragment extends BaseFragment<Group_RunningPresenter,Group_RunningView> implements Group_RunningView{
    private GetFragment getFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Group_RunningPresenter getPresenter() {
        return new Group_RunningPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getFragment = (GetFragment) context;
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_group__running, null);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @OnClick({R.id.btn_gp_survey, R.id.btn_gp_information, R.id.btn_gp_message, R.id.btn_gp_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gp_survey:
                Intent intent = new Intent(mActivity, Group_SurveyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gp_information:
                getFragment.getIndex(1);
                break;
            case R.id.btn_gp_message:
                getFragment.getIndex(2);
                break;
            case R.id.btn_gp_mine:
                getFragment.getIndex(3);
                break;
            default:
                getFragment.getIndex(0);
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    public interface GetFragment {
        void getIndex(int index);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
