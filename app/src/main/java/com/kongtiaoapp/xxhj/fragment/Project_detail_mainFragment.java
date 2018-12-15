package com.kongtiaoapp.xxhj.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.Group_Project_DetailsActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

/**
 * create an instance of this fragment.
 */
public class Project_detail_mainFragment extends BaseFragment<BasePresenterLpl, BaseView> implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            startActivity(new Intent(mActivity, Group_Project_DetailsActivity.class).putExtra("projectId", App.sp.getProjectId()));
        }
    }

    @Override
    public BasePresenterLpl getPresenter() {
        return new BasePresenterLpl() {
            @Override
            protected BaseModule getModel() {
                return new BaseModule() {
                    @Override
                    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

                    }
                };
            }
        };
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_project_detail_main, null);
    }


    @Override
    public void setText(Object data) {

    }
}
