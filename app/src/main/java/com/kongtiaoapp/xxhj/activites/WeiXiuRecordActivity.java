package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.workorder.adapter.WeiXiuReecordAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class WeiXiuRecordActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    private List<String> list = new ArrayList<>();//维修模块的内容
    @BindView(R.id.glv_repair_record)
    GridView glv_repair_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_wei_xiu_record;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        list.add("配电室");
        list.add("冷站");
        list.add("电梯");
        list.add("给排水");
        list.add("生活热水");
        list.add("设备");
        list.add("弱电");
        list.add("维保");
        list.add("大楼");
        list.add("外围");
        list.add("燃气");
        list.add("新风机");
        list.add("能源统计");
        list.add("工具台账");
        list.add("社工设备");

        WeiXiuReecordAdapter adapter = new WeiXiuReecordAdapter(this, list);
        glv_repair_record.setAdapter(adapter);
    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return new BasePresenterLpl() {
            @Override
            protected BaseModule getModel() {
                return new BaseModule() {
                    @Override
                    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

                    }
                };
            }
        };
    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        finish();
    }

    //点击维修记录里面的功能小模块
    @OnItemClick({R.id.glv_repair_record})
    public void onItemClick(int position) {
        if (position == 0) {
            startActivity(new Intent(this, BPDActivity.class));
        }
        else if (position == 1) {

                /*    new Intent(mContext, AddDeviceRunningActivity.class)
                            .putExtra("deviceId", "b71bf862d735cd0e67d035c76c68a702")
                            .putExtra("type", "bbcs")
                            .putExtra(ConstantValue.AUTO,true)
                            .putExtra("deviceName", "约克冷机点检"));*/
                    startActivity(new Intent(this, RunningDeviceListActivity.class));
        }
        else if (position == 2) {
            startActivity(new Intent(new Intent(WeiXiuRecordActivity.this, Elevator_TourActivity.class)));
        }
    }

    @Override
    public void setText(Object data) {

    }
}
