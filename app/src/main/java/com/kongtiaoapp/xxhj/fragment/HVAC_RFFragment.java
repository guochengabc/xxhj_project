package com.kongtiaoapp.xxhj.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AddDeviceRunningActivity;
import com.kongtiaoapp.xxhj.activites.AddRunningSystemActivity;
import com.kongtiaoapp.xxhj.adapter.DeviceRunningInfoAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.ChangeTokenBean;
import com.kongtiaoapp.xxhj.bean.RecordFormDeviceBean;
import com.kongtiaoapp.xxhj.bean.RunningDevice;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.XUtil;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningDeviceListPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningDeviceListView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.utils.StringUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.暖通空调记录
 */
public class HVAC_RFFragment extends BaseFragment<RunningDeviceListPresenter, RunningDeviceListView> implements RunningDeviceListView {
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.submit_device)
    ClickTextView submitDevice;
    @BindView(R.id.rl_sys)
    RelativeLayout rlSys;
    @BindView(R.id.tv_sys_gou)
    ImageView tvSysGou;
    /*  @BindView(R.id.txt_enter_params)
      TextView txt_enter_params;*/
    private String projectId;
    private List<RunningDevice.RunningDeviceList> mList = new ArrayList<>();
    private DeviceRunningInfoAdapter adapter;
    private DbManager db;
    private Map<String, String> type;
    private boolean isAuto = App.sp.isAuto();
    private RunningDevice info;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        RegisterReceiver();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public RunningDeviceListPresenter getPresenter() {
        return new RunningDeviceListPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_hvac_rf, null);
    }

    public void setData(RecordFormDeviceBean.ResobjBean bean) {
        if (bean!=null){
            ToastUtils.showToast(mActivity, bean.getRecordForm().get(0).getData().get(0).getName());
            onResume();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.i(TAG, "=====bundle==" + bundle.getString("key"));
        }
        projectId = App.sp.getProjectId();
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            RunningDevice.RunningDeviceList item = (RunningDevice.RunningDeviceList) parent.getAdapter().getItem(position);
            startActivity(
                    new Intent(mActivity, AddDeviceRunningActivity.class)
                            .putExtra("deviceId", item.getDeviceId())
                            .putExtra("type", item.getType())
                            .putExtra(ConstantValue.AUTO, isAuto)
                            .putExtra("deviceName", item.getName()));
        });
     /*   if (isAuto) {
            submitDevice.setVisibility(View.GONE);
        } else {*/
        submitDevice.setVisibility(View.VISIBLE);
        //  }
        initListener();
        setDatas();
    }

    private void setDatas() {
        adapter = new DeviceRunningInfoAdapter(mActivity, mList);
        gridView.setAdapter(adapter);
        if (!StringUtils.isEmpty(projectId)) {
            setSysState();
            getDataForServers(projectId);
        } else {
            rlSys.setVisibility(View.INVISIBLE);
            submitDevice.setVisibility(View.INVISIBLE);
            gridView.setVisibility(View.INVISIBLE);
        }
    }

    protected void initListener() {
        rlSys.setOnClickListener(v -> {
            if (info != null) {
                List<RunningDevice.RunningDeviceList> infoResobj = info.getResobj();
                if (infoResobj != null) {
                    if (!infoResobj.get(0).getType().equals("bbcs")) {//望京这边特殊的所有的表的type都是bbcs
                        startActivity(new Intent(mActivity, AddRunningSystemActivity.class).putExtra("projectId", projectId).putExtra(ConstantValue.AUTO, isAuto));
                    }
                }

            }
        });
    }


    private void changeToken() {
        presenter.changeToken(mActivity, projectId);
    }

    private void getDataForServers(String projectId) {
        presenter.onResume(mActivity, projectId);
    }

    // R.id.txt_enter_params  录入参数入口
    @OnClick({R.id.submit_device})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_device:
                try {
                    List<ProjectTable> projects = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).findAll();
                    if (projects == null) {
                        return;
                    }
                    type = new HashMap<>();
                    //获取设备种类
                    for (ProjectTable project : projects) {
                        String key = null;
                        key = project.getName();
                        type.put(key, String.valueOf(project.getId()));
                    }
                    JSONObject jsonObject = new JSONObject();
                    List<JSONObject> vel = new ArrayList<>();
                    for (String key : type.keySet()) {
                        vel.clear();
                        for (ProjectTable project : projects) {
                            if (key.equals(project.getName())) {
                                vel.add(new JSONObject(project.getInfo()));
                            }
                        }
                        JSONArray resObj = new JSONArray(vel);
                        if ("System".equals(key)) {
                            jsonObject.put(key, vel.get(0));
                            continue;
                        }
                        jsonObject.put(key, resObj);
                    }
                    JSONObject result = new JSONObject();
                    result.put("uid", App.sp.getUid());
                    result.put("projectId", projectId);
                    result.put("data", jsonObject);
                    String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + HttpMethod.POSTRUNNINGDATA + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + result.toString() + "}";
                    presenter.submit(mActivity, string);
                } catch (DbException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

          /*  case R.id.txt_enter_params:
                Intent intent = new Intent(this, Auto_Manual_ParamsActivity.class);
                startActivity(intent);
                break;*/
            default:

                break;
        }

    }

    private myReceiver receiver;

    /**
     * 注册广播接受者
     */
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.DEVICE_SAVE);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        mActivity.registerReceiver(receiver, intentFilter);

    }

    private void setSysState() {
        try {
            ProjectTable ProjectSystem = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("name", "=", "System").findFirst();
            if (ProjectSystem == null) {
                tvSysGou.setVisibility(View.INVISIBLE);

            } else {
                if (ProjectSystem.isSave()) {
                    tvSysGou.setVisibility(View.VISIBLE);
                } else {
                    tvSysGou.setVisibility(View.INVISIBLE);
                }

            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeToken(Object data) {
        ChangeTokenBean changeTokenBean = (ChangeTokenBean) data;
        App.sp.setToken(changeTokenBean.getResobj().getToken());
        getDataForServers(projectId);
    }

    @Override
    public void submit_View() {
        ToastUtils.showToast(mActivity, getString(R.string.commit_succeed));
        try {
            List<ProjectTable> projectTable = db.selector(ProjectTable.class).where("projectId", "=", projectId).findAll();
            db.delete(projectTable);
            getDataForServers(projectId);
            setSysState();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setText(Object response) {
        info = (RunningDevice) response;
        if (info.getResobj() != null || info.getResobj().size() > 0) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }


    /**
     * 广播接收者
     */
    public class myReceiver extends BroadcastReceiver {


        // 重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.DEVICE_SAVE)) {
                setSysState();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.unregisterReceiver(receiver);
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        if (type != null) {
            type.clear();
            type = null;
        }
    }
}
