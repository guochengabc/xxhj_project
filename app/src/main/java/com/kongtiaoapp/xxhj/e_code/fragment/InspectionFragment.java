package com.kongtiaoapp.xxhj.e_code.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.InspectionAdapter;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.InspectionBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.InspectionPresenter;
import com.kongtiaoapp.xxhj.mvp.view.InspectionView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.  巡检项
 */
public class InspectionFragment extends BaseFragment<InspectionPresenter, InspectionView> implements InspectionView {
    @BindView(R.id.lv_inspection)
    ListView lv_inspection;
    @BindView(R.id.addDeviceInfo_tv_save)
    ClickTextView addDeviceInfo_tv_save;
    private InspectionAdapter adapter;
    private String deviceId;

    public InspectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public InspectionPresenter getPresenter() {
        return new InspectionPresenter();
    }
    @OnClick({R.id.addDeviceInfo_tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.addDeviceInfo_tv_save:
                commitInsp();//提交巡检项
                break;
        }
    }

    private void commitInsp() {
        if (adapter!=null){
            List list=new ArrayList<>();
            List<Map<String, String>> listInsp = adapter.getListInsp();
            Map<String,List<Map<String, String>>> map=new LinkedHashMap<>();
            list.add(deviceId);
            list.add(listInsp);
            presenter.commitInsp(mActivity,list);
        }
    }



    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_inspection, null);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            DeviceNameE_CodeBean device = (DeviceNameE_CodeBean) bundle.getSerializable("device");
            if (device != null) {
                deviceId = device.getDeviceId();
                presenter.onResume(mActivity, deviceId);
            }
        }

    }


    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object data) {
        InspectionBean bean = (InspectionBean) data;
        InspectionBean.ResobjBean resobj = bean.getResobj();
        List<InspectionBean.ResobjBean.InspBean> listInspection = resobj.getInsp();
        adapter = new InspectionAdapter(listInspection, mActivity);
        lv_inspection.setAdapter(adapter);
    }

    @Override
    public void commitInsp(Object data) {
        addDeviceInfo_tv_save.setText("已提交");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
