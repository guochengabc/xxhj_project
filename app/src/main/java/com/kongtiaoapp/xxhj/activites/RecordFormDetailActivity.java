package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.TestBean;
import com.kongtiaoapp.xxhj.login_register.fragment.Register_Company_Fragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RecordFormDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormDetailView;
import com.kongtiaoapp.xxhj.ui.address.AssetsUtils;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 记录表单具体设备
 */
public class RecordFormDetailActivity extends BaseActivity<RecordFormDetailPresenter, RecordFormDetailView> implements RecordFormDetailView {
    @BindView(R.id.tl_reFDetail)
    MyTablayout tl_reFDetail;
    @BindView(R.id.dv_reFDetail)
    DefinationViewpager dv_reFDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_record_form_detail;
    }

    @Override
    protected void initView() {
        String title[] = {"冷机组一", "冷机组二", "冷机组三", "冷机组四", "冷机组五", "冷机组六", "冷机组七", "冷机组八"};
        List<Fragment> list_fragemnt = new ArrayList<>();
        // list_fragemnt.add(new Register_Group_Fragment());
        String form = AssetsUtils.readText(this, "formcontent.json");
        TestBean bean = JSON.parseObject(form, TestBean.class);
        for (int i = 0; i < title.length; i++) {
            Log.i(TAG, "创建:" + (i + 1));
            Bundle bundle = new Bundle();
            bundle.putSerializable("key", bean);
            bundle.putInt("num",i);
            Register_Company_Fragment fragment = new Register_Company_Fragment();
            fragment.setArguments(bundle);
            list_fragemnt.add(fragment);
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        dv_reFDetail.setAdapter(adapter);
        dv_reFDetail.setNoScroll(false);
        dv_reFDetail.setOffscreenPageLimit(title.length);
        tl_reFDetail.setupWithViewPager(dv_reFDetail);
        tl_reFDetail.addTablayoutChanges(this, list_fragemnt, title, dv_reFDetail);//设置监听
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected RecordFormDetailPresenter getPresenter() {
        return new RecordFormDetailPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
