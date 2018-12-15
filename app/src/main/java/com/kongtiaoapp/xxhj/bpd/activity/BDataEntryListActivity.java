package com.kongtiaoapp.xxhj.bpd.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BPD_DataEntryListAdapter;
import com.kongtiaoapp.xxhj.bean.BPD_DataEntryBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.BDataEntryListP;
import com.kongtiaoapp.xxhj.mvp.view.BDataEntryListV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class BDataEntryListActivity extends BaseActivity<BDataEntryListP, BDataEntryListV> implements BDataEntryListV {
    private int currentPage = 1;
    @BindView(R.id.listView)
    MyListView listView;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private CustomViewPager viewPager;
    private List<BPD_DataEntryBean.ResobjBean.PowerBean> listAll = new ArrayList<BPD_DataEntryBean.ResobjBean.PowerBean>();
    private BPD_DataEntryListAdapter adapter;
    private boolean isClick = true;


    @Override
    public BDataEntryListP getPresenter() {
        return new BDataEntryListP();
    }


    @Override
    protected int initContentView() {
        return R.layout.activity_bdata_entry_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        adapter = new BPD_DataEntryListAdapter(this, listAll);
        listView.setAdapter(adapter);
        presenter.onResume(this);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        BPD_DataEntryBean.ResobjBean.PowerBean bean = (BPD_DataEntryBean.ResobjBean.PowerBean) adapter.getItem(position);

        startActivity(new Intent(this, BDataEntryActivity.class).putExtra("bpd", bean));

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object data) {
        txt_nodata.setVisibility(View.GONE);
        BPD_DataEntryBean bean = (BPD_DataEntryBean) data;
        List<BPD_DataEntryBean.ResobjBean.PowerBean> list = bean.getResobj().getPower();
        listAll.addAll(list);
        adapter.setList(listAll);
    }

    @Override
    public void list_null() {
        listAll.clear();
        txt_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
