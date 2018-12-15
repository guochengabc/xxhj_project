package com.kongtiaoapp.xxhj.recordform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ReadingSearchP;
import com.kongtiaoapp.xxhj.mvp.view.ReadingSearchV;
import com.kongtiaoapp.xxhj.recordform.fragment.RecordSearchFragment;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 录表查询
 */
public class ReadingSearchActivity extends BaseActivity<ReadingSearchP, ReadingSearchV> implements ReadingSearchV {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.mtl_search)
    MyTablayout mtl_search;//
    @BindView(R.id.vp_search)
    DefinationViewpager vp_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_reading_search;
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
        String[] title = new String[]{"暖通空调", "能源管理", "变配电"};
        String[] recordType = new String[]{"HVac", "Energy", "Power"};
        List<Fragment> listFragment = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            RecordSearchFragment searchFragment = new RecordSearchFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title[i]);
            bundle.putString("recordType", recordType[i]);
            bundle.putInt("position", i);
            searchFragment.setArguments(bundle);
            listFragment.add(searchFragment);
        }
        mtl_search.addTablayoutSetting(this, getSupportFragmentManager(), listFragment, title, vp_search);
    }

    @Override
    protected ReadingSearchP getPresenter() {
        return new ReadingSearchP();
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
