package com.kongtiaoapp.xxhj.recordform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MReadingP;
import com.kongtiaoapp.xxhj.mvp.view.MReadingV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  模块抄表
 */
public class MReadingActivity extends BaseActivity<MReadingP,MReadingV> implements MReadingV {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_mreading;
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
    protected MReadingP getPresenter() {
        return new MReadingP();
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
