package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.adapter.RecordFormDeviceListAdapter;
import com.kongtiaoapp.xxhj.bean.RecordFormDeviceBean;
import com.kongtiaoapp.xxhj.fragment.AddDeviceRunningFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RecordFormPrensenter;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormView;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 记录表单
 */
public class RecordFormActivity extends BaseActivity<RecordFormPrensenter, RecordFormView> implements RecordFormView {
    @BindView(R.id.txt_recordForm)
    TextView txt_recordForm;
    @BindView(R.id.ml_recordForm)
    MyLinearlayout ml_recordForm;
    @BindView(R.id.tl_rf)
    MyTablayout tl_rf;
    @BindView(R.id.vp_rf)
    DefinationViewpager vp_rf;
    @BindView(R.id.dl_recordForm)
    DrawerLayout dl_recordForm;
    @BindView(R.id.mlv_recordForm)
    ExpandableListView mlv_recordForm;//左侧画出的
    private RecordFormDeviceListAdapter adapterDevice;
    private MyFragmentAdapter adapter;
    private List<RecordFormDeviceBean.ResobjBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_record_form;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        dl_recordForm.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                ml_recordForm.getFocus(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                ml_recordForm.getFocus(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void initData() {
        presenter.onResume(this);
        // 设置一级item点击的监听器
        mlv_recordForm.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;//true代表不可点击
            }
        });

        // 设置二级item点击的监听器
        mlv_recordForm.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (list != null) {
                    RecordFormDeviceBean.ResobjBean.RecordFormBean bean = list.get(groupPosition).getRecordForm().get(childPosition);


                    List<Fragment> list_fragemnt = new ArrayList<>();
                    List<RecordFormDeviceBean.ResobjBean.RecordFormBean.DataBean> listData = bean.getData();
                    txt_recordForm.setText(bean.getDeviceName());
                    String[] title = new String[listData.size()];
                    for (int i = 0; i < listData.size(); i++) {
                        title[i] = listData.get(i).getName();
                        list_fragemnt.add(new AddDeviceRunningFragment());
                    }
                    tl_rf.removeAllTabs();
                    adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
                    vp_rf.setAdapter(adapter);
                    vp_rf.setOffscreenPageLimit(bean.getCount());
                    vp_rf.setNoScroll(false);
                    tl_rf.setupWithViewPager(vp_rf);
                    tl_rf.addTablayoutChanges(RecordFormActivity.this, list_fragemnt, title, vp_rf);//设置监听
                    if (listData != null) {
                        for (int i = 0; i < list_fragemnt.size(); i++) {
                            AddDeviceRunningFragment fragment = (AddDeviceRunningFragment) list_fragemnt.get(i);
                            fragment.setData(listData.get(i));
                        }
                        dl_recordForm.closeDrawer(Gravity.START);
                    }
                }
                return false;

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.txt_csType, R.id.iv_backLeft})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_csType:
                startActivity(new Intent(this, ChangeShiftsActivity.class));
                break;
            case R.id.iv_backLeft:
                if (dl_recordForm.isDrawerOpen(Gravity.START)) {
                    dl_recordForm.closeDrawers();
                }
                finish();
                break;
            default:

                break;
        }
    }

/*
    @OnItemClick(lv_recordForm)
    public void onItemClick(int position) {

    }
*/

    @Override
    protected RecordFormPrensenter getPresenter() {
        return new RecordFormPrensenter();
    }

    @Override
    public void setText(Object data) {
        RecordFormDeviceBean bean = (RecordFormDeviceBean) data;
        list = bean.getResobj();
        List<RecordFormDeviceBean.ResobjBean.RecordFormBean> listGroupData = null;
        if (list != null) {
            RecordFormDeviceBean.ResobjBean rsBean = list.get(0);
            listGroupData = rsBean.getRecordForm();
            if (listGroupData.isEmpty()){
                return;
            }
            List<RecordFormDeviceBean.ResobjBean.RecordFormBean.DataBean> listData = listGroupData.get(0).getData();
            txt_recordForm.setText(rsBean.getName());
            List<Fragment> list_fragemnt = null;
            if (listData != null) {
                String title[] = new String[listData.size()];
                list_fragemnt = new ArrayList<>();
                for (int i = 0; i < title.length; i++) {
                    title[i] = listData.get(i).getName();
                    list_fragemnt.add(new AddDeviceRunningFragment());
                }
          /*
            list_fragemnt.add(new Work_RFFragment());
            list_fragemnt.add(new BPD_RFFragment());*/
                adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
                vp_rf.setAdapter(adapter);
                vp_rf.setOffscreenPageLimit(title.length);
                if (title.length==1){
                    tl_rf.setVisibility(View.GONE);
                }else{
                    tl_rf.setVisibility(View.VISIBLE);
                }
                vp_rf.setNoScroll(false);
                tl_rf.setupWithViewPager(vp_rf);
                tl_rf.addTablayoutChanges(this, list_fragemnt, title, vp_rf);//设置监听
            }
            adapterDevice = new RecordFormDeviceListAdapter(list, this);
            mlv_recordForm.setAdapter(adapterDevice);
            for (int i = 0; i < list.size(); i++) {
                mlv_recordForm.expandGroup(i);
            }
            mlv_recordForm.setGroupIndicator(null);
            if (listData != null) {
                for (int i = 0; i < list_fragemnt.size(); i++) {
                    AddDeviceRunningFragment fragment = (AddDeviceRunningFragment) list_fragemnt.get(i);
                    fragment.setData(listData.get(i));
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
