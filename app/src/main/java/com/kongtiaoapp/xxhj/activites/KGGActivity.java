package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KGGActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    @BindView(R.id.tv_title)
    TextView tv_title;
    private String type = "0";//0代表2011开关柜   1代表202开关柜

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_kgg;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        if (type.equals("1")) {
            tv_title.setText("202开关柜");
        }
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

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        close_key();
        finish();
    }

    @Override
    public void setText(Object data) {

    }
}
