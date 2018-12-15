package com.kongtiaoapp.xxhj.login_register.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ModifyPWDPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ModifyPWDView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyPWDActivity extends BaseActivity<ModifyPWDPresenter,ModifyPWDView>implements  ModifyPWDView {
    @BindView(R.id.edt_password)
    EditText et_password;
    @BindView(R.id.edt_new_password)
    EditText et_new_password;
    @BindView(R.id.edt_second_new_password)
    EditText edt_second_new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_modify_pwd;
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
    protected ModifyPWDPresenter getPresenter() {
        return new ModifyPWDPresenter();
    }

    @OnClick({R.id.btn_modify_pwd, R.id.iv_back})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.btn_modify_pwd:
                String edt_passwords = et_password.getText().toString();//当前密码
                String edt_new_passwords = et_new_password.getText().toString();//新密码
                String edt_second_new_passwords = edt_second_new_password.getText().toString();//确认新密码
                if (TextUtils.isEmpty(edt_passwords)) {
                    ToastUtils.showToast(this, "请输入" + getString(R.string.original_pwd));
                    return;
                }
                if (TextUtils.isEmpty(edt_new_passwords)) {
                    ToastUtils.showToast(this, "请输入" + getString(R.string.new_pwd));
                    return;
                }
                if (TextUtils.isEmpty(edt_second_new_passwords)) {
                    ToastUtils.showToast(this, "请输入" + getString(R.string.second_new_pwd));
                    return;
                }
                if (edt_passwords.length() >= 6 && edt_new_passwords.length() >= 6) {
                    ToastUtils.showToast(this, getString(R.string.pwd_atlast_six));
                }
                if (edt_new_passwords.equals(edt_second_new_passwords)) {
                    getDataForService(edt_passwords, edt_new_passwords);
                } else {
                    ToastUtils.showToast(this, "密码输入不一致");
                }

                break;
        }
    }

    private void getDataForService(String edt_passwords, String edt_new_passwords) {
        if (!Pattern.compile("^[a-zA-Z]{1}.+").matcher(edt_new_passwords).matches()) {
            ToastUtils.showToast(this, getResources().getString(R.string.register_start_english));
            return;
        }
        List<String> list=new ArrayList<>();
        list.add(edt_passwords);
        list.add(edt_new_passwords);
        presenter.onResume(this,list);
    }

    @Override
    public void setText(Object text) {

    }
}
