package com.kongtiaoapp.xxhj.login_register.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MainActivity;
import com.kongtiaoapp.xxhj.bean.LoginBean;
import com.kongtiaoapp.xxhj.mvp.presenter.LoginPresenter;
import com.kongtiaoapp.xxhj.mvp.view.LoginView;
import com.kongtiaoapp.xxhj.service.GetPushService;
import com.kongtiaoapp.xxhj.ui.progress.KProgressHUD;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.LinearLayoutView;
import com.kongtiaoapp.xxhj.utils.MD5Utils;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.RegexUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shinelon on 2016/6/12.
 */
public class LoginActivity extends AppCompatActivity implements LoginView, LinearLayoutView.KeyBordStateListener {
    @BindView(R.id.login_bt_log)
    ClickTextView loginBtLog;   //登录按钮
    @BindView(R.id.et_phone)
    EditText etPhone;   //手机号
    @BindView(R.id.et_password)
    EditText etPassWrod;    //密码
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;    //忘记密码
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_second)
    ImageView iv_second;
    @BindView(R.id.login_root)
    LinearLayoutView login_rot;
    @BindView(R.id.line_clear_phone)
    LinearLayout line_clear_phone;
    @BindView(R.id.txt_login_again)
    TextView txt_login_again;
    private String phone;
    private String pass;
    private String uid;
    private KProgressHUD mProgress;
    protected static Gson gson = new Gson();
    private LoginPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_two);
        presenter = new LoginPresenter();
        presenter.attach(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String quit_login = intent.getStringExtra("quit_login");
        if (intent != null && quit_login != null) {
            if (quit_login.equals("quit_login")) {
                txt_login_again.setVisibility(View.VISIBLE);
            }
        }
        //初始化个推推送
        PushManager.getInstance().initialize(this.getApplicationContext(), GetPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.kongtiaoapp.xxhj.service.GetInfo_IntentService.class);
        // TODO: add setContentView(...) invocation

        if (App.sp.isLogin()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        login_rot.setKeyBordStateListener(this);
        etPhone.addTextChangedListener(new Change_Phone_State());
        etPassWrod.addTextChangedListener(new Change_PhonePassWord_State());
        if (!App.sp.getPhone().equals("")) {
            etPhone.setText(App.sp.getPhone());
        }
    }

    @OnClick({R.id.login_bt_log, R.id.tv_forget_pass, R.id.join_register, R.id.line_clear_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pass:       //忘记密码
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.login_bt_log:     //登录
                phone = etPhone.getText().toString();
                pass = etPassWrod.getText().toString();
                if (!checkNetworked()) {
//                    showToast(TAG, "您的网络不可用，请检查网络连接");
                    ToastUtils.showToast(this, "您的网络不可用，请检查网络连接");
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    //   showToast(TAG, "请输入手机号码");
                    ToastUtils.showToast(this, "请输入手机号码");
                    return;
                } else if (!RegexUtils.isMobileNO(phone)) {
                    //   showToast(TAG, "手机号码格式不正确,请确认后输入");
                    ToastUtils.showToast(this, "手机号码格式不正确,请确认后输入");
                    return;
                } else if (TextUtils.isEmpty(pass)) {
                    //  showToast(TAG, "请输入登录密码");
                    ToastUtils.showToast(this, "请输入登录密码");
                    return;
                } else if (pass.length() < 6) {
                    //  showToast(TAG, "密码至少设置6位，请确认后输入");
                    ToastUtils.showToast(this, "密码至少设置6位，请确认后输入");
                    return;
                }
                login(phone, pass);
                break;
            case R.id.join_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.line_clear_phone:
                etPhone.setText("");
                App.sp.removeSp("phone");
                line_clear_phone.setVisibility(View.GONE);
                txt_login_again.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void login(String phone, String pass) {
        List<String> list = new ArrayList<>();
        list.add(phone);
        list.add(MD5Utils.String2MD5(pass));
        presenter.onResume(this, list);
    }

    private void getUserInfo() {
        presenter.getUserInfo(this, uid);//获取用户信息
    }

    /**
     * 禁止项目文字大小跟随系统变化，预防布局错乱
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        // config.setToDefaults();
        config.fontScale = 1.0f;
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }

    protected boolean checkNetworked() {
        if (!NetworkUtils.checkNetwork(this)) {
            ToastUtils.showToast(this, getString(R.string.no_net));
            return false;
        }

        return true;
    }

    protected void showProgressDialog() {
        if (mProgress == null) {
            mProgress = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("加载中...");
        }
        if (mProgress.isShowing()) {
            return;
        }

        mProgress.show();
    }

    protected void dismissProgressDialog() {
        if (mProgress != null) {
            mProgress.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }

        return true;
    }


    public void exit() {
        finish();
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }


    @Override
    public void stateChange(int state) {
        switch (state) {
            case LinearLayoutView.KEYBORAD_HIDE:
                ivIcon.setVisibility(View.VISIBLE);
                iv_second.setVisibility(View.GONE);
                break;
            case LinearLayoutView.KEYBORAD_SHOW:
                ivIcon.setVisibility(View.GONE);
                iv_second.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setText(Object text) {
        LoginBean loginBean = (LoginBean) text;
        uid = loginBean.getResobj().getUserId();
        if (uid != null) {
            LoginBean.ResobjBean resobj = loginBean.getResobj();
            if (resobj != null) {
                String loginType = resobj.getLoginType();
                if (loginType == null) {
                    loginType = "";
                }
                App.sp.setLeader(loginType);
            }

        }
        App.sp.setToken(loginBean.getResobj().getToken());
        List<String> list_roles = loginBean.getResobj().getRoles();//权限只能列表
        Log.i("LoginActivity", "token===" + loginBean.getResobj().getToken());
        App.sp.setRole(gson.toJson(list_roles).toString());
        getUserInfo();
    }

    @Override
    public void getProjectList(Object data) {

    }

    class Change_Phone_State implements TextWatcher {
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 10;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals("")) {
                line_clear_phone.setVisibility(View.VISIBLE);
                if (s.length() == 1) {
                    txt_login_again.setVisibility(View.GONE);
                }
            } else {
                line_clear_phone.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    class Change_PhonePassWord_State implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            if (s.length() == 1) {
                txt_login_again.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
        presenter.deAttach();
    }


}
