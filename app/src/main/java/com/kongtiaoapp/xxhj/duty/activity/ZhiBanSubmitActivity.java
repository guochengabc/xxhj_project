package com.kongtiaoapp.xxhj.duty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HistoryActivity;
import com.kongtiaoapp.xxhj.fragment.LatePassWorkFragment;
import com.kongtiaoapp.xxhj.fragment.LateTakeWorkFragment;
import com.kongtiaoapp.xxhj.duty.fragment.ZhiBanSubmitFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hoda on 2016/11/7 0007.
 * <p>
 * Description:提交值班记录
 */
public class ZhiBanSubmitActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tl_duty)
    MyTablayout tl_duty;
    @BindView(R.id.vp_duty)
    DefinationViewpager vp_duty;
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_zhiban_submit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String title[] = {"新建交班", "最近交班", "最近接班"};
        List<Fragment> list_fragemnt = new ArrayList<>();
        list_fragemnt.add(new ZhiBanSubmitFragment());
        list_fragemnt.add(new LatePassWorkFragment());//最近交班
        list_fragemnt.add(new LateTakeWorkFragment());//最近接班
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vp_duty.setAdapter(adapter);
        vp_duty.setNoScroll(false);
        vp_duty.setOffscreenPageLimit(3);
        tl_duty.setupWithViewPager(vp_duty);
        tl_duty.addTablayoutChanges(this,list_fragemnt,title, vp_duty);//设置监听

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

    @OnClick({R.id.iv_back,R.id.iv_search})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                startActivity(new Intent(this, HistoryActivity.class));
                break;
        }
    }
    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void setText(Object data) {

    }
}

