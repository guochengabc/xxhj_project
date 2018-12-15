package com.kongtiaoapp.xxhj.energymetering.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceTitleBean;
import com.kongtiaoapp.xxhj.energymetering.fragment.EnergyDeviceListFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyRecordListP;
import com.kongtiaoapp.xxhj.mvp.view.EnergyRecordListV;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnergyRecordListActivity extends BaseActivity<EnergyRecordListP, EnergyRecordListV> implements EnergyRecordListV {
    @BindView(R.id.mtl_recordForm)
    MyTablayout mtl_recordForm;//滑动卡
    @BindView(R.id.dvp_recordForm)
    DefinationViewpager dvp_recordForm;//盛装fragment的容器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_energy_record_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        presenter.onResume(this);
        //presenter.getTopData(this);
    }

    @Override
    protected EnergyRecordListP getPresenter() {
        return new EnergyRecordListP();
    }

    @OnClick({R.id.iv_back, R.id.txt_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_check://录前检测
                presenter.getCheckBefore(this);
                break;
        }
    }

    @Override
    public void setText(Object data) {
        EnergyDeviceTitleBean bean = (EnergyDeviceTitleBean) data;
        EnergyDeviceTitleBean.ResobjBean resobj = bean.getResobj();
        List<EnergyDeviceTitleBean.ResobjBean.ConsCategBean> consCateg = resobj.getConsCateg();
        String[] title = new String[consCateg.size()];
        List<Fragment> list_fragemnt = new ArrayList<>();
        for (int i = 0; i < consCateg.size(); i++) {
            title[i] = consCateg.get(i).getName();
            EnergyDeviceListFragment fragment = new EnergyDeviceListFragment();
            list_fragemnt.add(fragment);
            Bundle bundle = new Bundle();
            bundle.putString("type", consCateg.get(i).getType());
            bundle.putInt("position", i);
            fragment.setArguments(bundle);
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        dvp_recordForm.setAdapter(adapter);
        dvp_recordForm.setNoScroll(false);
        mtl_recordForm.setupWithViewPager(dvp_recordForm);
        mtl_recordForm.addTablayoutChanges(this, list_fragemnt, title, dvp_recordForm);//设置监听
        dvp_recordForm.setOffscreenPageLimit(title.length);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void getTop(Object data) {

    }

    @Override
    public void getDeviceList(Object data) {

    }

    @Override
    public void list_null() {

    }

    @Override
    public void getError(Object data) {

    }
}
 