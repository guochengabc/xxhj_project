package com.kongtiaoapp.xxhj.login_register.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ForgetPwdPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ForgetPwdView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.TimeButton;
import com.kongtiaoapp.xxhj.utils.RegexUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-6-12.
 * 忘记密码页面
 */
public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter, ForgetPwdView> implements ForgetPwdView {
    @BindView(R.id.et_tel)//手机号
            EditText etTel;
    @BindView(R.id.et_sms_code)
    EditText etSmsCode;
    @BindView(R.id.btn_time)
    TimeButton btnTime;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_enter)
    ClickTextView btnEnter;

    private String phone, smsCode, pwd;

    @Override
    protected int initContentView() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initView() {
        btnTime.setTimeStartClickListener(v -> {
            phone = etTel.getText().toString();
            if (TextUtils.isEmpty(phone)) {
                ToastUtils.showToast(this, getString(R.string.please_phone));
                return;
            }
            if (!RegexUtils.isMobileNO(phone)) {
                ToastUtils.showToast(this, getString(R.string.phone_error_reset));
                return;
            }
            presenter.sendCode(this, phone);
            btnTime.Start();
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected ForgetPwdPresenter getPresenter() {
        return new ForgetPwdPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_password, R.id.btn_enter, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_password:
                break;
            case R.id.btn_enter:
                phone = etTel.getText().toString();
                smsCode = etSmsCode.getText().toString().trim();
                pwd = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(this, getString(R.string.please_phone));
                }
                if (TextUtils.isEmpty(smsCode)) {
                    ToastUtils.showToast(this, getString(R.string.please_identify_code));
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showToast(this, getString(R.string.phone_password));
                    return;
                }
                changePwd();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void changePwd() {
        List<String> list = new ArrayList<>();
        list.add(phone);
        list.add(smsCode);
        list.add(pwd);
        presenter.changgePwd(this, list);
    }

    @Override
    public void sendCode() {

    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


