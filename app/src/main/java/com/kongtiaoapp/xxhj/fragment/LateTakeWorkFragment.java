package com.kongtiaoapp.xxhj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetZhiBanInfo;
import com.kongtiaoapp.xxhj.bean.getUserList;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhiBanDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanDetailVeiw;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xxhj_g on 2017/8/18. 最近接班
 */

public class LateTakeWorkFragment extends BaseFragment<ZhiBanDetailPresenter, ZhiBanDetailVeiw> implements ZhiBanDetailVeiw {
    @BindView(R.id.myline_touch)
    MyLinearlayout myline_touch;
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

    @BindView(R.id.line_status)
    LinearLayout line_status;
    @BindView(R.id.txt_jiaoban_status)//交班状态
            TextView txt_jiaoban_status;
    @BindView(R.id.txt_jiaoban_status_content)
    TextView txt_jiaoban_status_content;
    @BindView(R.id.txt_clear)
    TextView txt_clear;
    @BindView(R.id.line_commit)
    LinearLayout line_commit;
    private String dutyId = "";
    private GetZhiBanInfo.ResobjBean item;
    private String SuccessorId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ZhiBanDetailPresenter getPresenter() {
        return new ZhiBanDetailPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_duty_commit, null);
    }

    @Override
    public void initData() {
        super.initData();
        line_status.setVisibility(View.VISIBLE);
        line_commit.setVisibility(View.GONE);
        txt_jiaoban_status.setText("接班状态");
        myline_touch.getFocus(true);
        etJiebanName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_jieban_name && event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(mActivity, UserListActivity.class);
                    intent.putExtra("type", 0);
                    startActivityForResult(intent, REQUEST_CODE_TWO);
                }
                return true;
            }
        });
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getDataForServers();
        }
    }
    private void getDataForServers() {
        presenter.getLateTakeWork(mActivity, "1");
    }

    @OnClick({ R.id.et_jieban_time, R.id.et_jiaoban_time})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.et_jiaoban_time:
                setdatePicker(0);
                break;
            case R.id.et_jieban_time:
                setdatePicker(1);
                break;
        }
    }


    private void setdatePicker(final int item) {
        //item  0  代表的是值班时间   1  代表的是接班时间
        // 时间选择器
        TimePickerView pvTime = new TimePickerView(mActivity, TimePickerView.Type.ALL);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(date -> {
            switch (item) {
                case 0:
                    etJiaobanTime.setText(getTime(date));
                    break;
                case 1:
                    etJiebanTime.setText(getTime(date));
                    break;
                default:
                    break;
            }

        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = null;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void setText(Object response) {
        GetZhiBanInfo info = (GetZhiBanInfo) response;
        item = info.getResobj();
        if (item.getDutyStatusName() != null) {
            txt_jiaoban_status_content.setText(item.getDutyStatusName());
        }
        if (item.getDutyId() != null) {
            dutyId = item.getDutyId();
        }
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
            etJiaobanTime.setText(item.getBeginTime());
        }
        if (!TextUtils.isEmpty(item.getEndTime())) {
            etJiebanTime.setText(item.getEndTime());
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE) {
            if (intent != null) {

                String action = intent.getAction();

                if (action.equals(ReceiverAction.SELECTUSER)) {
                    getUserList.UserList user = (getUserList.UserList) intent.getSerializableExtra("user");
                    etJiebanName.setText(user.getUserName());
                    etJiebanPhone.setText(user.getPhone());
                    SuccessorId = user.getUserId();
                }

            }
        }
    }

    @Override
    public void submit_View(Object data) {
        ToastUtils.showToast(mActivity, getString(R.string.jiaoban_succedd));
    }

    @Override
    public void setNull() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}