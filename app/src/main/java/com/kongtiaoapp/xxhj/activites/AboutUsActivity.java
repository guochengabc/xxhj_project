package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetAboutUs;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AboutAsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AboutUsView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS-PC on 2016/6/17.
 * 关于我们页面
 */
public class AboutUsActivity extends BaseActivity<AboutAsPresenter, AboutUsView> implements AboutUsView {

    @BindView(R.id.aboutUs_tv_info)
    TextView mInfo;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.line_about)
    LinearLayout line_about;

    @OnClick({R.id.iv_back})
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮
                finish();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_about_us;
    }

    /*初始化xutils*/
    @Override
    protected void initView() {

    }

    /*设置监听*/
    @Override
    protected void initListener() {

    }

    /*初始化数据，并获取数据*/
    @Override
    protected void initData() {
        presenter.OnResume(this);

    }

    /*初始化Presenter*/
    @Override
    protected AboutAsPresenter getPresenter() {
        return new AboutAsPresenter();
    }

    /*加载会展图标*/
    @Override
    public void loadImageView(Object data) {
        Picasso.with(this).load(data.toString()).placeholder(R.mipmap.logo).into(ivLogo);
    }


    @Override
    public void setText(Object data) {
        mInfo.setText(((GetAboutUs.AboutUs) data).getIntroduce());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ivLogo.setBackgroundResource(0);
        line_about.setBackgroundResource(0);
        System.gc();
    }
}
