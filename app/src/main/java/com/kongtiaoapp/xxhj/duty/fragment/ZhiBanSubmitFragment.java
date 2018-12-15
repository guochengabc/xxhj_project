package com.kongtiaoapp.xxhj.duty.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.adapter.MySplashAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetSucceedRecordBean;
import com.kongtiaoapp.xxhj.bean.getUserList;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhiBanSubmitPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanSubmitView;
import com.kongtiaoapp.xxhj.ui.dialog.MyVersionDialog;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.最近交班
 */
public class ZhiBanSubmitFragment extends BaseFragment<ZhiBanSubmitPresenter, ZhiBanSubmitView> implements ZhiBanSubmitView {
 ;
    @BindView(R.id.et_jiaoban_name)
    EditText etJiaobanName;
    @BindView(R.id.et_jiaoban_phone)
    EditText etJiaobanPhone;
    @BindView(R.id.et_jieban_name)
    EditText etJiebanName;
    @BindView(R.id.et_jieban_phone)
    EditText etJiebanPhone;
    private MySplashAdapter adapter;

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
    @BindView(R.id.txt_clear)
    TextView txt_clear;
    @BindView(R.id.et_jiaoban_name_second)
    EditText et_jiaoban_name_second;//输入交班成员
    @BindView(R.id.et_jieban_name_second)
    EditText et_jieban_name_second;//接班成员
    private String SuccessorId;
    private List<String> list = new ArrayList<>();

    private android.os.Handler handler = new android.os.Handler();
    private Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            MyVersionDialog.Builder builder = new MyVersionDialog.Builder(mActivity);
            builder.setTitle("交班人信息");
            builder.setMessage(adapter);
            builder.setPositiveButton("确定接班", (dialog, which) -> {
                dialog.dismiss();
                sureCommit = "1";
                Commitzhiban(sureCommit);//确实接班

            });
            builder.setNegativeButton("取消接班",
                    (dialog, which) -> {
                        dialog.dismiss();
                        sureCommit="0";
                        Commitzhiban(sureCommit);//确实接班
                    });
            builder.create().show();
        }
    };
    private String dutyId;
    private String sureCommit;

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
    public ZhiBanSubmitPresenter getPresenter() {
        return new ZhiBanSubmitPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_duty_commit, null);
    }

    @Override
    public void initData() {
        super.initData();
        getDataForService();//获取值班信息
        etJiaobanName.setText(App.sp.getName());
        etJiaobanPhone.setText(App.sp.getPhone());
        etJiebanName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_jieban_name && event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(mActivity, UserListActivity.class);
                    intent.putExtra("type", 0);
                    startActivityForResult(intent,REQUEST_CODE);
                }
                return true;
            }
        });
    }
    private void getDataForService() {
        presenter.onResume(mActivity, "");
    }

    private void Commitzhiban(String status) {
        List<String> list = new ArrayList<>();
        list.add(dutyId);
        list.add(status);
        presenter.Commitzhiban(mActivity, list);
    }

    private void submit() {

        String jiaobanTime = etJiaobanTime.getText().toString().trim();//交班时间
        String jiebanTime = etJiebanTime.getText().toString().trim();//接班时间
        String WorkContents = etWrokInfo.getText().toString().trim();//工作内容
        String Keyworks = etJiaojieInfo.getText().toString().trim();//值班重点工作内容
        String LeaderAssign = etLingdaoInfo.getText().toString().trim();//领导交办内容
        if (TextUtils.isEmpty(SuccessorId)) {
            ToastUtils.showToast(mActivity, getString(R.string.duty_jieban_first));
            return;
        }
        if (TextUtils.isEmpty(jiaobanTime)) {
            ToastUtils.showToast(mActivity, getString(R.string.duty_jiaoban_time));
            return;
        }
      /*  if (TextUtils.isEmpty(WorkContents)) {
            ToastUtils.showToast(mActivity, getString(R.string.duty_work_content));
            return;
        }

        if (TextUtils.isEmpty(Keyworks)) {
            ToastUtils.showToast(mActivity, getString(R.string.duty_work_important_content));
            return;
        }
        if (TextUtils.isEmpty(LeaderAssign)) {
            ToastUtils.showToast(mActivity, getString(R.string.duty_leader_content));
            return;
        }*/
        List<String> list = new ArrayList<>();
        list.add(SuccessorId);
        list.add(et_jiaoban_name_second.getText().toString());
        list.add(jiebanTime);
        list.add(jiaobanTime);
        list.add(et_jieban_name_second.getText().toString());
        list.add(WorkContents);
        list.add(Keyworks);
        list.add(LeaderAssign);
        presenter.submit(mActivity, list);
    }

    @OnClick({ R.id.txt_clear, R.id.txt_comment, R.id.et_jieban_time, R.id.et_jiaoban_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_clear:
                clear();
                break;
            case R.id.txt_comment:
                submit();
                break;
            case R.id.et_jiaoban_time:
                setdatePicker(0);
                break;
            case R.id.et_jieban_time:
                setdatePicker(1);
                break;
        }
    }


    private void clear() {
        SuccessorId = "";
        etJiaobanTime.setText("");
        etJiebanTime.setText("");
        etWrokInfo.setText("");
        etJiaojieInfo.setText("");
        etLingdaoInfo.setText("");
        etJiebanName.setText("");
        etJiebanPhone.setText("");
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode==REQUEST_CODE){
            if (intent!=null){

            String action = intent.getAction();

            if (action.equals(ReceiverAction.SELECTUSER)) {
                getUserList.UserList user = (getUserList.UserList) intent.getSerializableExtra("user");
                etJiebanName.setText(user.getUserName());
                etJiebanPhone.setText(user.getPhone());
                SuccessorId = user.getUserId();
                //initData();initData
            }

            }
        }
    }


    @Override
    public void submit_View(Object data) {
        ToastUtils.showToast(mActivity, "已提交,请等待对方确认！");
    }

    @Override
    public void setList(Object data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        GetSucceedRecordBean bean = (GetSucceedRecordBean) data;
        GetSucceedRecordBean.ResobjBean resobj = bean.getResobj();
        if (resobj.getDutyStatus().equals("0")) {
            list.add(resobj.getOffDutyName());//交班人姓名
            list.add(resobj.getOffDutyPhone());//交班人手机号
            list.add("交班时间:" + format.format(new Date((Long.parseLong(bean.getResobj().getOffDutyTime())))) + ":00:00");
            list.add("交接事项:" + resobj.getKeyworks());
            dutyId = resobj.getDutyId();
            adapter = new MySplashAdapter(mActivity, list);
            handler.postDelayed(runnable3, 100);

        }
        if (!TextUtils.isEmpty(bean.getResobj().getOffDutyTime())) {
            etJiebanTime.setText(format.format(new Date((Long.parseLong(bean.getResobj().getOffDutyTime())))) + ":00:00");
        }
        etJiaobanTime.setText(format.format(new Date(System.currentTimeMillis())) + ":00:00");
    }

    @Override
    public void Commitzhiban_View(Object data) {
        if (sureCommit.equals("0")){
            ToastUtils.showToast(mActivity, getString(R.string.cancel_takeWork));
        }else if (sureCommit.equals("1")){
            ToastUtils.showToast(mActivity, getString(R.string.takeWork_succedd));
        }

        Intent mIneten = new Intent();
        mIneten.setAction(ReceiverAction.PROJECT_SELECT);
        mIneten.putExtra(ConstantValue.PROJECTID, App.sp.getProjectId());
        mActivity.sendBroadcast(mIneten);
        clear();
    }

    @Override
    public void list_Null() {
        etJiaobanTime.setText(new SimpleDateFormat("yyyy-MM-dd HH").format(new Date(System.currentTimeMillis())) + ":00:00");
    }

    @Override
    public void setText(Object data) {

    }



    @Override
    public void onDestroy() {

        super.onDestroy();

    }

}
