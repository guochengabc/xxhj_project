package com.kongtiaoapp.xxhj.activites;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoGetParamAdapter;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AddDevicePresenter;
import com.kongtiaoapp.xxhj.mvp.view.AddDeviceInfoView;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.draglistview.DragListView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 添加设备信息页面
 */
public class AddDeviceInfoActivity extends BaseActivity<AddDevicePresenter, AddDeviceInfoView> implements AddDeviceInfoView, MyScrollView.OnScrollListener, AbsListView.OnScrollListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.listview)
    DragListView mListview;
    @ViewInject(R.id.txt_setting_draw)
    TextView txt_setting_draw;
    @ViewInject(R.id.addDeviceInfo_tv_save)
    TextView addDeviceInfo_tv_save;
    @ViewInject(R.id.line_add_device_information)
    LinearLayout line_add_device_information;
    private String type = "";
    private List<DeviceParam.DeviceParamList> deviceParamLists = new ArrayList<>();
    private DeviceInfoGetParamAdapter adapter;
    private String deviceId = "";
    private String input = "";//1 为修改  0为新加
    private String userId;

    @Event(value = {R.id.iv_back, R.id.addDeviceInfo_tv_save})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.addDeviceInfo_tv_save:
                List list = new ArrayList<>();
                list.clear();
                list.add(type);
                list.add(input);
                list.add(deviceId);
                list.add(adapter);
                list.add(deviceParamLists);
                presenter.SaveDv(this, list);
                break;
        }
    }

    @Override
    protected int initContentView() {

        return R.layout.activity_add_device_information;
    }

    /*初始化布局并设置监听*/
    @Override
    protected void initView() {
        x.view().inject(this);
        txt_setting_draw.setVisibility(View.GONE);
        mListview.setDragEnabled(false);
        mListview.setOnScrollListener(this);
        input = getIntent().getStringExtra("input");
        type = getIntent().getStringExtra("type");
        deviceId = getIntent().getStringExtra("deviceId");
       tv_title.setText("设备");
        if (UserManegerList.HVAC_Operate()) {
            if (deviceId != null) {
                if (!deviceId.equals(App.sp.getUid())) {
                    mListview.setItemsCanFocus(false);
                    addDeviceInfo_tv_save.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        adapter = new DeviceInfoGetParamAdapter(deviceParamLists, this);
        mListview.setAdapter(adapter);
        if ("1".equals(input)) {
            //修改
            presenter.ModifyDv(this, deviceId);
        } else {
            //添加
            presenter.AddDv(this, type);
        }

    }

    /*初始华presenter*/
    @Override
    protected AddDevicePresenter getPresenter() {
        return new AddDevicePresenter();
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY > 0) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AddDeviceInfoActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //当停止滚动时
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    //滚动时
                //没错，下面这一坨就是隐藏软键盘的代码
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AddDeviceInfoActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:   //手指抬起，但是屏幕还在滚动状态
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /*接收网络返回过来的数据*/
    @Override
    public void setList(Object data) {
        DeviceParam response = (DeviceParam) data;
        deviceParamLists.clear();
        deviceParamLists.addAll(response.getResobj());
        adapter.notifyDataSetChanged();
    }

    /*接受反过来的数据*/
    @Override
    public void setText(Object text) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        line_add_device_information.setBackgroundResource(0);
        if (deviceParamLists != null) {
            deviceParamLists.clear();
            deviceParamLists = null;
        }

    }
}
