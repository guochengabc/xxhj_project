package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_asset;
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

    @Override
    public void setText(Object data) {

    }

    @OnClick({R.id.rl_wuye_info, R.id.device_info, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_wuye_info://项目信息
                startActivity(new Intent(App.application, ProjectInfoActivity.class).putExtra("type", 1));
                break;
            case R.id.device_info://设备信息
                startActivity(new Intent(App.application, DeviceInfoActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
            default:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
