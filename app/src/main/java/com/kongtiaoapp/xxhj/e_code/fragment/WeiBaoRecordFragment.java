package com.kongtiaoapp.xxhj.e_code.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.MaintBean;
import com.kongtiaoapp.xxhj.e_code.activity.WeiBaoDetailActivity;
import com.kongtiaoapp.xxhj.e_code.adapter.WeiBaoAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.WeiBaoRecordP;
import com.kongtiaoapp.xxhj.mvp.view.WeiBaoRecordV;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.view.DialogPrompt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiBaoRecordFragment extends BaseFragment<WeiBaoRecordP, WeiBaoRecordV> implements WeiBaoRecordV {
    @BindView(R.id.sv_work_Order)
    SpringView sv_work_Order;
    @BindView(R.id.lv_workOrder)
    ListView lv_workOrder;
    private int currentPage = 1;
    private List<MaintBean.ResobjBean> listAll = new ArrayList<>();
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    sv_work_Order.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    sv_work_Order.onFinishFreshAndLoad();
                    break;
                case R.id.iv_back:

                    break;
                default:
                    break;
            }
        }
    };


    private boolean isRefresh;
    private WeiBaoAdapter adapter;
    private String deviceId = "";
    private int deletePosition;

    public WeiBaoRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public WeiBaoRecordP getPresenter() {
        return new WeiBaoRecordP();
    }

    @OnItemClick({R.id.lv_workOrder})
    public void onItemClick(int position) {
        startActivity(new Intent(mActivity, WeiBaoDetailActivity.class).putExtra("id", listAll.get(position).getMaintenanceRecordId()));
    }

    @Override
    protected View initView() {
        View inflate = View.inflate(mActivity, R.layout.fragment_wei_bao_record, null);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            DeviceNameE_CodeBean device = (DeviceNameE_CodeBean) bundle.getSerializable("device");
            if (device != null) {
                deviceId = device.getDeviceId();
            }
        }
        initListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        isRefresh=true;
        getDataForServers();
    }

    private void getDataForServers() {
        List<String> param = new ArrayList<>();
        param.add(deviceId);
        param.add(currentPage + "");
        presenter.onResume(mActivity, param);
    }

    private void initListView() {
        adapter = new WeiBaoAdapter(listAll, mActivity);
        lv_workOrder.setAdapter(adapter);
        sv_work_Order.setHeader(new RotationHeader(getContext()));
        sv_work_Order.setFooter(new RotationFooter(getContext()));
        sv_work_Order.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        currentPage = 1;
                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
        lv_workOrder.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deletePosition = position;

                DialogPrompt.Builder builder = new DialogPrompt.Builder(mActivity);
                builder.setTitle("确定删除该条记录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.delete(mActivity, listAll.get(position).getMaintenanceRecordId() + "");
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

                return true;
            }
        });
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getList(Object data) {
        List<MaintBean.ResobjBean> list = (List<MaintBean.ResobjBean>) data;
        if (isRefresh) {
            listAll.clear();
        }
        listAll.addAll(list);
        adapter.setList(listAll);
    }

    @Override
    public void delete() {
        listAll.remove(deletePosition);
        if (listAll.isEmpty()){
            listAll.clear();
        }
        adapter.setList(listAll);
    }
}
