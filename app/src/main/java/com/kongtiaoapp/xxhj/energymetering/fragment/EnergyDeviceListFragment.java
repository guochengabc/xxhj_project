package com.kongtiaoapp.xxhj.energymetering.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.energymetering.adapter.EnergyDeviceListAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyRecordListP;
import com.kongtiaoapp.xxhj.mvp.view.EnergyRecordListV;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnergyDeviceListFragment extends BaseFragment<EnergyRecordListP, EnergyRecordListV> implements EnergyRecordListV {
    @BindView(R.id.txt_noData)
    TextView txt_noData;
    @BindView(R.id.mlv_eneryDeviceList)
    ExpandableListView mlv_eneryDeviceList;
    private List<EnergyDeviceListBean.ResobjBean.OneDataBean> listOne;
    private boolean isLoading = false;
    private String type;
    private int position = 0;
    private EnergyDeviceListAdapter adapter;

    public EnergyDeviceListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public EnergyRecordListP getPresenter() {
        return new EnergyRecordListP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_energy_device_list, null);
    }

    @Override
    public void initData() {
        super.initData();
        initLister();
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        position = bundle.getInt("position");
        if (type != null) {
            if (position == 0) {
                presenter.getDeviceList(mActivity, type, "", "0");
            }
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (position != 0) {
                if (!isLoading) {
                    isLoading = true;
                    presenter.getDeviceList(mActivity, type, "", "0");//0代表不用检查录入状态   ""代表全部

                }
            }
        }
    }

    private void initLister() {
        //设置不可点击
        mlv_eneryDeviceList.setGroupIndicator(null);
        mlv_eneryDeviceList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listOne.get(groupPosition).getTwoData() == null || listOne.get(groupPosition).getTwoData().isEmpty()) {//不可点击   设置响应事件
                    DeviceNameE_CodeBean bean = new DeviceNameE_CodeBean();
                    EnergyDeviceListBean.ResobjBean.OneDataBean oneDataBean = listOne.get(groupPosition);
                    bean.setSensorId(oneDataBean.getOneSid());
                    bean.setName(oneDataBean.getOneName());
                    startActivity(new Intent(mActivity, EnergyRecordActivity.class).putExtra("device", (Serializable) (bean)));
                    return true;
                } else {//可点击
                    return false;
                }
            }
        });
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void getTop(Object data) {

    }

    @Override
    public void getDeviceList(Object data) {
        txt_noData.setVisibility(View.GONE);
        // String energy = AssetsUtils.readText(mActivity, "energy.json");
        //EnergyDeviceListBean bean = JSONObject.parseObject(energy, EnergyDeviceListBean.class);
        EnergyDeviceListBean bean = (EnergyDeviceListBean) data;
        EnergyDeviceListBean.ResobjBean resobj = bean.getResobj();
        listOne = resobj.getOneData();
        if (adapter == null) {
            adapter = new EnergyDeviceListAdapter(mActivity, listOne);
            mlv_eneryDeviceList.setAdapter(adapter);
            if (position == 0) {
                mlv_eneryDeviceList.invalidate();
                adapter.setList(listOne);
            }
        } else if (adapter != null) {
            adapter.setList(listOne);
        }

        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mlv_eneryDeviceList.expandGroup(i);
        }
    }

    @Override
    public void list_null() {
        txt_noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void getError(Object data) {
        txt_noData.setVisibility(View.VISIBLE);
        txt_noData.setText("" + data.toString());
    }
}
