package com.kongtiaoapp.xxhj.login_register.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.broadcast.SmsObserver;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Register_PersonalPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Register_PersonalView;
import com.kongtiaoapp.xxhj.utils.MD5Utils;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register_Personal_Fragment extends BaseFragment<Register_PersonalPresenter, Register_PersonalView> implements Register_PersonalView {
    @BindView(R.id.rg_check_agree)
    CheckBox rg_check_agree;
    @BindView(R.id.rg_txt_company_name)
    TextView rg_txt_company_name;//公司名
    @BindView(R.id.rg_txt_instruction)//说明
            TextView rg_txt_instruction;
    @BindView(R.id.rg_pl_company_name)//企业名称
            EditText rg_pl_company_name;
    @BindView(R.id.rg_pl_user_name)
    EditText rg_pl_user_name;//真实姓名
    @BindView(R.id.rg_pl_phone)
    EditText rg_pl_phone;//手机号
    @BindView(R.id.rg_pl_identifying_code)
    EditText rg_pl_identifying_code;//验证码
    @BindView(R.id.rg_getcode)
    Button rg_getcode;//获取验证码
    @BindView(R.id.rg_pl_password)
    EditText rg_pl_password;//密码
    @BindView(R.id.rg_pl_sure_password)
    EditText rg_pl_sure_password;//确认密码
    @BindView(R.id.rg_txt_found)
    TextView rg_txt_found;
    private SmsObserver smsObserver;
    private BackTimer time;
    //进行短信验证handler
    public static final int MSG_RECEIVED_CODE = 1;
/*    Handler mHandler = new Handler() {
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case MSG_RECEIVED_CODE:
                    rg_pl_identifying_code.setText(msg.obj.toString());
                    break;
            }
        }
    };*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Register_PersonalPresenter getPresenter() {
        return new Register_PersonalPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_register__company, null);
    }

    @Override
    public void initData() {
        super.initData();
        rg_txt_company_name.setText(getResources().getString(R.string.invate_number));
        rg_pl_company_name.setHint(getResources().getString(R.string.pl_invate_code));
        rg_txt_instruction.setText(getResources().getString(R.string.personal_instruction));
        rg_txt_company_name.setInputType(InputType.TYPE_CLASS_NUMBER);
        rg_txt_found.setText(getResources().getString(R.string.found_pesonal_number));
        time = new BackTimer(60000, 1000);
    }

    @OnClick({R.id.rg_txt_found, R.id.rg_getcode})
    public void Onclick(TextView txt_found) {
        String phone = rg_pl_phone.getText().toString();
        String telRegex = "[1][34578]\\d{9}";
        switch (txt_found.getId()) {
            case R.id.rg_txt_found:
                String company_name = rg_pl_company_name.getText().toString();
                String user_name = rg_pl_user_name.getText().toString();
                String pl_code = rg_pl_identifying_code.getText().toString();
                String password = rg_pl_password.getText().toString();
                String pl_sure_password = rg_pl_sure_password.getText().toString();
                if (!checkNetworked()) {
                    return;
                } else if (TextUtils.isEmpty(company_name)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_invate_code));
                    return;
                } else if (TextUtils.isEmpty(user_name)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_real_name));
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_phone));
                    return;
                } else if (!phone.matches(telRegex)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.phone_error));
                    return;
                } else if (TextUtils.isEmpty(pl_code)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_identifying_code));
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_password));
                    return;
                } else if (password.length() < 6) {
                    //  showToast(TAG, "密码至少设置6位，请确认后输入");
                    ToastUtils.showToast(mActivity, "密码至少设置6位，请确认后输入");
                    return;
                } else if (TextUtils.isEmpty(pl_sure_password)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_sure_password));
                    return;
                } else if (pl_sure_password.length() < 6) {
                    //  showToast(TAG, "密码至少设置6位，请确认后输入");
                    ToastUtils.showToast(mActivity, "密码至少设置6位，请确认后输入");
                    return;
                } else if (!password.equals(pl_sure_password)) {
                    ToastUtils.showToast(mActivity, "输入密码不一致");
                    return;
                } else if (!rg_check_agree.isChecked()) {
                    ToastUtils.showToast(mActivity, "请同意注册协议");
                    return;
                } else if (!password.equals(pl_sure_password)) {
                    ToastUtils.showToast(mActivity, "输入密码不一致");
                    return;
                }
                getServiceData(company_name, user_name, phone, pl_code, password);//进行网络请求
                break;
            case R.id.rg_getcode:
                if (!checkNetworked()) {
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.pl_phone));
                    return;
                } else if (!phone.matches(telRegex)) {
                    ToastUtils.showToast(mActivity, getResources().getString(R.string.phone_error));
                    return;
                }
                time.start();
                sendIdentify(phone);//发送验证码
                break;
            default:
                break;
        }
    }

    protected boolean checkNetworked() {
        if (!NetworkUtils.checkNetwork(mActivity)) {
            ToastUtils.showToast(mActivity, getResources().getString(R.string.no_net));
            return false;
        }

        return true;
    }

    private void sendIdentify(String phone) {
        presenter.sendIdentify(mActivity, phone);
    }

    private void getServiceData(String company_name, String user_name, String phone, String pl_code, String password) {
        if (!Pattern.compile("^[a-zA-Z]{1}.+").matcher(password).matches()) {
            ToastUtils.showToast(mActivity, getResources().getString(R.string.register_start_english));
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(phone);
        list.add(MD5Utils.String2MD5(password));
        list.add(company_name);
        list.add(user_name);
        list.add(pl_code);
        presenter.getServiceData(mActivity, list);
    }
    @Override
    public void setText(Object data) {

    }

    @Override
    public void finishActivity() {
        ToastUtils.showToast(mActivity, mActivity.getString(R.string.register_succeed));
        mActivity.finish();
    }

    private class BackTimer extends CountDownTimer {
        public BackTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            // rg_getcode.setBackgroundResource(R.drawable.shape_button_pres);
            rg_getcode.setText("重新验证");
            rg_getcode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//倒计时显示
            rg_getcode.setClickable(false);
            rg_getcode.setText(millisUntilFinished / 1000 + "秒");
            // rg_getcode.setBackgroundResource(R.drawable.shape_button_default);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
