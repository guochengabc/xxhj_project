package com.kongtiaoapp.xxhj.duty.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetZhiBanInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhiBanDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanDetailVeiw;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-9-13.
 * 说明:值班记录详情
 */
public class ZhiBanDetailActivity extends BaseActivity<ZhiBanDetailPresenter, ZhiBanDetailVeiw> implements ZhiBanDetailVeiw {
    @BindView(R.id.et_jiaoban_name)
    EditText etJiaobanName;
    @BindView(R.id.et_jiaoban_phone)
    EditText etJiaobanPhone;
    @BindView(R.id.et_jieban_name)
    EditText etJiebanName;
    @BindView(R.id.et_jieban_phone)
    EditText etJiebanPhone;
    @BindView(R.id.et_jiaoban_time)
    EditText etJiaobanTime;
    @BindView(R.id.et_jieban_time)
    EditText etJiebanTime;
    @BindView(R.id.et_wrok_info)
    EditText etWrokInfo;
    @BindView(R.id.et_jiaojie_info)
    EditText etJiaojieInfo;
    @BindView(R.id.et_lingdao_info)
    EditText etLingdaoInfo;
    @BindView(R.id.et_jiaoban_name_second)
    EditText et_jiaoban_name_second;
    @BindView(R.id.et_jieban_name_second)
    EditText et_jieban_name_second;//接班成员
    private String dutyId;
    private GetZhiBanInfo.ResobjBean item;

    @Override
    protected int initContentView() {
        return R.layout.activity_zhiban_detail;
    }

    @Override
    protected void initView() {
        dutyId = getIntent().getStringExtra("dutyId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getDataForServers();
    }

    @Override
    protected ZhiBanDetailPresenter getPresenter() {
        return new ZhiBanDetailPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(this, dutyId);
    }

    @OnClick(R.id.iv_back)
    public void Onlick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setText(Object response) {
        GetZhiBanInfo info = (GetZhiBanInfo) response;
        item = info.getResobj();
        if (!TextUtils.isEmpty(item.getOffDutyName())) {
            etJiaobanName.setText(item.getOffDutyName());
        }
        if (!TextUtils.isEmpty(item.getOffDutyPhone())) {
            etJiaobanPhone.setText(item.getOffDutyPhone());
        }
        if (!TextUtils.isEmpty(item.getSuccessorName())) {
            etJiebanName.setText(item.getSuccessorName());
        }
        if (!TextUtils.isEmpty(item.getSuccessorPhone())) {
            etJiebanPhone.setText(item.getSuccessorPhone());
        }
        if (!TextUtils.isEmpty(item.getBeginTime())) {
            etJiaobanTime.setText(TimeUtils.getDetailTime(item.getBeginTime()));
        }
        if (!TextUtils.isEmpty(item.getEndTime())) {
            etJiebanTime.setText(TimeUtils.getDetailTime(item.getEndTime()));
        }
        if (!TextUtils.isEmpty(item.getWorkContents())) {
//                        String s = "工作内容: " + item.getWorkContents();
//                        StringFormatUtil spanStr = new StringFormatUtil(this, s,
//                                item.getWorkContents(), R.color.font_six).fillColor();
//                        etWrokInfo.setText(spanStr.getResult());
            etWrokInfo.setText(item.getWorkContents());
        }
        if (!TextUtils.isEmpty(item.getKeyworks())) {
//                        String s = "重点工作及交接事项: " + item.getKeyworks();
//                        StringFormatUtil spanStr = new StringFormatUtil(this, s,
//                                item.getKeyworks(), R.color.font_six).fillColor();
//                        tvWorkImportance.setText(spanStr.getResult());
            etJiaojieInfo.setText(item.getKeyworks());
        }
        if (!TextUtils.isEmpty(item.getLeaderAssign())) {
//                        String s = "领导交办: " + item.getLeaderAssign();
//                        StringFormatUtil spanStr = new StringFormatUtil(this, s,
//                                item.getLeaderAssign(), R.color.font_six).fillColor();
//                        tvLingdao.setText(spanStr.getResult());
            etLingdaoInfo.setText(item.getLeaderAssign());
        }
        if (TextUtils.isEmpty(item.getOffDutyName())) {
            et_jiaoban_name_second.setText("");
        } else {
            et_jiaoban_name_second.setText(item.getOffDutyMember());
        }
        if (TextUtils.isEmpty(item.getSuccessorName())) {
            et_jieban_name_second.setText("");
        } else {
            et_jieban_name_second.setText(item.getSuccessorMember());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void submit_View(Object data) {

    }

    @Override
    public void setNull() {

    }
}
