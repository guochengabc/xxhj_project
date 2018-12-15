package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.igexin.sdk.PushManager;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.service.GetPushService;
import com.umeng.analytics.MobclickAgent;

public class WellcomeActivity extends AppCompatActivity {
    public int CAMERA = 1000;
    public static final int NO_CAMERA = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        // 读写 sd card 权限非常重要, android6.0默认禁止的, 建议初始化之前就弹窗让用户赋予该权限
        PushManager.getInstance().initialize(this.getApplicationContext(), GetPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.kongtiaoapp.xxhj.service.GetInfo_IntentService.class);

        /*if (App.sp.isFirstLogoin()) {
            toMyGuideActivity();
        } else {
            toLoginAcitivity();
        }*/
    }
/*
    @Override
    protected int initContentView() {
        return R.layout.activity_wellcome;
    }

    @Override
    protected void initView() {

        if (App.sp.isFirstLogoin()) {
            toMyGuideActivity();
        } else {
            toLoginAcitivity();
        }
    }
*/

 /*   @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }
*/
    protected void toLoginAcitivity() {

        if (App.sp.isLogin()) {
            Intent intent = new Intent(WellcomeActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(WellcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void toMyGuideActivity() {
        //管理相机权限      //
        enter_Guide();//进入向导
    }


    private void enter_Guide() {
        Intent intent = new Intent(WellcomeActivity.this, MyGuideActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
          MobclickAgent.onResume(this);
        Intent intent = new Intent(WellcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
           MobclickAgent.onPause(this);
    }


}
