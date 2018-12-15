package com.kongtiaoapp.xxhj.activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.DvListPresenter;
import com.kongtiaoapp.xxhj.mvp.view.DvListView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.utils.BigToSmallUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 设备信息列表页面
 */
public class DeviceListActivity extends BaseActivity<DvListPresenter, DvListView> implements DvListView, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @BindView(R.id.gridView)
    GridView gridView;//设备列表
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private DeviceInfoListAdapter adapter;
    private List<DeviceInfo.DeviceInfoList> mList = new ArrayList<>();
    private String dictCode;
    private String deleteDeviceId;

    private int currentPage = 1;

    private boolean isRefresh = true;
    /*刷新数据*/
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private int delete_position;

    @Event(value = {R.id.iv_back, R.id.deviceInfo_iv_add})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮

                finish();
                break;
            case R.id.deviceInfo_iv_add://右上角添加按钮
                startActivity(new Intent(this, AddDeviceInfoActivity.class).putExtra("type", dictCode).putExtra("input", "0"));
                break;

        }
    }


    @Override
    protected int initContentView() {
        dictCode = getIntent().getStringExtra("dictCode");
        RegisterReceiver();
        return R.layout.activity_device_info;
    }

    /*进行相应的初始化*/
    @Override
    protected void initView() {
        x.view().inject(this);
        tvTitle.setText(getIntent().getStringExtra("name"));
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {//刷新
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {//加载更多
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    /*设置监听事件*/
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        adapter = new DeviceInfoListAdapter(mContext, mList, BigToSmallUtils.pascalToUnderline(dictCode));
        gridView.setAdapter(adapter);
        getDataForServies();
    }

    @Override
    protected DvListPresenter getPresenter() {
        return new DvListPresenter();
    }

    /*刚进入界面获取服务器数据*/
    private void getDataForServies() {
        List list = new ArrayList<>();
        list.add(dictCode);
        list.add(currentPage + "");
        presenter.onResume(this, list);
    }

    @Override
    public void setList(Object data) {
        DeviceInfo dict = (DeviceInfo) data;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(dict.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteDevice() {
        ToastUtils.showToast(mContext, getString(R.string.delete_succeed));
        isRefresh = true;
        adapter.remove(delete_position);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DeviceInfo.DeviceInfoList item = (DeviceInfo.DeviceInfoList) adapter.getItem(position);
        startActivity(new Intent(this, AddDeviceInfoActivity.class).putExtra("name",item.getName()).putExtra("deviceId", item.getDeviceId()).putExtra("type", dictCode).putExtra("input", "1").putExtra("userId", item.getUserId()));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        DeviceInfo.DeviceInfoList item = (DeviceInfo.DeviceInfoList) adapter.getItem(position);
        deleteDeviceId = item.getDeviceId();
        delete_position = position;
        if (UserManegerList.HVAC_Operate() && !App.sp.getUid().equals(item.getUserId())) {
            return false;
        }
        ShowDoubleButtonDialog("确定删除此设备?", 200);
        return true;
    }

    @Override
    protected void doubleButtonDialogCallBack(DoubleButtonDialog dialog) {
        super.doubleButtonDialogCallBack(dialog);
        if (dialog.getCode() == 200) {
            // Toast.makeText(mContext,"确定删除此足迹",Toast.LENGTH_SHORT).show();
            presenter.deleteDevice(DeviceListActivity.this, deleteDeviceId);
            dialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private myReceiver receiver;

    /**
     * 注册广播接受者
     */
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.DEVICE_FRESH);
        // 实例化广播监听器；
        receiver = new myReceiver();
        // 将广播监听器和过滤器注册在一起；
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    public void setText(Object text) {

    }


    /**
     * 广播接收者
     */
    public class myReceiver extends BroadcastReceiver {
        // 重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            isRefresh = true;
            getDataForServies();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
