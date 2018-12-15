package com.kongtiaoapp.xxhj.e_code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.WeiBaoDetailP;
import com.kongtiaoapp.xxhj.mvp.view.WeiBaoDetailV;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWeiBaoActivity extends BaseActivity<WeiBaoDetailP, WeiBaoDetailV> implements WeiBaoDetailV {
    @BindView(R.id.txt_weibao_jian)
    EditText txt_weibao_jian;//简述
    @BindView(R.id.txt_weibao_content)
    EditText txt_weibao_content;//内容
    @BindView(R.id.txt_weibao_people)
    EditText txt_weibao_people;//人员
    @BindView(R.id.txt_weibao_time)
    TextView txt_weibao_time;//时间
    @BindView(R.id.txt_weibao_remark)
    EditText txt_weibao_remark;//备注
    private String deviceId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_add_wei_bao;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            deviceId = intent.getStringExtra("deviceId");
        }
    }

    @Override
    protected WeiBaoDetailP getPresenter() {
        return new WeiBaoDetailP();
    }
    @OnClick({R.id.iv_back,R.id.txt_save,R.id.txt_weibao_time})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_save:
                commitWeiBao();//添加维保记录
                break;
            case R.id.txt_weibao_time:
                TimePickerView pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                pvTime.setOnTimeSelectListener(date -> txt_weibao_time.setText(getTime(date)));
                pvTime.show();
                break;
            default:
                break;
        }
    }
    private static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    private void commitWeiBao() {
        if (deviceId==null || deviceId.equals("")){
            ToastUtils.showToast(this,"返回上一界面，重新尝试！");
            return;
        }
        if (txt_weibao_jian.getText().toString().trim().equals("")){
            ToastUtils.showToast(this,"请简要概述维保内容！");
            return;
        } if (txt_weibao_time.getText().toString().trim().equals("")){
            ToastUtils.showToast(this,"请选择维保时间！");
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(txt_weibao_people.getText().toString());
        list.add(txt_weibao_jian.getText().toString());
        list.add(txt_weibao_content.getText().toString());
        list.add(txt_weibao_time.getText().toString());
        list.add(txt_weibao_remark.getText().toString());
        list.add(deviceId);
        presenter.commitWeiBao(this,list);
    }

    @Override
    public void setText(Object data) {

    }
}
