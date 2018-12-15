package com.kongtiaoapp.xxhj.energymetering.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceTitleBean;
import com.kongtiaoapp.xxhj.energymetering.fragment.EnergyStatusFragment;
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

/**
 * 能源录入状态
 */
public class EnergyRecordStatusActivity extends BaseActivity<EnergyRecordListP, EnergyRecordListV> implements EnergyRecordListV,RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rgp_record)
    RadioGroup rgp_record;//查看录入状态
    @BindView(R.id.radio_all)
    RadioButton radio_all;//全部
    @BindView(R.id.radio_YRecord)
    RadioButton radio_YRecord;//已录入
    @BindView(R.id.radio_NRecord)
    RadioButton radio_NRecord;//未录入
    @BindView(R.id.line_help)
    LinearLayout line_help;
    @BindView(R.id.mtl_recordForm)
    MyTablayout mtl_recordForm;//滑动卡
    @BindView(R.id.dvp_recordForm)
    DefinationViewpager dvp_recordForm;//盛装fragment的容器
    private int isVisible = 0;//0不可见   1可见
    private List<Fragment> list_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_energy_record_status;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        rgp_record.setOnCheckedChangeListener(this);
        rgp_record.check(R.id.radio_all);
    }


    @Override
    protected void initData() {
        presenter.onResume(this);
    }

    @Override
    protected EnergyRecordListP getPresenter() {
        return new EnergyRecordListP();
    }

    @OnClick({R.id.iv_back, R.id.img_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_help:
                ++isVisible;
                if (isVisible % 2 == 1) {
                    line_help.setVisibility(View.VISIBLE);
                } else if (isVisible % 2 == 0) {
                    line_help.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void setText(Object data) {
        EnergyDeviceTitleBean bean = (EnergyDeviceTitleBean) data;
        EnergyDeviceTitleBean.ResobjBean resobj = bean.getResobj();
        List<EnergyDeviceTitleBean.ResobjBean.ConsCategBean> consCateg = resobj.getConsCateg();
        String[] title = new String[consCateg.size()];
        list_fragment = new ArrayList<>();
        for (int i = 0; i < consCateg.size(); i++) {
            title[i] = consCateg.get(i).getName();
            EnergyStatusFragment fragment = new EnergyStatusFragment();
            list_fragment.add(fragment);
            Bundle bundle = new Bundle();
            bundle.putString("type", consCateg.get(i).getType());
            bundle.putInt("position", i);
            fragment.setArguments(bundle);
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragment);
        dvp_recordForm.setAdapter(adapter);
        dvp_recordForm.setNoScroll(false);
        mtl_recordForm.setupWithViewPager(dvp_recordForm);
        mtl_recordForm.addTablayoutChanges(this, list_fragment, title, dvp_recordForm);//设置监听
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

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        EnergyStatusFragment fragment= (EnergyStatusFragment) list_fragment.get(mtl_recordForm.getPosition());

        switch (checkedId){
            case R.id.radio_all:
                fragment.setRecordStatus("");
                for (int i = 0; i < list_fragment.size(); i++) {
                    if (i!=mtl_recordForm.getPosition()){
                        ((EnergyStatusFragment)list_fragment.get(i)).setLoading("");//重置所有的isLoading为true  除了当前页
                    }

                }
                break;
            case R.id.radio_YRecord:
                fragment.setRecordStatus("has");
                for (int i = 0; i < list_fragment.size(); i++) {
                    if (i!=mtl_recordForm.getPosition()){
                        ((EnergyStatusFragment)list_fragment.get(i)).setLoading("has");//重置所有的isLoading为true  除了当前页
                    }

                }
                break;
            case R.id.radio_NRecord:
                fragment.setRecordStatus("not");
                for (int i = 0; i < list_fragment.size(); i++) {
                    if (i!=mtl_recordForm.getPosition()){
                        ((EnergyStatusFragment)list_fragment.get(i)).setLoading("not");//重置所有的isLoading为true  除了当前页
                    }

                }
                break;
        }
    }
}
