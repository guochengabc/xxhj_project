package com.kongtiaoapp.xxhj.recordform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AdditionalReadingP;
import com.kongtiaoapp.xxhj.mvp.view.AdditionalReadingV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 补录模块
 */
public class AdditionalReadingActivity extends BaseActivity<AdditionalReadingP, AdditionalReadingV> implements AdditionalReadingV {
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_additional_reading;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            if (title != null) {
                tv_title.setText(title);
            }
        }
    }

    @Override
    protected AdditionalReadingP getPresenter() {
        return new AdditionalReadingP();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
