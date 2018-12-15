package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DictNew;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.DeviceInfoPresenter;
import com.kongtiaoapp.xxhj.mvp.view.DeviceInfoView;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 设备信息页面
 */
public class DeviceInfoActivity extends BaseActivity<DeviceInfoPresenter, DeviceInfoView> implements DeviceInfoView, AdapterView.OnItemClickListener {
    @BindView(R.id.gridView)
    GridView gridView;//设备列表
    @BindView(R.id.deviceInfo_iv_add)
    ImageView iv_add;//添加设备
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.tv_title)
    TextView tvTitle;//设备title
    private DeviceInfoAdapter adapter;
    private List<DictNew.ResobjBean> mList = new ArrayList<>();
    private int currentPage = 1;
    private boolean isRefresh = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    presenter.onResume(DeviceInfoActivity.this);
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    presenter.onResume(DeviceInfoActivity.this);
                    springView.onFinishFreshAndLoad();
                    break;
                default:
                    break;
            }
        }
    };

    @Event(value = {R.id.iv_back, R.id.deviceInfo_iv_add})
    private void getEvent(View view) {
        switch (view.getId()) {            case R.id.iv_back://返回按钮
                finish();
                break;
            case R.id.deviceInfo_iv_add://右上角添加按钮
                startActivity(new Intent(this, DeviceInfoDetailActivity.class));
                break;

        }
    }


    @Override
    protected int initContentView() {
        return R.layout.activity_device_info;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
        iv_add.setVisibility(View.GONE);
        tvTitle.setText("设备类型");
        gridView.setNumColumns(2);
        gridView.setOnItemClickListener(this);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        adapter = new DeviceInfoAdapter(mContext, mList);
        gridView.setAdapter(adapter);
        presenter.onResume(DeviceInfoActivity.this);
    }

    @Override
    protected DeviceInfoPresenter getPresenter() {
        return new DeviceInfoPresenter();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DictNew.ResobjBean item = (DictNew.ResobjBean) adapter.getItem(position);
        Intent intent = new Intent(this, DeviceListActivity.class);
        intent.putExtra("dictCode", item.getDeviceType());
        intent.putExtra("name", item.getDeviceName());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setList(Object data) {
        DictNew dict = (DictNew) data;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(dict.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
