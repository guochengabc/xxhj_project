package com.kongtiaoapp.xxhj.activites;


import android.app.Activity;
import android.view.View;

import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import butterknife.OnClick;

/**
 * 整体运行评价
 */
public class RunningEvaluateActivity extends BaseActivity {

    @Override
    protected int initContentView() {
        return R.layout.activity_running_evaluate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenterLpl getPresenter() {
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

    @OnClick({R.id.iv_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
