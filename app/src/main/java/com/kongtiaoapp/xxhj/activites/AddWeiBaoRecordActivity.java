package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.WeibaoBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AddWBRecordPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AddWBRecordView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 添加维保记录页面
 */
public class AddWeiBaoRecordActivity extends BaseActivity<AddWBRecordPresenter, AddWBRecordView> implements AddWBRecordView {

    @ViewInject(R.id.addWeiBaoRecord_iv_pic)
    private ImageView mPic;//上面的图片

    @ViewInject(R.id.addWeiBaoRecord_et_deviceName)
    private TextView mDeviceName;//设备名称

    @ViewInject(R.id.addWeiBaoRecord_et_weiXiuName)
    private EditText mWeiXiuName;//维修人员

    @ViewInject(R.id.addWeiBaoRecord_et_weiXiuTime)
    private TextView mWeiXiuTime;//维修时间

    @ViewInject(R.id.addWeiBaoRecord_et_address)
    private EditText mAddress;//安装地点

    @ViewInject(R.id.addWeiBaoRecord_et_result)
    private EditText mResult;//维修结果

    @ViewInject(R.id.addWeiBaoRecord_et_dutyName)
    private TextView mDutyName;//值班人员

    @ViewInject(R.id.addWeiBaoRecord_et_gongsi)
    private EditText mCompany;//维保公司

    @ViewInject(R.id.addWeiBaoRecord_et_Phone)
    private EditText mPhone;//联系电话

    @ViewInject(R.id.addWeiBaoRecord_et_money)
    private EditText mMoney;//维保费用

    private String maintenanceRecordId;
    private String deviceName;
    private String weiXiuName;
    private String weiXiuTime;
    private String result;
    private String dutyName;
    private String deviceId;
    private String weiXiuCompany;
    private String weiXiuPhone;
    private String weiXiuMoney;

    @Event(value = {R.id.iv_back, R.id.addWeiBaoRecord_tv_save, R.id.addWeiBaoRecord_et_deviceName, R.id.addWeiBaoRecord_et_weiXiuTime})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮
                finish();
                break;
            case R.id.addWeiBaoRecord_et_deviceName://选择设备
                startActivityForResult(
                        new Intent(mContext, UserDeviceListActivity.class), 100);
                break;
            case R.id.addWeiBaoRecord_et_weiXiuTime://选择时间
                // 时间选择器
                TimePickerView pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                pvTime.setOnTimeSelectListener(date -> mWeiXiuTime.setText(getTime(date)));
                pvTime.show();
                break;
            case R.id.addWeiBaoRecord_tv_save://保存按钮
                deviceName = mDeviceName.getText().toString();
                weiXiuName = mWeiXiuName.getText().toString();
                weiXiuTime = mWeiXiuTime.getText().toString();
                result = mResult.getText().toString();
                dutyName = mDutyName.getText().toString();
                weiXiuCompany = mCompany.getText().toString();
                weiXiuPhone = mPhone.getText().toString();
                weiXiuMoney = mMoney.getText().toString();
                if (TextUtils.isEmpty(deviceName) || TextUtils.isEmpty(deviceId)) {
                    ToastUtils.showToast(this, getString(R.string.weibao_choose_device));
                    return;
                }
                if (TextUtils.isEmpty(weiXiuName)) {
                    ToastUtils.showToast(this, getString(R.string.weibao_require_person));
                    return;
                }
                if (TextUtils.isEmpty(weiXiuTime)) {
                    ToastUtils.showToast(this, getString(R.string.weibao_require_time));
                    return;
                }
                if (TextUtils.isEmpty(result)) {
                    ToastUtils.showToast(this, getString(R.string.weibao_require_result));
                    return;
                }
                if (TextUtils.isEmpty(dutyName)) {
                    ToastUtils.showToast(this, getString(R.string.weibao_duty_person));
                    return;
                }
                List list = new ArrayList<>();
                list.add(deviceId);
                list.add(maintenanceRecordId);
                list.add(weiXiuTime);
                list.add(weiXiuName);
                list.add(result);
                list.add(weiXiuCompany);
                list.add(weiXiuPhone);
                list.add(weiXiuMoney);
                presenter.save(this, list);
                break;
        }
    }

    @Override
    protected int initContentView() {
        maintenanceRecordId = getIntent().getStringExtra("MaintenanceRecordId");
        return R.layout.activity_add_weibao_record;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mDutyName.setText(App.sp.getName());
        if (!TextUtils.isEmpty(maintenanceRecordId)) {
            presenter.onResume(this, maintenanceRecordId);
        }
    }

    @Override
    protected AddWBRecordPresenter getPresenter() {
        return new AddWBRecordPresenter();
    }


    private static String getTime(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            // 选择设备
            if (data != null && resultCode == RESULT_OK) {
                deviceId = data.getStringExtra("DeviceId");
                mDeviceName.setText(data.getStringExtra("Name"));
                mPic.setImageResource(DrawableUtils.getLeveImage(data.getStringExtra("Type")));
            }
        }
    }
    /*回传过来的数据*/
    @Override
    public void setText(Object response) {
        WeibaoBean info = (WeibaoBean) response;
        WeibaoBean.WeiBao item = info.getResobj();
        if (item != null) {
            mDeviceName.setText(item.getDeviceName());
            mWeiXiuName.setText(item.getTechnician());
            if (!TextUtils.isEmpty(item.getTime())) {
                mWeiXiuTime.setText(TimeUtils.getDetailTimeNoCHN(item.getTime()));
            }
            mCompany.setText(item.getCompany());
            mResult.setText(item.getContent());
            mDutyName.setText(item.getUserName());
            mPic.setImageResource(DrawableUtils.getLeveImage(item.getDeviceType()));
            mPhone.setText(item.getPhone());
            mMoney.setText(item.getCost());
            deviceId = item.getDeviceId();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
