package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.InviteCodeBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.InvitePresenter;
import com.kongtiaoapp.xxhj.mvp.view.InviteView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteActivity extends BaseActivity<InvitePresenter,InviteView> implements  InviteView {
    @BindView(R.id.txt_invite)
    TextView txt_invite;
    @BindView(R.id.edt_invite)
    EditText edt_invite;
    @BindView(R.id.txt_reset_invite)
    TextView txt_reset_invite;
    @BindView(R.id.line_one)
    LinearLayout line_one;
    private String first = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.onResume(this);
    }

    @Override
    protected InvitePresenter getPresenter() {
        return new InvitePresenter();
    }

    @OnClick({R.id.txt_reset_invite, R.id.iv_back})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.txt_reset_invite:
                edt_invite.setVisibility(View.VISIBLE);
                String pwd = edt_invite.getText().toString();
                if (first.equals("1") && TextUtils.isEmpty(pwd)) {
                    ToastUtils.showToast(this, getString(R.string.invite_password));
                    return;
                }
                if (first.equals("1")) {
                    presenter.resetInvieCode(this,pwd);//重置邀请码
                }
                txt_reset_invite.setText("确认重置");
                first = "1";
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void resetInviteCode(Object response) {
        InviteCodeBean bean= (InviteCodeBean) response;
        txt_invite.setText(bean.getResobj().getInviteCode() + "");
        txt_reset_invite.setText("重置邀请码");
        edt_invite.setVisibility(View.GONE);
        edt_invite.setText("");
        first = "0";
    }

    @Override
    public void setGone() {
        edt_invite.setVisibility(View.GONE);
    }

    @Override
    public void setText(Object text) {
        InviteCodeBean bean= (InviteCodeBean) text;
        txt_invite.setText(bean.getResobj().getInviteCode() + "");
        line_one.setVisibility(View.VISIBLE);
    }
}
