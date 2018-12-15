package com.kongtiaoapp.xxhj.hvac;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.adapter.RunningRecord_DutyAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.RunningRecord_DutyBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningRecord_DutyPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecord_DutyView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class RunningRecordHVACActivity extends BaseActivity<RunningRecord_DutyPresenter, RunningRecord_DutyView> implements View.OnClickListener, RunningRecord_DutyView {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;
    TextView txt_starttime;//开始时间
    TextView txt_endtime;//结束时间
    Button btn_search;//查询功能按钮
    private String start_time = "", end_time = "";
    private boolean isRefresh;
    private boolean isSearch = false;
    private int currentPage = 1;
    List<RunningRecord_DutyBean.ResobjBean> list, listAll;
    private RunningRecord_DutyAdapter adapter = null;
    private CustomViewPager vp;
    private TextView txt_nodata;
    private TextView txt_please;
    private String userId = "";
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public RunningRecord_DutyPresenter getPresenter() {
        return new RunningRecord_DutyPresenter();
    }

    @Override
    public void initData() {
        View view = LayoutInflater.from(RunningRecordHVACActivity.this).inflate(R.layout.record_running_head, null, false);
        ImageView imageView= (ImageView) view.findViewById(R.id.iv_back);
        txt_starttime = ((TextView) view.findViewById(R.id.txt_starttime));
        txt_endtime = ((TextView) view.findViewById(R.id.txt_endtime));
        btn_search = ((Button) view.findViewById(R.id.btn_search));
        txt_nodata = ((TextView) view.findViewById(R.id.txt_nodata));
        txt_please = ((TextView) view.findViewById(R.id.txt_please));
        imageView.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        txt_starttime.setOnClickListener(this);
        txt_endtime.setOnClickListener(this);
        txt_please.setOnClickListener(this);
        listView.addHeaderView(view);
        list = new ArrayList<>();
        listAll = new ArrayList<>();
        adapter = new RunningRecord_DutyAdapter(RunningRecordHVACActivity.this, listAll);
        listView.setAdapter(adapter);
        springView.setHeader(new RotationHeader(RunningRecordHVACActivity.this));
        springView.setFooter(new RotationFooter(RunningRecordHVACActivity.this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        currentPage = 1;
                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
        getDataForService();
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_running_record;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initListener() {

    }


    private void setdatePicker(final int timeType) {
        // 时间选择器
        TimePickerView pvTime = null;
        pvTime = new TimePickerView(RunningRecordHVACActivity.this, TimePickerView.Type.ALL);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                String time = getTime(date);
                if (timeType == 1) {
                    txt_starttime.setText(time);
                    start_time = time;
                } else if (timeType == 0) {
                    txt_endtime.setText(time);
                    end_time = time;
                }
            }
        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(date);
    }

    private void getDataForService() {
        List<String> list_param = new ArrayList<>();
        list_param.add(String.valueOf(currentPage));
        list_param.add(list.size() + "");
        if (currentPage > 1) {
            if (list.size() >= 1) {
                list_param.add(list.get((list.size() - 1)).getRecordId());
            }
        }
        list_param.add(start_time);
        list_param.add(end_time);
        list_param.add(userId);
        presenter.onResume(RunningRecordHVACActivity.this, list_param);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_starttime:
                setdatePicker(1);//1代表开始时间
                break;
            case R.id.txt_endtime:
                setdatePicker(0);//0代表结束时间
                break;
            case R.id.btn_search:
                isRefresh = true;
                isSearch = true;
                getDataForService();
                break;
            case R.id.txt_please:
                Intent intent = new Intent(RunningRecordHVACActivity.this, UserListActivity.class);
                intent.putExtra("type", 2);
                startActivityForResult(intent, 1000);
                break;
            default:

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10001) {
            if (data != null) {
                txt_please.setText(data.getStringExtra("name"));
                userId = data.getStringExtra("uid");
            }

        }
    }

    @Override
    public void setList(Object data) {
        RunningRecord_DutyBean bean = (RunningRecord_DutyBean) data;
        txt_nodata.setVisibility(View.GONE);
        list.clear();
        if (isRefresh) {
            listAll.clear();
        }
        list = bean.getResobj();
        listAll.addAll(list);
        adapter.setList(listAll);
        adapter.notifyDataSetChanged();
        isSearch = false;
    }

    @Override
    public void list_null() {
        txt_nodata.setVisibility(View.VISIBLE);
        if (isSearch) {
            listAll.clear();
        }
        isSearch = false;
        currentPage = 1;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error() {
        txt_nodata.setVisibility(View.GONE);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
