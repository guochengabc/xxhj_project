package com.kongtiaoapp.xxhj.login_register.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.login_register.fragment.Register_Company_Fragment;
import com.kongtiaoapp.xxhj.login_register.fragment.Register_Personal_Fragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.RegisterPresenter;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter,BaseView> implements BaseView{
    @BindView(R.id.tablayout_register)
    MyTablayout tablayout_register;
    @BindView(R.id.register_viewpager)
    DefinationViewpager vpg_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String title[] = {"企业账户", "个人账户"};
        List<Fragment> list_fragemnt = new ArrayList<>();
       // list_fragemnt.add(new Register_Group_Fragment());
        list_fragemnt.add(new Register_Company_Fragment());
        list_fragemnt.add(new Register_Personal_Fragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_fragment.setAdapter(adapter);
        vpg_fragment.setNoScroll(false);
        tablayout_register.setupWithViewPager(vpg_fragment);
        tablayout_register.addTablayoutChanges(this,list_fragemnt,title, vpg_fragment);//设置监听
    }

    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }

    @OnClick(R.id.iv_back)
    void onClick(ImageView img) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setText(Object data) {

    }
}
