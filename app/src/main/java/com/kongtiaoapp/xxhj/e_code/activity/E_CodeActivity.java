package com.kongtiaoapp.xxhj.e_code.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.widget.LinearLayout;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class E_CodeActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView, QRCodeView.Delegate {
    @BindView(R.id.zbarview)
    ZBarView mZBarView;
    @BindView(R.id.line_openLight)
    LinearLayout line_openLight;
    @BindView(R.id.line_closeLight)
    LinearLayout line_closeLight;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mZBarView.startSpot(); // 显示扫描框，并且延迟0.5秒后开始识别
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_e__code;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.line_openLight, R.id.line_closeLight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_openLight://开灯
                mZBarView.openFlashlight(); // 打开闪光灯
                line_openLight.setBackgroundResource(R.drawable.shape_press_light);
                line_closeLight.setBackgroundResource(R.drawable.shape_unpress_light);
                break;
            case R.id.line_closeLight://关灯
                mZBarView.closeFlashlight(); // 关闭闪光灯
                line_openLight.setBackgroundResource(R.drawable.shape_unpress_light);
                line_closeLight.setBackgroundResource(R.drawable.shape_press_light);
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mZBarView.setDelegate(this);
        mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别

//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 700);

    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {
        mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZBarView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(null);
            handler = null;
        }
    }

    /* @Override
     public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {


     }
 */
    @Override
    public void onScanQRCodeSuccess(String result) {
        try {
            DeviceNameE_CodeBean deviceNameE_codeBean = gson.fromJson(result, DeviceNameE_CodeBean.class);
            Intent intent = new Intent();
            if (deviceNameE_codeBean.getDeviceId() == null) {
                intent.setClass(this, EnergyRecordActivity.class);
                intent.putExtra("device", (Serializable) deviceNameE_codeBean);
            } else {
                intent.setClass(this, RecordFormEcodeActivity.class);
                intent.putExtra("device", (Serializable) deviceNameE_codeBean);
            }
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(E_CodeActivity.this, OtherPageActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
        }
        vibrate();

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
